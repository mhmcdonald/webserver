import java.io.*;
import java.net.*;
/** This class extends java's Thread class, and thus allows us to create each connection
* on a separate thread. It gets called by the WebServer class. It makes calls to the HTTP
* request and the three response classes, depending on the outcome of the request. 
*/
class TcpConnection extends Thread {

    private Socket sock;
    private PrintWriter printWriter;
    private BufferedReader buffRead;
    private DataOutputStream dOutStream;
    Boolean httpProtocol = false;   //only if the request contains a valid HTTP header will this be fliped to true

     TcpConnection(Socket sock) throws Exception {
        this.sock = sock;
        buffRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        printWriter = new PrintWriter(sock.getOutputStream());
        dOutStream = new DataOutputStream(sock.getOutputStream());
    }

    @Override
    public void run() { //run method is what makes this multi-threaded, allowing simultaneous connections

        try {
            String requestString = "";
            while (buffRead.ready() || requestString.length() == 0)
                requestString += (char) buffRead.read();

            String[] requestLines = requestString.split("\n");
            String[] header = requestLines[0].split(" ");
            if (header.length > 1){
                String protocol = header[2];
																//I parse the first line of the request to make sure it's a HTTP request. Otherwise, we just kill the request.
                if (protocol.toString().trim().contains("HTTP/1")){
                    httpProtocol = true;
                }else{
                    System.out.println("This is an HTTP - only webserver. Other protocols are not supported.");
                }
            }

            //System.out.println(requestString);  //display the request in the console

            if (!httpProtocol){ //if we received something other than a valid HTTP request, we attempt to publish a 500 status response
                InvalidHttpResponse invalidRes = new InvalidHttpResponse("501 Not Implemented", "text/html");
            }else { //in the event we have received an HTTP request, we do the following

                HttpRequest req = new HttpRequest(requestString);
                if (!req.validRequest) {
                    InvalidHttpResponse invalidRes = new InvalidHttpResponse(req.invalidNum, req.mimeType);
                    try {
                        dOutStream.writeBytes(invalidRes.negResponse);
                    } catch (SocketException se) {
                        printWriter.write(invalidRes.negResponse.toCharArray());
                    }
                    //System.out.println(invalidRes.negResponse);
                } else {
                    if (req.redirect) {
                        RedirectResponse redRes = new RedirectResponse(req, req.mimeType);
                        try {
                            dOutStream.writeBytes(redRes.response);
                        } catch (SocketException se) {
                            printWriter.write(redRes.response.toCharArray());
                        }
                        //System.out.println(redRes.response);
                    } else {
                        HttpResponse res = new HttpResponse(req, req.mimeType);
                        try {
                            dOutStream.writeBytes(res.response);
                        } catch (SocketException se) {
                            printWriter.write(res.response.toCharArray());
                        }
                        //System.out.println(res.response);
                    }
                }
            }
            printWriter.close();    //close the printwriter
            buffRead.close(); //close our buffered reader
            dOutStream.close(); //close the data output stream
            sock.close();   //close the connection

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
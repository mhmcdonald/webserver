/**
 * This class dictates most of the standard HTTP Responses. I have separated the Invalid, and Redirection responses into
 * seperate classes given their different nature.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

class HttpResponse {

    String response;

    /**This provides the successful HTTP response, as well as the 404 if a file was not found
					* in our www tree structure. Only succesfully GET requests return a 200 response along
					* with data in the body. 
					*/

     HttpResponse(HttpRequest request, String fileType) {
        try {
												String currentDirectory = System.getProperty("user.dir");
            String root = currentDirectory + File.separator+"www";
            File file = new File(root + request.filename);  //We take the file stored on the webserver
            FileInputStream inputStream = new FileInputStream(file);    //Create an input stream from that file

            response = "HTTP/1.1 200 OK \r\n"; // 200 OK indicates Standard response for successful HTTP requests.
            response += "Server: Mark McDonald's Web Server/1.0 \r\n";
            response += "Content-Type: " + fileType + "\r\n";
            response += "Connection: close \r\n";
            response += "Content-Length: " + file.length() + "\r\n";
            response += "\r\n";
            if (request.getRequest){	//we only return the body if it's a get request, we don't do this for head requests
            int streamCounter;
            while ((streamCounter = inputStream.read()) != -1) {    //push the contents of the input stream into our response so it can render in a client (e.g. browser)
                response += (char) streamCounter;
            }}
            inputStream.close();    // We close the inputStream once we're finished
        }catch (FileNotFoundException e){   //If a file cannot be located on the webserver, we will return a 404 response
            response = "HTTP/1.1 404 Not Found\r\n";     // 404 indicates Not Found
            response += "Server: Mark McDonald's Web Server/1.0 \r\n";
            response += "Content-Type: " + fileType + "\r\n";
            response += "Connection: close \r\n";
            response += "Content-Length: 0" + "\r\n";
            response += "\r\n";
        }catch (Exception e){   //If none of my other error handling logic has caught this Request, we will return a 500 response
            response = "HTTP/1.1 500 Internal Server Error \r\n"; //500 indicates Internal Server Error. Hopefully this code is not reached.
            response += "Server: Mark McDonald's Web Server/1.0 \r\n";
            response += "Content-Type: " + fileType + "\r\n";
            response += "Connection: close \r\n";
            response += "Content-Length: 0" + "\r\n";
            response += "\r\n";

        }
    }
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * This class, HttpRequest is responsible for forming the request.
 */
 class HttpRequest {

    String filename = "";
    String mimeType = "";

    Boolean validRequest = false;
    Boolean redirect = false;
    Boolean getRequest = false;
    String reDirection = "";
    String gotoURL = "";
    String invalidNum = "403 Forbidden";

    HttpRequest(String request) throws Exception {

        String lines[] = request.split("\n");
        String header[] = lines[0].split(" ");
        String httpMethod = header[0];


        if (header.length > 1) {
            String protocol = "";
            try {
                filename = header[1];
                protocol = header[2];
            } catch (ArrayIndexOutOfBoundsException excpt) {
                excpt.printStackTrace();
                filename = "";
                protocol = "";
            }
        }

        //This server only handles GET and HEAD methods. So, we flip the validRequest to True if either of these methods is found
        if (httpMethod.equals("GET")) {validRequest = true; getRequest = true;}
        else if (httpMethod.equals("HEAD")) {
            validRequest = true;
        }

        String currentDirectory = System.getProperty("user.dir");  //get the path of the current directory
        String redirectFile = currentDirectory + File.separator + "www" + File.separator + "redirect.defs";
        BufferedReader buff = new BufferedReader(new FileReader(redirectFile));
        String sCurrentLine;


        while ((sCurrentLine = buff.readLine()) != null) {
            String[] redirected = sCurrentLine.split(" ");
            String searchFor = redirected[0];
            String redirectUrl = redirected[1];

            if (searchFor.equals(filename)) {
                redirect = true;
                gotoURL = redirectUrl;
            }
        }

        //set the MIME type based on the extension of the content being requested
        if (!(redirect)) {
            if (filename.toLowerCase().contains(".html") || filename.toLowerCase().contains(".htm")) {
                mimeType = "text/html";
            } else if (filename.toLowerCase().contains(".pdf")) {
                mimeType = "application/pdf";
            } else if (filename.toLowerCase().contains(".png")) {
                mimeType = "image/png";
            } else if (filename.toLowerCase().contains(".jpeg") || filename.toLowerCase().contains(".jpg")) {
                mimeType = "image/jpeg";
            } else if (filename.toLowerCase().contains(".txt") || filename.toLowerCase().contains(".java")) {
                mimeType = "text/plain";
            }


            if (filename.trim().equals("/redirect.defs")) {
                validRequest = false;
                invalidNum = "404 Not Found";
            }

        }
    }
}


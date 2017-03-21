/**
 *The RedirectResponse class dictates how the response of a file in the redirect.defs should work
 */
class RedirectResponse {

    String response;
    
     RedirectResponse(HttpRequest request, String fileType) {

        response = "HTTP/1.1 301 Moved Permanently\r\n";
         response += "Location: " + request.gotoURL + " \r\n";
        response += "Server: Mark McDonald's Web Server/1.0 \r\n";
        response += "Content-Type: text/html \r\n";
        response += "Connection: close \r\n";
        response += "\r\n";


    }
}
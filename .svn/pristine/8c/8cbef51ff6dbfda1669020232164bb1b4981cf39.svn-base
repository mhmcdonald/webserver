/**
 * In the event of a known Response that will be negative in nature and not return content in it's response
 * this InvalidHttpResponse class gets used. It takes a HTTP status code, and the type of content that was attempted to access
 * as constructor arguments. We use this class when we specifically know why content will not be served (404, 403, 501 etc)
 */

class InvalidHttpResponse {

    String negResponse;

     InvalidHttpResponse(String sStatus, String fileType) {

            negResponse = "HTTP/1.1 " + sStatus + "\r\n";
            negResponse += "Server: Mark McDonald's Server/1.0 \r\n";
            negResponse += "Content-Type: " + fileType + "\r\n";
            negResponse += "Connection: close \r\n";
            negResponse += "Content-Length: 0" + "\r\n";
            negResponse += "\r\n";


    }
}

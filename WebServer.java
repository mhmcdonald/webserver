import java.net.*;
import java.io.*;

/**
 * This is the main class of the webserver. We execute the program from this class.
 */
public class WebServer {

    private ServerSocket serverSocket;  //socket used for establishing TCP connection

    //our main method calls the server to run. Takes command line argument for the port number
    public static void main(String[] args) throws IOException {
        /* Accept the port number on which it will listen as a command-line flag of the form --serverPort=12345 */

        String argument0 = args[0];
        String[] parts = argument0.split("=");
        String portValue = parts[1]; //port number as a String
        int portNum = Integer.valueOf(portValue);

        new WebServer().runServer(portNum);
    }

    //runServer calls the server and binds it to the port specificed by the user's command line argument
    private void runServer(int port) {
        try {

            System.out.println("The WebServer is running on port: " + port);
            serverSocket = new ServerSocket(port);  
            acceptRequests();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void acceptRequests(){
        while(true){    //this server can accept simultaneous requests, so it needs to be listening for multiple requests
            try {
                Socket sock = serverSocket.accept();
                TcpConnection connection = new TcpConnection(sock);

                connection.start();    //TcpConnection implements Thread. The start() method starts the new Thread.
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}



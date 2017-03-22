#HTTP Web Server
##MPCS 54001
##Mark McDonald

This document provides some instructions on how to run the WebServer program. This simple web server supports get and head requests. It accepts one command-line flag (--serverPort), indicating the TCP port to bind.

Please refer to the code’s comments for details about how the program works. I used curl and Postman to review and verify that the HTTP responses are responding correctly.

Instructions
To test these programs do the following:

This webserver has been written in 6 Java classes. All 6 of these Java classes must first be compiled by doing the following:

Place the 6 .java files in a directory on your machine. CD into that directory. In terminal, run the following commands:

- javac WebServer.java
- javac HttpRequest.java
- javac TcpConnection.java
- javac HttpResponse.java
- javac InvalidHttpResponse.java
- javac RedirectResponse.java

You will also want to place some .html, .jpeg, .png, .pdf files in a root directory that has been placed inside the same directory as these java files. This root directory must be named “www”.

The to run the programs, you need to begin by running the WebServer with an available port number
For example:
java WebServer --serverPort=1342

You should then receive a confirmation in the console that the WebServer is running on this port. If not, try a different port.

Now that the WebServer is running on this port, we can go ahead and begin making some requests of the server. You will do this by using one of the file names that you have placed inside of your www (root) directory.

To do this in the browser, open up a browser (e.g. Chrome, Safari) and access the web server at http://localhost:1342/yourfile.html (replacing 1342 with the port number you used and yourfile.html with the file name you would like to see served.

At this point you should see your requested file appear successfully.

To stop the web server you can enter the command Control + C on your keyboard.

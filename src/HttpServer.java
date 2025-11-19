import java.io.*;
import java.net.*;

public class HttpServer {

    // Declare class variables
    private int port;

    // Constructor
    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        // Creates a new server
        ServerSocket server = new ServerSocket(port);

        try {
            System.out.println("Server started on port " + port);
            while(true) {

                // Pauses execution until a client is connected
                try(Socket client = server.accept()) {
                    handleClient(client);
                }
            }
        }

        finally {
            server.close();
        }
    }

    private void handleClient(Socket client) throws IOException {
        System.out.println("Client connected!");

        // Converts the request bytes into readable text
        InputStream in = client.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(isr);

        String requestLine = reader.readLine();

        // Checks if client connect but don't send any data
        if (requestLine == null) {
            System.out.println("Client closed connection before sending data.");
            return;
        }

        // Split parts
        String[] parts = requestLine.split(" ");

        if(parts.length < 3) {
            System.out.println("Malformed request line: " + requestLine);
            return;
        }

        String method = parts[0];
        String path = parts[1];
        String version = parts[2];

        // Prints request line
        System.out.println("Request line: " + requestLine);

        // Reads the other lines
        String headerLine;
        while ((headerLine = reader.readLine()) != null && !headerLine.isEmpty()) {
            System.out.println("Header: " + headerLine);
        }

        // Send response
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);

        // Body text
        String body = "Hello World!";

        out.print("HTTP/1.1 200 OK\r\n");
        out.print("Content-Type: text/plain\r\n");
        out.print("Content-Length: " + body.length() + "\r\n");
        out.print("Connection: close\r\n");
        out.print("\r\n");
        out.print(body);

        out.flush();
        client.close();
    }
}
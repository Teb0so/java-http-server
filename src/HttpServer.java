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

        System.out.println("Server started on port " + port);

        // Pauses execution until a client is connected
        Socket client = server.accept();
        handleClient(client);
        System.out.println("Client connected!");

        server.close();
    }

    private void handleClient(Socket client) throws IOException {
        System.out.println("Client connected!");

        // Converts the request bytes into readeble text
        InputStream in = client.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(isr);

        String requestLine = reader.readLine();

        // Checks if client connect but don't send any data
        if (requestLine == null) {
            System.out.println("Client closed connection before sending data.");
            return;
        }

        // Prints request line
        System.out.println("Request line: " + requestLine);

        client.close();
    }
}
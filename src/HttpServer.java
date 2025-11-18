import java.io.*;
import java.net.*;

public class HttpServer {

    private int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(port);

        System.out.println("Server started on port " + port);

        Socket client = server.accept();
        System.out.println("Client connected!");

        InputStream in = client.getInputStream();
        InputStream isr = new InputStreamReader(in);
        BufferedReader reader = new InputStreamReader(isr);

        String requestLine = reader.readLine();
        
        if (requestLine == null) {
            System.out.println("Client closed connection before sending data.");
            return;
        }

        System.out.println("Client closed connections before sending data.");

        System.out.println("Request line: " + requestLine);

        client.close();
        server.close();
    }

    private void handleClient(Socket client) {
        System.out.println("Client connected!");
    }
}
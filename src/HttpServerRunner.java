import java.io.IOException;

public class HttpServerRunner {
    public static void main(String[] args) throws IOException {
        HttpServer server = new HttpServer(8080);
        server.start();
    }
}

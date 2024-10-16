package was.v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static util.MyLogger.log;

public class HttpServerV2  {
    private final ExecutorService es = Executors.newFixedThreadPool(10);
    private final int PORT;

    public HttpServerV2(int PORT) {
        this.PORT = PORT;
    }
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 시작 port : " + PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            es.submit(new HttpRequestHandlerV2(socket));
        }
    }

}

package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.autocloseable.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class Session implements Runnable {
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final CommandManager commandManager;
    private final SessionManager sessionManager;

    private boolean closed = false;
    private String username;


    public Session(Socket socket, CommandManager commandManager, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.commandManager = commandManager;
        this.sessionManager = sessionManager;
        sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 클라이언트에게 문자 빋기
                String received = input.readUTF();
                log("client -> server: " + received);

                // 서버에서 전체로 문자 전파하기
                commandManager.execute(received, this);
            }
        } catch (Exception e) {
            log(e);
        } finally {
            sessionManager.remove(this);
            sessionManager.sendAll(username + " 님이 퇴장하셨습니다.");
            close();
        }
    }

    public synchronized void close() {
        if (closed) {
            return;
        }

        closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket);
    }

    public void send (String message) throws IOException {
        log("server -> client: " + message);
        output.writeUTF(message);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

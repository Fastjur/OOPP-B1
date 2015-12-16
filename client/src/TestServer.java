import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Govert on 15-12-2015.
 */
public class TestServer extends Thread {
    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {

        }
        this.start();
    }

    @Override
    public void run() {

    }
}

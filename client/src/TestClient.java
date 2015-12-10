import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Fastjur on 10-12-2015.
 * SHOULD NOT BE USED ON RELEASES
 * SOLELY FOR TESTING PURPOSES
 * @author Jurriaan Den Toonder
 */
public class TestClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
            Thread.sleep(1000);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

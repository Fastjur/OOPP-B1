import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Created by Fastjur on 10-12-2015.
 * SHOULD NOT BE USED ON RELEASES
 * SOLELY FOR TESTING PURPOSES
 * @author Jurriaan Den Toonder
 */
public class TestClient {

    private static Socket socket;
    private static InputStream in;
    private static OutputStream out;

    public static void main(String[] args) {
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8372);
            Thread.sleep(1000);
            in = socket.getInputStream();
            out = socket.getOutputStream();

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("action", "match");

            ObjectNode data = mapper.createObjectNode();
            data.put("maxdist", 7.5);
            data.put("latitude", 51.2658);
            data.put("longitude", 49.5608);
            jsonObject.put("data", data);

            String json = mapper.writeValueAsString(jsonObject);
            sendMessage(json);

            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private String decodeMessage(byte[] buf) {
        return new String(buf, StandardCharsets.UTF_8);
    }

    private static byte[] encodeMessage(String input) {
        return input.getBytes(StandardCharsets.UTF_8); // TODO: encryption?
    }

    /**
     * Send a message to the client associated with this thread
     *
     * @param message The message to send
     * @throws java.io.IOException when the socket is being written to while trying to write.
     */
    public static synchronized void sendMessage(String message) throws java.io.IOException {
        byte[] msg = encodeMessage(message);
        out.write(ByteBuffer.allocate(4).putInt(msg.length).array());
        out.write(msg);
    }
}

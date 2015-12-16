package test;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import shared.AvailableTimes;
import shared.TimePeriod;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
    private static boolean shouldStop;

    public static void main(String[] args) {
        shouldStop = false;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 8372);
            Thread.sleep(1000);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            AvailableTimes aTimes = new AvailableTimes();
            ArrayList<String> learning = new ArrayList<>(),
                    teaching = new ArrayList<>(),
                    buddys = new ArrayList<>(),
                    languages = new ArrayList<>();
            aTimes.addTimePeriod(1, new TimePeriod("12:00", "18:00"));
            aTimes.addTimePeriod(2, new TimePeriod("10:00", "12:00"));
            aTimes.addTimePeriod(2, new TimePeriod("18:00", "20:00"));
            aTimes.addTimePeriod(3, new TimePeriod(630, 700));
            learning.add("plassen");
            teaching.add("wiskunde");
            buddys.add("poepen");
            languages.add("Nederlands");


            ObjectMapper mapper = new ObjectMapper();
            ObjectNode login = mapper.createObjectNode();
            login.put("action", "login");
            ObjectNode loginData = mapper.createObjectNode();
            loginData.put("email", "sinterklaas@sintmail.nl");
            loginData.put("pass", "Pepernoten01");
            login.put("requestData", loginData);
            String loginJson = mapper.writeValueAsString(login);
            sendMessage(loginJson);

            ObjectNode jsonObject = mapper.createObjectNode();
            jsonObject.put("action", "match");
            String jsonlearning = mapper.writeValueAsString(learning),
                    jsonteaching = mapper.writeValueAsString(teaching),
                    jsonbuddys = mapper.writeValueAsString(buddys),
                    jsonlanguages = mapper.writeValueAsString(languages);

            ObjectNode data = mapper.createObjectNode();
            data.put("maxdist", 500);
            data.put("latitude", 51.98278);
            data.put("longitude", 4.34825);
            data.put("availability", aTimes.toJson());
            data.put("learning", jsonlearning);
            data.put("teaching", jsonteaching);
            data.put("buddys", jsonbuddys);
            data.put("languages", jsonlanguages);

            jsonObject.put("data", data);

            String json = mapper.writeValueAsString(jsonObject);
            sendMessage(json);

            sendMessage("{\"action\": \"logout\"}");

            while (!shouldStop) {
                try {
                    byte[] buf = new byte[4];
                    in.read(buf);
                    int messageLength = ByteBuffer.wrap(buf).getInt();
                    if(messageLength > 0 && messageLength < 65536) {
                        byte[] buffer = new byte[messageLength];
                        in.read(buffer, 0, messageLength);
                        String message = decodeMessage(buffer);
                        handleMessage(message);
                    }
                } catch (SocketTimeoutException e) {
                    shouldStop = true;
                    socket.close();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void handleMessage(String message) {
        System.out.println(message);
    }

    private static String decodeMessage(byte[] buf) {
        return new String(buf, StandardCharsets.UTF_8);
    }

    private static byte[] encodeMessage(String input) {
        return input.getBytes(StandardCharsets.UTF_8); // TODO: encryption?
    }

    public static synchronized void sendMessage(String message) throws java.io.IOException {
        byte[] msg = encodeMessage(message);
        out.write(ByteBuffer.allocate(4).putInt(msg.length).array());
        out.write(msg);
    }
}

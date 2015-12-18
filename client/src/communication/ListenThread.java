package communication;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import shared.Response;
import shared.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by Govert on 14-12-2015.
 */
public class ListenThread extends Thread {

    private boolean shouldstop;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ListenThread(Socket socket) throws IOException {
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
        this.setName("ListenThread");
    }

    @Override
    public void run() {
        this.shouldstop = false;

        while (!shouldstop && Backend.isConnected()) {
            try {
                byte[] buf = new byte[4];
                inputStream.read(buf);
                int messagelength = ByteBuffer.wrap(buf).getInt();
                if (messagelength > 0 && messagelength < 65536) {
                    byte[] buffer = new byte[messagelength];
                    inputStream.read(buffer, 0, messagelength);
                    String response = decodeMessage(buffer);
                    handleResponse(response);
                } else {
                    System.out.println("Client sent an invalid request, disconnecting client.");
                    Backend.closeConnection();
                }
            } catch (java.net.SocketException SEx) {
                // Socket closed.
            } catch (java.io.IOException IOEx) {
                System.out.println("Something went wrong while reading a message from the network.\n" +
                        IOEx.getLocalizedMessage());
            }
        }
        Backend.onDisconnect(!shouldstop);
    }

    private void handleResponse(String response) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode responseObj;
        try {
            responseObj = mapper.readTree(response);
            String responseTo = responseObj.get("responseTo").getTextValue();

            Response res = new Response(responseTo);
            res.errorCode = responseObj.get("errorCode").getIntValue();
            res.errorMessage = responseObj.get("errorMessage").getTextValue();

            switch (responseTo) {
                case "match":
                    ArrayList<User> canTeach = mapper.readValue(responseObj.get("responseData").get("canTeach")
                                    .getTextValue(),
                            mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class)),
                            canLearn = mapper.readValue(responseObj.get("responseData").get("canLearn").getTextValue(),
                                    mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class)),
                            canBuddyUp = mapper.readValue(responseObj.get("responseData").get("canBuddyUp")
                                            .getTextValue(),
                                    mapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class));
                    break;
            }

            Backend.onResponse(res);
        } catch (java.io.IOException ex) {
            System.out.println("Something went wrong while reading a message from the network.\n" +
                    ex.getLocalizedMessage());
        }
    }

    public void end() {
        this.shouldstop = true;
    }

    private String decodeMessage(byte[] buf) {
        return new String(buf, StandardCharsets.UTF_8);
    }

    private byte[] encodeMessage(String input) {
        return input.getBytes(StandardCharsets.UTF_8); // TODO: encryption?
    }

    public synchronized void sendMessage(String message) throws java.io.IOException {
        byte[] msg = encodeMessage(message);
        outputStream.write(ByteBuffer.allocate(4).putInt(msg.length).array());
        outputStream.write(msg);
    }
}
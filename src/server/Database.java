package server;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

/**
 * Created by Fastjur on 18-11-2015.
 */
public class Database {

    private JSONObject db;

    public Database(String file) throws IOException {
        try {
            this.db = readDB(file);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject readDB(String file) throws IOException, JSONException {
        String jsonData = readFile(file);
        JSONObject jobj = new JSONObject(jsonData);
        return jobj;
    }

    private String readFile(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while(line != null) {
            sb.append(line);
            line = br.readLine();
        }
        return sb.toString().replaceAll("\\s","");
    }

    public String toString() {
        return this.db.toString();
    }

}

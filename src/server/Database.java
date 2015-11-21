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

    /**
     * Adds new user/study/course to database.
     * @param obj JSONObject to be added to database.
     * @param key "users", "studies" or "courses".
     */
    public void addObjDB(JSONObject obj, String key){
        db.append(key, obj);
    }

    /**
     * Writes database to specified file in json format.
     * @param file
     * @throws IOException
     */
    public void writeFile(String file) throws IOException{
        String dataBase = db.toString(4);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(dataBase);
        bw.close();
    }

    public String toString() {
        return this.db.toString();
    }

}

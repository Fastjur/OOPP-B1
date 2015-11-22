package server;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Database class that reads the database file
 *
 * @author Jurriaan Den Toonder
 * @version 0.1
 */
public class Database {

    private JSONObject db;

    /**
     * Constructor of Database object
     * @param file
     * @throws IOException
     * @throws JSONException
     */
    public Database(String file) throws IOException, JSONException {
        this.db=readDB(file);
    }

    /**
     * Sends the filepath to the readFile function and converts the JSON string to a JSON object
     * @param file Filepath to database
     * @return JSON object of the database
     * @throws IOException
     * @throws JSONException
     */
    private JSONObject readDB(String file) throws IOException, JSONException {
        String jsonData = readFile(file);
        return new JSONObject(jsonData);
    }

    /**
     * Reads the file and returns a string representing the JSON database
     * @param file Filepath to database
     * @return String, JSON representation of database
     * @throws IOException
     */
    private String readFile(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
        }
        return sb.toString();
    }

    /**
     * Returns the JSON object of the database in a string representation
     * @return String
     */
    public String toString() {
        return this.db.toString();
    }

}

package database;

import reader.CsvTestDataReader;

import java.util.List;
import java.util.Map;

public class DatastoreUtils {
    private static final CsvTestDataReader reader = CsvTestDataReader.getInstance();
    // establish a connection to the database here
    public static void loadUsersFrom(String filePath) {
        List<Map<String, String>> data = reader.readCsv(filePath);
        writeToDatabase(data);
    }

    private static void writeToDatabase(List<Map<String, String>> data) {
        // write data to database
    }
}

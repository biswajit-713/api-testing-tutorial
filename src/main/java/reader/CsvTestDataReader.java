package reader;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvTestDataReader {
    private static volatile CsvTestDataReader instance = null;

    private CsvTestDataReader() {
    }

    public static CsvTestDataReader getInstance() {
        if (instance == null) {
            synchronized (CsvTestDataReader.class) {
                if (instance == null) {
                    instance = new CsvTestDataReader();
                }

            }
        }
        return instance;
    }

    public List<Map<String, String>> readCsv(String filePath) {
        String csvFile = "src/test/resources" + filePath;

        List<Map<String, String>> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] headers = reader.readNext();
            String[] line;
            int columnCount = headers.length;

            while ((line = reader.readNext()) != null) {
                Map<String, String> rowValue = new HashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = headers[i].trim();
                    String columnValue = line[i].trim();
                    rowValue.put(columnName, columnValue);
                }
                data.add(rowValue);
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }
}

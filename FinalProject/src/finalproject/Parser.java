package finalproject;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//this class is for parsing the data from csv files and store in the memory
public class Parser {
    private String fileUrl;
    public ArrayList<String[]> data;
    public HashMap<String, Integer> fields;

    Parser(String link) {
        fileUrl = link;
        data = new ArrayList<>();
        fields = new HashMap<>();
    }

    public void read() {
        try {
            String filePath = new File("").getAbsolutePath();
            System.out.println (filePath);
            FileReader fr = new FileReader(filePath + fileUrl);
            CSVReader reader = new CSVReader(fr);
            String[] lineInArray;
            lineInArray = reader.readNext();
            for (int i=0; i<lineInArray.length; i++) {
                fields.put(lineInArray[i], i);
            }
            while ((lineInArray = reader.readNext()) != null) {
                data.add(lineInArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

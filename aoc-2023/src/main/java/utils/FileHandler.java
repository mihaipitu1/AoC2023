package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<String> getInputParameters(String dayNo) {
        List<String> parsedLineInput = new ArrayList<>();

        var fileName = String.format("src/main/resources/inputDays/%s.txt",dayNo);

        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(dataInputStream));

            String lineRead;
            while((lineRead = br.readLine()) != null) {
                parsedLineInput.add(lineRead);
            }

            dataInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parsedLineInput;
    }
}

package dataFetcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static Float[] readFloatArray(String filePath) throws IOException{
        List<String> stringArrayList = new ArrayList<String>();
        System.out.println(Paths.get(filePath));
        stringArrayList = Files.readAllLines(Paths.get(filePath));

        Float floatArray[] = new Float[stringArrayList.size()];

        for(Integer i = 0; i < stringArrayList.size(); i++){
            floatArray[i] = Float.parseFloat(stringArrayList.get(i));
        }

        return floatArray;

    }
}

package dataFetcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataFile {
    public static Double[] readFloatArray(String filePath) throws IOException{
        List<String> stringArrayList = new ArrayList<String>();
        System.out.println(Paths.get(filePath));
        stringArrayList = Files.readAllLines(Paths.get(filePath));

        for (int i = 0; i < Math.min(2, stringArrayList.size()); i++) {
            stringArrayList.remove(0);
        }

        Double floatArray[] = new Double[stringArrayList.size()];

        for(Integer i = 0; i < stringArrayList.size(); i++){
            floatArray[i] = Double.parseDouble(stringArrayList.get(i));
        }

        return floatArray;

    }


    // public static Float[] getTimeOfService(Float rand[]){
    //     Float ts[] = new Float[rand.length];
    //     for(Integer i = 0; i < rand.length; i++) {
    //         Double dValue = Math.log(rand[i]);
    //         ts[i] = (0 - dValue.floatValue())/8;
    //     }

    //     return ts;
    // }

    // public static Float[] getTimeOfArrival(Float rand[]){
    //     Float tc[] = new Float[rand.length];
    //     for(Integer i = 0; i < rand.length; i++) {
    //         Double dValue = Math.log(rand[i]);
    //         tc[i] = (0 - dValue.floatValue())/2;
    //     }

    //     return tc;
    // }
}

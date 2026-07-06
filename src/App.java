import dataFetcher.DataFile;



public class App {
    private static String filePath = "./resources/data/rand.dat";
    public static void main(String[] args) throws Exception {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        

        Float rand[] = DataFile.readFloatArray(filePath);

        Float ts[] = DataFile.getTimeOfServiceFromFloatArray(rand);

        // for (Float item : rand) {
            
        //     System.out.println(item);
        // }


        for (Float item : ts) {
            System.out.println(item);
        }
    }
}

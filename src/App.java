import dataFetcher.FileReader;



public class App {
    private static String filePath = "./resources/data/rand.dat";
    public static void main(String[] args) throws Exception {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        

        Float rand[] = FileReader.readFloatArray(filePath);

        // for (Float item : rand) {
            
        //     System.out.println(item);
        // }
    }
}

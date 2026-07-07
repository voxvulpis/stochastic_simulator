import SystemData.SystemInfo;
import dataFetcher.DataFile;



public class App {
    private static String filePath = "./resources/data/";
    public static void main(String[] args) throws Exception {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        

        Double rand[] = DataFile.readFloatArray(filePath);
        SystemInfo fullInfo = new SystemInfo();

        fullInfo.fillTsTec(rand);
        fullInfo.completeInfo();

        //TODO Atualizar Rand para ter 1050 entradas e desconsiderar as 50 primeiras
        //TODO Implementar repetição com sementes diferentes (130)
        //TODO Calcular média de médias
        //TODO Calcular desvio padrão


        // fullInfo.printInfo();
        fullInfo.printInfoRounded();

        // Float ts[] = DataFile.getTimeOfService(rand);

        // for (Float item : rand) {
            
        //     System.out.println(item);
        // }

        // for (int i = 0; i < 10; i++) {
        //     System.out.println(rand[i]);
        // }

        // for (Float item : ts) {
        //     System.out.println(item);
        // }


    }
}

package dataFetcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import simulation.Simulator;
import systemData.DataStructure;
import systemData.SystemInfo;

//Classe para ler números gerados automaticamente.
//Classe com métodos estáticos para operações com arquivos.
public class DataFile {

    //Método de leitura para arquivos individuais
    public static Double[] readFloatArray(String filePath) throws Exception{
        List<String> stringArrayList = new ArrayList<String>();
        
        
        try {
            stringArrayList = Files.readAllLines(Paths.get(filePath));
            //System.out.println(Paths.get(filePath));
            
        } catch (Exception e) {
            throw new Exception("File " + filePath + " does not exists.");
        }

        for (int i = 0; i < Math.min(100, stringArrayList.size()); i++) {
            stringArrayList.remove(0);
        }

        Double floatArray[] = new Double[stringArrayList.size()];

        for(Integer i = 0; i < stringArrayList.size(); i++){
            floatArray[i] = Double.parseDouble(stringArrayList.get(i));
        }

        return floatArray;

    }

    //Método para leitura de mútiplos arquivos, retorna lista com números randômicos.
    public static List<Double[]> readMultipleFloatArrays(String fileDirectory) throws Exception{
        int i = 1;
        String filePath;
        List<Double[]> floatMatrix = new ArrayList<Double[]>();

        //Code block resposável por:
        //Contruir nome dos arquivos a serem lidos.
        //Chamar método de leitura para cada arquivo.
        //Retornar exceção se nenhum arquivo puder ser lido.
        while (i > 0) {
            filePath = fileDirectory + "rand" + i + ".data";
            try {
                floatMatrix.add(readFloatArray(filePath));
            } catch (Exception e) {
                if (i == 0) {
                    throw new Exception("Error: No file Read");
                }
                else{
                    i--;
                    break;
                }
            }
            i++;
        }

        // for (Double[] doubles : floatMatrix) {
        //     for (Double d : doubles) {
        //         System.out.print(d + " ");
        //     }
        //     System.out.print("\n\n");
        //     System.out.println("Last read: " + i);
        // }

        return floatMatrix;

    }

    public static void saveResults(Simulator simulator){
        Path path = mkDir();
        Integer index = 0;

        StringBuilder simulatorStatsStrBuilder = new StringBuilder();
        simulatorStatsStrBuilder.append("Tempo Médio de Serviço");
        simulatorStatsStrBuilder.append(",");
        simulatorStatsStrBuilder.append("Devio Padrão Serviço");
        simulatorStatsStrBuilder.append(",");
        simulatorStatsStrBuilder.append("Tempo Médio de Fila");
        simulatorStatsStrBuilder.append(",");
        simulatorStatsStrBuilder.append("Devio Padrão Fila");
        simulatorStatsStrBuilder.append(",");
        simulatorStatsStrBuilder.append("Tempo Médio no Sistema");
        simulatorStatsStrBuilder.append(",");
        simulatorStatsStrBuilder.append("Devio Padrão Sistema");
        simulatorStatsStrBuilder.append(",");
        simulatorStatsStrBuilder.append("Tempo Médio de Inatividade");
        simulatorStatsStrBuilder.append(",");
        simulatorStatsStrBuilder.append("Devio Padrão Inatividade");
        simulatorStatsStrBuilder.append('\n');

        simulatorStatsStrBuilder.append(
            simulator.getAll().stream().map(
                String::valueOf).collect(Collectors.joining(",")
            )
        );
        

        try {
            Files.write(Paths.get(path.toString(), "simulator_stats.csv"), simulatorStatsStrBuilder.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder statsStrBuilder = new StringBuilder();
            
        statsStrBuilder.append("Tempo Médio de Serviço");
        statsStrBuilder.append(",");
        statsStrBuilder.append("Tempo Médio de Fila");
        statsStrBuilder.append(",");
        statsStrBuilder.append("Tempo Médio no Sistema");
        statsStrBuilder.append(",");
        statsStrBuilder.append("Tempo Médio de Inatividade");
        statsStrBuilder.append('\n');
        

        for (SystemInfo table : simulator.getInfo()) {
            index++;
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("randTEC");
            stringBuilder.append(',');
            stringBuilder.append("randTS");
            stringBuilder.append(',');
            stringBuilder.append("Tempo Entre Chegadas");
            stringBuilder.append(',');
            stringBuilder.append("Tempo de Serviço");
            stringBuilder.append(',');
            stringBuilder.append("Chegada");
            stringBuilder.append(',');
            stringBuilder.append("Começo do Serviço");
            stringBuilder.append(',');
            stringBuilder.append("Fim do Serviço");
            stringBuilder.append(',');
            stringBuilder.append("Tempo de Fila");
            stringBuilder.append(',');
            stringBuilder.append("Tempo no Sistema");
            stringBuilder.append(',');
            stringBuilder.append("Tempo Inativo do Servidor");
            stringBuilder.append('\n');

            for (DataStructure line : table.getDataStructureArray()) {
                stringBuilder.append(
                    line.getAll().stream().map(
                        String::valueOf).collect(Collectors.joining(",")
                    )
                );
                stringBuilder.append('\n');
            }
            try {
                Files.write(Paths.get(path.toString(), "table_" + index + ".csv"), stringBuilder.toString().getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            

            statsStrBuilder.append(
                    table.getAll().stream().map(
                        String::valueOf).collect(Collectors.joining(",")
                    )
                );
            statsStrBuilder.append('\n');
            
        }

        try {
            Files.write(Paths.get(path.toString(), "tables_stats.csv"), statsStrBuilder.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Path mkDir(){
        Path path = Paths.get("results");
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
        Path path2 = Paths.get("results/" + date);

        try {
            Files.createDirectories(path);
            Files.createDirectories(path2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path2;
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

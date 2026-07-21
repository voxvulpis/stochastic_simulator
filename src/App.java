import dataFetcher.DataFile;
import simulation.Simulator;

//Função Main
public class App {
    //Define diretório com arquivos de números randômicos
    //Arquivos gerados por código em C, como feito em aula
    //Sementes definidas no arquivo 'keys.data' obtidas da página https://www.math.uchicago.edu/~luis/allprimes.html
    //Código C compilado e executado a partir do arquivo shell'generate_random_numbers.sh' (Funciona somente em Linux)
    private static String fileDirecctory = "./resources/data/";
    //Define o número de servidores no sistema
    private static Integer servitors = 1;
    public static void main(String[] args) throws Exception {

        //Cria entidade da classe simulador.
        Simulator simulator = new Simulator();

        //Roda simulação
        simulator.runSimulation(fileDirecctory, servitors);

        //Salva resultados para uma nova pasta dentro do diretório ./results/
        DataFile.saveResults(simulator);

    }

}

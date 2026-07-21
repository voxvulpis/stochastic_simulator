package systemData;

import java.util.ArrayList;
import java.util.List;

//Classe que contem a uma tabela do simulador e as estatísticas desta.
//Possui atributos e métodos para seu preenchimento
public class SystemInfo {
    //Tabela do simulador
    private ArrayList<DataStructure> dataStructureArray;
    //Estatísticas
    private Double serviceMeanTime;
    private Double queueMeanTime;
    private Double systemMeanTime;
    private Double idleMeanTime;
    //Variável auxiliar para simulação com mais de um servidor
    private List<Double> servers;

    public ArrayList<DataStructure> getDataStructureArray() {
        return dataStructureArray;
    }

    public void setDataStructureArray(ArrayList<DataStructure> dataStructureArray) {
        this.dataStructureArray = dataStructureArray;
    }

    public Double getServiceMeanTime() {
        return serviceMeanTime;
    }

    public void setServiceMeanTime(Double serviceMeanTime) {
        this.serviceMeanTime = serviceMeanTime;
    }

    public Double getQueueMeanTime() {
        return queueMeanTime;
    }

    public void setQueueMeanTime(Double queueMeanTime) {
        this.queueMeanTime = queueMeanTime;
    }

    public Double getSystemMeanTime() {
        return systemMeanTime;
    }

    public void setSystemMeanTime(Double systemMeanTime) {
        this.systemMeanTime = systemMeanTime;
    }

    public Double getIdleMeanTime() {
        return idleMeanTime;
    }

    public void setIdleMeanTime(Double idleMeanTime) {
        this.idleMeanTime = idleMeanTime;
    }

    public SystemInfo() {
        dataStructureArray = new ArrayList<DataStructure>();
    }

    public SystemInfo(ArrayList<systemData.DataStructure> dataStructure) {
        dataStructureArray = dataStructure;
    }

    public List<Double> getAll(){
        List<Double> all = new ArrayList<Double>();
        // private Double serviceMeanTime;
        // private Double queueMeanTime;
        // private Double systemMeanTime;
        // private Double idleMeanTime;

        all.add(serviceMeanTime);
        all.add(queueMeanTime);
        all.add(systemMeanTime);
        all.add(idleMeanTime);


        return all;
    }

    //Método que preenche os valores de Tempo de Serviço e Tempo de Chegada a partir de uma lista Double
    //Também define o tamanho final da Tabela completa
    public void fillTsTec(Double rand[]){
        int size = 0;

        //Define tamanho da tabela, sendo este metade do tamanho da lista Double (Arredondado)
        size = Math.floorDiv(rand.length, 2);

        for (int index = 0; index < size; index++) {
            dataStructureArray.add(new DataStructure());
            dataStructureArray.get(index).setRandForTEC(rand[index*2]);
            dataStructureArray.get(index).setRandForTS(rand[index*2 + 1]);
            dataStructureArray.get(index).setTEC(-(Math.log(rand[index*2]))/(1.0 / 2.0));
            dataStructureArray.get(index).setTS(-(Math.log(rand[index*2 + 1]))/(1.0 / 8.0));
        }
    }

    //Completa as informações da tabela a partir dos tempos de Serviço e Chegada
    public void completeInfo(Integer servitors){
        //Exceção para primeira linha de informações
        fillFirstLine();
        //Completa o resto da tabela
        fillRemainingLines(servitors);
        //Calcula estatísticas da tabela
        calculateStats();
    }

    //Método para preencher a primeira linha da tabela
    private void fillFirstLine(){
        dataStructureArray.get(0).setArrival(dataStructureArray.get(0).getTEC());
        dataStructureArray.get(0).setStart(dataStructureArray.get(0).getArrival());
        dataStructureArray.get(0).setServiceEnd(dataStructureArray.get(0).getStart() + dataStructureArray.get(0).getTS());
        dataStructureArray.get(0).setQueueTime(0.0);
        dataStructureArray.get(0).setSystemTime(dataStructureArray.get(0).getServiceEnd() - dataStructureArray.get(0).getArrival());
        dataStructureArray.get(0).setIdleTime(dataStructureArray.get(0).getArrival());
    }

    //Método para completar a tabela
    // private void fillRemainingLines(){
    //     for (int i = 1; i < dataStructureArray.size(); i++) {
    //         dataStructureArray.get(i).setArrival(dataStructureArray.get(i).getTEC() + dataStructureArray.get(i - 1).getArrival());
    //         dataStructureArray.get(i).setQueueTime(Math.max(0.0, dataStructureArray.get(i - 1).getServiceEnd() - dataStructureArray.get(i).getArrival()));
    //         dataStructureArray.get(i).setStart(dataStructureArray.get(i).getArrival() + dataStructureArray.get(i).getQueueTime());
    //         dataStructureArray.get(i).setServiceEnd(dataStructureArray.get(i).getStart() + dataStructureArray.get(i).getTS());
    //         dataStructureArray.get(i).setSystemTime(dataStructureArray.get(i).getServiceEnd() - dataStructureArray.get(i).getArrival());
    //         dataStructureArray.get(i).setIdleTime(Math.max(0.0, dataStructureArray.get(i).getStart() - dataStructureArray.get(i - 1).getServiceEnd()));
    //     }
    // }

    //Método para completar a tabela, com mútiplos servidores
    //Utiliza a variável servers para determinar qual servidor seria o primeiro a estar disponível na simulação
    private void fillRemainingLines(Integer servitors){

        //Tratativa para caso parâmetro chegue nulo (Desnecessário no contexto atual do código, mas retido para facilitar possível mudança na lógica)
        servitors = servitors != null ? servitors : 1;

        //Inicializa variável
        servers = new ArrayList<Double>();

        //Inicializa servidores disponíveis
        for (int index = 0; index < servitors; index++) {
            servers.add(0.0);
        }

        //Primeiro servidor já utilizado na primeira linha da tabela
        servers.set(0, dataStructureArray.getFirst().getServiceEnd());


        //Calcula informações da tabela
        for (int i = 1; i < dataStructureArray.size(); i++) {
            dataStructureArray.get(i).setArrival(dataStructureArray.get(i).getTEC() + dataStructureArray.get(i - 1).getArrival());
            dataStructureArray.get(i).setQueueTime(Math.max(0.0, getFirstEndTime() - dataStructureArray.get(i).getArrival()));
            dataStructureArray.get(i).setStart(dataStructureArray.get(i).getArrival() + dataStructureArray.get(i).getQueueTime());
            dataStructureArray.get(i).setServiceEnd(dataStructureArray.get(i).getStart() + dataStructureArray.get(i).getTS());
            dataStructureArray.get(i).setSystemTime(dataStructureArray.get(i).getServiceEnd() - dataStructureArray.get(i).getArrival());
            dataStructureArray.get(i).setIdleTime(Math.max(0.0, dataStructureArray.get(i).getStart() - getFirstEndTime()));
            setServerEndTime(dataStructureArray.get(i).getServiceEnd());
        }

        //Limpa variável
        servers.clear();
        servers = null;
    }

    //Retorna o menor valor da lista de servers
    //Lógica para determinar qual seria o servidor que atenderia o próximo na fila
    private Double getFirstEndTime(){
        Double firstToEnd = servers.getFirst();

        for (Double value : servers) {
            if (value < firstToEnd) {
                firstToEnd = value;
            }
        }

        return firstToEnd;
    }

    //Atualiza o menor valor da lista de servers
    private void setServerEndTime(Double newTime){
        Double firstToEnd = servers.getFirst();

        for (Double value : servers) {
            if (value < firstToEnd) {
                firstToEnd = value;
            }
        }

        servers.set(servers.indexOf(firstToEnd), newTime);
    }

    //Método para calcular estatísticas da tabela
    private void calculateStats(){
        serviceMeanTime = 0.0;
        queueMeanTime = 0.0;
        systemMeanTime = 0.0;
        idleMeanTime = 0.0;

        for (int i = 0; i < dataStructureArray.size(); i++){
            serviceMeanTime += dataStructureArray.get(i).getTS();
            queueMeanTime += dataStructureArray.get(i).getQueueTime();
            systemMeanTime += dataStructureArray.get(i).getSystemTime();
            idleMeanTime += dataStructureArray.get(i).getIdleTime();
        }

        serviceMeanTime /= Double.valueOf(dataStructureArray.size());
        queueMeanTime /= Double.valueOf(dataStructureArray.size());
        systemMeanTime /= Double.valueOf(dataStructureArray.size());
        idleMeanTime /= Double.valueOf(dataStructureArray.size());
    }

    //Para Debug
    public void printInfo(){
        for (DataStructure data : dataStructureArray) {
            System.out.println(
                "TEC: " + data.getTEC() 
                + "\tTS: " + data.getTS() 
                + "\tRandTEC: " + data.getArrival() 
                + "\tRandTS: " + data.getStart()
                + "\tRandTS: " + data.getServiceEnd()
                + "\tRandTS: " + data.getQueueTime()
                + "\tRandTS: " + data.getSystemTime()
                + "\tRandTS: " + data.getIdleTime()
                + "\tRandTS: " + data.getRandForTEC()
                + "\tRandTS: " + data.getRandForTS());
        }
    }

    //Para Debug
    public void printInfoRounded(){
        int i = 1;
        for (DataStructure data : dataStructureArray) {
            System.out.println(
                "Inf. " + i
                + "\tTEC: " + (Math.round(data.getTEC() * 1000.0 ) / 1000.0)
                + "\tTS: " + (Math.round(data.getTS() * 1000.0 ) / 1000.0)
                + "\tArrival: " + (Math.round(data.getArrival() * 1000.0 ) / 1000.0)
                + "\tStart: " + (Math.round(data.getStart() * 1000.0 ) / 1000.0)
                + "\tServ. End: " + (Math.round(data.getServiceEnd() * 1000.0 ) / 1000.0)
                + "\tQueue: " + (Math.round(data.getQueueTime() * 1000.0 ) / 1000.0)
                + "\tSys. Time: " + (Math.round(data.getSystemTime() * 1000.0 ) / 1000.0)
                + "\tIdle: " + (Math.round(data.getIdleTime() * 1000.0 ) / 1000.0)
                // + "\tRandTS: " + data.getRandForTEC()
                // + "\tRandTS: " + data.getRandForTS()
            );
            i++;
        }
        System.out.println("\nEstatisticas: "
            + "\tTempo de Seviço Médio:\t" + (Math.round(serviceMeanTime * 1000.0) / 1000.0)
            + "\tTempo de Fila Média:\t" + (Math.round(queueMeanTime * 1000.0) / 1000.0)
            + "\tTempo Médio no Sistema:\t" + (Math.round(systemMeanTime * 1000.0) / 1000.0)
            + "\tTempo Ocioso Médio:\t" + (Math.round(idleMeanTime * 1000.0) / 1000.0) 
        );
    }

    
}

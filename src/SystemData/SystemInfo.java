package SystemData;

import java.util.ArrayList;

public class SystemInfo {
    private ArrayList<DataStructure> dataStructureArray;
    private Double serviceMeanTime;
    private Double queueMeanTime;
    private Double systemMeanTime;
    private Double idleMeanTime;


    public SystemInfo() {
        dataStructureArray = new ArrayList<DataStructure>();
    }

    public SystemInfo(ArrayList<SystemData.DataStructure> dataStructure) {
        dataStructureArray = dataStructure;
    }

    public void fillTsTec(Double rand[]){
        int size = 0;

        size = Math.floorDiv(rand.length, 2);

        for (int index = 0; index < size; index++) {
            dataStructureArray.add(new DataStructure());
            dataStructureArray.get(index).setRandForTEC(rand[index*2]);
            dataStructureArray.get(index).setRandForTS(rand[index*2 + 1]);
            dataStructureArray.get(index).setTEC(-(Math.log(rand[index*2]))/(1.0 / 2.0));
            dataStructureArray.get(index).setTS(-(Math.log(rand[index*2 + 1]))/(1.0 / 8.0));
        }
    }

    public void completeInfo(){
        //Excecao para primeiro conjunto de informacoes
        dataStructureArray.get(0).setArrival(dataStructureArray.get(0).getTEC());
        dataStructureArray.get(0).setStart(dataStructureArray.get(0).getArrival());
        dataStructureArray.get(0).setServiceEnd(dataStructureArray.get(0).getStart() + dataStructureArray.get(0).getTS());
        dataStructureArray.get(0).setQueueTime(0.0);
        dataStructureArray.get(0).setSystemTime(dataStructureArray.get(0).getServiceEnd() - dataStructureArray.get(0).getArrival());
        dataStructureArray.get(0).setIdleTime(dataStructureArray.get(0).getArrival());

        for (int i = 1; i < dataStructureArray.size(); i++) {
            dataStructureArray.get(i).setArrival(dataStructureArray.get(i).getTEC() + dataStructureArray.get(i - 1).getArrival());
            dataStructureArray.get(i).setQueueTime(Math.max(0.0, dataStructureArray.get(i - 1).getServiceEnd() - dataStructureArray.get(i).getArrival()));
            dataStructureArray.get(i).setStart(dataStructureArray.get(i).getArrival() + dataStructureArray.get(i).getQueueTime());
            dataStructureArray.get(i).setServiceEnd(dataStructureArray.get(i).getStart() + dataStructureArray.get(i).getTS());
            dataStructureArray.get(i).setSystemTime(dataStructureArray.get(i).getServiceEnd() - dataStructureArray.get(i).getArrival());
            dataStructureArray.get(i).setIdleTime(Math.max(0.0, dataStructureArray.get(i).getStart() - dataStructureArray.get(i - 1).getServiceEnd()));
        }

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
}

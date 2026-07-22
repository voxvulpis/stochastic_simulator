package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dataFetcher.DataFile;
import systemData.DataStructure;
import systemData.SystemInfo;

public class Simulator {
    private List<SystemInfo> info;
    private Double serviceMean;
    private Double queueMean;
    private Double systemMean;
    private Double idleMean;
    private Double serviceSt;
    private Double queueSt;
    private Double systemSt;
    private Double idleSt;

    public Simulator() {
        this.info = new ArrayList<SystemInfo>();
        this.serviceMean = 0.0;
        this.queueMean = 0.0;
        this.systemMean = 0.0;
        this.idleMean = 0.0;
        this.serviceSt = 0.0;
        this.queueSt = 0.0;
        this.systemSt = 0.0;
        this.idleSt = 0.0;
    }

    
    
    public void runSimulation(String fileDirectory, Integer servitors) throws Exception{
        //Cria lista de arrays de números randômicos
        List<Double[]> randList = fetchRandNumbers(fileDirectory);

        //Cria e preenche uma lista de tabelas
        fillSystemInfoList(randList, servitors);

        //Calcula estatísticas a partir das estatísticas de cada tabela
        calculateCompleteStats();

    }

    //Método Wrapper, resposável por ler arquivos com números randomicamente gerados 
    public List<Double[]> fetchRandNumbers(String fileDirectory) throws Exception{
        try {
            return DataFile.readMultipleFloatArrays(fileDirectory);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    //Método que cria e preenche tabelas usando um lista de arrays de números randômicos
    private void fillSystemInfoList(List<Double[]> randList, Integer servitors){
        //Cria uma tabela nova para cada arquivo de números randômicos
        //Preenche Tempos de Serviço e Chegada de cada tabela
        //Completa cada tabela
        for (Double[] doubles : randList) {
            this.info.add(new SystemInfo());
            this.info.getLast().fillTsTec(doubles);
            this.info.getLast().completeInfo(servitors);
            // System.out.println(
            //     "Info " + this.info.size() + ": "
            //     + "Service:" + (Math.round(this.info.getLast().getServiceMeanTime() * 1000.0) / 1000.0)
            //     + "\tQueue:" + (Math.round(this.info.getLast().getQueueMeanTime() * 1000.0) / 1000.0)
            //     + "\tSystem:" + (Math.round(this.info.getLast().getSystemMeanTime() * 1000.0) / 1000.0)
            //     + "\tIdle:" + (Math.round(this.info.getLast().getIdleMeanTime() * 1000.0) / 1000.0) 
            // );
        }
    }

    //Método que cacula as estatísticas finais utilizando as tabelas preenchidas
    private void calculateCompleteStats(){
        for (SystemInfo item : this.info) {
            this.serviceMean += item.getServiceMeanTime();
            this.queueMean += item.getQueueMeanTime();
            this.idleMean += item.getIdleMeanTime();
            this.systemMean += item.getSystemMeanTime();
        }
        this.serviceMean /= this.info.size();
        this.queueMean /= this.info.size();
        this.idleMean /= this.info.size();
        this.systemMean /= this.info.size();

        for (SystemInfo item : this.info) {
            this.serviceSt += Math.pow(item.getServiceMeanTime() - this.serviceMean, 2);
            this.queueSt += Math.pow(item.getQueueMeanTime() - this.queueMean, 2);
            this.idleSt += Math.pow(item.getIdleMeanTime() - this.idleMean, 2);
            this.systemSt += Math.pow(item.getSystemMeanTime() - this.systemMean, 2);
        }
        this.serviceSt /= this.info.size() - 1;
        this.serviceSt /= Math.sqrt(this.serviceSt);
        this.queueSt /= this.info.size() - 1;
        this.queueSt /= Math.sqrt(this.queueSt);
        this.idleSt /= this.info.size() - 1;
        this.idleSt /= Math.sqrt(this.idleSt);
        this.systemSt /= this.info.size() - 1;
        this.systemSt /= Math.sqrt(this.systemSt);

        System.out.println(
            "TS Mean Value: " + (Math.round(this.serviceMean * 1000.0) / 1000.0) 
            + "\tStandart Deviation: " + (Math.round(this.serviceSt * 1000.0) / 1000.0)
            + "\nQueue Mean Value: " + (Math.round(this.queueMean * 1000.0) / 1000.0) 
            + "\tStandart Deviation: " + (Math.round(this.queueSt * 1000.0) / 1000.0)
            + "\nIdle Mean Value: " + (Math.round(this.idleMean * 1000.0) / 1000.0) 
            + "\tStandart Deviation: " + (Math.round(this.idleSt * 1000.0) / 1000.0)
            + "\nSystem Mean Value: " + (Math.round(this.systemMean * 1000.0) / 1000.0) 
            + "\tStandart Deviation: \t" + (Math.round(this.systemSt * 1000.0) / 1000.0)
        );
    }

    public List<Double> getAll(){
        List<Double> all = new ArrayList<Double>();
        
        all.add(serviceMean);
        all.add(serviceSt);
        all.add(queueMean);
        all.add(queueSt);
        all.add(systemMean);
        all.add(systemSt);
        all.add(idleMean);
        all.add(idleSt);

        return all;
    }

    public List<SystemInfo> getInfo() {
        return info;
    }



    public void setInfo(List<SystemInfo> info) {
        this.info = info;
    }



    public Double getServiceMean() {
        return serviceMean;
    }



    public void setServiceMean(Double serviceMean) {
        this.serviceMean = serviceMean;
    }



    public Double getQueueMean() {
        return queueMean;
    }



    public void setQueueMean(Double queueMean) {
        this.queueMean = queueMean;
    }



    public Double getSystemMean() {
        return systemMean;
    }



    public void setSystemMean(Double systemMean) {
        this.systemMean = systemMean;
    }



    public Double getIdleMean() {
        return idleMean;
    }



    public void setIdleMean(Double idleMean) {
        this.idleMean = idleMean;
    }



    public Double getServiceSt() {
        return serviceSt;
    }



    public void setServiceSt(Double serviceSt) {
        this.serviceSt = serviceSt;
    }



    public Double getQueueSt() {
        return queueSt;
    }



    public void setQueueSt(Double queueSt) {
        this.queueSt = queueSt;
    }



    public Double getSystemSt() {
        return systemSt;
    }



    public void setSystemSt(Double systemSt) {
        this.systemSt = systemSt;
    }



    public Double getIdleSt() {
        return idleSt;
    }



    public void setIdleSt(Double idleSt) {
        this.idleSt = idleSt;
    }

    

    
}

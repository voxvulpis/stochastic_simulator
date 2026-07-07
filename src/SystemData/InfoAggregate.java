package SystemData;

import java.util.ArrayList;

import dataFetcher.DataFile;

public class InfoAggregate {
    private ArrayList<SystemInfo> aggList;
    private Double meanest;
    private Double stdDeviation;
    
    public InfoAggregate() {
        aggList = new ArrayList<SystemInfo>();
        meanest = 0.0;
        stdDeviation = 0.0;
    }
    
    public InfoAggregate(ArrayList<SystemInfo> aggList, Double meanest, Double stdDeviation) {
        this.aggList = aggList;
        this.meanest = meanest;
        this.stdDeviation = stdDeviation;
    }

    public ArrayList<SystemInfo> getAggList() {
        return aggList;
    }

    public void setAggList(ArrayList<SystemInfo> aggList) {
        this.aggList = aggList;
    }

    public Double getMeanest() {
        return meanest;
    }

    public void setMeanest(Double meanest) {
        this.meanest = meanest;
    }

    public Double getStdDeviation() {
        return stdDeviation;
    }

    public void setStdDeviation(Double stdDeviation) {
        this.stdDeviation = stdDeviation;
    }

    public void makeSystemInfoArrayList(String filePath){
        
    }

    // Double rand[] = DataFile.readFloatArray(filePath);
    // SystemInfo fullInfo = new SystemInfo();

    
}

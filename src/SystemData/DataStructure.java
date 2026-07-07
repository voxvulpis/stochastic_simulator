package SystemData;

public class DataStructure {
    private Double randForTEC;
    private Double randForTS;
    private Double TEC;
    private Double TS;
    private Double arrival;
    private Double start;
    private Double serviceEnd;
    private Double queueTime;
    private Double systemTime;
    private Double idleTime;
    
    
    public DataStructure() {
    }

    public DataStructure(java.lang.Double randForTEC, java.lang.Double randForTS, java.lang.Double tEC,
            java.lang.Double tS, java.lang.Double arrival, java.lang.Double start, java.lang.Double serviceEnd,
            java.lang.Double queueTime, java.lang.Double systemTime, java.lang.Double idleTime) {
        this.randForTEC = randForTEC;
        this.randForTS = randForTS;
        TEC = tEC;
        TS = tS;
        this.arrival = arrival;
        this.start = start;
        this.serviceEnd = serviceEnd;
        this.queueTime = queueTime;
        this.systemTime = systemTime;
        this.idleTime = idleTime;
    }

    public Double getRandForTEC() {
        return randForTEC;
    }

    public void setRandForTEC(Double randForTEC) {
        this.randForTEC = randForTEC;
    }

    public Double getRandForTS() {
        return randForTS;
    }

    public void setRandForTS(Double randForTS) {
        this.randForTS = randForTS;
    }

    public Double getTEC() {
        return TEC;
    }

    public void setTEC(Double tEC) {
        TEC = tEC;
    }

    public Double getTS() {
        return TS;
    }

    public void setTS(Double tS) {
        TS = tS;
    }

    public Double getArrival() {
        return arrival;
    }

    public void setArrival(Double arrival) {
        this.arrival = arrival;
    }

    public Double getStart() {
        return start;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public Double getServiceEnd() {
        return serviceEnd;
    }

    public void setServiceEnd(Double serviceEnd) {
        this.serviceEnd = serviceEnd;
    }

    public Double getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(Double queueTime) {
        this.queueTime = queueTime;
    }

    public Double getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(Double systemTime) {
        this.systemTime = systemTime;
    }

    public Double getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(Double idleTime) {
        this.idleTime = idleTime;
    }

    

    
}

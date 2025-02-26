package processor;
/**
 * SummaryStat class to store the summary statistics
 */
public class SummaryStat {
    // instance variables
    private final String statType;
    private int amount;
    /**
     * Constructor for SummaryStat
     * @param x
     * @param a
     */
    public SummaryStat(String x, int a){
        statType = x;
        amount = a;
    }
    /**
     * @return statType
     */
    public String getStatType(){
        return statType;
    }
    /**
     * @return amount
     */
    public int getAmount()
    {
        return amount;
    }
    /**
     * Increase the amount by 1
     */
    public void increase(){
        amount++;
    }
    /**
     * @return String representation of the object
     */
    public String toString(){
        return statType+": "+amount;
    }
}

package processor;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
public class StateCountMap {
    protected Map<String,Integer> wordCounts;
    protected boolean finishedCounting;

    public StateCountMap(){
        wordCounts = new TreeMap();
    }

    public void finishedCounting(){
        finishedCounting = true;
    }
    /**
     * adds word into map if first instance, else increament count
     * Handles misspellings of certain states 
     * @param word
     */
    public void count(String word){
        if(!finishedCounting){
            if(word.contains("Miss")){
                if(!wordCounts.containsKey("Miss.")){
                    wordCounts.put("Miss.", 1);
                }
                else{
                    int count = wordCounts.get("Miss.");
                    wordCounts.put("Miss.", count+1);
                }
            }
            else if(word.contains("Tex")){
                if(!wordCounts.containsKey("Tex.")){
                    wordCounts.put("Tex.", 1);
                }
                else{
                    int count = wordCounts.get("Tex.");
                    wordCounts.put("Tex.", count+1);
                }
            }
            else if(!wordCounts.containsKey(word.strip())){
                wordCounts.put(word.strip(), 1);
            }
            else{
                int count = wordCounts.get(word.strip());
                wordCounts.put(word.strip(), count+1);
            }
        }
    }
    /**
     * 
     * @param word
     * @return count for that word
     */
    public int getCount(String word){
        if(!wordCounts.containsKey(word.strip()))
            return 0;
        else
            return wordCounts.get(word.strip());
    }
    /**
     * toString that returns "State, Count" for every state in the tree map
     */
    public String toString(){
        StringBuilder x = new StringBuilder();
        Set<Map.Entry<String, Integer> > entries 
            = wordCounts.entrySet();
        entries.forEach((entry) -> { 
            x.append((entry.getKey() + ", " + entry.getValue()+"\n"));     
        });
        return x.toString();
    }
    
}

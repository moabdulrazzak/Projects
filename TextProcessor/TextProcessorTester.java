import java.io.File;
import java.io.IOException;
import processor.TextProcessor;
import static processor.TextProcessor.stateCount93;

public class TextProcessorTester {
    public static void main(String[] args) {
        try {
            TextProcessor.processText();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        if(new File("./1892/ReasonSummaries92.csv").exists())
            System.out.println("1892 Offense Summaries file found");
        else
            System.out.println("1892 Offense Summaries file not found");

        if(new File("./1892/StateSummaries92.csv").exists())
            System.out.println("1892 State Summaries file found");
        else
            System.out.println("1892 State Summaries file not found");

        if(new File("./1893/1893events.csv").exists())
            System.out.println("1893 Events file found");
        else
            System.out.println("1893 Events file not found");

        if(new File("./1893/1893eventsByDate.csv").exists())
            System.out.println("1893 Events sorted by date file found");
        else
            System.out.println("1893 Events sorted by date file not found");

        if(new File("./1893/1893eventsByState.csv").exists())
            System.out.println("1893 Events sorted by state file found");
        else
            System.out.println("1893 Events sorted by state file not found");
        
        if(new File("./1893/ReasonSummaries93.csv").exists())
            System.out.println("1893 Offense Summaries file found");
        else
            System.out.println("1893 Offense Summaries file found");

        if(new File("./1893/StateSummaries93.csv").exists())
            System.out.println("1893 State Summaries file found");
        else
            System.out.println("1893 State Summaries file found");

        if(new File("./1894/1894events.csv").exists())
            System.out.println("1894 Events file found");
        else
            System.out.println("1894 Events file not found");

        if(new File("./1894/1894eventsByDate.csv").exists())
            System.out.println("1894 Events sorted by date file found");
        else
            System.out.println("1894 Events sorted by date file not found");

        if(new File("./1894/1894eventsByState.csv").exists())
            System.out.println("1894 Events sorted by state file found");
        else
            System.out.println("1894 Events sorted by state file not found");
        
        if(new File("./1894/ReasonSummaries94.csv").exists())
            System.out.println("1894 Offense Summaries file found");
        else
            System.out.println("1894 Offense Summaries file found");

        if(new File("./1894/StateSummaries94.csv").exists())
            System.out.println("1894 State Summaries file found");
        else
            System.out.println("1894 State Summaries file found");
        System.out.println("Testing State Count for Alabama in 1893. Should be 21");
        if(stateCount93.getCount("Ala.") == 21)
            System.out.println("And it is!");
        else
            System.out.println("It is not, currently is: " + stateCount93.getCount("Ala."));
    }
}

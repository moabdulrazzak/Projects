package processor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class TextProcessor {
    public static ArrayList<String[]> Lines = new ArrayList<>();
    public static ArrayList<Event> totalEvents = new ArrayList<>();
    public static ArrayList<Event> Events1893 = new ArrayList<>();
    public static ArrayList<Event> Events1894 = new ArrayList<>();
    public static ArrayList<SummaryStat> reasonStats93 = new ArrayList<>();
    public static ArrayList<SummaryStat> reasonStats94 = new ArrayList<>();
    public static ArrayList<SummaryStat> reasonStats92 = new ArrayList<>();
    public static ArrayList<SummaryStat> stateStats92 = new ArrayList<>();
    public static StateCountMap stateCount93 = new StateCountMap();
    public static StateCountMap stateCount94 = new StateCountMap();

    // Main method to process the text data and generate various output files
    public static void processText() throws IOException{
        // Writers to output summary statistics and events to CSV files for different years
        FileWriter summaryFile93 = new FileWriter("./1893/ReasonSummaries93.csv");
        FileWriter summaryFile94 = new FileWriter("./1894/ReasonSummaries94.csv");
        FileWriter summaryFile92 = new FileWriter("./1892/ReasonSummaries92.csv");
        FileWriter stateFile93 = new FileWriter("./1893/StateSummaries93.csv");
        FileWriter stateFile94 = new FileWriter("./1894/StateSummaries94.csv");
        FileWriter stateFile92 = new FileWriter("./1892/StateSummaries92.csv");
        FileWriter eventFile93 = new FileWriter("./1893/1893events.csv");
        FileWriter eventFile94 = new FileWriter("./1894/1894events.csv");
        FileWriter sortedEventFile94 = new FileWriter("./1894/1894eventsByDate.csv");
        FileWriter sortedEventFile93 = new FileWriter("./1893/1893eventsByDate.csv");
        FileWriter sortedStateFile94 = new FileWriter("./1894/1894eventsByState.csv");
        FileWriter sortedStateFile93 = new FileWriter("./1893/1893eventsByState.csv");

        // PrintWriter used to write formatted text data
        PrintWriter printer = new PrintWriter(eventFile93);

        // Splits the main text file into manageable segments (paragraphs)
        splitFiles("./RedRecordIdaBWells.txt");

        // Reads the split text file and separates content into paragraphs
        Scanner fileReader = new Scanner(new File("splitRedRecord.txt"));
        fileReader.useDelimiter("\\s*\\n\\s*\\n\\s*"); // Separates text into paragraphs based on double newlines

        // Processes each paragraph into arrays of strings, where each element represents part of an event
        while(fileReader.hasNext()){
            String paragraph = fileReader.next();
            String[] words = paragraph.split(";");//Each array now has each element of the Event object separeted by a comma 
            Lines.add(words);     
        }
        int eventCounter;

        // Iterates through each line of parsed text and creates Event objects
        for(int i = 0; i < Lines.size(); i++)
        {
            int extras1=0, extras2=0, extras3 =0; //Values to adjust count for when event has multiple unknowns in one event
            eventCounter =0; //Reset after evey new event type

            // Checks if the line represents a valid event structure (skips offense names)
            if(Lines.get(i)[0].split(",").length>2)
            {
                for (String para : Lines.get(i)) {
                    if(para.split(",").length==8) //Handles missing semicolon
                    {
                        String[] E = para.split(",");
                        Event x = new Event(Lines.get(i-1)[0], E[0], E[1], E[2], E[3]); // the offense name will always be 1 index behind
                        Event y = new Event(Lines.get(i-1)[0], E[4], E[5], E[6], E[7]);
                        totalEvents.add(x);
                        totalEvents.add(y);
                        if(i<=59){  //where data for 1893 ends 
                            Events1893.add(x);
                            Events1893.add(y);
                            eventCounter+=2;
                            stateCount93.count(x.getState());
                            stateCount93.count(y.getState());
                        }
                        else{
                            Events1894.add(x);
                            Events1894.add(y);
                            eventCounter+=2;
                            stateCount94.count(x.getState());
                            stateCount94.count(y.getState());
                        }
                    }
                    else if(para.split(",").length==7) //Handles missing semicolon
                    {
                        String[] E = para.split(",");
                        Event x = new Event(Lines.get(i-1)[0], E[0], E[1], E[2], E[3].substring(0, 4));
                        Event y = new Event(Lines.get(i-1)[0], E[3].substring(4), E[4], E[5], E[6]);
                        totalEvents.add(x);
                        totalEvents.add(y);
                        if(i<=59){
                            Events1893.add(x);
                            Events1893.add(y);
                            stateCount93.count(x.getState());
                            stateCount93.count(y.getState());
                            eventCounter+=2;
                        }
                        else{
                            Events1894.add(x);
                            Events1894.add(y);
                            eventCounter+=2;
                            stateCount94.count(x.getState());
                            stateCount94.count(y.getState());
                        }
                    }
                    else if(para.split(",").length==3) //Handles missing city
                    {
                        String[] E = para.split(",");
                        Event x = new Event(Lines.get(i-1)[0], E[0], E[1], "", E[2]);
                        totalEvents.add(x);
                        if(i<=59){
                            Events1893.add(x);
                            stateCount93.count(x.getState());
                            eventCounter++;
                        }
                        else{
                            Events1894.add(x);
                            eventCounter++;
                            stateCount94.count(x.getState());
                        }

                    }
                    else if(para.split(",").length==5) //Handles second name
                    {
                        String[] E = para.split(",");
                        Event x = new Event(Lines.get(i-1)[0], E[0], E[1]+E[2], E[3], E[4]);
                        totalEvents.add(x);
                        if(i<=59){
                            Events1893.add(x);
                            stateCount93.count(x.getState());
                            eventCounter++;
                        }
                        else{
                            Events1894.add(x);
                            eventCounter++;
                            stateCount94.count(x.getState());
                        }
                    }
                    else
                    {
                        String[] E = para.split(",");
                        Event x = new Event(Lines.get(i-1)[0], E[0], E[1], E[2], E[3]);
                        totalEvents.add(x);
                        if(x.getName().contains("two"))
                            extras1 += 1;
                        else if(x.getName().contains("three"))
                            extras2+=2;
                        else if(x.getName().contains("four"))
                            extras3+=3;
                        if(i<=59){
                            Events1893.add(x);
                            eventCounter++;
                            stateCount93.count(x.getState());
                        }
                        else{
                            Events1894.add(x);
                            eventCounter++;
                            stateCount94.count(x.getState());
                        }
                    }
                }
                SummaryStat reason = new SummaryStat(Lines.get(i-1)[0],(eventCounter)+(extras1+extras2+extras3));
                if(i>55)
                    reasonStats94.add(reason);
                else
                    reasonStats93.add(reason);
            }
            //Handles Edge case where the last event is not formatted correctly
            if(i==60){
                for(String Summarys92: Lines.get(i)){
                    String[] s = Summarys92.split(",");
                    if(s[0].contains("Oklahoma"))
                        break;
                    SummaryStat x= new SummaryStat(s[0].strip(), Integer.parseInt(s[1].strip()));
                    stateStats92.add(x);
                }
                stateStats92.add(new SummaryStat("Oklahoma",2));     
            }
            //Handles Edge case where the last event is not formatted correctly
            if(i==61){
                for(String Reasons92: Lines.get(i)){
                    String[] s = Reasons92.split(",");
                    if(s[0].contains("no offense"))
                        break;
                    SummaryStat x= new SummaryStat(s[0].strip(), Integer.parseInt(s[1].strip()));
                    reasonStats92.add(x);
                }    
                reasonStats92.add(new SummaryStat("no offense stated",2)); 
            }
        }
        // Prints all CSV files
        printCSV(eventFile93, Events1893);
        printCSV(eventFile94, Events1894);
        printCSV(summaryFile93, reasonStats93);
        printCSV(summaryFile94, reasonStats94);
        printCSV(stateFile92, stateStats92);
        printCSV(summaryFile92, reasonStats92);
        printStateSummCSV(stateFile93, stateCount93);
        printStateSummCSV(stateFile94, stateCount94);
        printCSV(sortedEventFile94, sortByDate(Events1894, 1894));
        printCSV(sortedEventFile93, sortByDate(Events1893,1893));
        printCSV(sortedStateFile93, sortByState(Events1893));
        printCSV(sortedStateFile94, sortByState(Events1894));
        fileReader.close();
        eventFile93.close();
        stateFile92.close();
        summaryFile92.close();
    } //processText()
    /**
     * Splits the Red Record book into a new file just with the relevant data  
     * @param fileName
     * @throws IOException
     */
    public static void splitFiles(String fileName) throws IOException{
        Scanner reader = new Scanner(new File(fileName));
        FileWriter output = new FileWriter("splitRedRecord.txt");
        PrintWriter printer = new PrintWriter(output);
        int lineNum = 0;
        while(reader.hasNextLine()){
            if((lineNum>=449 && lineNum<=681)||(lineNum>=694 && lineNum<=700) ||(lineNum>=706 && lineNum<=711)|| (lineNum >= 3221 && lineNum <= 3466))
            {
                if(lineNum == 556){ //adjust formatting
                    printer.print(";");
                    reader.nextLine();
                    lineNum++;
                }
                printer.println(reader.nextLine());
                lineNum++;
            }
            else
            {
                reader.nextLine();
                lineNum++;
            }

        }
        printer.close();
        reader.close();
    }//splitFiles{}
    /**
     * Sorts array list of events by date using Arraylist's sort method and dates isBefore and isAfter methods
     * @param events
     * @param year
     * @return Sorted Array List of events
     */
    public static ArrayList<Event> sortByDate(ArrayList<Event> events, int year){
        events.sort((e1, e2) -> {
            LocalDate date1 = e1.getLocalDate(year);
            LocalDate date2 = e2.getLocalDate(year);
            
            // Use LocalDate's comparison methods
            if (date1.isBefore(date2)) {
                return -1;
            } else if (date1.isAfter(date2)) {
                return 1;
            } else {
                return 0;
            }
        });
        return events;
    } //sortByDate()
    /**
     * Sorts array list of events alphabetically by state using a Comparator
     * @param events
     * @return Sorted Array List of events
     */
    public static ArrayList<Event> sortByState(ArrayList<Event> events){
        events.sort(Comparator.comparing(Event::getState).thenComparing(Event::getCity));
        return events;
    }//sortByState()
    /**
     * Method to print all necessary CSV files
     * @param file
     * @param List, can take any type of Arraylist 
     */
    public static void printCSV(FileWriter file, ArrayList<?> List){
        PrintWriter printer = new PrintWriter(file);
        Object obj = List.get(0);
        if(obj instanceof Event){
            printer.println("Name, Date, City, State, Reason");
            for(Object i:List){
                Event x = (Event) i;
                printer.println(x.getName()+"," +x.getDate()+","+x.getCity()+","+x.getState()+","+x.getType());
            }
        }
        else if(obj instanceof SummaryStat){
            printer.println("Offense, Amount");
            for(Object i:List){
                SummaryStat x = (SummaryStat) i;
                printer.println(x.getStatType()+","+x.getAmount());
            }
        }
        printer.close();
    }//printCSV()
    /**
     * 
     * @param file
     * @param map
     */
    public static void printStateSummCSV(FileWriter file, StateCountMap map){
        PrintWriter printer = new PrintWriter(file);
        printer.println(map.toString());
        printer.close();
    }//printStateSummCSV
}//TextProcessor

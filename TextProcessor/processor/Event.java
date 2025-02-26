package processor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Event {
    private final String type, date, name, city, state;
    /**
     * 
     * @param type
     * @param date
     * @param name
     * @param city
     * @param state
     */
    public Event(String t, String d, String n, String c, String s)
    {
        type = t;
        date = d.replace("\n", "");
        name = n.replace("\n", "");
        city = c.replace("\n", "");
        state = s.replace("\n", "");
    }
    /**
     * @return Event Type
     */
    public String getType(){
        return type;
    }
    /**
     * @return Event Date
     */
    public String getDate(){
        return date;
    }
    /**
     * @return Event Name
     */
    public String getName(){
        return name;
    }
    /**
     * @return Event City
     */
    public String getCity(){
        return city.trim();
    }
    /**
     * @return Event State
     */
    public String getState(){
        return state.trim();
    }
    public String toString(){
        return name + " was lynched on" + date + " in " + city +
        "," + state + " because of " + type;
    }
    /**
     * Formats input date to align with local date formatter
     * @param date
     * @return processed date
     */
    public String preprocessDate(String date) {
        //for formatting errors
        date = date.replace("Sept.", "Sep.");
        date = date.replace("Sep.16", "Sep. 16");
        date = date.replace("Aug.12", "Aug. 12");
        date = date.replace("Aug.21", "Aug. 21");
        date = date.replace("Dec.8", "Dec. 8");

        String regex = "([A-Za-z]+)(\\d+)"; //removes unwanted spacing and typos
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()) {
            date = matcher.replaceFirst(matcher.group(1) + " " + matcher.group(2));
        }
        return date.trim();
    }
    /**
     * Converts date from string into date object
     * @param year
     * @return
     */
    public LocalDate getLocalDate(int year){
        DateTimeFormatter abbreviatedFormatter = DateTimeFormatter.ofPattern("MMM. d yyyy", Locale.ENGLISH);
        DateTimeFormatter fullMonthFormatter = DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.ENGLISH);
        
        String processedDate = preprocessDate(date) + " " + year;

        try {
            return LocalDate.parse(processedDate, abbreviatedFormatter);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(processedDate, fullMonthFormatter);
        }
    }
}

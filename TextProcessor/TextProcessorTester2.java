import java.util.Collections;
import java.util.Comparator;
import processor.*;

public class TextProcessorTester2 {
    public static void main(String[] args) {
        testEventClass();
        testStateCountMap();
        testTextProcessorMethods();
        performanceTestTextProcessor();
    }

    private static void testEventClass() {
        System.out.println("\nTesting Event Class\n-----------------");

        // Test Event creation and getters
        Event event = new Event("MURDER", "Sept. 15", "Paul Hill", "Carrollton", "Ala.");
        System.out.println("Event Type: " + event.getType());
        System.out.println("Event Date: " + event.getDate());
        System.out.println("Event Name: " + event.getName());
        System.out.println("Event City: " + event.getCity());
        System.out.println("Event State: " + event.getState());

        // Test date preprocessing
        String preprocessedDate = event.preprocessDate("Sept.15");
        System.out.println("Preprocessed Date: " + preprocessedDate);

        // Test LocalDate conversion
        System.out.println("LocalDate: " + event.getLocalDate(1893));
    }

    private static void testStateCountMap() {
        System.out.println("\nTesting StateCountMap Class\n-----------------");

        StateCountMap stateCountMap = new StateCountMap();
        stateCountMap.count("Ala.");
        stateCountMap.count("Ala.");
        stateCountMap.count("Tex.");

        // Test count method
        System.out.println("Count for Ala.: " + stateCountMap.getCount("Ala."));
        System.out.println("Count for Tex.: " + stateCountMap.getCount("Tex."));
        System.out.println("Count for Miss.: " + stateCountMap.getCount("Miss."));

        // Test toString
        System.out.println("StateCountMap Contents:\n" + stateCountMap.toString());

        stateCountMap.finishedCounting();
        stateCountMap.count("Tex.");
        System.out.println("After finishing counting, count for Tex.: " + stateCountMap.getCount("Tex."));
    }

    private static void testTextProcessorMethods() {
        System.out.println("\nTesting TextProcessor Functional Methods\n-----------------");

        // Sample data setup
        TextProcessor.totalEvents.clear();
        TextProcessor.Events1893.clear();

        Event event1 = new Event("MURDER", "Sept. 15", "Paul Hill", "Carrollton", "Ala.");
        Event event2 = new Event("MURDER", "Sept. 16", "Paul Archer", "Carrollton", "Ala.");
        TextProcessor.totalEvents.add(event1);
        TextProcessor.totalEvents.add(event2);
        TextProcessor.Events1893.add(event1);
        TextProcessor.Events1893.add(event2);

        // Sort events by date
        Collections.sort(TextProcessor.Events1893, Comparator.comparing(e -> e.getLocalDate(1893)));
        System.out.println("Sorted Events by Date:");
        for (Event event : TextProcessor.Events1893) {
            System.out.println(event);
        }

        // Verify statistics
        SummaryStat stat = new SummaryStat("MURDER", 2);
        System.out.println("SummaryStat Type: " + stat.getStatType());
        System.out.println("SummaryStat Amount: " + stat.getAmount());

        stat.increase();
        System.out.println("After Increase, SummaryStat Amount: " + stat.getAmount());
    }

    private static void performanceTestTextProcessor() {
        System.out.println("\nPerformance Testing TextProcessor\n-----------------");

        final int TEST_SIZE = 10000;
        TextProcessor.totalEvents.clear();

        // Populate events for testing
        for (int i = 0; i < TEST_SIZE; i++) {
            Event event = new Event("MURDER", "Sept. " + (i % 30 + 1), "Person " + i, "City" + i, "State" + (i % 50));
            TextProcessor.totalEvents.add(event);
        }

        // Measure sorting performance
        long start = System.nanoTime();
        Collections.sort(TextProcessor.totalEvents, Comparator.comparing(e -> e.getLocalDate(1893)));
        long end = System.nanoTime();

        System.out.println("Sorting " + TEST_SIZE + " events took: " + (end - start) / 1_000_000 + " ms");
    }
}

# Code Documentation

## Overview
Designed to process, analyze, and generate summaries of text data related to lynching statistics from Ida B. Wells' "A Red Record." The code aims to read and parse text data, count occurrences of specific events, categorize them by year and reason, and output detailed summaries in CSV format.

---

## Class Descriptions

### 1. **StateCountMap**
This class handles counting occurrences of state names in text data, with special handling for misspelled or abbreviated states.

#### Fields:
- `Map<String, Integer> wordCounts`: Stores state names as keys and their occurrence counts as values.
- `boolean finishedCounting`: Indicates whether counting has concluded.

#### Methods:
- `StateCountMap()`: Initializes the `wordCounts` map.
- `finishedCounting()`: Sets `finishedCounting` to true, disabling further counting.
- `count(String word)`: Adds or updates the count for a given word, handling common misspellings of states like "Miss." and "Tex.".
- `getCount(String word)`: Retrieves the count for a specified word.
- `toString()`: Returns a formatted string of all state counts in "State, Count" format.

---

### 2. **TextProcessor**
This is the main class responsible for parsing the input text, creating `Event` objects, and generating summary statistics and CSV files.

#### Fields:
- Multiple `ArrayList` fields to store lines, events, and summary statistics for different years (e.g., `Lines`, `totalEvents`, `Events1893`).
- Instances of `StateCountMap` for specific years (`stateCount93`, `stateCount94`).

#### Methods:
- `processText()`: Main method for text processing. Splits the input text into paragraphs, parses events, counts states, and generates CSV output.
- `splitFiles(String fileName)`: Extracts relevant data segments from the input file based on line numbers.
- `sortByDate(ArrayList<Event> events, int year)`: Sorts events by date using `LocalDate` comparisons.
- `sortByState(ArrayList<Event> events)`: Sorts events alphabetically by state and city.
- `printCSV(FileWriter file, ArrayList<?> list)`: Writes a list of events or summary statistics to a CSV file.
- `printStateSummCSV(FileWriter file, StateCountMap map)`: Writes state summaries to a CSV file.

---

### 3. **Event**
Represents an individual event, storing details such as type, date, name, city, and state.

#### Fields:
- `String type, date, name, city, state`: Stores event details.

#### Methods:
- `Event(String t, String d, String n, String c, String s)`: Constructor to initialize an event.
- `getType(), getDate(), getName(), getCity(), getState()`: Getters for respective fields.
- `preprocessDate(String date)`: Corrects formatting issues in date strings.
- `getLocalDate(int year)`: Converts the event's date to a `LocalDate` object, considering year context.

---

### 4. **SummaryStat**
Stores summary statistics for offenses or state occurrences.

#### Fields:
- `String statType`: Type of statistic (e.g., offense name).
- `int amount`: Number of occurrences.

#### Methods:
- `SummaryStat(String x, int a)`: Constructor to initialize a statistic.
- `getStatType(), getAmount()`: Getters for respective fields.
- `increase()`: Increments the count by 1.
- `toString()`: Returns a string representation of the statistic.

---

### 5. **TextProcessorTester**
Tests the `TextProcessor` class by running its main methods and validating output files.

#### Methods:
- `main(String[] args)`: Executes `TextProcessor.processText()` and checks the existence of output files.

---

### 6. **TextProcessorTester2**
Tests individual components of the program.

#### Methods:
- `testEventClass()`: Verifies the functionality of the `Event` class.
- `testStateCountMap()`: Tests counting functionality in `StateCountMap`.
- `testTextProcessorMethods()`: Validates sorting and statistics generation in `TextProcessor`.
- `performanceTestTextProcessor()`: Benchmarks sorting performance with a large dataset.

---

## Workflow
1. **Text Input:**
   - The `processText` method in `TextProcessor` reads the input file and splits it into paragraphs using `splitFiles`.

2. **Event Parsing:**
   - Each paragraph is parsed into `Event` objects. These events are categorized by year and stored in appropriate lists.

3. **Counting and Summarization:**
   - State occurrences are counted using `StateCountMap`.
   - Summary statistics for offenses are generated using `SummaryStat`.

4. **Output Generation:**
   - Results are written to CSV files using `printCSV` and `printStateSummCSV`.

---

## Limitations
- **Hardcoded Line Numbers:** The `splitFiles` method depends on fixed line numbers, limiting flexibility.
- **Error Handling:** Limited exception handling, particularly in the `processText` method.

---

## Future Improvements
- Dynamically determine relevant text sections instead of relying on hardcoded line numbers.
- Enhance exception handling to provide more robust error reporting.

---

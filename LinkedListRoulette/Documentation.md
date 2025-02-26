# Code Documentation

## Overview
This project implements a roulette game simulation. The system includes a linked-list representation of the roulette wheel, betting functionality, and a graphical user interface (GUI) for user interaction. It supports various types of bets and handles game mechanics such as balance management and result evaluation.

---

## Class Descriptions

### 1. **Bet**
Represents a single bet placed by a player.

#### Fields:
- `String type`: Type of the bet (e.g., "Number", "Color", "Odd/Even").
- `Object value`: Value of the bet (e.g., a number, "Red", "Even").
- `int amount`: The amount of money wagered.

#### Methods:
- `Bet(String type, Object value, int amount)`: Constructor to initialize a bet.
- `boolean isWinningBet(RouletteNode result)`: Checks if the bet matches the result of the roulette spin.
- `String toString()`: Returns a string representation of the bet.

---

### 2. **InsufficientBalanceException**
Custom exception class thrown when a player attempts to place a bet exceeding their available balance.

#### Methods:
- `InsufficientBalanceException(String message)`: Constructor to initialize the exception with a message.

---

### 3. **RouletteNode**
Represents a node in the linked list that forms the roulette wheel.

#### Fields:
- `int number`: The number on the roulette node.
- `String color`: The color of the node (e.g., "Red", "Black", "Green").
- `RouletteNode next`: A reference to the next node in the linked list.

#### Methods:
- `RouletteNode(int number, String color)`: Constructor to initialize a node.
- `int getNumber()`: Returns the number on the node.
- `String getColor()`: Returns the color of the node.
- `String toString()`: Returns a string representation of the node.
- `int compareTo(Object o)`: Compares two nodes based on their numbers.

---

### 4. **RouletteWheel**
Implements the roulette wheel as a circular linked list.

#### Fields:
- `RouletteNode head`: The head of the linked list.

#### Methods:
- `RouletteWheel()`: Constructor to initialize the wheel.
- `void intializeWheel()`: Sets up the wheel with numbers and colors in the correct sequence.
- `RouletteNode spin(int steps)`: Spins the wheel a specified number of steps and returns the resulting node.
- `RouletteNode spinBall()`: Spins the wheel a random number of steps and returns the resulting node.
- `String toString()`: Returns a string representation of the entire wheel.

---

### 5. **Player**
Represents a player in the roulette game.

#### Fields:
- `int balance`: The player’s current balance.
- `ArrayList<Bet> bets`: A list of bets placed by the player.

#### Methods:
- `Player(int initialBalance)`: Constructor to initialize the player’s balance.
- `void placeBet(Bet bet) throws InsufficientBalanceException`: Places a bet if sufficient balance is available.
- `void evaluateBets(RouletteNode result)`: Evaluates all bets against the result and updates the balance.
- `int calculatePayout(Bet bet)`: Calculates the payout for a winning bet based on its type.
- `int getBalance()`: Returns the player’s current balance.
- `ArrayList<Bet> getBets()`: Returns the list of bets placed by the player.

---

### 6. **RouletteGUI**
Provides a graphical user interface for the roulette game.

#### Fields:
- `RouletteWheel wheel`: The roulette wheel.
- `Player player`: The player with an initial balance of $1000.
- `JTextArea betSummaryArea`: Displays the list of placed bets.
- `JLabel balanceLabel`: Displays the player’s current balance.

#### Methods:
- `RouletteGUI()`: Constructor to set up the GUI.
- `JPanel createBetAmountPanel()`: Creates a panel for selecting bet amounts.
- `JPanel createRouletteTablePanel()`: Creates a panel representing the roulette table.
- `JPanel createBetSummaryPanel()`: Creates a panel to display the summary of bets.
- `JButton createSpinButton()`: Creates a button to spin the wheel.
- `void updateBetSummary()`: Updates the display of placed bets.
- `void addBet(Bet b)`: Adds a bet to the player’s list of bets and deducts the amount from the balance.

---

## Workflow
1. **Initialize Components:**
   - Create a `RouletteWheel` and populate it with nodes.
   - Instantiate a `Player` with a starting balance.

2. **Place Bets:**
   - Players can place various types of bets using the GUI or programmatically in tests.

3. **Spin the Wheel:**
   - The wheel spins a random number of steps to determine the result.

4. **Evaluate Bets:**
   - Bets are evaluated, and payouts are calculated based on the result.

5. **Display Results:**
   - The GUI updates to show the outcome and the player’s balance.

---

## Highlights
- **Circular Linked List:** Efficient implementation of the roulette wheel.
- **Custom Exception Handling:** Prevents invalid betting operations.
- **Comprehensive Testing:** Separate classes for testing various components.
- **User-Friendly GUI:** Provides an interactive interface for placing bets and spinning the wheel.

---

## Limitations
- **Static Bet Types:** Limited to "Number", "Color", and "Odd/Even".
- **No Persistence:** Game state is not saved between sessions.

---

## Future Improvements
- Add more bet types (e.g., "High/Low", "Columns").
- Implement game state saving and loading.
- Enhance GUI with more detailed statistics and animations.

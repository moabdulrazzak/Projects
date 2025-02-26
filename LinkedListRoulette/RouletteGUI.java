import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
/**
 * GUI for the Roulette game
 */
public class RouletteGUI extends JFrame {
    // Instance variables
    private RouletteWheel wheel = new RouletteWheel(); // Roulette wheel
    private Player player = new Player(1000); // Initial balance of $1000
    private JTextArea betSummaryArea = new JTextArea(10, 30); // Display placed bets
    private JLabel balanceLabel; // Display player balance
    private JPanel summaryPanel; // Panel to display bet summary
    final int[] selectedBetAmount = { 1 }; // Default bet amount

    // Constructor
    public RouletteGUI() {
        // Frame setup
        setTitle("Roulette Game");
        setLayout(new BorderLayout());

        // Betting Panel
        JPanel betAmountPanel = createBetAmountPanel();
        JPanel tablePanel = createRouletteTablePanel();
        summaryPanel = createBetSummaryPanel();

        // Layout
        add(betAmountPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.SOUTH);
        add(createSpinButton(), BorderLayout.EAST);

        // Frame settings
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Create the panel for betting amounts
     * @return JPanel
     */
    private JPanel createBetAmountPanel() {
        JPanel panel = new JPanel(); // Panel to hold betting amount buttons
        JButton[] buttons = { new JButton("$1"), new JButton("$5"), new JButton("$10"), new JButton("$20") }; // Bet amount buttons
        int[] betAmounts = { 1, 5, 10, 20 }; // Bet amounts
        balanceLabel = new JLabel("Balance: $" + player.getBalance()); // Display player balance
        JTextField customBet = new JTextField(8); // Custom bet amount field
        JLabel customBetLabel = new JLabel("Custom Bet: "); // Custom bet label
        
        // Add action listeners to bet amount buttons
        for (int i = 0; i < buttons.length; i++) {
            int amount = betAmounts[i];
            buttons[i].addActionListener(e -> selectedBetAmount[0] = amount);
            panel.add(buttons[i]);
        }
        // Add action listener to custom bet amount field
        customBet.addActionListener(e -> selectedBetAmount[0] = Integer.parseInt(customBet.getText()));
        // Add components to panel
        panel.add(customBetLabel);
        panel.add(customBet);
        panel.add(balanceLabel);
        return panel; 
    }

    /**
     * Create the panel for the roulette table
     * @return JPanel
     */
    private JPanel createRouletteTablePanel() {
        // Numbers on the roulette wheel
        int[] RedNums = new int[]{32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3};
        int[] BlackNums = new int[]{15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26};
        JPanel panel = new JPanel(new GridLayout(4, 10)); // Panel to hold roulette table buttons
        // Add number buttons
        for (int i = 0; i <= 36; i++) {
            final int number = i; 
            JButton numberButton = new JButton(String.valueOf(number)); 
            numberButton.addActionListener(e -> {
                addBet(new Bet("Number", number, selectedBetAmount[0])); // Add bet
                updateBetSummary(); 
            });
            // Set button color based on number
            numberButton.setOpaque(true);
            numberButton.setBorderPainted(false);  
            numberButton.setForeground(Color.white);
            for(int x:RedNums){
                if(number==x)
                    numberButton.setBackground(Color.red);
            }
            for(int x:BlackNums){
                if(number==x)
                    numberButton.setBackground(Color.black);
            }
            if(number == 0)
                numberButton.setBackground(Color.green);
            panel.add(numberButton);
        }
        JButton redButton = new JButton("Red"); // Red button
        redButton.setOpaque(true); 
        redButton.setBorderPainted(false);  
        redButton.setForeground(Color.white);
        redButton.setBackground(Color.red); 
        redButton.addActionListener(e -> {
            addBet(new Bet("Color", "Red", selectedBetAmount[0])); 
            updateBetSummary();
        });
        JButton blackButton = new JButton("Black"); // Black button
        blackButton.setOpaque(true);
        blackButton.setBorderPainted(false);  
        blackButton.setForeground(Color.white);
        blackButton.setBackground(Color.black); 
        blackButton.addActionListener(e -> {
            addBet(new Bet("Color", "Black", selectedBetAmount[0])); 
            updateBetSummary();
        });
        panel.add(redButton);
        panel.add(blackButton);
        JButton oddButton = new JButton("Odd"); // Odd button
        oddButton.setOpaque(true);
        oddButton.setBorderPainted(false);  
        oddButton.setForeground(Color.white);
        oddButton.setBackground(Color.gray);
        oddButton.addActionListener(e -> {
            addBet(new Bet("Odd/Even", "Odd", selectedBetAmount[0]));
            updateBetSummary();
        });
        JButton evenButton = new JButton("Even"); // Even button
        evenButton.setOpaque(true);
        evenButton.setBorderPainted(false);  
        evenButton.setForeground(Color.white);
        evenButton.setBackground(Color.gray);
        evenButton.addActionListener(e -> {
            addBet(new Bet("Odd/Even", "Even", selectedBetAmount[0]));
            updateBetSummary();
        });
        panel.add(oddButton);
        panel.add(evenButton);
        return panel;
    }

    /**
     * Create the panel for the bet summary
     * @return JPanel
     */
    private JPanel createBetSummaryPanel() {
        JPanel panel = new JPanel();
        betSummaryArea.setEditable(false);
        panel.add(new JScrollPane(betSummaryArea));
        return panel;
    }

    /**
     * Create the spin button
     * @return JButton
     */
    private JButton createSpinButton() {
        JButton spinButton = new JButton("Spin"); 
        spinButton.addActionListener(e -> {
            if (player.getBets().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please place at least one bet!");
                return;
            }
            RouletteNode result = wheel.spinBall(); // Spin the wheel
            JOptionPane.showMessageDialog(null, "Ball landed on: " + result.getNumber() + " (" + result.getColor() + ")"); // Display result
            player.evaluateBets(result); // Evaluate bets
            balanceLabel.setText("Balance: $" + player.getBalance()); // Update balance
            player.getBets().clear();
            updateBetSummary();
        });
        return spinButton;
    }

    /**
     * Update the bet summary
     */
    public void updateBetSummary() {
        StringBuilder sb = new StringBuilder("Placed Bets:\n");
        for (Bet bet : player.getBets()) {
            sb.append(bet.type).append(" - ").append(bet.value).append(" ($").append(bet.amount).append(")\n");

        }
        betSummaryArea.setText(sb.toString());
    }
    /**
     * Add a bet to the player
     * @param b
     */
    private void addBet(Bet b){
        try {
            player.placeBet(b);
        } catch (InsufficientBalanceException e) { 
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RouletteGUI::new); // Create GUI
    }
}

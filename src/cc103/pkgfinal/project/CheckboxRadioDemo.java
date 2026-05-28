package cc103.pkgfinal.project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CheckboxRadioDemo extends JFrame implements ActionListener {
    
    // Declare components
    private final JPanel mainPanel;
    private JTextField nameTextField; 
    private JTextField addressTextField;     
    private JTextField contactTextField;      
    private JCheckBox pizzaCheckBox;
    private JCheckBox burgerCheckBox;
    private JCheckBox saladCheckBox;
    private JRadioButton buy1take1RadioButton;
    private JRadioButton soloRadioButton;
    private JRadioButton studentmealRadioButton;
    private JButton submitButton;
    private JButton colorButton;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel resultLabel;
    private JLabel validationLabel;
    
    // Prices for each food item depending on meal plan
    // Pizza prices
    private static final double PIZZA_BUY1TAKE1 = 100.00;
    private static final double PIZZA_SOLO = 98.00;
    private static final double PIZZA_STUDENT = 75.00;
    
    // Burger prices
    private static final double BURGER_BUY1TAKE1 = 99.00;
    private static final double BURGER_SOLO = 55.00;
    private static final double BURGER_STUDENT = 45.00;
    
    // Salad prices
    private static final double SALAD_BUY1TAKE1 = 79.00;
    private static final double SALAD_SOLO = 45.00;
    private static final double SALAD_STUDENT = 35.00;
    
    // Constructor
    public CheckboxRadioDemo() {
      
        setTitle("Checkbox and Radio Button Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 750); 
        setLocationRelativeTo(null);
        
        // Create main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Create panels for different sections
        JPanel customerPanel = createCustomerPanel(); 
        JPanel foodPanel = createFoodPanel();
        JPanel mealplanPanel = createmealplanPanel();
        JPanel buttonPanel = createButtonPanel();
        
        // Use a middle container to stack customer info and food selections cleanly
        JPanel centerContainer = new JPanel(new BorderLayout(10, 10));
        centerContainer.add(customerPanel, BorderLayout.NORTH);
        centerContainer.add(foodPanel, BorderLayout.CENTER);
        
        // Add panels to main panel
        mainPanel.add(centerContainer, BorderLayout.NORTH);
        mainPanel.add(mealplanPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add main panel to frame
        add(mainPanel);
        
        // Make frame visible
        setVisible(true);
    }
    
    // Method to create customer input panel
    private JPanel createCustomerPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Customer Information"));
        
        JLabel lblName = new JLabel("Full Name: *");
        nameTextField = new JTextField(20);
        
        JLabel lblAddress = new JLabel("Address: *");
        addressTextField = new JTextField(20);
        
        JLabel lblContact = new JLabel("Contact Number: *");
        contactTextField = new JTextField(20);
        
        panel.add(lblName);
        panel.add(nameTextField);
        
        panel.add(lblAddress);
        panel.add(addressTextField);
        
        panel.add(lblContact);
        panel.add(contactTextField);
        
        return panel;
    }
    
    // Method to create food selection panel with categories
    private JPanel createFoodPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 50, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Select your food items"));
        
        pizzaCheckBox = new JCheckBox("Pizza");
        burgerCheckBox = new JCheckBox("Burger");
        saladCheckBox = new JCheckBox("Salad");
        
        panel.add(pizzaCheckBox);
        panel.add(burgerCheckBox);
        panel.add(saladCheckBox);
        
        return panel;
    }
    
    // Method to create meal plan selection panel with categories
    private JPanel createmealplanPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Choose your meal plan"));
        
        // Create radio buttons showing the different meal plans
        buy1take1RadioButton = new JRadioButton("Buy 1 Take 1");
        soloRadioButton = new JRadioButton("Solo");
        studentmealRadioButton = new JRadioButton("Student Meal");
        
        // Group radio buttons so only one can be selected
        ButtonGroup mealplanButtonGroup = new ButtonGroup();
        mealplanButtonGroup.add(buy1take1RadioButton);
        mealplanButtonGroup.add(soloRadioButton);
        mealplanButtonGroup.add(studentmealRadioButton);
        
        panel.add(buy1take1RadioButton);
        panel.add(soloRadioButton);
        panel.add(studentmealRadioButton);
        
        return panel;
    }
    
    // Method to create button panel with submit button and result label
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        
        submitButton = new JButton("Submit Order");
        submitButton.addActionListener(this);
        
        colorButton = new JButton("Change Background Color");
        colorButton.addActionListener(this);
        
        saveButton = new JButton("Save Order");
        saveButton.addActionListener(this);
        
        loadButton = new JButton("Load Orders");
        loadButton.addActionListener(this);
        
        resultLabel = new JLabel("Please select your order", SwingConstants.CENTER);
        resultLabel.setForeground(Color.BLUE);
        
        validationLabel = new JLabel("", SwingConstants.CENTER);
        validationLabel.setForeground(Color.RED);
        
        JPanel buttonRow = new JPanel(new FlowLayout());
        buttonRow.add(submitButton);
        buttonRow.add(colorButton);
        buttonRow.add(saveButton);
        buttonRow.add(loadButton);
        
        panel.add(buttonRow, BorderLayout.NORTH);
        panel.add(resultLabel, BorderLayout.CENTER);
        panel.add(validationLabel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // Method to validate contact number (digits only, 7-15 characters)
    private boolean validateContact(String contact) {
        if (contact == null || contact.isEmpty()) {
            return false;
        }
        if (!contact.matches("\\d+")) {
            return false;
        }
        int length = contact.length();
        return length >= 7 && length <= 15;
    }
    
    // Validate food selection
    private boolean hasFoodSelected() {
        return pizzaCheckBox.isSelected() || burgerCheckBox.isSelected() || saladCheckBox.isSelected();
    }
    
    // Validate required fields with specific error messages
    private boolean validateRequiredFields() {
        String inputName = nameTextField.getText().trim();
        String inputAddress = addressTextField.getText().trim();
        String inputContact = contactTextField.getText().trim();
        
        if (inputName.isEmpty()) {
            validationLabel.setText("Error: Full Name is required.");
            return false;
        }
        
        if (inputAddress.isEmpty()) {
            validationLabel.setText("Error: Address is required.");
            return false;
        }
        
        if (inputContact.isEmpty()) {
            validationLabel.setText("Error: Contact Number is required.");
            return false;
        }
        
        if (!validateContact(inputContact)) {
            validationLabel.setText("Error: Contact must be 7-15 digits only.");
            return false;
        }
        
        validationLabel.setText("");
        return true;
    }
    
    // Get meal plan name
    private String getMealPlanName() {
        if (buy1take1RadioButton.isSelected()) {
            return "Buy 1 Take 1";
        } else if (soloRadioButton.isSelected()) {
            return "Solo";
        } else if (studentmealRadioButton.isSelected()) {
            return "Student Meal";
        }
        return "None Selected";
    }
    
    // Get price for Pizza based on selected meal plan
    private double getPizzaPrice() {
        if (buy1take1RadioButton.isSelected()) {
            return PIZZA_BUY1TAKE1;
        } else if (soloRadioButton.isSelected()) {
            return PIZZA_SOLO;
        } else if (studentmealRadioButton.isSelected()) {
            return PIZZA_STUDENT;
        }
        return 0.00;
    }
    
    // Get price for Burger based on selected meal plan
    private double getBurgerPrice() {
        if (buy1take1RadioButton.isSelected()) {
            return BURGER_BUY1TAKE1;
        } else if (soloRadioButton.isSelected()) {
            return BURGER_SOLO;
        } else if (studentmealRadioButton.isSelected()) {
            return BURGER_STUDENT;
        }
        return 0.00;
    }
    
    // Get price for Salad based on selected meal plan
    private double getSaladPrice() {
        if (buy1take1RadioButton.isSelected()) {
            return SALAD_BUY1TAKE1;
        } else if (soloRadioButton.isSelected()) {
            return SALAD_SOLO;
        } else if (studentmealRadioButton.isSelected()) {
            return SALAD_STUDENT;
        }
        return 0.00;
    }
    
    // Format currency with proper Peso symbol
    private String formatCurrency(double amount) {
        return "\u20B1 " + String.format("%.2f", amount);
    }
    
    // Handle button click event
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            
            String inputName = nameTextField.getText().trim();
            String inputAddress = addressTextField.getText().trim();
            String inputContact = contactTextField.getText().trim();
            
            if (!validateRequiredFields()) {
                return;
            }
            
            if (!hasFoodSelected()) {
                validationLabel.setText("Error: Please select at least one food item.");
                return;
            }
            
            if (!buy1take1RadioButton.isSelected() && !soloRadioButton.isSelected() && !studentmealRadioButton.isSelected()) {
                validationLabel.setText("Error: Please select a meal plan.");
                return;
            }
            
            validationLabel.setText("");
            JOptionPane.showMessageDialog(this, "Order Submitted Successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            displayOrderSummary(inputName, inputAddress, inputContact);
            
        } else if (e.getSource() == colorButton) {
            Color color = JColorChooser.showDialog(this, "Choose Background Color", mainPanel.getBackground());
            if (color != null) {
                mainPanel.setBackground(color);
            }
        } else if (e.getSource() == saveButton) {
            saveOrderToFile();
        } else if (e.getSource() == loadButton) {
            loadOrdersFromFile();
        }
    }
    
    // Method to save order to file
    private void saveOrderToFile() {
        String customerName = nameTextField.getText().trim();
        String address = addressTextField.getText().trim();
        String contact = contactTextField.getText().trim();
        
        if (!validateRequiredFields() || !hasFoodSelected()) {
            JOptionPane.showMessageDialog(this, "Cannot save invalid order. Please fix errors first.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (!buy1take1RadioButton.isSelected() && !soloRadioButton.isSelected() && !studentmealRadioButton.isSelected()) {
            validationLabel.setText("Error: Please select a meal plan.");
            JOptionPane.showMessageDialog(this, "Cannot save - Please select a meal plan.", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true))) {
            StringBuilder orderRecord = new StringBuilder();
            orderRecord.append("=== ORDER RECORD ===\n");
            orderRecord.append("Customer: ").append(customerName).append("\n");
            orderRecord.append("Address: ").append(address).append("\n");
            orderRecord.append("Contact: ").append(contact).append("\n");
            orderRecord.append("Meal Plan: ").append(getMealPlanName()).append("\n");
            orderRecord.append("Food Items:\n");
            
            double totalCost = 0.0;
            
            // Save each food item with its price based on selected meal plan
            if (pizzaCheckBox.isSelected()) {
                double price = getPizzaPrice();
                orderRecord.append("  - Pizza: ").append(formatCurrency(price)).append("\n");
                totalCost += price;
            }
            if (burgerCheckBox.isSelected()) {
                double price = getBurgerPrice();
                orderRecord.append("  - Burger: ").append(formatCurrency(price)).append("\n");
                totalCost += price;
            }
            if (saladCheckBox.isSelected()) {
                double price = getSaladPrice();
                orderRecord.append("  - Salad: ").append(formatCurrency(price)).append("\n");
                totalCost += price;
            }
            
            orderRecord.append("Total: ").append(formatCurrency(totalCost)).append("\n");
            orderRecord.append("Timestamp: ").append(new java.util.Date()).append("\n");
            orderRecord.append("------------------------\n");
            
            writer.write(orderRecord.toString());
            JOptionPane.showMessageDialog(this, "Order saved successfully to orders.txt", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            validationLabel.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving order: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Method to load and display orders from file
    private void loadOrdersFromFile() {
        File file = new File("orders.txt");
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "No saved orders found. Please save an order first.", 
                "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        StringBuilder orders = new StringBuilder("<html><b>Saved Orders:</b><br><br>");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                orders.append(line).append("<br>");
            }
            orders.append("</html>");
            JOptionPane.showMessageDialog(this, orders.toString(), 
                "Order Records", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading orders: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Method to display the order summary with separated categories
    private void displayOrderSummary(String customerName, String address, String contact) {
        StringBuilder orderDetails = new StringBuilder("<html>");
        double totalCost = 0.0;
        String mealPlan = getMealPlanName();
        
        orderDetails.append("<b>Customer Info:</b><br>");
        orderDetails.append("Name: ").append(customerName).append("<br>");
        orderDetails.append("Address: ").append(address).append("<br>");
        orderDetails.append("Contact: ").append(contact).append("<br><br>");
        
        orderDetails.append("<b>Meal Plan:</b> ").append(mealPlan).append("<br><br>");
        
        orderDetails.append("<b>Your Order:</b><br>");
        
        // Pizza section with price based on meal plan
        if (pizzaCheckBox.isSelected()) {
            double price = getPizzaPrice();
            orderDetails.append("* Pizza: ").append(formatCurrency(price)).append("<br>");
            totalCost += price;
        }
        
        // Burger section with price based on meal plan
        if (burgerCheckBox.isSelected()) {
            double price = getBurgerPrice();
            orderDetails.append("* Burger: ").append(formatCurrency(price)).append("<br>");
            totalCost += price;
        }
        
        // Salad section with price based on meal plan
        if (saladCheckBox.isSelected()) {
            double price = getSaladPrice();
            orderDetails.append("* Salad: ").append(formatCurrency(price)).append("<br>");
            totalCost += price;
        }
        
        orderDetails.append("<br><b>Total Cost:</b> ").append(formatCurrency(totalCost));
        orderDetails.append("</html>");
        
        resultLabel.setText(orderDetails.toString());
        
        validationLabel.setText("");
    }
    
    // Main method - THIS RUNS THE PROGRAM
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CheckboxRadioDemo::new);
    }
}
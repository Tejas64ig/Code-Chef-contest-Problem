import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 class HomeBudgetPlanner extends JFrame implements ActionListener {

    JLabel budgetLabel;
    JTextField budgetField;
    JButton setBudgetButton;

    JLabel itemNameLabel;
    JComboBox<String> itemNameCombo;
    JLabel shopLabel;
    JTextField shopField;
    JLabel qtyLabel;
    JTextField qtyField;
    JLabel priceLabel;
    JTextField priceField;
    JLabel priorityLabel;
    JTextField priorityField;

    JButton addItemButton, calculateButton, optimizeButton, clearButton;

    JTable itemsTable;
    DefaultTableModel tableModel;

    JLabel totalCostLabel;

    private ArrayList<Item> items;
    private double totalBudget = 0;
    private boolean budgetSet = false;

    private final String[] applianceNames = {"Toaster", "Blender", "Microwave Oven", "Electric Kettle", "Coffee Maker",
            "Air Fryer", "Vacuum Cleaner", "Iron", "Rice Cooker"};

    public HomeBudgetPlanner() {
        setTitle("Home Budget Planner");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        items = new ArrayList<>();

        budgetLabel = new JLabel("Total Budget (₹):");
        budgetLabel.setBounds(20, 20, 150, 30);
        add(budgetLabel);

        budgetField = new JTextField();
        budgetField.setBounds(180, 20, 150, 30);
        add(budgetField);

        setBudgetButton = new JButton("Set Budget");
        setBudgetButton.setBounds(350, 20, 120, 30);
        setBudgetButton.addActionListener(this);
        add(setBudgetButton);

        itemNameLabel = new JLabel("Item:");
        itemNameLabel.setBounds(20, 70, 60, 25);
        add(itemNameLabel);

        itemNameCombo = new JComboBox<>(applianceNames);
        itemNameCombo.setBounds(70, 70, 150, 25);
        itemNameCombo.addActionListener(e -> autofillItemDetails());
        add(itemNameCombo);

        shopLabel = new JLabel("Shop:");
        shopLabel.setBounds(230, 70, 40, 25);
        add(shopLabel);

        shopField = new JTextField();
        shopField.setBounds(280, 70, 150, 25);
        add(shopField);

        qtyLabel = new JLabel("Qty:");
        qtyLabel.setBounds(440, 70, 30, 25);
        add(qtyLabel);

        qtyField = new JTextField();
        qtyField.setBounds(480, 70, 50, 25);
        add(qtyField);

        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(540, 70, 40, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(590, 70, 70, 25);
        add(priceField);

        priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(670, 70, 60, 25);
        add(priorityLabel);

        priorityField = new JTextField();
        priorityField.setBounds(730, 70, 40, 25);
        add(priorityField);

        addItemButton = new JButton("Add Item");
        addItemButton.setBounds(780, 70, 100, 30);
        addItemButton.addActionListener(this);
        add(addItemButton);

        calculateButton = new JButton("Calculate Total");
        calculateButton.setBounds(20, 110, 160, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);

        optimizeButton = new JButton("Optimize Budget");
        optimizeButton.setBounds(200, 110, 160, 30);
        optimizeButton.addActionListener(this);
        add(optimizeButton);

        clearButton = new JButton("Clear All");
        clearButton.setBounds(380, 110, 120, 30);
        clearButton.addActionListener(this);
        add(clearButton);

        tableModel = new DefaultTableModel(new String[]{"Item", "Shop", "Qty", "Price", "Priority", "Cost"}, 0);
        itemsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemsTable);
        scrollPane.setBounds(20, 150, 860, 350);
        add(scrollPane);

        totalCostLabel = new JLabel("Total Cost: ₹0.00");
        totalCostLabel.setBounds(700, 510, 180, 30);
        totalCostLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(totalCostLabel);

        setVisible(true);
    }

    private void autofillItemDetails() {
        String selected = (String) itemNameCombo.getSelectedItem();
        switch (selected) {
            case "Toaster":
                shopField.setText("Local Market");
                qtyField.setText("1");
                priceField.setText("1500");
                priorityField.setText("7");
                break;
            case "Blender":
                shopField.setText("Online");
                qtyField.setText("1");
                priceField.setText("2500");
                priorityField.setText("5");
                break;
            case "Microwave Oven":
                shopField.setText("Online");
                qtyField.setText("1");
                priceField.setText("6000");
                priorityField.setText("3");
                break;
            case "Electric Kettle":
                shopField.setText("Local Market");
                qtyField.setText("1");
                priceField.setText("1200");
                priorityField.setText("6");
                break;
            case "Coffee Maker":
                shopField.setText("Online");
                qtyField.setText("1");
                priceField.setText("3000");
                priorityField.setText("5");
                break;
            case "Air Fryer":
                shopField.setText("Online");
                qtyField.setText("1");
                priceField.setText("4500");
                priorityField.setText("4");
                break;
            case "Vacuum Cleaner":
                shopField.setText("Online");
                qtyField.setText("1");
                priceField.setText("5000");
                priorityField.setText("2");
                break;
            case "Iron":
                shopField.setText("Local Market");
                qtyField.setText("1");
                priceField.setText("1000");
                priorityField.setText("8");
                break;
            case "Rice Cooker":
                shopField.setText("Local Market");
                qtyField.setText("1");
                priceField.setText("2000");
                priorityField.setText("5");
                break;
            default:
                shopField.setText("");
                qtyField.setText("");
                priceField.setText("");
                priorityField.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == setBudgetButton) {
            try {
                totalBudget = Double.parseDouble(budgetField.getText());
                if (totalBudget <= 0) throw new NumberFormatException();
                budgetSet = true;
                budgetField.setEnabled(false);
                setBudgetButton.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Budget set: ₹" + totalBudget);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid budget input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == addItemButton) {
            addItem();
        } else if (source == calculateButton) {
            calculateTotal(true);
        } else if (source == optimizeButton) {
            optimizeBudget();
        } else if (source == clearButton) {
            clearAll();
        }
    }

    private void addItem() {
        try {
            String item = (String) itemNameCombo.getSelectedItem();
            String shop = shopField.getText().trim();
            int qty = Integer.parseInt(qtyField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());
            int priority = Integer.parseInt(priorityField.getText().trim());

            if (shop.isEmpty() || qty <= 0 || price <= 0 || priority < 1 || priority > 10) {
                JOptionPane.showMessageDialog(this, "Please enter valid details with priority between 1 and 10.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Check for duplicate priority
            for (Item i : items) {
                if (i.priority == priority) {
                    JOptionPane.showMessageDialog(this, "Priority " + priority + " already exists. Please assign unique priorities.", "Duplicate Priority", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            double cost = qty * price;
            items.add(new Item(item, shop, qty, price, priority, cost));
            updateTable();
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format entered.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        double total = 0;
        for (Item i : items) {
            tableModel.addRow(new Object[]{i.name, i.shop, i.qty, i.price, i.priority, i.cost});
            total += i.cost;
        }
        totalCostLabel.setText(String.format("Total Cost: ₹%.2f", total));
    }

    private void clearInputFields() {
        shopField.setText("");
        qtyField.setText("");
        priceField.setText("");
        priorityField.setText("");
    }

    private void calculateTotal(boolean showMessage) {
        double total = 0;
        for (Item i : items) total += i.cost;
        totalCostLabel.setText(String.format("Total Cost: ₹%.2f", total));
        if (showMessage) {
            JOptionPane.showMessageDialog(this, String.format("Total Cost: ₹%.2f", total));
        }
    }

    private void optimizeBudget() {
        if (!budgetSet) {
            JOptionPane.showMessageDialog(this, "Please set the budget first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double total = 0;
        for (Item i : items) total += i.cost;

        if (total <= totalBudget) {
            JOptionPane.showMessageDialog(this, "You are already within the budget.");
            return;
        }

        // Sort by priority descending (lowest priority last)
        items.sort((a, b) -> b.priority - a.priority);

        for (int i = items.size() - 1; i >= 0 && total > totalBudget; i--) {
            Item item = items.get(i);

            int minQty = (item.priority == 9 || item.priority == 10) ? 3 : 0;

            while (item.qty > minQty && total > totalBudget) {
                item.qty--;
                item.cost = item.qty * item.price;
                total = 0;
                for (Item it : items) total += it.cost;

                if (item.qty == 0 && minQty == 0) {
                    items.remove(i);
                    break;
                }
            }
        }
        updateTable();
        JOptionPane.showMessageDialog(this, String.format("Budget optimized. New Total Cost: ₹%.2f", total));
    }

    private void clearAll() {
        items.clear();
        totalBudget = 0;
        budgetSet = false;
        budgetField.setEnabled(true);
        setBudgetButton.setEnabled(true);
        updateTable();
        clearInputFields();
        totalCostLabel.setText("Total Cost: ₹0.00");
    }

    class Item {
        String name, shop;
        int qty, priority;
        double price, cost;

        Item(String name, String shop, int qty, double price, int priority, double cost) {
            this.name = name;
            this.shop = shop;
            this.qty = qty;
            this.price = price;
            this.priority = priority;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeBudgetPlanner::new);
    }
}

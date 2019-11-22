package computerStore.Order;

import computerStore.Inventroy.GlobalInventoryList;
import computerStore.ItemTable.ItemTableModelRenderer;

import javax.swing.*;

public class AddItemToOrderView extends JFrame {
    private JPanel main;
    private JTextField amount;
    private JButton saveButton;
    private JTable table1;
    private JTextField nameField;
    private JTextField catalogNumberField;
    private JTextField totalAmountField;

    public JTextField getAmount() {
        return amount;
    }

    JButton getSaveButton() {
        return saveButton;
    }

    JTable getTable1() {
        return table1;
    }

    JTextField getNameField() {
        return nameField;
    }

    JTextField getCatalogNumberField() {
        return catalogNumberField;
    }

    JTextField getTotalAmountField() {
        return totalAmountField;
    }

    AddItemToOrderView() {
        this.setVisible(true);
        this.setBounds(0, 0, 500, 500);
        setContentPane(main);
        setLocationRelativeTo(null);
        this.setTitle("Order Item");
        table1.setModel(GlobalInventoryList.getInstance());
        table1.setDefaultRenderer(Object.class, new ItemTableModelRenderer());
        table1.setAutoCreateRowSorter(true);
    }

}

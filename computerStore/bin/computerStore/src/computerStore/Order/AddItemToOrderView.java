package computerStore.Order;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import computerStore.Inventroy.InventoryModel;

import javax.swing.*;
import java.awt.*;

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

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTable getTable1() {
        return table1;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getCatalogNumberField() {
        return catalogNumberField;
    }

    public JTextField getTotalAmountField() {
        return totalAmountField;
    }

    public AddItemToOrderView() {
        this.setVisible(true);
        this.setBounds(0, 0, 500, 500);
        setContentPane(main);
        setLocationRelativeTo(null);
        this.setTitle("Order Item");
        table1.setModel(InventoryModel.getInstance());

    }

}

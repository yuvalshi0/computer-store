package computerStore.NewItem;

import computerStore.db.ItemModel;

import javax.swing.*;
import java.awt.*;

public class newItemView extends JFrame {
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel mainFrame;
    private JTextField amount;
    private JTextField catalogNumber;
    private JTextField price;
    private JTextField nameText;
    private JLabel error;
    private JLabel newItemTitle;

    JButton getSaveButton() {
        return saveButton;
    }

    JTextField getAmount() {
        return amount;
    }

    JTextField getCatalogNumber() {
        return catalogNumber;
    }

    JTextField getPrice() {
        return price;
    }

    JTextField getNameText() {
        return nameText;
    }

    JButton getCancelButton() {
        return cancelButton;
    }

    newItemView() {
        setBounds(0, 0, 500, 250);
        setContentPane(mainFrame);
        setLocationRelativeTo(null);
        this.setTitle("New Item");
        error.setVisible(false);
        newItemTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));

    }

    void error() {
        error.setVisible(true);
    }

}

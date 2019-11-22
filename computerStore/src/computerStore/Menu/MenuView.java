package computerStore.Menu;

import javax.swing.*;

import computerStore.Order.GlobalOrderList;

import java.awt.*;

public class MenuView extends JFrame {
    private JPanel main;
    private JButton newOrder;
    private JButton inventoryManagementButton;
    private JTable table1;

    public JTable getOrderTable() {
        return table1;
    }

    private MenuController controller = new MenuController(this);


    public MenuView() {
        this.setBounds(0, 0, 500, 600);
        this.setLocationRelativeTo(null);
        this.setContentPane(main);
        this.setTitle("Welcome");
        table1.setModel(GlobalOrderList.getInstance());
        table1.setDefaultRenderer(Object.class,new MenuTableRenderer());
        table1.setFont(new Font("Arial", Font.BOLD, 15));
    }

    public JButton getNewOrder() {
        return newOrder;
    }

    public JButton getInventoryManagementButton() {
        return inventoryManagementButton;
    }

}

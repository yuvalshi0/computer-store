package computerStore.Order;
import javax.swing.*;
import java.awt.*;

public class OrderView extends JFrame {

    private JPanel orderBody;
    private JTextField firstNameField;
    private JPanel cutomerDetails;
    private JTextField lastNameField;
    private JTextField IDField;
    private JPanel OrderItem;
    private JTable orderItemTable;
    private JButton newOrderItem;
    private JPanel OrderSummary;
    private JTextField totalPriceField;
    private JLabel totalPrice;
    private JComboBox payingMethod;
    private JButton saveButton;
    private JButton payButton;
    private JButton cancelButton;
    private JLabel isPayedLabel;
    private OrderController controller = new OrderController(this);

    //TODO: make private

    public OrderView() {
        this.setBounds(0, 0, 501, 850);
        setContentPane(orderBody);
        setLocationRelativeTo(null);
        this.setTitle("Order");
        this.payButton.setBackground(Color.gray);
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getIDField() {
        return IDField;
    }

    public JTable getOrderItemTable() {
        return orderItemTable;
    }

    public JButton getNewOrderItem() {
        return newOrderItem;
    }

    public JTextField getTotalPriceField() {
        return totalPriceField;
    }

    public JComboBox getPayingMethod() {
        return payingMethod;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getPayButton() {
        return payButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JPanel getCutomerDetails() {
        return cutomerDetails;
    }

    public JPanel getOrderItem() {
        return OrderItem;
    }

    public JPanel getOrderSummary() {
        return OrderSummary;
    }

    public JLabel getIsPayedLabel() {
        return isPayedLabel;
    }

}

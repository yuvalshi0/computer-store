package computerStore.Order;

import computerStore.Inventroy.InventoryModel;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class OrderController {
    OrderView view;
    OrderModel model;
    private boolean isActive = true;
    private boolean isNew = true;

    //TODO: Split to make it more readable
    OrderController(OrderView view) {
        this.view = view;
        this.model = new OrderModel();
        this.view.getOrderItemTable().setModel(this.model.itemOrderList);
        this.view.getSaveButton().addActionListener(e -> saveNewOrder());
        this.view.getNewOrderItem().addActionListener(e -> openNewItemView());
        this.view.getCancelButton().addActionListener(e-> cancel());
        this.view.getOrderItemTable().getModel().addTableModelListener(e -> calcTotalPrice());
        this.view.getPayButton().addActionListener(e-> payOrder());
        this.view.getOrderItemTable().addMouseListener(new RowEdit());
        this.model.setStatus(OrderModel.Status.UNPAID);


    }

    private void saveNewOrder() {
        //TODO: add try catch
            this.model.setFirstName(view.getFirstNameField().getText());
            this.model.setLastName(view.getLastNameField().getText());
            this.model.setId(Double.parseDouble(view.getIDField().getText()));
            this.model.setTotalPrice(Double.parseDouble(this.view.getTotalPriceField().getText()));
            this.model.setPayingMethod(setPayingMethod(this.view.getPayingMethod().getSelectedIndex()));
            this.view.getPayButton().setBackground(Color.lightGray);
            this.view.setEnabled(true);
        if(isNew) {
            GlobalOrderList.getInstance().getOrderList().add(this.model);
            isNew = false;
        }

        GlobalOrderList.getInstance().refresh();
    }

    private OrderModel.PayingMethod setPayingMethod(int index) {
        if (index == 0) {
            return OrderModel.PayingMethod.CREDIT;
        }
        else return OrderModel.PayingMethod.CASH;
    }

    private void calcTotalPrice() {
        double sum = this.model.calcTotalAmount();
        this.view.getTotalPriceField().setText(Double.toString(sum));
    }

    private void openNewItemView() {
        new AddItemToOrderController(this.model);
    }

    private class RowEdit extends MouseAdapter {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            super.mouseClicked(e);
            if (e.getClickCount() == 2 && isActive) {
                OrderItem temp = model.itemOrderList.getOrderList().get(
                (view.getOrderItemTable().getSelectedRow()));
                new AddItemToOrderController(model,temp,view.getOrderItemTable().getSelectedRow());
            }
        }
    }

    //TODO: also, make more readalbe
    private void payOrder() {
        Component[] comps = this.view.getCutomerDetails().getComponents();
        for (Component comp : comps) {
            comp.setEnabled(false);
        }
        Component[] comps2 = this.view.getOrderSummary().getComponents();
        for (Component comp : comps2) {
            comp.setEnabled(false);
        }
        Component[] comps3 = this.view.getOrderItem().getComponents();
        for (Component comp : comps3) {
            comp.setEnabled(false);
        }
        this.view.getOrderItemTable().setDefaultRenderer(Object.class,new OrderItemDisabler());
        this.view.getOrderItemTable().getTableHeader().setDefaultRenderer(new OrderItemDisabler());
        this.model.setStatus(OrderModel.Status.PAID);
        this.model.removeOrderItems();
        this.view.getOrderItemTable().setOpaque(false);
        this.isActive = false;
        this.view.getIsPayedLabel().setVisible(true);
        this.view.getSaveButton().setEnabled(false);
        this.view.getPayButton().setEnabled(false);
        this.view.getCancelButton().setText("Back");
        GlobalOrderList.getInstance().refresh();
        InventoryModel.getInstance().refresh();
    }

    private void cancel() {
        this.view.dispose();
    }
}

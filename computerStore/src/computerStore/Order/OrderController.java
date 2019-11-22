package computerStore.Order;

import computerStore.Inventroy.GlobalInventoryList;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class OrderController {
    private OrderView view;
    private OrderModel model;
    private boolean isActive = true;
    private boolean isNew = true;

    public OrderController() {
        this.model = new OrderModel();
        this.model.setStatus(OrderModel.Status.UNPAID);
        initView();

        //for some reason this doesnt work in the initveiew function
        this.view.getOrderItemTable().getModel().addTableModelListener(e -> calcTotalPrice());
    }

    public OrderController(OrderModel temp) {
        this.model = temp;
        initView();
        initFields();
    }

    private void initFields() {
        isNew = false;
        this.view.getPayButton().setEnabled(true);
        view.getFirstNameField().setText(model.getFirstName());
        view.getLastNameField().setText(model.getLastName());
        view.getIDField().setText(Long.toString(model.getId()));
        view.getTotalPriceField().setText(Long.toString(model.getTotalPrice()));
        view.getPayingMethod().setSelectedIndex(model.getPayingMethod());
        if (model.getStatus().equals("Paid")) {
            payRender();
        }
    }

    private void initView() {
        this.view = new OrderView();
        this.view.setVisible(true);
        this.view.getSaveButton().addActionListener(e -> saveNewOrder());
        this.view.getNewOrderItem().addActionListener(e -> openNewItemView());
        this.view.getCancelButton().addActionListener(e -> cancel());
        this.view.getPayButton().addActionListener(e -> payOrder());
        this.view.getOrderItemTable().addMouseListener(new RowEdit());
        this.view.getOrderItemTable().setModel(this.model.itemOrderList);
        if (isNew) {
            this.view.getPayButton().setEnabled(false);
        }
        calcTotalPrice();
    }

    private void saveNewOrder() {
        try {
            this.model.setFirstName(view.getFirstNameField().getText());
            this.model.setLastName(view.getLastNameField().getText());
            this.model.setId(Long.parseLong(view.getIDField().getText()));
            this.model.setTotalPrice(Long.parseLong(this.view.getTotalPriceField().getText()));
            this.model.setPayingMethod(setPayingMethod(this.view.getPayingMethod().getSelectedIndex()));
            this.view.getPayButton().setBackground(Color.lightGray);
            this.view.getPayButton().setEnabled(true);
            if (isNew) {
                GlobalOrderList.getInstance().getOrderList().add(this.model);
                isNew = false;
            }
            GlobalOrderList.getInstance().refresh();
        } catch (NumberFormatException err) {
            //TODO
        }
    }

    private OrderModel.PayingMethod setPayingMethod(int index) {
        if (index == 0) {
            return OrderModel.PayingMethod.CREDIT;
        } else return OrderModel.PayingMethod.CASH;
    }

    private void calcTotalPrice() {
        long sum = this.model.calcTotalAmount();
        this.view.getTotalPriceField().setText(Long.toString(sum));
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
                new AddItemToOrderController(model, temp, view.getOrderItemTable().getSelectedRow());
            }
        }
    }

    private void payOrder() {
        payRender();
        this.model.setStatus(OrderModel.Status.PAID);
        this.model.removeOrderItems();
        this.isActive = false;
        this.view.getCancelButton().setText("Back");
        GlobalInventoryList.getInstance().refresh();
        GlobalOrderList.getInstance().refresh();
    }

    private void payRender() {
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
        this.view.getOrderItemTable().setDefaultRenderer(Object.class, new OrderItemDisabler());
        this.view.getOrderItemTable().getTableHeader().setDefaultRenderer(new OrderItemDisabler());
        this.view.getOrderItemTable().setOpaque(false);
        this.isActive = false;
        this.view.getIsPayedLabel().setVisible(true);
        this.view.getSaveButton().setEnabled(false);
        this.view.getPayButton().setEnabled(false);
        this.view.getCancelButton().setText("Back");
    }

    private void cancel() {
        this.view.dispose();
    }
}

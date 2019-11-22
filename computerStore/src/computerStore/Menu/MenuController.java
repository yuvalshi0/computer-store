package computerStore.Menu;

import computerStore.Inventroy.InventoryView;
import computerStore.Order.GlobalOrderList;
import computerStore.Order.OrderController;
import computerStore.Order.OrderModel;

import java.awt.event.MouseAdapter;

class MenuController {
    private MenuView view;

    MenuController(MenuView view) {
        this.view = view;
        this.view.getNewOrder().addActionListener(e -> newOrder());
        this.view.getInventoryManagementButton().addActionListener(e -> openInventory());
        this.view.getOrderTable().addMouseListener(new RowEdit());
    }

    private class RowEdit extends MouseAdapter {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            super.mouseClicked(e);
            if (e.getClickCount() == 2) {
                OrderModel temp = GlobalOrderList.getInstance().getOrderList().get(
                        (view.getOrderTable().getSelectedRow()));
                new OrderController(temp);
            }
        }
    }

    private void newOrder() {
        new OrderController();
    }

    private void openInventory() {
        new InventoryView().setVisible(true);
    }
}

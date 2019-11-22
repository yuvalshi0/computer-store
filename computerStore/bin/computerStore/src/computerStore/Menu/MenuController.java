package computerStore.Menu;

import computerStore.Inventroy.InventoryView;
import computerStore.Order.OrderView;

public class MenuController {
    private MenuView view;

    public MenuController(MenuView view) {
        this.view = view;
        this.view.getNewOrder().addActionListener(e->newOrder());
        this.view.getInventoryManagementButton().addActionListener(e->openInventory());
    }

    private void newOrder() {
       new OrderView().setVisible(true);
    }

    private void openInventory() {
        new InventoryView().setVisible(true);
    }
}

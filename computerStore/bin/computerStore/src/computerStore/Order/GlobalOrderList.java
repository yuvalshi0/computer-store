package computerStore.Order;

import computerStore.Menu.MenuTableModel;

public class GlobalOrderList {
    private static MenuTableModel ourInstance = new MenuTableModel();

    public static MenuTableModel getInstance() {
        return ourInstance;
    }

    private GlobalOrderList() {
    }
}

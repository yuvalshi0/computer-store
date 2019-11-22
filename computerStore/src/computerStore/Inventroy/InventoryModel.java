package computerStore.Inventroy;

import computerStore.ItemTable.ItemTableModel;

//TODO consider deleting, and adding the new item view to inherite the model from inventroy model
public class InventoryModel {
    private static ItemTableModel instance;

    public static ItemTableModel getInstance() {
        if(instance == null) {
            instance = new ItemTableModel();
        }
        return instance;
    }
}

package computerStore.NewItem;

import computerStore.Inventroy.GlobalInventoryList;
import computerStore.db.ItemModel;

public class newItemController {
    private newItemView view;
    private ItemModel model;
    private final int index;

    public newItemController() {
        initView();
        this.index = 0;
        this.view.getSaveButton().addActionListener(e -> addNewItem());
    }

    //For edit
    public newItemController(ItemModel item, int index) {
        this.model = item;
        initView();
        initFields();
        this.index = index;
    }

    private void initView() {
        this.view = new newItemView();
        this.view.setVisible(true);
        this.view.getCancelButton().addActionListener(e -> exit());

    }

    private void initFields() {
        this.view.getAmount().setText(Integer.toString(this.model.getAmountInStock()));
        this.view.getCatalogNumber().setText(Integer.toString(this.model.getCatalogNumber()));
        this.view.getCatalogNumber().setEnabled(false);
        this.view.getPrice().setText(Integer.toString(this.model.getPriceForUnit()));
        this.view.getNameText().setText(this.model.getName());
        this.view.getSaveButton().addActionListener(e -> editItem());
    }

    private void exit() {
        view.dispose();
    }

    private void addNewItem() {
        if (GlobalInventoryList.getInstance().isCatalogNumberExists(Integer.parseInt(view.getCatalogNumber().getText()))) {
            view.error();
        } else {
            try {
                GlobalInventoryList.getInstance().addNewItem(
                        view.getNameText().getText(),
                        Integer.parseInt(view.getPrice().getText()),
                        Integer.parseInt(view.getCatalogNumber().getText()),
                        Integer.parseInt(view.getAmount().getText())
                );
                GlobalInventoryList.getInstance().refresh();
                view.dispose();
            } catch (NumberFormatException e) {
                view.error();
            }
        }
    }

    private void editItem() {
        try {
            GlobalInventoryList.getInstance().editSelectedItem(
                    view.getNameText().getText(),
                    Integer.parseInt(view.getPrice().getText()),
                    Integer.parseInt(view.getCatalogNumber().getText()),
                    Integer.parseInt(view.getAmount().getText()),
                    index
            );
            GlobalInventoryList.getInstance().refresh();
            view.dispose();
        } catch (NumberFormatException e) {
            view.error();
        }
    }

}

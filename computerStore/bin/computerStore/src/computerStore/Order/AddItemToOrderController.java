package computerStore.Order;

import computerStore.Inventroy.InventoryModel;
import computerStore.db.ItemListModel;
import computerStore.db.ItemModel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;


public class AddItemToOrderController {
    AddItemToOrderView view;
    OrderModel model;
    private int index;


    //constructor for edited form
    public AddItemToOrderController(OrderModel model, OrderItem temp, int index) {
        this.view = new AddItemToOrderView();
        initField(temp);
        this.index = index;
        this.model = model;
        this.view.getTable1().addMouseListener(new RowSelected());
        this.view.getAmount().getDocument().addDocumentListener(new AmountChanged());
        this.view.getSaveButton().addActionListener(e -> editSavedItem());

    }

    public AddItemToOrderController(OrderModel model) {
        this.model = model;
        this.view = new AddItemToOrderView();
        this.view.getTable1().addMouseListener(new RowSelected());
        this.view.getAmount().getDocument().addDocumentListener(new AmountChanged());
        this.view.getSaveButton().addActionListener(e -> saveNewItem());
    }

    public void initField(OrderItem temp) {
        this.index = 0;
        this.view.getCatalogNumberField().setText(Integer.toString(temp.getCatalogNumber()));
        this.view.getNameField().setText(temp.getName());
        this.view.getTotalAmountField().setText(Integer.toString(temp.getTotalPrice()));
        this.view.getAmount().setText(Integer.toString(temp.getOrderAmount()));
        int index = InventoryModel.getInstance().searchByCatalogNumber(temp.getCatalogNumber());
        this.view.getTable1().setRowSelectionInterval(index,index);
    }

    //TODO: add try catch
    private class RowSelected extends MouseAdapter {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            super.mouseClicked(e);
            ItemModel temp = ItemListModel.getInstance().getItemList().get(view.getTable1().getSelectedRow());
            view.getNameField().setText(temp.getName());
            view.getNameField().setText(temp.getName());
            view.getCatalogNumberField().setText(Integer.toString(temp.getCatalogNumber()));
            try {
                view.getTotalAmountField().setText(
                        Integer.toString(Integer.parseInt(view.getAmount().getText()) * (ItemListModel.getInstance().getItemList().get(view.getTable1().getSelectedRow()).getPriceForUnit()))
                );
            } catch (NumberFormatException err) {
                view.getTotalAmountField().setText("0");
            }
        }
    }

    public class AmountChanged implements DocumentListener {
//TODO: make more clear (Add to a function) + add try cache
        public void insertUpdate(DocumentEvent e) {
            if(Integer.parseInt(view.getAmount().getText()) > ItemListModel.getInstance().getItemList().get(view.getTable1().getSelectedRow()).getAmountInStock()) {
                view.getTotalAmountField().setText("Not enough in stock!");
                view.getSaveButton().setEnabled(false);
            } else {
                view.getTotalAmountField().setText(
                        Integer.toString(Integer.parseInt(view.getAmount().getText()) * (ItemListModel.getInstance().getItemList().get(view.getTable1().getSelectedRow()).getPriceForUnit()))

                );
                view.getSaveButton().setEnabled(true);
            }
        }

        public void removeUpdate(DocumentEvent e) {
            view.getTotalAmountField().setText("0");
        }
        public void changedUpdate(DocumentEvent e) {
        }

    }
    //TODO: make more beaufiful
    public void saveNewItem() {
        ItemModel temp = ItemListModel.getInstance().getItemList().get(view.getTable1().getSelectedRow());
        OrderItem temp2 = new OrderItem(temp.getCatalogNumber(), temp.getPriceForUnit(),temp.getAmountInStock(), temp.getName(),Integer.parseInt(view.getAmount().getText()));
        this.model.itemOrderList.addNewOrderItem(temp2);
        this.model.itemOrderList.refresh();
        this.view.dispose();
    }

    public void editSavedItem() {
        ItemModel temp = ItemListModel.getInstance().getItemList().get(view.getTable1().getSelectedRow());
        OrderItem temp2 = new OrderItem(temp.getCatalogNumber(), temp.getPriceForUnit(),temp.getAmountInStock(), temp.getName(),Integer.parseInt(view.getAmount().getText()));
        this.model.itemOrderList.editSelectedItem(temp2,index);
        this.model.itemOrderList.refresh();
        this.view.dispose();
    }
}

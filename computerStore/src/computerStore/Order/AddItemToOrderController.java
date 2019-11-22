package computerStore.Order;

import computerStore.Inventroy.GlobalInventoryList;
import computerStore.db.ItemListModel;
import computerStore.db.ItemModel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.MouseAdapter;


class AddItemToOrderController {
    private AddItemToOrderView view;
    private OrderModel model;
    private int index;


    //constructor for edited form
     AddItemToOrderController(OrderModel model, OrderItem temp, int index) {
        this.view = new AddItemToOrderView();
        initField(temp);
        this.index = index;
        this.model = model;
        this.view.getTable1().addMouseListener(new RowSelected());
        this.view.getAmount().getDocument().addDocumentListener(new AmountChanged());
        this.view.getSaveButton().addActionListener(e -> editSavedItem());

    }

     AddItemToOrderController(OrderModel model) {
        this.model = model;
        this.view = new AddItemToOrderView();
        this.view.getTable1().addMouseListener(new RowSelected());
        this.view.getAmount().getDocument().addDocumentListener(new AmountChanged());
        this.view.getSaveButton().addActionListener(e -> saveNewItem());
    }

     private void initField(OrderItem temp) {
        this.index = 0;
        this.view.getCatalogNumberField().setText(Integer.toString(temp.getCatalogNumber()));
        this.view.getNameField().setText(temp.getName());
        this.view.getTotalAmountField().setText(Integer.toString(temp.getTotalPrice()));
        this.view.getAmount().setText(Integer.toString(temp.getOrderAmount()));
        int index = GlobalInventoryList.getInstance().searchByCatalogNumber(temp.getCatalogNumber());
        this.view.getTable1().setRowSelectionInterval(index,index);
    }

    private class RowSelected extends MouseAdapter {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            super.mouseClicked(e);
            int index = view.getTable1().getSelectedRow();
            ItemModel itemSelected = ItemListModel.getInstance().getItemList().get(index);
            view.getNameField().setText(itemSelected.getName());
            view.getNameField().setText(itemSelected.getName());
            view.getCatalogNumberField().setText(Integer.toString(itemSelected.getCatalogNumber()));

            try {
                int amountInput = Integer.parseInt(view.getAmount().getText());
                int priceCalc = model.calcPrice(itemSelected, amountInput);
                int amountInStock = ItemListModel.getInstance().getItemList().get(view.getTable1().getSelectedRow()).getAmountInStock();

                if (amountInput > amountInStock) {
                    view.getTotalAmountField().setText("Not enough in stock!");
                    view.getSaveButton().setEnabled(false);
                } else {

                    String price = Integer.toString(priceCalc);
                    view.getTotalAmountField().setText(price);
                    view.getSaveButton().setEnabled(true);
                }
            } catch (NumberFormatException err) {
                view.getTotalAmountField().setText("0");
            }
        }
    }

    //TODO: split
    public class AmountChanged implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {

            int amountInput = Integer.parseInt(view.getAmount().getText());
            int amountInStock = ItemListModel.getInstance().getItemList().get(view.getTable1().getSelectedRow()).getAmountInStock();
            int index = view.getTable1().getSelectedRow();
            ItemModel itemSelected = ItemListModel.getInstance().getItemList().get(index);
            int priceCalc = model.calcPrice(itemSelected, amountInput);

            try {
                if (amountInput > amountInStock) {
                    view.getTotalAmountField().setText("Not enough in stock!");
                    view.getSaveButton().setEnabled(false);
                } else {

                    String price = Integer.toString(priceCalc);
                    view.getTotalAmountField().setText(price);
                    view.getSaveButton().setEnabled(true);
                }
            } catch (NumberFormatException err) {
                view.getTotalAmountField().setText("0");
            }
        }


        public void removeUpdate(DocumentEvent e) {
            view.getTotalAmountField().setText("0");
        }

        public void changedUpdate(DocumentEvent e) {
        }

    }

    private void saveNewItem() {
        ItemModel itemSelected = ItemListModel.getInstance().getItemList().get(view.getTable1().getSelectedRow());
        OrderItem temp = new OrderItem(
                itemSelected.getCatalogNumber(),
                itemSelected.getPriceForUnit(),
                itemSelected.getAmountInStock(),
                itemSelected.getName(),
                Integer.parseInt(view.getAmount().getText()));

        this.model.itemOrderList.addNewOrderItem(temp);
        this.model.itemOrderList.refresh();
        this.view.dispose();
    }

    private void editSavedItem() {
        ItemModel itemSelected = ItemListModel.getInstance().getItemList().get(view.getTable1().getSelectedRow());
        OrderItem temp = new OrderItem(
                itemSelected.getCatalogNumber(),
                itemSelected.getPriceForUnit(),
                itemSelected.getAmountInStock(),
                itemSelected.getName(),
                Integer.parseInt(view.getAmount().getText()));

        this.model.itemOrderList.editSelectedItem(temp,index);
        this.model.itemOrderList.refresh();
        this.view.dispose();
    }
}

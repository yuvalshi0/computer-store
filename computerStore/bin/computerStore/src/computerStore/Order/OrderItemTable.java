package computerStore.Order;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class OrderItemTable extends AbstractTableModel {
    private ArrayList<OrderItem> orderList = new ArrayList<>();
    private String[] columns;

    OrderItemTable() {
        super();
        columns = new String[]{ "Catalog Number", "Name","Amount" , "Total Price"};
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return this.orderList.size();
    }

    public ArrayList<OrderItem> getOrderList() {
        return orderList;
    }

    @Override
    public Object getValueAt(int row, int col) {
        OrderItem temp = orderList.get(row);
        switch(col) {
            case(0): return temp.getCatalogNumber();
            case(1): return temp.getName();
            case(2): return temp.getOrderAmount();
            case(3): return temp.getTotalPrice();
            default: return null;
        }
    }

    public void addNewOrderItem(OrderItem temp) {
        this.orderList.add(temp);
    }

    public void editSelectedItem(OrderItem temp,int index) {
        this.orderList.set(index,temp);
    }

    @Override
    public String getColumnName(int col) {
        return columns[col] ;
    }

    public void refresh() {
        this.fireTableDataChanged();
    }

}


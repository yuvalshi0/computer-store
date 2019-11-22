package computerStore.Menu;

import javax.swing.table.AbstractTableModel;

import computerStore.Order.OrderModel;

import java.util.ArrayList;

public class MenuTableModel extends AbstractTableModel {
    private ArrayList<OrderModel> orderList = new ArrayList<>();
    private String[] columns;

    public MenuTableModel() {
        super();
        columns = new String[]{ "ID","First Name", "Last Name"," Total Price" , "Status"};
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public int getRowCount() {
        return this.orderList.size();
    }

    public ArrayList<OrderModel> getOrderList() {
        return orderList;
    }

    @Override
    public Object getValueAt(int row, int col) {
        OrderModel temp = orderList.get(row);
        switch(col) {
            case(0): return temp.getId();
            case(1): return temp.getFirstName();
            case(2): return temp.getLastName();
            case(3): return temp.getTotalPrice();
            case(4): return temp.getStatus();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        return columns[col] ;
    }

    public void refresh() {
        this.fireTableDataChanged();
    }

}

package computerStore.ItemTable;

import computerStore.db.ItemListModel;
import computerStore.db.ItemModel;

import javax.swing.table.AbstractTableModel;

public class ItemTableModel extends AbstractTableModel   {
	ItemListModel list = ItemListModel.getInstance();
	private String[] columns;
	
	public ItemTableModel() {
		super();
		columns = new String[]{ "Catalog Number", "Name","Amount" , "Price"};
	}
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public int getRowCount() {
		return this.list.getItemList().size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		ItemModel temp = list.getItemList().get(row);
		switch(col) {
		case(0): return temp.getCatalogNumber();
		case(1): return temp.getName();
		case(2): return temp.getAmountInStock();
		case(3): return temp.getPriceForUnit();
		default: return null;
		}
	}
	
	public void addNewItem(String name, int price, int catalogNumber, int amountInStock) {
		this.list.addNewItem(name, price, catalogNumber,amountInStock);
	}

	public void editSelectedItem(String name, int price, int catalogNumber, int amountInStock, int index) {
		this.list.editSelectedItem(name, price, catalogNumber,amountInStock,index);
	}
	
	@Override
	 public String getColumnName(int col) {
		    return columns[col] ;
		  }
	
	public void refresh() {
		this.fireTableDataChanged();
	}

	public int searchByCatalogNumber(int catalogNumber) {
		int i = 0;
		for(ItemModel temp : this.list.getItemList()) {
			if (temp.getCatalogNumber() == catalogNumber) {
				return i;
			}
			i++;
		}
		return 0;
	}

	public ItemListModel getList() {
		return list;
	}
}

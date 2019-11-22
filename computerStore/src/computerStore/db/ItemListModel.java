package computerStore.db;

import java.util.ArrayList;

public class ItemListModel {
	private static ItemListModel instance;
	private ArrayList<ItemModel> itemList = new ArrayList<ItemModel>();
	
	public static ItemListModel getInstance() {
		if(instance == null) {
			instance = new ItemListModel(); 
		}

		return instance;
	}
	
	private ItemListModel() {
		seeder();
	}
	
	public void addNewItem(String name, int priceForUnit, int catalogNumber, int amountInStock) {
		ItemModel temp = new ItemModel(catalogNumber, priceForUnit,amountInStock, name);
		ItemListModel.getInstance().itemList.add(temp);
	}

	public void editSelectedItem(String name, int priceForUnit, int catalogNumber, int amountInStock, int index) {
		ItemModel temp = new ItemModel(catalogNumber, priceForUnit,amountInStock, name);
		ItemListModel.getInstance().itemList.set(index, temp);
	}

	void seeder() {
		for (int i = 0; i < 100; i++) {
			itemList.add(
					new ItemModel(
							i*3 + i, i * 10, i,"Item" + i
					)
			);
		}
	}

	public ArrayList<ItemModel> getItemList() {
		return ItemListModel.getInstance().itemList;
	}
}

package computerStore.NewItem;
import computerStore.Inventroy.InventoryModel;
import computerStore.db.ItemModel;

public class newItemController {
	newItemView view;
	private final int index;

	public newItemController() {
		this.view = new newItemView();
		this.view.getCancelButton().addActionListener(e -> exit());
		this.view.getSaveButton().addActionListener(e -> addNewItem());
		this.index = 0;
	}

	//For edit
	public newItemController(ItemModel x, int index) {
		this.view = new newItemView();
		view.initFields(x);
		this.view.getCancelButton().addActionListener(e -> exit());
		this.view.getSaveButton().addActionListener(e -> editItem());
		this.index = index;
	}

	public void exit() {
		view.dispose();
	}

	public void addNewItem() {
		try {
			InventoryModel.getInstance().addNewItem(
					view.getNameText().getText(),
					Integer.parseInt(view.getPrice().getText()),
					Integer.parseInt(view.getCatalogNumber().getText()),
					Integer.parseInt(view.getAmount().getText())
			);
			InventoryModel.getInstance().refresh();
			view.dispose();
		} catch (NumberFormatException e) {
			view.error();
		}
	}

	public void editItem() {
		try {
			InventoryModel.getInstance().editSelectedItem(
					view.getNameText().getText(),
					Integer.parseInt(view.getPrice().getText()),
					Integer.parseInt(view.getCatalogNumber().getText()),
					Integer.parseInt(view.getAmount().getText()),
					index
			);
			InventoryModel.getInstance().refresh();
			view.dispose();
		} catch (NumberFormatException e) {
			view.error();
		}
	}

}

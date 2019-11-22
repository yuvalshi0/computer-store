package computerStore.Inventroy;
import computerStore.NewItem.newItemController;
import computerStore.db.ItemListModel;
import computerStore.db.ItemModel;

import java.awt.event.MouseAdapter;

public class InventoryController {
	InventoryView view;

	public InventoryController(InventoryView view) {
		this.view = view;
		this.view.getNewItemButton().addActionListener(e -> newItemAction());
		this.view.getTable().addMouseListener(new RowEdit());

	}

	public void newItemAction() {
		new newItemController();
	}

	private class RowEdit extends MouseAdapter  {
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			super.mouseClicked(e);
			if (e.getClickCount() == 2) {
				ItemModel temp = ItemListModel.getInstance().getItemList().get(view.getTable().getSelectedRow());
				new newItemController(temp,view.getTable().getSelectedRow());
			}
		}
	}
}

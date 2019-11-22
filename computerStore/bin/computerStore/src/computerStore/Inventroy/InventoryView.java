package computerStore.Inventroy;

import computerStore.ItemTable.ItemTableModelRenderer;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class InventoryView extends JFrame {
	JLabel lblNewLabel = new JLabel("Inventory management:");
	JScrollPane scrollPane = new JScrollPane();
	JLabel lblNewLabel_1 = new JLabel("Store inventory:");
	JLabel lblNewLabel_3 = new JLabel("*Click specific row to edit an item");
	JButton newItemButton = new JButton("Add New Item...");
	private JTable table = new JTable();

	InventoryController controller = new InventoryController(this);

	public InventoryView() {
		table.setDefaultRenderer(Object.class,new ItemTableModelRenderer());
		table.setModel(InventoryModel.getInstance());
		this.setBounds(0, 0, 600, 800);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		scrollPane.setBounds(62, 111, 466, 533);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 31, 564, 32);
		getContentPane().add(lblNewLabel);
		lblNewLabel_1.setBounds(62, 86, 171, 14);
		getContentPane().add(lblNewLabel_1);
		newItemButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newItemButton.setBounds(62, 655, 147, 23);
		getContentPane().add(newItemButton);
		table.setFont(new Font("Arial", Font.BOLD, 15));
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setBounds(350, 635, 564, 32);
		this.setTitle("Inventory management");
	}

	public JButton getNewItemButton() {
		return newItemButton;
	}

	public JTable getTable() {
		return table;
	}




}

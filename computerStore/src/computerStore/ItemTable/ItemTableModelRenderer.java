package computerStore.ItemTable;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class ItemTableModelRenderer implements TableCellRenderer {

    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);

        int x = (Integer)table.getModel().getValueAt(row, 2 );

        if(x == 0)
        {
            c.setBackground(Color.red);
        }
        else
        {
            c.setBackground(null);
        }
        if (isSelected) {
            c.setBackground(Color.lightGray);
        }
        return(c);
    }
}
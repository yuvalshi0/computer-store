package computerStore.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class OrderItemDisabler implements TableCellRenderer {

    private static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);
        c.setBackground(new Color(192, 192, 192, 200));
        c.setForeground(new Color(255, 255, 255, 200));

        if (isSelected) {
            c.setBackground(Color.darkGray);
        }
        return (c);
    }
}
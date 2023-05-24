package Gra;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PlanszaTableCellRenderer extends DefaultTableCellRenderer {

    ImageIcon sciana = new ImageIcon("sciana.jpg");
    ImageIcon packman = new ImageIcon("pacman.png");
    ImageIcon coin = new ImageIcon("coin.jfif");


    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        Integer content = (Integer) value;

        switch (content) {
            case 0:
                cell.setBackground(Color.RED); // Empty Space
                cell.setForeground(Color.RED);
                setIcon(new ImageIcon(coin.getImage().getScaledInstance(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(row), Image.SCALE_DEFAULT)));
                break;
            case 1:
                cell.setBackground(Color.BLUE); // Wall
                cell.setForeground(Color.BLUE);
                setIcon(new ImageIcon(sciana.getImage().getScaledInstance(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(row), Image.SCALE_DEFAULT)));

                break;
            case 2:
                cell.setBackground(Color.BLACK); // Empty Space
                cell.setForeground(Color.BLACK); // Coin
                setIcon(null);
                break;
            case 3:
                cell.setBackground(Color.YELLOW); // Dot
                cell.setForeground(Color.YELLOW);
                setIcon(new ImageIcon(packman.getImage().getScaledInstance(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(row), Image.SCALE_DEFAULT)));

                break;
            case 4:
                cell.setBackground(Color.GREEN); // Ghost
                break;
//            case 5:
//                cell.setBackground(Color.RED); // Ghost
//                break;
//            default:
//                cell.setBackground(Color.WHITE);
//                break;
        }
        return cell;
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.reports;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Maria
 */
public class MultiLineCellRenderer 
        implements TableCellRenderer {
    public static boolean BOLD;
    public MultiLineCellRenderer(){   
        
//es un array con su tostring        
    }

    

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
     
        JTextArea area = new JTextArea();
        area.setFont(jtable.getFont());
         area.setLineWrap(true);
         area.setText((String) value);
area.setEditable(false);
         JScrollPane pane = new JScrollPane(area);

         return pane;
    }
}
    


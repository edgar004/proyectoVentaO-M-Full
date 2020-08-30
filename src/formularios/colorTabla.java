/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

/**
 *
 * @author Edgar Tavarez
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class colorTabla extends DefaultTableCellRenderer {
private int columna ;

public colorTabla (int Colpatron)
{
    this.columna = Colpatron;
}

@Override
public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
{        
    setBackground(Color.white);
    table.setForeground(Color.black);
    super.getTableCellRendererComponent(table, value, selected, focused, row, column);
    if(Integer.parseInt(table.getValueAt(row,2).toString())<20)
    {
        this.setForeground(Color.RED);
    }else{
        setForeground(Color.BLACK);
    }
    return this;
  }
  }

package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.NumberFormat;

//Custom renderer to format JTable index cells to integers and center them
public class IndexRenderer extends DefaultTableCellRenderer {
    private NumberFormat format;

    IndexRenderer() {
        super();
        format = NumberFormat.getIntegerInstance();
        setHorizontalAlignment(SwingConstants.CENTER); //centered
    }

    //here we format the value
    public void setValue(Object value) {
        if (value != null) {
            value = format.format(value);
        }
        super.setValue(value);
    }
}

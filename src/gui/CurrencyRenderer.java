package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.NumberFormat;

//Custom renderer to format JTable cells using currency format
public class CurrencyRenderer extends DefaultTableCellRenderer {
    private NumberFormat format;

    CurrencyRenderer() {
        super();
        format = NumberFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(2); //2 decimal places
        setHorizontalAlignment(SwingConstants.RIGHT); //right-aligned
    }

    //here we format the value
    public void setValue(Object value) {
        if (value != null) {
            value = format.format(value);
        }
        super.setValue(value);
    }
}
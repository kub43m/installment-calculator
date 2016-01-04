package calculation;

/**
 * Created by Kuba on 12/23/2015.
 */

//Enum representing loan repayment frequency - it can be either monthly or yearly repayments
public enum CalcRepaymentFrequency {
    MONTHLY, YEARLY;

    //label is used in toString method so that it could be displayed in JComboBox instead of enum name
    private String label;
    //tooltip is used for setting context-based tooltips for combo-box items
    private String tooltip;

    //labels
    private static String monthlyLabel = "Monthly";
    private static String yearlyLabel = "Yearly";

    //tooltips
    private static String monthlyTooltip = "Payments are made (or interest accrues) every month.";
    private static String yearlyTooltip = "Payments are made (or interest accrues) every year.";;

    //initialize fields
    static {
        MONTHLY.label = monthlyLabel;
        MONTHLY.tooltip = monthlyTooltip;

        YEARLY.label = yearlyLabel;
        YEARLY.tooltip = yearlyTooltip;
    }

    @Override
    public String toString() {
        return label;
    }

    public String getTooltip() { return tooltip; }

}

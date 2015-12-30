package calculation;

/**
 * Created by Kuba on 12/23/2015.
 */

// Enum representing options for period input: it can be either in months or years.
public enum CalcPeriodInputType {
    YEAR("Years"),
    MONTH("Months");

    //Label is used in toString method so that it could be displayed in JComboBox instead of enum name
    private String label;

    private CalcPeriodInputType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}

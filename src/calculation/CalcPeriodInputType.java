package calculation;

/**
 * Created by Kuba on 12/23/2015.
 */
public enum CalcPeriodInputType {
    YEAR("Years"),
    MONTH("Months");

    private String label;

    private CalcPeriodInputType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}

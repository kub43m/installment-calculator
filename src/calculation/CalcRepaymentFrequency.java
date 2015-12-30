package calculation;

/**
 * Created by Kuba on 12/23/2015.
 */
public enum CalcRepaymentFrequency {
    MONTHLY("Monthly"),
    YEARLY("Yearly");

    private String label;

    private CalcRepaymentFrequency(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}

package calculation;

/**
 * Created by Kuba on 12/23/2015.
 */

//Enum representing loan repayment frequency - it can be either monthly or yearly repayments
public enum CalcRepaymentFrequency {
    MONTHLY("Monthly"),
    YEARLY("Yearly");

    //Label is used in toString method so that it could be displayed in JComboBox instead of enum name
    private String label;

    private CalcRepaymentFrequency(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

}

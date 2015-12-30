package calculation;

/**
 * Created by Kuba on 12/23/2015.
 */

//Enum representing repayment strategies
public enum CalcRepaymentType {
    CONSTANT_CAPITAL_PART("Constant capital part"),
    CONSTANT_INSTALLMENT("Constant installment"),
    CAPITAL_AND_INTEREST_AT_END("Capital and interest paid at the end"),
    CAPITAL_AT_END_INTEREST_PER_PERIOD("Capital paid at the end, interest every period");

    //Label is used in toString method so that it could be displayed in JComboBox instead of enum name
    private String label;

    private CalcRepaymentType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}

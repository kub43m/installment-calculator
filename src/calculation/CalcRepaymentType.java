package calculation;

//Enum representing repayment strategies
public enum CalcRepaymentType {
    CONSTANT_CAPITAL_PART,
    CONSTANT_INSTALLMENT,
    CAPITAL_AND_INTEREST_AT_END,
    CAPITAL_AT_END_INTEREST_PER_PERIOD;

    //label is used in toString method so that it could be displayed in JComboBox instead of enum name
    private String label;
    //tooltip is used for setting context-based tooltips for combo-box items
    private String tooltip;

    //labels
    private static String constantCapitalPartLabel = "Constant capital part";
    private static String constantInstallmentLabel = "Constant installment";
    private static String capitalAndInterestAtEndLabel = "Capital and interest paid at the end";
    private static String capitalAtEndInterestPerPeriodLabel = "Capital paid at the end, interest every period";

    //tooltips
    private static String constantCapitalPartTooltip = "Every period the same amount of capital is repaid; interest payments are diminishing.";
    private static String constantInstallmentTooltip = "Every period the same installment is paid; interest payments are diminishing, capital part is increasing.";
    private static String capitalAndInterestAtEndTooltip = "All of capital and interest is paid at maturity; interest accrues every period.";
    private static String capitalAtEndInterestPerPeriodTooltip = "All of capital is paid at maturity, interest payments are made every period.";

    //initialize fields
    static{
        CONSTANT_CAPITAL_PART.label = constantCapitalPartLabel;
        CONSTANT_CAPITAL_PART.tooltip = constantCapitalPartTooltip;

        CONSTANT_INSTALLMENT.label = constantInstallmentLabel;
        CONSTANT_INSTALLMENT.tooltip = constantInstallmentTooltip;

        CAPITAL_AND_INTEREST_AT_END.label = capitalAndInterestAtEndLabel;
        CAPITAL_AND_INTEREST_AT_END.tooltip = capitalAndInterestAtEndTooltip;

        CAPITAL_AT_END_INTEREST_PER_PERIOD.label = capitalAtEndInterestPerPeriodLabel;
        CAPITAL_AT_END_INTEREST_PER_PERIOD.tooltip = capitalAtEndInterestPerPeriodTooltip;
    }


    @Override
    public String toString() {
        return label;
    }

    public String getTooltip() { return tooltip; }

}

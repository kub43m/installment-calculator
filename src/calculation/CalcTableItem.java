package calculation;


//Object holding results for a single period - it represents a single row in a detailed results table
public class CalcTableItem {
    private int period;
    private double debt;
    private double interest;
    private double capitalPart;
    private double installment;

    //Constructor:
    public CalcTableItem(int period, double debt, double interest, double capitalPart) {
        this.period = period;
        this.debt = debt;
        this.interest = interest;
        this.capitalPart = capitalPart;
        installment = interest + capitalPart;
    }

    /* Overloaded constructor to be able to force installment = 0 in cases where interest and capital are payed at end.
     * A boolean flag could be added to the initial constructor but this issue hasn't been noticed until later in the
     * development process and this overloaded constructor was introduced not to break existing code. */
    public CalcTableItem(int period, double debt, double interest, double capitalPart, double installment) {
        this.period = period;
        this.debt = debt;
        this.interest = interest;
        this.capitalPart = capitalPart;
        this.installment = installment;
    }

    //Getters:
    public int getPeriod() { return period; }
    public double getDebt() { return debt; }
    public double getInterest() { return interest; }
    public double getCapitalPart() { return capitalPart; }
    public double getInstallment() { return installment; }

}

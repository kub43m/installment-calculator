package calculation;

/**
 * Created by Kuba on 12/23/2015.
 */
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

    //Getters:
    public int getPeriod() { return period; }
    public double getDebt() { return debt; }
    public double getInterest() { return interest; }
    public double getCapitalPart() { return capitalPart; }
    public double getInstallment() { return installment; }

}

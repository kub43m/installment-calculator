package calculation;

/**
 * Created by Kuba on 12/22/2015.
 */
public class ConstCapitalPartCalc implements Calculation {

    private double debt;
    private double rate;
    private int noPeriods;
    private double totalInstallment = 0;
    private double totalInterest = 0;
    private CalcResult calcResult = new CalcResult();

    public ConstCapitalPartCalc(double debt, double rate, int noPeriods) {
        this.debt = debt;
        this.rate = rate;
        this.noPeriods = noPeriods;
    }

    public double getDebt() {
        return debt;
    }

    public double getRate() {
        return rate;
    }

    public int getNoPeriods() {
        return noPeriods;
    }

    private CalcResult calculate(double debt, double rate, int noPeriods) {

        double debtOutstanding = debt;
        double capitalPart = debt / noPeriods;
        double interest;
        double installment;

        for (int i=1; i<=noPeriods; i++) {
            interest = debtOutstanding * rate;
            installment = interest + capitalPart;
            //int period, double debt, double interest, double capitalPart
            calcResult.addRow(new CalcTableItem(i, debtOutstanding, interest, capitalPart));
            debtOutstanding -= capitalPart;
            totalInstallment += installment;
            totalInterest += interest;
        }
        calcResult.setInstallmentSum(totalInstallment);
        calcResult.setInterestSum(totalInterest);
        return calcResult;
    }

    @Override
    public CalcResult calculate(){
        return calculate(debt, rate, noPeriods);
    }

}

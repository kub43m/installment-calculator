package calculation;

/**
 * Created by Kuba on 12/23/2015.
 */
public class ConstInstallmentCalc implements Calculation{
    //double debt, double rate, int noPeriods, CalcPeriodInputType type
    private double debt;
    private double rate;
    private int noPeriods;
    private double totalInstallment = 0.0d;
    private double totalInterest = 0.0d;
    private CalcResult calcResult = new CalcResult();

    public ConstInstallmentCalc(double debt, double rate, int noPeriods) {
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

    @Override
    public CalcResult calculate() {
        double debtOutstanding = debt;
        double installment = calcInstallment();
        double capitalPart;
        double interest;

        for (int i=1; i<=noPeriods; i++) {
            interest = debtOutstanding * rate;
            capitalPart = installment - interest;
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

    //Helper methods:
    //Calculate constant installment
    public double calcInstallment() {
        double v = 1 / (1 + rate); //discounting factor for one period
        return debt / geomSeriesSum(v, v, noPeriods);
    }

    //Calculate geometric series sum
    //a1 - first element of series, q - quotient, n - #elems to sum
    public double geomSeriesSum(double a1, double q, int n) {
        return a1 * (1 - Math.pow(q, n)) / (1 - q);
    }
}

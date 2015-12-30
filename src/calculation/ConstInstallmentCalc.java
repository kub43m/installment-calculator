package calculation;

/**
 * Created by Kuba on 12/23/2015.
 */
/* An implementation of Calculation interface modelling the situation where every period the same installment is is paid
 * -> the capital part is increasing and interest part is decreasing every period.
 * The way to compute the value of constant installment for given parameters is described below */
public class ConstInstallmentCalc implements Calculation{
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
        //set up
        double debtOutstanding = debt;
        double installment = calcInstallment(); //constant installment (see details below)
        double capitalPart;
        double interest;

        //Calculate financial values for every period, period by period
        for (int i=1; i<=noPeriods; i++) {
            interest = debtOutstanding * rate;
            capitalPart = installment - interest;
            //Add the period's financial values to the table (add a single row to the table)
            //Args: int period, double debt, double interest, double capitalPart
            calcResult.addRow(new CalcTableItem(i, debtOutstanding, interest, capitalPart));
            debtOutstanding -= capitalPart;
            totalInstallment += installment;
            totalInterest += interest;
        }
        //set totals' final values
        calcResult.setInstallmentSum(totalInstallment);
        calcResult.setInterestSum(totalInterest);
        return calcResult;
    }


    /* COMPUTING CONSTANT INSTALLMENT:
     *
     * Let v be the discount factor for single period:
     *
     * v := 1/(1+i), where i is the interest rate (in %)
     *
     * Every month, the same installment is paid, but the installment has different present value (computed at the
     * moment of obtaining the loan) - the smaller the further it is in time from the moment of taking the loan.
     * For example, the first payment has present value of CF_1 / v1, second payment has value of CF_2 / v2 and so on.
     * (CF_i and vi are i-th Cash Flow and discount factor, respectively). Because each one of them is equal, we obtain:
     * CF_1 = CF_2 = ... = CF_n =: CF. Loan value K is the present value of all future payments, hence:
     *
     * K = CF * (v + v^2 + ... + v^n) and so
     *
     * CF = K / (v + v^2 + ... + v^n)
     *
     * Setting a_n := v + v^2 + ... + v^n, we obtain that K = CF * a_n and so the final formula for CF is:
     *
     * CF = K / a_n.
     *
     * */

    //Helper methods:
    //Calculate constant installment
    public double calcInstallment() {
        double v = 1 / (1 + rate); //discounting factor for one period
        return debt / geomSeriesSum(v, v, noPeriods);
    }

    //Calculate geometric series sum
    //Formula: sum = a1 * (1-q^n) / (1-q), where:
    //a1 - first element of series, q - quotient, n - #elems to sum
    public double geomSeriesSum(double a1, double q, int n) {
        return a1 * (1 - Math.pow(q, n)) / (1 - q);
    }
}

package calculation;

/* An implementation of Calculation interface modelling the situation where every period the same amount of capital is
 * paid (capital part of the installment is constant) and interest is paid every period based on the amount of debt
 * outstanding -> installments are decreasing every period */
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

    @Override
    public CalcResult calculate() {

        //set up
        double debtOutstanding = debt;
        double capitalPart = debt / noPeriods; //constant capital part for every period
        double interest;
        double installment;

        //Calculate financial values for every period, period by period
        for (int i=1; i<=noPeriods; i++) {
            interest = debtOutstanding * rate;
            installment = interest + capitalPart;
            //Add the period's financial values to the table (add a single row to the table)
            //Args: int period, double debt, double interest, double capitalPart
            calcResult.addRow(new CalcTableItem(i, debtOutstanding, interest, capitalPart));
            debtOutstanding -= capitalPart; //capital payment decreases outstanding debt
            totalInstallment += installment;
            totalInterest += interest;
        }
        //set totals' final values
        calcResult.setInstallmentSum(totalInstallment);
        calcResult.setInterestSum(totalInterest);
        return calcResult;
    }

}

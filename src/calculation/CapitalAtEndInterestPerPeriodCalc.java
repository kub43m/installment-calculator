package calculation;

/* An implementation of Calculation interface modelling the situation where the capital part is paid at loan's maturity
 * and interest payments are made every period. */
public class CapitalAtEndInterestPerPeriodCalc implements Calculation {
    private double debt;
    private double rate;
    private int noPeriods;
    private double totalInstallment = 0;
    private double totalInterest = 0;
    private CalcResult calcResult = new CalcResult();

    public CapitalAtEndInterestPerPeriodCalc(double debt, double rate, int noPeriods) {
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
        double capitalPart;
        double interest;
        double installment = 0;

        //Calculate financial values for every period, period by period
        for (int i=1; i<=noPeriods; i++) {
            interest = debtOutstanding * rate;
            if (i != noPeriods){ //not in the last period of the loan
                capitalPart = 0;
            } else { //the last period of the loan
                capitalPart = debtOutstanding;
            }
            //Add the period's financial values to the table (add a single row to the table)
            //Args: int period, double debt, double interest, double capitalPart, double interest
            calcResult.addRow(new CalcTableItem(i, debtOutstanding, interest, capitalPart));
            //update totals
            installment = capitalPart + interest;
            totalInterest += interest;
            totalInstallment += installment;
        }
        //set totals' final values
        calcResult.setInstallmentSum(totalInstallment);
        calcResult.setInterestSum(totalInterest);
        return calcResult;
    }
}

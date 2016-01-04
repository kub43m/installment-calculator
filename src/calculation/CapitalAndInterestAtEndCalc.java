package calculation;

/**
 * Created by Kuba on 2015-12-30.
 */

/* An implementation of Calculation interface modelling the situation where both the capital part and all of interest
 * are payed at loan's maturity. Interest is accrued every period meaning that it increases debt outstanding's value. */
public class CapitalAndInterestAtEndCalc implements Calculation {
    private double debt;
    private double rate;
    private int noPeriods;
    private double totalInstallment = 0;
    private double totalInterest = 0;
    private CalcResult calcResult = new CalcResult();

    public CapitalAndInterestAtEndCalc(double debt, double rate, int noPeriods) {
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
                installment = 0;
            } else { //the last period of the loan
                capitalPart = debtOutstanding;
                installment = interest + capitalPart;
            }
            //Add the period's financial values to the table (add a single row to the table)
            //WE USE OVERLOADED CONSTRUCTOR TO FORCE INSTALLMENT IN EACH PERIOD EXCEPT FOR THE LAST ONE TO EQUAL ZERO
            //Args: int period, double debt, double interest, double capitalPart, double interest, double installment
            calcResult.addRow(new CalcTableItem(i, debtOutstanding, interest, capitalPart, installment));
            //update totals
            debtOutstanding += interest;
            totalInterest += interest;
        }
        //set totals' final values
        totalInstallment = installment;
        calcResult.setInstallmentSum(totalInstallment);
        calcResult.setInterestSum(totalInterest);
        return calcResult;
    }
}

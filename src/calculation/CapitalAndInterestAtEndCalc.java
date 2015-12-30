package calculation;

/**
 * Created by Kuba on 2015-12-30.
 */
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

        for (int i=1; i<=noPeriods; i++) {
            interest = debtOutstanding * rate;
            if (i != noPeriods){
                capitalPart = 0;
                installment = 0;
            } else {
                capitalPart = debtOutstanding;
                installment = interest + capitalPart;
            }
            //WE USE OVERLOADED CONSTRUCTOR TO FORCE INSTALLMENT IN EACH PERIOD EXCEPT FOR THE LAST ONE EQUAL ZERO
            //int period, double debt, double interest, double capitalPart, double interest
            calcResult.addRow(new CalcTableItem(i, debtOutstanding, interest, capitalPart, installment));
            debtOutstanding += interest;
            totalInterest += interest;
        }
        totalInstallment = installment;
        calcResult.setInstallmentSum(totalInstallment);
        calcResult.setInterestSum(totalInterest);
        return calcResult;
    }
}

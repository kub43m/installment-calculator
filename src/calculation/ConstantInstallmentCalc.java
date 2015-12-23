package calculation;

/**
 * Created by Kuba on 12/23/2015.
 */
public class ConstantInstallmentCalc implements Calculation{
    //double debt, double rate, int noPeriods, CalcPeriodInputType type
    private double debt;
    private double rate;
    private int noPeriods;

    public ConstantInstallmentCalc(double debt, double rate, int noPeriods) {
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
        return null;
    }
}

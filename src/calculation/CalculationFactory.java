package calculation;

/**
 * Created by Kuba on 2016-01-03.
 */
/* Factory Class that takes user input parses it and creates an appropriate Calculation object */
public class CalculationFactory {

    private static double debt;
    private static double rate;
    private static int noPeriods;

    public static Calculation createCalculation(double debt, double rate, int noPeriods,
                                       CalcRepaymentType repaymentType, CalcPeriodInputType periodInput, CalcRepaymentFrequency accrualRate) {

        initializeParameters(debt, rate, noPeriods, periodInput, accrualRate);
        return getCalculation(repaymentType);
    }

    private static void initializeParameters(double debt, double rate, int noPeriods, CalcPeriodInputType periodInput, CalcRepaymentFrequency accrualRate) {
        CalculationFactory.debt = debt;
        CalculationFactory.rate = rate;
        CalculationFactory.noPeriods = noPeriods;

        recalculateNoPeriods(periodInput, accrualRate);
        recalculateRate(periodInput, accrualRate);
    }

    private static Calculation getCalculation(CalcRepaymentType repaymentType) {
        Calculation calc = null; //has to be initialized as null to avoid compiler error (var might not have been initialized)

        //choose appropriate Calculation object
        switch (repaymentType) {
            case CONSTANT_CAPITAL_PART:
                calc = new ConstCapitalPartCalc(CalculationFactory.debt, CalculationFactory.rate, CalculationFactory.noPeriods);
                break;
            case CONSTANT_INSTALLMENT:
                calc = new ConstInstallmentCalc(CalculationFactory.debt, CalculationFactory.rate, CalculationFactory.noPeriods);
                break;
            case CAPITAL_AND_INTEREST_AT_END:
                calc = new CapitalAndInterestAtEndCalc(CalculationFactory.debt, CalculationFactory.rate, CalculationFactory.noPeriods);
                break;
            case CAPITAL_AT_END_INTEREST_PER_PERIOD:
                calc = new CapitalAtEndInterestPerPeriodCalc(CalculationFactory.debt, CalculationFactory.rate, CalculationFactory.noPeriods);
                break;
        }

        return calc;
    }

    //scale interest rate
    private static void recalculateRate(CalcPeriodInputType periodInput, CalcRepaymentFrequency accrualRate) {
        if (periodInput == CalcPeriodInputType.YEAR && accrualRate == CalcRepaymentFrequency.MONTHLY) {
            CalculationFactory.rate /= 12;
        }
        else if (periodInput == CalcPeriodInputType.MONTH && accrualRate == CalcRepaymentFrequency.MONTHLY) {
            CalculationFactory.rate /= 12;
        }
        else if (periodInput == CalcPeriodInputType.MONTH && accrualRate == CalcRepaymentFrequency.YEARLY) {
            //this is not allowed and this case is avoided in the GUI section
            throw new RuntimeException("Input in months and yearly interest accrual not allowed.");
        }
    }

    //scale #periods
    private static void recalculateNoPeriods(CalcPeriodInputType periodInput, CalcRepaymentFrequency accrualRate) {
        if (periodInput == CalcPeriodInputType.YEAR && accrualRate == CalcRepaymentFrequency.MONTHLY) {
            CalculationFactory.noPeriods *= 12;
        }
    }

}

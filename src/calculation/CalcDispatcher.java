package calculation;

/**
 * Created by Kuba on 12/26/2015.
 */

/* Class that takes user input parses it and delegates calculation to an appropriate Calculation object */
public class CalcDispatcher {

    public static CalcResult calculate(double debt, double rate, int noPeriods,
        CalcRepaymentType repaymentType, CalcPeriodInputType periodInput, CalcRepaymentFrequency accrualRate)
    {

        Calculation calc = null; //has to be initialized as null to avoid compiler error (var might not have been initialized)

        if (periodInput == CalcPeriodInputType.YEAR && accrualRate == CalcRepaymentFrequency.MONTHLY){
            //scale #periods and interest rate
            noPeriods *= 12;
            rate /= 12;
        } else if (periodInput == CalcPeriodInputType.MONTH && accrualRate == CalcRepaymentFrequency.MONTHLY){
            //scale interest rate
            rate /= 12;
        } else if (periodInput == CalcPeriodInputType.MONTH && accrualRate == CalcRepaymentFrequency.YEARLY) {
            //this is not allowed and this case is avoided in the GUI section
            throw new RuntimeException("Input in months and yearly interest accrual not allowed.");
        }
        //last case is YEARs & YEARLY interest accrual -> neither rate nor noPeriods need to be modified

        //here we choose appropriate Calculation object
        switch (repaymentType){
            case CONSTANT_CAPITAL_PART: calc = new ConstCapitalPartCalc(debt, rate, noPeriods); break;
            case CONSTANT_INSTALLMENT:  calc = new ConstInstallmentCalc(debt, rate, noPeriods); break;
            case CAPITAL_AND_INTEREST_AT_END:  calc = new CapitalAndInterestAtEndCalc(debt, rate, noPeriods); break;
            case CAPITAL_AT_END_INTEREST_PER_PERIOD: calc = new CapitalAtEndInterestPerPeriodCalc(debt, rate, noPeriods); break;
        }

        return  calc.calculate();
    }
}

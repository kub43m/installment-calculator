import calculation.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Kuba on 2016-01-04.
 */
//values for these tests can be found in the accompanying excel spreadsheet "loan_repayment_examples",
//sheets: 1.CapitalAndInterestAtEnd, 2.CapitalAtEndInterestPerPeriod, 3.ConstantCapitalPart and 4.ConstantInstallment
public class CalculationFactoryTest {

    /* *******************************
     *                               *
     *      ConstInstallmentCalc     *
     *                               *
     *********************************/

    //ConstInstallmentCalcTest
    //Input: year, repayment freq: yearly
    @Test
    public void testCalculateConstInstallment1() throws Exception {
        //public static CalcResult calculate(double debt, double rate, int noPeriods, CalcRepaymentType repaymentType, CalcPeriodInputType periodInput, CalcRepaymentFrequency accrualRate)
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 4, CalcRepaymentType.CONSTANT_INSTALLMENT, CalcPeriodInputType.YEAR, CalcRepaymentFrequency.YEARLY).calculate();
        assertEquals(261.883730d, cr.getInterestSum(), 0.001d);
        assertEquals(1261.883730d, cr.getInstallmentSum(), 0.001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 784.53d, 547.513d, 286.7943d};
        double[] interest = new double[]{100.0d, 78.453d, 54.7513d, 28.67943d};
        double capitalPart[] = new double[]{215.47d, 237.017d, 260.7187d, 286.790570};
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.005d);
            assertEquals(interest[i-1], cti.getInterest(), 0.005d);
            assertEquals(capitalPart[i-1], cti.getCapitalPart(), 0.005d);
            assertEquals(interest[i-1] + capitalPart[i-1], cti.getInstallment(), 0.005d);
            i++;
        }
    }

    //ConstInstallmentCalcTest
    //Input: year, repayment freq: monthly
    @Test
    public void testCalculateConstInstallment2() throws Exception {
        //public static CalcResult calculate(double debt, double rate, int noPeriods, CalcRepaymentType repaymentType, CalcPeriodInputType periodInput, CalcRepaymentFrequency accrualRate)
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 1, CalcRepaymentType.CONSTANT_INSTALLMENT, CalcPeriodInputType.YEAR, CalcRepaymentFrequency.MONTHLY).calculate();

        assertEquals(54.990647d, cr.getInterestSum(), 0.001d);
        assertEquals(1054.990647d, cr.getInstallmentSum(), 0.001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 920.417446d, 840.171704d, 759.257248d, 677.668504d,
                595.399855d, 512.445633d, 428.800126d, 344.457573d, 259.412166d, 173.658046d, 87.189310d};
        double[] interest = new double[]{8.333333d, 7.670145d, 7.001431d, 6.327144d, 5.647238d, 4.961665d,
                4.270380d, 3.573334d, 2.870480d, 2.161768d, 1.447150d, 0.726578d};
        double capitalPart[] = new double[]{79.582554d, 80.245742d, 80.914456d, 81.588743d, 82.268650d,
                82.954222d, 83.645507d, 84.342553d, 85.045407d, 85.754119d, 86.468737d, 87.189310d};
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.005d);
            assertEquals(interest[i-1], cti.getInterest(), 0.005d);
            assertEquals(capitalPart[i-1], cti.getCapitalPart(), 0.005d);
            assertEquals(interest[i-1] + capitalPart[i-1], cti.getInstallment(), 0.005d);
            i++;
        }
    }

    //ConstInstallmentCalcTest
    //Input: month, repayment freq: monthly
    @Test
    public void testCalculateConstInstallment3() throws Exception {
        //public static CalcResult calculate(double debt, double rate, int noPeriods, CalcRepaymentType repaymentType, CalcPeriodInputType periodInput, CalcRepaymentFrequency accrualRate)
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 6, CalcRepaymentType.CONSTANT_INSTALLMENT, CalcPeriodInputType.MONTH, CalcRepaymentFrequency.MONTHLY).calculate();

        assertEquals(29.368365d, cr.getInterestSum(), 0.001d);
        assertEquals(1029.368365d, cr.getInstallmentSum(), 0.001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 836.771939d, 672.183644d, 506.223781d, 338.880918d, 170.143531d};
        double[] interest = new double[]{8.333333d, 6.973099d, 5.601530d, 4.218532d, 2.824008d, 1.417863d};
        double capitalPart[] = new double[]{163.228061d, 164.588295d, 165.959864d, 167.342863d, 168.737387d, 170.143531d};
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.005d);
            assertEquals(interest[i-1], cti.getInterest(), 0.005d);
            assertEquals(capitalPart[i-1], cti.getCapitalPart(), 0.005d);
            assertEquals(interest[i-1] + capitalPart[i-1], cti.getInstallment(), 0.005d);
            i++;
        }
    }



    /* *******************************
     *                               *
     *      ConstCapitalPartCalc     *
     *                               *
     *********************************/

    //ConstCapitalPartCalcTest
    //Input: year, repayment freq: yearly
    @Test
    public void testCalculateConstCapitalPart1() throws Exception {
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 4, CalcRepaymentType.CONSTANT_CAPITAL_PART, CalcPeriodInputType.YEAR, CalcRepaymentFrequency.YEARLY).calculate();

        assertEquals(cr.getInterestSum(), 250.0d, 0.000001d);
        assertEquals(cr.getInstallmentSum(), 1250.0d, 0.000001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 750.0d, 500.0d, 250.0d};
        double[] interest = new double[]{100.0d, 75.0d, 50.0d, 25.0d};
        double capitalPart = 250.0d;
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.000001d);
            assertEquals(interest[i-1], cti.getInterest(), 0.000001d);
            assertEquals(capitalPart, cti.getCapitalPart(), 0.000001d);
            assertEquals(interest[i-1] + capitalPart, cti.getInstallment(), 0.000001d);
            i++;
        }
    }

    //ConstCapitalPartCalcTest
    //Input: year, repayment freq: monthly
    @Test
    public void testCalculateConstCapitalPart2() throws Exception {
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 1, CalcRepaymentType.CONSTANT_CAPITAL_PART, CalcPeriodInputType.YEAR, CalcRepaymentFrequency.MONTHLY).calculate();

        assertEquals(cr.getInterestSum(), 54.166667d, 0.000001d);
        assertEquals(cr.getInstallmentSum(), 1054.166667d, 0.000001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 916.666667d, 833.333333d, 750.0d, 666.666667d, 583.333333d,
                500.0d, 416.666667d, 333.333333d, 250.0d, 166.666667d, 83.333333d};
        double[] interest = new double[]{8.333333d, 7.638889d, 6.944444d, 6.25d, 5.555556d, 4.861111d, 4.166667d,
                3.472222d, 2.777778d, 2.083333d, 1.388889d, 0.694444d};
        double capitalPart = 83.333333d;
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.000001d);
            assertEquals(interest[i-1], cti.getInterest(), 0.000001d);
            assertEquals(capitalPart, cti.getCapitalPart(), 0.000001d);
            assertEquals(interest[i-1] + capitalPart, cti.getInstallment(), 0.000001d);
            i++;
        }
    }

    //ConstCapitalPartCalcTest
    //Input: month, repayment freq: monthly
    @Test
    public void testCalculateConstCapitalPart3() throws Exception {
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 6, CalcRepaymentType.CONSTANT_CAPITAL_PART, CalcPeriodInputType.MONTH, CalcRepaymentFrequency.MONTHLY).calculate();

        assertEquals(cr.getInterestSum(), 29.166667d, 0.000001d);
        assertEquals(cr.getInstallmentSum(), 1029.166667d, 0.000001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 833.333333d, 666.666667d, 500.0d, 333.333333d, 166.666667d};
        double[] interest = new double[]{8.333333d, 6.944444d, 5.555556d, 4.166667d, 2.777778d, 1.388889d};
        double capitalPart = 166.666667d;
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.000001d);
            assertEquals(interest[i-1], cti.getInterest(), 0.000001d);
            assertEquals(capitalPart, cti.getCapitalPart(), 0.000001d);
            assertEquals(interest[i-1] + capitalPart, cti.getInstallment(), 0.000001d);
            i++;
        }
    }



    /* **************************************
     *                                      *
     *      CapitalAndInterestAtEndCalc     *
     *                                      *
     ****************************************/

    //CapitalAndInterestAtEndCalcTest
    //Input: year, repayment freq: yearly
    @Test
    public void testCalculateCapitalAndInterestAtEnd1() throws Exception {
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 4, CalcRepaymentType.CAPITAL_AND_INTEREST_AT_END, CalcPeriodInputType.YEAR, CalcRepaymentFrequency.YEARLY).calculate();

        assertEquals(cr.getInterestSum(), 464.1d, 0.000001d);
        assertEquals(cr.getInstallmentSum(), 1464.1d, 0.000001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 1100.0d, 1210.0d, 1331.0d};
        double[] interest = new double[]{100.0d, 110.0d, 121.0d, 133.1d};
        double capitalPart[] = new double[]{0.0d, 0.0d, 0.0d, 1331.0};
        double installment[] = new double[]{0.0d, 0.0d, 0.0d, 1464.1d};
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.000001d);
            assertEquals(interest[i-1], cti.getInterest(), 0.000001d);
            assertEquals(capitalPart[i-1], cti.getCapitalPart(), 0.000001d);
            assertEquals(installment[i-1], cti.getInstallment(), 0.000001d);
            i++;
        }
    }

    //CapitalAndInterestAtEndCalcTest
    //Input: year, repayment freq: monthly
    @Test
    public void testCalculateCapitalAndInterestAtEnd2() throws Exception {
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 1, CalcRepaymentType.CAPITAL_AND_INTEREST_AT_END, CalcPeriodInputType.YEAR, CalcRepaymentFrequency.MONTHLY).calculate();

        assertEquals(cr.getInterestSum(), 104.713067d, 0.000001d);
        assertEquals(cr.getInstallmentSum(), 1104.713067d, 0.000001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 1008.333333d, 1016.736111d, 1025.208912d, 1033.752320d,
                1042.366922d, 1051.053313d, 1059.812091d, 1068.643858d, 1077.549224d, 1086.528801d, 1095.583207d};
        double[] interest = new double[] {8.333333d, 8.402778d, 8.472801d, 8.543408d, 8.614603d, 8.686391d, 8.758778d,
                8.831767d, 8.905365d, 8.979577d, 9.054407d, 9.129860d};
        double capitalPart[] = new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1095.583207d};
        double installment[] = new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1104.713067d};
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.000001d);
            assertEquals(interest[i-1], cti.getInterest(), 0.000001d);
            assertEquals(capitalPart[i-1], cti.getCapitalPart(), 0.000001d);
            assertEquals(installment[i-1], cti.getInstallment(), 0.000001d);
            i++;
        }
    }

    //CapitalAndInterestAtEndCalcTest
    //Input: month, repayment freq: monthly
    @Test
    public void testCalculateCapitalAndInterestAtEnd3() throws Exception {
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 6, CalcRepaymentType.CAPITAL_AND_INTEREST_AT_END, CalcPeriodInputType.MONTH, CalcRepaymentFrequency.MONTHLY).calculate();

        assertEquals(cr.getInterestSum(), 51.053313d, 0.000001d);
        assertEquals(cr.getInstallmentSum(), 1051.053313, 0.000001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 1008.333333d, 1016.736111d, 1025.208912d, 1033.752320d,
                1042.366922d};
        double[] interest = new double[] {8.333333d, 8.402778d, 8.472801d, 8.543408d, 8.614603d, 8.686391d};
        double capitalPart[] = new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1042.366922d};
        double installment[] = new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1051.053313d};
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.000001d);
            assertEquals(interest[i-1], cti.getInterest(), 0.000001d);
            assertEquals(capitalPart[i-1], cti.getCapitalPart(), 0.000001d);
            assertEquals(installment[i-1], cti.getInstallment(), 0.000001d);
            i++;
        }
    }



    /* ********************************************
     *                                            *
     *      CapitalAtEndInterestPerPeriodCalc     *
     *                                            *
     **********************************************/

    //CapitalAtEndInterestPerPeriodCalcTest
    //Input: year, repayment freq: yearly
    @Test
    public void testCalculateCapitalAtEndInterestPerPeriod1() throws Exception {
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 4, CalcRepaymentType.CAPITAL_AT_END_INTEREST_PER_PERIOD, CalcPeriodInputType.YEAR, CalcRepaymentFrequency.YEARLY).calculate();

        assertEquals(400.0d, cr.getInterestSum(), 0.000001d);
        assertEquals(1400.0d, cr.getInstallmentSum(), 0.000001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 1000.0d, 1000.0d, 1000.0d};
        double[] interest = new double[]{100.0d, 100.0d, 100.0d, 100.0d};
        double capitalPart[] = new double[]{0.0d, 0.0d, 0.0d, 1000.0d};
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.000001d);
            assertEquals(interest[i-1], cti.getInterest(), 0.000001d);
            assertEquals(capitalPart[i-1], cti.getCapitalPart(), 0.000001d);
            assertEquals(interest[i-1] + capitalPart[i-1], cti.getInstallment(), 0.000001d);
            i++;
        }
    }

    //CapitalAtEndInterestPerPeriodCalcTest
    //Input: year, repayment freq: monthly
    @Test
    public void testCalculateCapitalAtEndInterestPerPeriod2() throws Exception {
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 1, CalcRepaymentType.CAPITAL_AT_END_INTEREST_PER_PERIOD, CalcPeriodInputType.YEAR, CalcRepaymentFrequency.MONTHLY).calculate();

        assertEquals(100.0d, cr.getInterestSum(), 0.000001d);
        assertEquals(1100.0d, cr.getInstallmentSum(), 0.000001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 1000.0d, 1000.0d, 1000.0d, 1000.0d, 1000.0d, 1000.0d,
                1000.0d, 1000.0d, 1000.0d, 1000.0d, 1000.0d};
        double[] interest = new double[]{8.333333d, 8.333333d, 8.333333d, 8.333333d, 8.333333d, 8.333333d,
                8.333333d, 8.333333d, 8.333333d, 8.333333d, 8.333333d, 8.333333d};
        double capitalPart[] = new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1000.0d};
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.000001d);
            assertEquals(interest[i-1], cti.getInterest(), 0.000001d);
            assertEquals(capitalPart[i-1], cti.getCapitalPart(), 0.000001d);
            assertEquals(interest[i-1] + capitalPart[i-1], cti.getInstallment(), 0.000001d);
            i++;
        }
    }

    //CapitalAtEndInterestPerPeriodCalcTest
    //Input: month, repayment freq: monthly
    @Test
    public void testCalculateCapitalAtEndInterestPerPeriod3() throws Exception {
        CalcResult cr = CalculationFactory.createCalculation(1000.0d, 0.1d, 6, CalcRepaymentType.CAPITAL_AT_END_INTEREST_PER_PERIOD, CalcPeriodInputType.MONTH, CalcRepaymentFrequency.MONTHLY).calculate();

        assertEquals(50.0d, cr.getInterestSum(), 0.000001d);
        assertEquals(1050.0d, cr.getInstallmentSum(), 0.000001d);
        List<CalcTableItem> ct = cr.getCalcTable();

        int i = 1;
        double[] debtOutstanding = new double[] {1000.0d, 1000.0d, 1000.0d, 1000.0d, 1000.0d, 1000.0d};
        double[] interest = new double[]{8.333333d, 8.333333d, 8.333333d, 8.333333d, 8.333333d, 8.333333d};
        double capitalPart[] = new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1000.0d};
        for (CalcTableItem cti : ct) {
            assertEquals(i, cti.getPeriod());
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.000001d);
            assertEquals(interest[i-1], cti.getInterest(), 0.000001d);
            assertEquals(capitalPart[i-1], cti.getCapitalPart(), 0.000001d);
            assertEquals(interest[i-1] + capitalPart[i-1], cti.getInstallment(), 0.000001d);
            i++;
        }
    }
}
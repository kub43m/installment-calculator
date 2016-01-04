import calculation.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Kuba on 2015-12-30.
 */
public class CapitalAtEndInterestPerPeriodCalcTest {

    @Test
    public void testConstructor() throws Exception {
        CapitalAtEndInterestPerPeriodCalc calc = new CapitalAtEndInterestPerPeriodCalc(1000.0d, 0.1d, 4);
        assertEquals(1000.0d, calc.getDebt(), 0.000001d);
        assertEquals(0.1d, calc.getRate(), 0.000001d);
        assertEquals(4, calc.getNoPeriods());
    }

    //values for this test can be found in the accompanying excel spreadsheet "loan_repayment_examples",
    //sheet: 2.CapitalAtEndInterestPerPeriod
    @Test
    public void testCalculate() throws Exception {
        Calculation calc = new CapitalAtEndInterestPerPeriodCalc(1000.0d, 0.1d, 4);
        CalcResult cr = calc.calculate();
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
}
import calculation.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Kuba on 2015-12-30.
 */
public class CapitalAndInterestAtEndCalcTest {

    //values for this test can be found in the accompanying excel spreadsheet "loan_repayment_examples",
    //sheet: 1.CapitalAndInterestAtEnd
    @Test
    public void testCalculate() throws Exception {
        Calculation calc = new CapitalAndInterestAtEndCalc(1000.0d, 0.1d, 4);
        CalcResult cr = calc.calculate();
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
            assertEquals(debtOutstanding[i-1], cti.getDebt(), 0.005d);
            assertEquals(interest[i-1], cti.getInterest(), 0.005d);
            assertEquals(capitalPart[i-1], cti.getCapitalPart(), 0.005d);
            assertEquals(installment[i-1], cti.getInstallment(), 0.005d);
            i++;
        }
    }

    @Test
    public void testConstructor() throws Exception {
        CapitalAndInterestAtEndCalc calc = new CapitalAndInterestAtEndCalc(1000.0d, 0.1d, 4);
        assertEquals(1000.0d, calc.getDebt(), 0.000001d);
        assertEquals(0.1d, calc.getRate(), 0.000001d);
        assertEquals(4, calc.getNoPeriods());
    }
}
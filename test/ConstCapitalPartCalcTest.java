import calculation.*;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;


/**
 * Created by Kuba on 12/23/2015.
 */
public class ConstCapitalPartCalcTest {

    @Test
    public void testCalculate() throws Exception {
        ConstCapitalPartCalc ccpc = new ConstCapitalPartCalc(1000.0d, 0.1d, 4);
        CalcResult cr = ccpc.calculate();
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

    @Test
    public void testConstructor() throws Exception {
        ConstCapitalPartCalc ccpc = new ConstCapitalPartCalc(1000.0d, 0.1d, 4);
        assertEquals(1000.0d, ccpc.getDebt(), 0.000001d);
        assertEquals(0.1d, ccpc.getRate(), 0.000001d);
        assertEquals(4, ccpc.getNoPeriods());
    }
}
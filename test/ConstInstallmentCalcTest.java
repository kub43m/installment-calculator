import calculation.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by Kuba on 12/23/2015.
 */
public class ConstInstallmentCalcTest {

    @Test
    public void testConstructor() throws Exception {
        ConstInstallmentCalc cic = new ConstInstallmentCalc(1000.0d, 0.1d, 4);
        assertEquals(1000.0d, cic.getDebt(), 0.000001d);
        assertEquals(0.1d, cic.getRate(), 0.000001d);
        assertEquals(4, cic.getNoPeriods());
    }

    @Test
    public void testCalculate() throws Exception {
        ConstInstallmentCalc cic = new ConstInstallmentCalc(1000.0d, 0.1d, 4);
        CalcResult cr = cic.calculate();
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

    @Test
    public void testGeomSeriesSum() throws Exception {
        ConstInstallmentCalc cic = new ConstInstallmentCalc(1000.0d, 0.1d, 4);
        assertEquals(3.169865, cic.geomSeriesSum(1/(1+0.1), 1/(1+0.1), 4), 0.000001);
    }

    @Test
    public void testCalcInstallment() throws Exception {
        ConstInstallmentCalc cic = new ConstInstallmentCalc(1000.0d, 0.1d, 4);
        assertEquals(315.470804d, cic.calcInstallment(), 0.000001d);
    }
}

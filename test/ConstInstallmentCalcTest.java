import calculation.CalcPeriodInputType;
import calculation.CalcResult;
import calculation.ConstantInstallmentCalc;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Kuba on 12/23/2015.
 */
public class ConstInstallmentCalcTest {

    @Test
    public void testConstructor() throws Exception {
        ConstantInstallmentCalc cic = new ConstantInstallmentCalc(1000.0d, 0.1d, 4);
        Assert.assertEquals(1000.0d, cic.getDebt(), 0.000001d);
        Assert.assertEquals(0.1d, cic.getRate(), 0.000001d);
        Assert.assertEquals(4, cic.getNoPeriods());
    }

    @Test
    public void testCalculate() throws Exception {
        ConstantInstallmentCalc cic = new ConstantInstallmentCalc(1000.0d, 0.1d, 4);
        CalcResult cr = cic.calculate();
//        Assert.assertNotNull(cr);
    }
}

package calculation;

/**
 * Created by Kuba on 12/22/2015.
 */

import java.util.*;

public class CalcResult {
    private double installmentSum = 0.0d;
    private double interestSum = 0.0d;
    private List<CalcTableItem> calcTable = new ArrayList<>();

    //Getters:
    public List<CalcTableItem> getCalcTable() { return calcTable; }
    public double getInstallmentSum() { return installmentSum; }
    public double getInterestSum() { return interestSum; }

    //Setters:
    public void setInstallmentSum(double installmentSum) { this.installmentSum = installmentSum; }
    public void setInterestSum(double interestSum) { this.interestSum = interestSum; }

    //Adding rows to calcTable:
    public void addRow(CalcTableItem item) { calcTable.add(item); }
}





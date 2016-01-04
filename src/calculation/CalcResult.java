package calculation;

/**
 * Created by Kuba on 12/22/2015.
 */

import java.util.*;

//Class that holds results of installments computation produced by object implementing Calculation interface
public class CalcResult {
    //variables holding total (aggregated)results
    private double installmentSum = 0.0d;
    private double interestSum = 0.0d;
    //list holding elements (capital part, interest, total installment) for individual periods (in a table-like manner)
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





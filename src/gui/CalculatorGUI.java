package gui;

import calculation.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Created by Kuba on 12/27/2015.
 */
public class CalculatorGUI {

    //initial values:
    private double debt = 100000;
    private double rate = 0.1;
    private int noPeriods = 30;

    //general frames and dialogs
    private JFrame mainFrame;
    private static String mainFrameTitleString = "Installment Calculator";
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel resultsPanel;
    private DetailsDialog detailsDialog;


    /* *******************
     ***     INPUT     ***
     *********************/
    //debt value:
    private JPanel debtValPanel;
    private JFormattedTextField debtField;
    private JLabel debtLabel;
    private static String debtString = "Debt value: ";
    private static String debtTooltipString = "Debt value we would like to calculate installments for";
    private NumberFormat debtFormat;
    private NumberFormatter debtFormatter;

    //interest rate value:
    private JPanel interestRatePanel;
    private JFormattedTextField interestRateField;
    private JLabel interestRateLabel;
    private static String interestRateString = "Interest rate: ";
    private static String interestRateTooltipString = "YEARLY interest rate";
    private NumberFormat interestRateFormat;
    private NumberFormatter interestRateFormatter;

    //#periods value:
    private JPanel noPeriodsPanel;
    private JFormattedTextField noPeriodsField;
    private JLabel noPeriodsLabel;
    private static String noPeriodsString = "Number of periods : ";
    private static String noPeriodsTooltipString = "Number of periods (either months or years -> choose on the right)";
    private JComboBox<CalcPeriodInputType> noPeriodsComboBox;
    private JPanel noPeriodsSubpanel;
    private NumberFormat noPeriodsFormat;
    private NumberFormatter noPeriodsFormatter;

    //repayment frequency:
    private JPanel repFreqPanel;
    private JComboBox<CalcRepaymentFrequency> repFreqComboBox;
    private JLabel repFreqLabel;
    private static String repFreqString = "Repayment Frequency: ";

    //repayment type value:
    private JPanel repTypePanel;
    private JComboBox<CalcRepaymentType> repTypeComboBox;
    private JLabel repTypeLabel;
    private static String repTypeString = "Repayment type: ";

    //buttons:
    private JPanel buttonsPanel;
    private JButton calculateButton;
    private JButton detailsButton;
    private static String calculateButtonString = "Calculate";
    private static String detailsButtonString = "Installment Details";


    /* *********************
     ***     RESULTS     ***
     ***********************/
    //CalcResult object:
    CalcResult calcResult;

    //Results Label
    private static String resultsString = "R E S U L T S:";
    private JLabel resultsLabel;
    private JPanel resultsLabelPanel;

    //total repayment
    private JPanel totRepPanel;
    private JLabel totRepLabel;
    private static String totRepString = "Total repayment: ";
    private JFormattedTextField totRepField;
    private NumberFormat totRepFormat;

    //total interest
    private JPanel totInterestPanel;
    private JPanel totInterestSubpanel;
    private JLabel totInterestLabel;
    private static String totInterestString = "Total interest: ";
    private JFormattedTextField totInterestField;
    private JFormattedTextField totInterestPercentField;
    private NumberFormat totInterestFormat;
    private NumberFormat totInterestPercentFormat;

    //total capital part
    private JPanel totCapPartPanel;
    private JPanel totCapPartSubpanel;
    private JLabel totCapPartLabel;
    private static String totCapPartString = "Total capital part: ";
    private JFormattedTextField totCapPartField;
    private JFormattedTextField totCapPartPercentField;
    private NumberFormat totCapPartFormat;
    private NumberFormat totCapPartPercentFormat;


    public static void main(String[] args) {
        CalculatorGUI gui = new CalculatorGUI();
        gui.setUpFormats();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.intitializeGUI();
            }
        });
    }

    private void setUpFormats() {
        //debt value:
        debtFormat = NumberFormat.getNumberInstance();
        debtFormatter = new NumberFormatter(debtFormat);
        debtFormatter.setValueClass(Double.class);
        debtFormatter.setMinimum(0.0d);
        debtFormatter.setMaximum(1000000000000.0); //1 bilion
        debtFormatter.setCommitsOnValidEdit(true);

        //interest rate:
        interestRateFormat = NumberFormat.getPercentInstance();
        interestRateFormat.setMinimumFractionDigits(2);
        interestRateFormatter = new NumberFormatter(interestRateFormat);
        interestRateFormatter.setValueClass(Double.class);
        interestRateFormatter.setMinimum(0.0);
        interestRateFormatter.setMaximum(1.0);
        interestRateFormatter.setCommitsOnValidEdit(true);

        //noPeriods:
        noPeriodsFormat = NumberFormat.getNumberInstance();
        noPeriodsFormatter = new NumberFormatter(noPeriodsFormat);
        noPeriodsFormatter.setValueClass(Integer.class);
        noPeriodsFormatter.setMinimum(0);
        noPeriodsFormatter.setMaximum(1200);
        noPeriodsFormatter.setCommitsOnValidEdit(true);

        //total repayment
        totRepFormat = NumberFormat.getCurrencyInstance();

        //total interest
        totInterestFormat = NumberFormat.getCurrencyInstance();
        totInterestPercentFormat = NumberFormat.getPercentInstance();

        //total capital part
        totCapPartFormat = NumberFormat.getCurrencyInstance();
        totCapPartPercentFormat = NumberFormat.getPercentInstance();
    }


    private void intitializeGUI() {
        //frame settings:
        mainFrame = new JFrame(mainFrameTitleString);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 400);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainFrame.add(mainPanel, BorderLayout.CENTER);

        //Experimental - add 'dummy' panels on each side of the main frame in order to have margins
        mainFrame.add(new JPanel(), BorderLayout.NORTH);
        mainFrame.add(new JPanel(), BorderLayout.SOUTH);
        mainFrame.add(new JPanel(), BorderLayout.WEST);
        mainFrame.add(new JPanel(), BorderLayout.EAST);

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 1));
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(7, 1));
        mainPanel.add(inputPanel);
        mainPanel.add(resultsPanel);

        //look and feel
        /*try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        /* *******************
         ***     INPUT     ***
         *********************/
        //debt value:
        debtValPanel = new JPanel(new GridLayout(1, 2)); //1 row, 2 columns
        debtLabel = new JLabel(debtString);
        debtValPanel.add(debtLabel);
        debtField = new JFormattedTextField(debtFormatter);
        debtField.setValue(debt);
        debtField.setToolTipText(debtTooltipString);
        debtValPanel.add(debtField);
        inputPanel.add(debtValPanel);

        //interest rate value:
        interestRatePanel = new JPanel(new GridLayout(1, 2));
        interestRateLabel = new JLabel(interestRateString);
        interestRatePanel.add(interestRateLabel);
        interestRateField = new JFormattedTextField(interestRateFormatter);
        interestRateField.setValue(rate);
        interestRateField.setToolTipText(interestRateTooltipString);
        interestRatePanel.add(interestRateField);
        inputPanel.add(interestRatePanel);

        //#periods value:
        noPeriodsPanel = new JPanel(new GridLayout(1, 2));
        noPeriodsSubpanel = new JPanel(new GridLayout(1, 2));
        noPeriodsLabel = new JLabel(noPeriodsString);
        noPeriodsPanel.add(noPeriodsLabel);
        noPeriodsPanel.add(noPeriodsSubpanel);
        noPeriodsField = new JFormattedTextField(noPeriodsFormatter);
        noPeriodsField.setValue(noPeriods);
        noPeriodsField.setToolTipText(noPeriodsTooltipString);
        noPeriodsSubpanel.add(noPeriodsField);
        noPeriodsComboBox = new JComboBox<>(CalcPeriodInputType.values());
        noPeriodsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (noPeriodsComboBox.getSelectedItem() == CalcPeriodInputType.MONTH) {
                    repFreqComboBox.setSelectedItem(CalcRepaymentFrequency.MONTHLY);
                    repFreqComboBox.setEnabled(false);
                } else {
                    repFreqComboBox.setEnabled(true);
                }
            }
        });
        noPeriodsSubpanel.add(noPeriodsComboBox);
        inputPanel.add(noPeriodsPanel);

        //repayment frequency:
        repFreqPanel = new JPanel(new GridLayout(1, 2));
        repFreqLabel = new JLabel(repFreqString);
        repFreqPanel.add(repFreqLabel);
        repFreqComboBox = new JComboBox<>(CalcRepaymentFrequency.values());
        repFreqPanel.add(repFreqComboBox);
        inputPanel.add(repFreqPanel);

        //repayment type value:
        repTypePanel = new JPanel(new GridLayout(1, 2));
        repTypeLabel = new JLabel(repTypeString);
        repTypePanel.add(repTypeLabel);
        repTypeComboBox = new JComboBox<>(CalcRepaymentType.values());
        repTypePanel.add(repTypeComboBox);
        inputPanel.add(repTypePanel);

        //dummy panel
        inputPanel.add(new JPanel());

        //buttons:
        buttonsPanel = new JPanel(new GridLayout(1, 2));
        calculateButton = new JButton(calculateButtonString);
        detailsButton = new JButton(detailsButtonString);
        detailsButton.setEnabled(false);
        buttonsPanel.add(calculateButton);
        buttonsPanel.add(detailsButton);
        inputPanel.add(buttonsPanel);



        /* *********************
         ***     RESULTS     ***
         ***********************/
        //results panel - dummy panels
        resultsPanel.add(new JPanel());
        resultsPanel.add(new JPanel());

        //results label:
        resultsLabel = new JLabel(resultsString, SwingConstants.CENTER);
        resultsLabel.setFont(new Font(null, Font.BOLD, 14));
        resultsLabelPanel = new JPanel();
        resultsLabelPanel.setLayout(new BorderLayout());
        resultsLabelPanel.add(resultsLabel, BorderLayout.CENTER);
        resultsLabelPanel.add(new JPanel(), BorderLayout.SOUTH);
        resultsPanel.add(resultsLabelPanel);

        //total repayment
        totRepPanel = new JPanel(new GridLayout(1, 2));
        totRepLabel = new JLabel(totRepString);
        totRepField = new JFormattedTextField(totRepFormat);
        totRepPanel.add(totRepLabel);
        totRepPanel.add(totRepField);
        resultsPanel.add(totRepPanel);
        totRepField.setEditable(false);

        //total interest
        totInterestPanel = new JPanel(new GridLayout(1, 2));
        totInterestSubpanel = new JPanel(new GridLayout(1, 2));
        totInterestField = new JFormattedTextField(totInterestFormat);
        totInterestPercentField = new JFormattedTextField(totInterestPercentFormat);
        totInterestLabel = new JLabel(totInterestString);
        totInterestPanel.add(totInterestLabel);
        totInterestSubpanel.add(totInterestField);
        totInterestSubpanel.add(totInterestPercentField);
        totInterestPanel.add(totInterestSubpanel);
        resultsPanel.add(totInterestPanel);
        totInterestField.setEditable(false);
        totInterestPercentField.setEditable(false);

        //total capital part
        totCapPartPanel = new JPanel(new GridLayout(1, 2));
        totCapPartSubpanel = new JPanel(new GridLayout(1, 2));
        totCapPartField = new JFormattedTextField(totCapPartFormat);
        totCapPartPercentField = new JFormattedTextField(totCapPartPercentFormat);
        totCapPartLabel = new JLabel(totCapPartString);
        totCapPartPanel.add(totCapPartLabel);
        totCapPartSubpanel.add(totCapPartField);
        totCapPartSubpanel.add(totCapPartPercentField);
        totCapPartPanel.add(totCapPartSubpanel);
        resultsPanel.add(totCapPartPanel);
        totCapPartField.setEditable(false);
        totCapPartPercentField.setEditable(false);

        //action listeners
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //get params
                double debt = (Double) debtField.getValue();
                double rate = (Double) interestRateField.getValue();
                int noPeriods = (Integer) noPeriodsField.getValue();
                CalcRepaymentType repType = (CalcRepaymentType) repTypeComboBox.getSelectedItem();
                CalcPeriodInputType inputType = (CalcPeriodInputType) noPeriodsComboBox.getSelectedItem();
                CalcRepaymentFrequency repFreq = (CalcRepaymentFrequency) repFreqComboBox.getSelectedItem();
                //calculate
                //public static CalcResult calculate(double debt, double rate, int noPeriods, CalcRepaymentType repaymentType,
                //  CalcPeriodInputType periodInput, CalcRepaymentFrequency accrualRate)
                calcResult = CalcDispatcher.calculate(debt, rate, noPeriods, repType, inputType, repFreq);
                //set results values
                totRepField.setValue(calcResult.getInstallmentSum());
                totInterestField.setValue(calcResult.getInterestSum());
                totCapPartField.setValue(calcResult.getInstallmentSum() - calcResult.getInterestSum());
                totInterestPercentField.setValue(calcResult.getInterestSum() / calcResult.getInstallmentSum());
                totCapPartPercentField.setValue(1.0d - calcResult.getInterestSum() / calcResult.getInstallmentSum());
                detailsButton.setEnabled(true); //enable details button
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                detailsDialog = new DetailsDialog(null, calcResult);
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        detailsDialog.setVisible(true);
                    }
                });
            }
        });

        //display frame:
        mainFrame.setVisible(true);
    }
}





class DetailsDialog extends JDialog {

    private static String titleString = "Repayment details"; //dialog window title

    private static String periodColumnString = "Period";
    private static String debtOutstandingColumnString = "Debt Outstanding";
    private static String interestColumnString = "Interest paid";
    private static String capitalPartColumnString = "Capital part paid";
    private static String installmentColumnString = "Installment paid";

    private static String[] columns = { periodColumnString, debtOutstandingColumnString, interestColumnString,
            capitalPartColumnString, installmentColumnString };

    private CalcResult result;
    private Object[][] data;

    private JScrollPane tableScrollPane;
    private JTable detailsTable;

    private JPanel closeButtonPanel;
    private JButton closeButton;
    private static String closeButtonString = "Close";

    private JPanel summaryPanel;
    private static String summaryPanelString = "Installment Details:";
    private JLabel summaryPanelLabel;

    public DetailsDialog(JFrame parent, CalcResult result) {
        //basic elements of dialog window
        super(parent, titleString, true);
        setSize(600,800);
        this.result = result;
        //method to prepare data:
        prepareDialog();
    }

    private void prepareDialog() {
        //set up summary panel
        createSummaryPanel();
        //set up table data
        setUpTableData();
        //create table
        createTable();
        //create close button
        createCloseButton();
        //adding elements
        addElements();
    }

    private void createSummaryPanel() {
        summaryPanel = new JPanel();
        summaryPanel.setLayout(new BorderLayout());
        summaryPanelLabel = new JLabel(summaryPanelString, SwingConstants.CENTER);
        summaryPanelLabel.setFont(new Font(null, Font.BOLD, 14));
        summaryPanel.add(new JPanel(), BorderLayout.NORTH);
        summaryPanel.add(new JPanel(), BorderLayout.SOUTH);
        summaryPanel.add(summaryPanelLabel, BorderLayout.CENTER);
    }

    private void createTable() {
        //        detailsTable = new JTable(data, columns); //wersja z Default table model - columns musi byc typu Object[]
        detailsTable = new JTable(new DetailsTableModel(columns, data));
        tableScrollPane = new JScrollPane(detailsTable);
        TableColumnModel columnModel = detailsTable.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(new IndexRenderer());
        columnModel.getColumn(1).setCellRenderer(new CurrencyRenderer());
        columnModel.getColumn(2).setCellRenderer(new CurrencyRenderer());
        columnModel.getColumn(3).setCellRenderer(new CurrencyRenderer());
        columnModel.getColumn(4).setCellRenderer(new CurrencyRenderer());
    }

    private void setUpTableData() {
        List<CalcTableItem> resultsList = result.getCalcTable();
        int noRows = resultsList.size();
        data = new Object[noRows][5];
        for (int i = 0; i<noRows; i++) {
            CalcTableItem item = resultsList.get(i);
            data[i][0] = item.getPeriod();
            data[i][1] = item.getDebt();
            data[i][2] = item.getInterest();
            data[i][3] = item.getCapitalPart();
            data[i][4] = item.getInstallment();
        }
    }

    private void createCloseButton() {
        closeButtonPanel = new JPanel();
        closeButtonPanel.setLayout(new BorderLayout());
        closeButton = new JButton(closeButtonString);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes the dialog
            }
        });
        closeButtonPanel.add(new JPanel(), BorderLayout.SOUTH);
        closeButtonPanel.add(new JPanel(), BorderLayout.NORTH);
        closeButtonPanel.add(new JPanel(), BorderLayout.WEST);
        closeButtonPanel.add(new JPanel(), BorderLayout.EAST);
        JPanel innerJPanel = new JPanel();
        innerJPanel.setLayout(new GridLayout(1, 3));
        innerJPanel.add(new JPanel());
        innerJPanel.add(closeButton);
        innerJPanel.add(new JPanel());
        closeButtonPanel.add(innerJPanel, BorderLayout.CENTER);
    }

    private void addElements() {
        add(tableScrollPane, BorderLayout.CENTER);
        add(closeButtonPanel, BorderLayout.SOUTH);
        add(summaryPanel, BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);
    }


    private class DetailsTableModel extends AbstractTableModel {

        String[] columnNames;
        Object[][] data;

        public DetailsTableModel(String[] columnNames, Object[][] data){
            this.columnNames = columnNames;
            this.data = data;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        //JTable uses this method to determine the default renderer/editor for each cell.
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        //set cells to not-editable
        public boolean isCellEditable(int row, int col) {
            return false;
        }

    }

}



//For testing:
//                JOptionPane.showMessageDialog(null, "The values are:\n" +
//                            "debt = " + debt + "\n" +
//                            "rate = " + rate + "\n" +
//                            "noPeriods = " + noPeriods + "\n" +
//                            "repType = " + repType + "\n" +
//                            "inputType = " + inputType + "\n" +
//                            "repFreq = " + repFreq + "\n",
//                        "Test", JOptionPane.INFORMATION_MESSAGE);



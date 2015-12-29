package gui;

import calculation.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Created by Kuba on 12/27/2015.
 */
public class CalculatorGUI {

    //initial values:
    private double debt = 100000;
    private double rate = 0.1;
    private int noPeriods = 30;

    private JFrame mainFrame;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel resultsPanel;
    private static String resultsString = "Results:";

    /* ***    INPUT   *** */
    //debt value:
    private JPanel debtValPanel;
    private JFormattedTextField debtField;
    private JLabel debtLabel;
    private static String debtString = "Debt value: ";
    private static String debtTooltipString = "Debt value we would like to repay";
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

    /* ***    RESULTS   *** */
    //total repayment
    private JPanel totRepPanel;
    private JLabel totRepLabel;
    private static String totRepString = "Total repayment: ";
    private JFormattedTextField totRepField;
    private NumberFormat totRepFormat;
//    private NumberFormatter totRepFormatter;

    //total interest
    private JPanel totInterestPanel;
    private JPanel totInterestSubpanel;
    private JLabel totInterestLabel;
    private static String totInterestString = "Total interest: ";
    private JFormattedTextField totInterestField;
    private JFormattedTextField totInterestPercentField;
    private NumberFormat totInterestFormat;
//    private NumberFormatter totInterestFormatter;
    private NumberFormat totInterestPercentFormat;
//    private NumberFormatter totInterestPercentFormatter;

    //total capital part
    private JPanel totCapPartPanel;
    private JPanel totCapPartSubpanel;
    private JLabel totCapPartLabel;
    private static String totCapPartString = "Total capital part: ";
    private JFormattedTextField totCapPartField;
    private JFormattedTextField totCapPartPercentField;
    private NumberFormat totCapPartFormat;
//    private NumberFormatter totCapPartFormatter;
    private NumberFormat totCapPartPercentFormat;
//    private NumberFormatter totCapPartPercentFormatter;


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
        mainFrame = new JFrame("Installment Calculator");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 400);
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainFrame.add(mainPanel);
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 1));
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(7, 1));
        mainPanel.add(inputPanel);
        mainPanel.add(resultsPanel);

        //look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        /* ***    INPUT   *** */
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
        buttonsPanel.add(calculateButton);
        buttonsPanel.add(detailsButton);
        inputPanel.add(buttonsPanel);

        //results panel - dummy panels
        resultsPanel.add(new JPanel());
        resultsPanel.add(new JPanel());

        //results label:
        resultsPanel.add(new JLabel(resultsString));


        /* ***    RESULTS   *** */
        //total repayment
        totRepPanel = new JPanel(new GridLayout(1, 2));
        totRepLabel = new JLabel(totRepString);
        totRepField = new JFormattedTextField(totRepFormat);
        totRepPanel.add(totRepLabel);
        totRepPanel.add(totRepField);
        resultsPanel.add(totRepPanel);
        totRepField.setValue(150000); //test only!
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
        totInterestField.setValue(50000); //test only!
        totInterestPercentField.setValue(0.33); //test only!
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
        totCapPartField.setValue(100000);
        totCapPartPercentField.setValue(0.67);
        totCapPartField.setEditable(false);
        totCapPartPercentField.setEditable(false);

        //display frame:
        mainFrame.setVisible(true);
    }
}

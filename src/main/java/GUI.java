import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import edu.willamette.cs1.wordle.WordleDictionary;

public class GUI extends JFrame implements ActionListener {

    //int count = 1;
    private static JLabel bLabel;
    private static JLabel bLabel1;
    private static JFrame frame;
    private static JPanel panel;
    private static JTextField textField;
    private static JComboBox bentoBox;
    private static JComboBox caliBox;
    private Double myInput;
    private Double result;
    private Double randNum;
    private String focusMsg;
    //int myIndex = 0;


    public GUI() throws Exception {
        JButton button = new JButton("Submit");
        JButton button1 = new JButton("Clear");
        JButton button2 = new JButton("Copy result");
        button.addActionListener(this);
        button1.addActionListener(this);
        //bLabel = new JLabel("Multiply by 6!: 0");
        bLabel = new JLabel("Welcome! Relax while we're settings things up for you. ", SwingConstants.CENTER);
        bLabel1 = new JLabel("to", SwingConstants.CENTER);
        frame = new JFrame();
        panel = new JPanel();
        textField = new JTextField(focusMsg, 16);

        randNum = (double) Math.round((Math.random() * (250.75 - 1.25 + 1) + 1.25) * 100) / 100;
        focusMsg = "Try entering " + randNum + "!";

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(textField.getText().equals(focusMsg)){
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (textField.getText().equals("")){
                    textField.setText(focusMsg);
                }
            }
        });



        //OLD ARRAY
        //String[] myArray = {"USD", "CAD", "PHP"};
        currencyConvert myConvert = new currencyConvert(myInput);
        String[] myArray = myConvert.getCur();
        bentoBox = new JComboBox<>(myArray);
        caliBox = new JComboBox<>(myArray);
        caliBox.setSelectedIndex(1);


        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(textField);
        panel.add(bentoBox);
        panel.add(bLabel1);
        panel.add(caliBox);
        panel.add(button);
        panel.add(button1);
        panel.add(bLabel);
        panel.add(button2);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 100));
        frame.setTitle("iConvert");
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //count *= 6;
        //bLabel.setText("Multiply by 6! \n" + count);

        //Display random ass string
        //bLabel.setText("Word of the day: " + getMyStr());

        String s = actionEvent.getActionCommand();
        //Print user's input from text field
        if (s.equals("Submit")) {
            //Check if user does not enter a number.
            if(textField.getText().equals(focusMsg)){
                bLabel.setText("Oops! Seems like you forgot to delete the text before " + randNum + "!");
            }
            else if (textField.getText() != null) {
                bLabel.setText("Please enter a number!");
            }
        }
        //Clear textbox
        else if (s.equals("Clear")) {
            if(!(textField.equals(focusMsg))) {
                textField.setText("");
                bLabel.setText("Text successfully cleared!");
            }
        }
        //Copy to clipboard
        else if (s.equals("Copy result")){
            Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection str1 = new StringSelection("Testing");
            clip.setContents(str1, str1);
        }

        //Check if user entered a negative number
        if (Double.parseDouble(textField.getText().trim()) < 0) {
            bLabel.setText("Please enter a positive number!");
        }

        //Lines below are for currency conversion
        myInput = Double.parseDouble(textField.getText().trim());
        currencyConvert myConvert = new currencyConvert(myInput);

        //Converting currency using FxRatesAPI.
        try {
            if (Double.parseDouble(textField.getText().trim()) > 0) {
                result = Math.round((myConvert.convert(bentoBox.getSelectedItem().toString(), caliBox.getSelectedItem().toString(), myInput) * 100)) / 100.0;
                bLabel.setText("Result: " + result + " " + caliBox.getSelectedItem().toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please connect to the internet!");
        }


        //Converting USD to any other currency
        /* OLD HARDCODED CONVERSION
        if (bentoBox.getSelectedItem().equals("USD") && (caliBox.getSelectedItem().equals("CAD"))) {
            if (Double.parseDouble(textField.getText().trim()) > 0) {
                bLabel.setText(myInput + "USD --> " + myConvert.convertUSDToCAD(myInput) + " CAD");
            }
        } else if (bentoBox.getSelectedItem().equals("USD") && caliBox.getSelectedItem().equals(("PHP"))) {
            if (Double.parseDouble(textField.getText().trim()) > 0) {
                bLabel.setText((myInput + " USD --> " + myConvert.convertUSDToPHP(myInput) + " PHP"));
            }
        } else if (bentoBox.getSelectedItem().equals("USD") && caliBox.getSelectedItem().equals("USD")) {
            bLabel.setText("Cannot convert USD to USD!");
        }

        //Converting CAD to any other currency
        if (bentoBox.getSelectedItem().equals("CAD") && caliBox.getSelectedItem().equals("USD")) {
            if (Double.parseDouble(textField.getText().trim()) > 0) {
                bLabel.setText((myInput + " CAD --> " + myConvert.convertCADtoUSD(myInput) + " USD"));
            }
        } else if (bentoBox.getSelectedItem().equals("CAD") && caliBox.getSelectedItem().equals("PHP")) {
            if (Double.parseDouble(textField.getText().trim()) > 0) {
                bLabel.setText((myInput + " CAD --> " + myConvert.convertCADtoPHP(myInput) + " PHP"));
            }
        } else if (bentoBox.getSelectedItem().equals("CAD") && caliBox.getSelectedItem().equals("CAD")) {
            bLabel.setText("Cannot convert CAD to CAD!");
        }
        */


        //Testing if program can print specific string based on what they chose from the drop-down menu.
        /*
        if(caliBox.getSelectedItem().equals("PHP")){
            bLabel.setText(("LIVE FROM NEW YORK, IT'S SATURDAY NIGHT!"));
        }
         */



        //Testing if program can print something if a user enters a specific string.
        /*
        if(s.equals("Submit")){
            if(textField.getText().equals("HOLY CRAP!!"))
            {
                bLabel.setText("YOU WEREN'T SUPPOSED TO FIND THIS!");
            }
        }
        */

        //Clear text
        /*
        else if(s.equals("Clear")){
            textField.setText("");
            bLabel.setText("Text successfully cleared!");
        }
         */
    }







    /* Testing retrieving random string from library
    private String getMyStr() {
        myIndex = 0;
        String myStr = "";
        for (int i = 0; i < WordleDictionary.FIVE_LETTER_WORDS.length; i++) {
            myIndex = (int) (Math.random() * WordleDictionary.FIVE_LETTER_WORDS.length);
            myStr = WordleDictionary.FIVE_LETTER_WORDS[myIndex];
        }
        return myStr;
        }
       */
        
        public Double getTextField(){
            return myInput;
        }

        /* Method to retrieve focus message
        public String getfocusMsg(){
            randNum = (double) Math.round((Math.random() * (250.75 - 1.25 + 1) + 1.25) * 100) / 100;
            return "Try entering " + randNum + "!";
        }
         */

        /* Retrieve random Double
        public Double getrandNum(){
            randNum = (double) Math.round((Math.random() * (250.75 - 1.25 + 1) + 1.25) * 100) / 100;
                    return randNum;
        }
         */
    }


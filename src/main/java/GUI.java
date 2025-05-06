import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GUI extends JFrame implements ActionListener {


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



    public GUI() throws Exception {
        JButton button = new JButton("Submit");
        JButton button1 = new JButton("Clear");
        JButton button2 = new JButton("Copy result");
        button.addActionListener(this);
        button1.addActionListener(this);
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
    }
}


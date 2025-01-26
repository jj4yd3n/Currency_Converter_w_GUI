import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import edu.willamette.cs1.wordle.WordleDictionary;

public class GUI extends JFrame implements ActionListener {

    int count = 1;
    private static JLabel bLabel;
    private static JLabel bLabel1;
    private static JFrame frame;
    private static JPanel panel;
    private static JTextField textField;
    private static JComboBox bentoBox;
    private static JComboBox caliBox;
    private Double myInput;
    int myIndex = 0;




    public GUI(){

        JButton button = new JButton("Submit");
        JButton button1 = new JButton("Clear");
        button.addActionListener(this);
        button1.addActionListener(this);
         //bLabel = new JLabel("Multiply by 6!: 0");
        bLabel = new JLabel("Welcome! Relax while we're settings things up for you. ");
        bLabel1 = new JLabel("to", SwingConstants.CENTER);
         frame = new JFrame();
         panel = new JPanel();
         textField = new JTextField("enter name",16);

         String[] myArray = {"USD", "CAD", "PHP"};
          bentoBox = new JComboBox<>(myArray);
          caliBox = new JComboBox<>(myArray);
        caliBox.setSelectedIndex(1);


        panel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        panel.setLayout(new GridLayout(0,1));
        panel.add(textField);
        panel.add(bentoBox);
        panel.add(bLabel1);
        panel.add(caliBox);
        panel.add(button);
        panel.add(button1);
        panel.add(bLabel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,100));
        frame.setTitle("Adobe Photoshop 2016");
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
        if(s.equals("Submit")){
            if(Double.parseDouble(textField.getText().trim()) >= 0) {
                myInput = Double.parseDouble(textField.getText().trim());
                bLabel.setText("You entered: " + myInput);
            }
            else if (Double.parseDouble(textField.getText().trim()) < 0){
                bLabel.setText("Please enter a positive number!");
            }
            else{
                bLabel.setText("Please enter a number!");
            }
        }
        else if(s.equals("Clear")){
            textField.setText("");
            bLabel.setText("Text successfully cleared!");
        }



        //Testing if program can print specific string based on what they chose from the drop-down menu.
        /*
        if(caliBox.getSelectedItem().equals("PHP")){
            bLabel.setText(("LIVE FROM NEW YORK, IT'S SATURDAY NIGHT!"));
        }
         */

        if (caliBox.getSelectedItem().equals("CAD")){
            if(Double.parseDouble(textField.getText().trim()) >= 0) {
                myInput = Double.parseDouble(textField.getText().trim());
                currencyConvert myConvert = new currencyConvert(myInput);
                bLabel.setText(myInput + "USD --> " + myConvert.convertToCAD(myInput) + " CAD");
            }
            else if (Double.parseDouble(textField.getText().trim()) < 0){
                bLabel.setText("Please enter a positive number!");
            }
            else{
                bLabel.setText("Please enter a number!");
            }
        }


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





    private String getMyStr() {
        int myIndex = 0;
        String myStr = "";
        for (int i = 0; i < WordleDictionary.FIVE_LETTER_WORDS.length; i++) {
            myIndex = (int) (Math.random() * WordleDictionary.FIVE_LETTER_WORDS.length);
            myStr = WordleDictionary.FIVE_LETTER_WORDS[myIndex];
        }
        return myStr;
        }
        
        public Double getTextField(){
            return myInput;
        }
    }


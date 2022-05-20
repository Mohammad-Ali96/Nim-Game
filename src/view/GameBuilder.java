package view;

import controller.ConstructeurJeu;
import model.Joueur;
import model.Tas;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GameBuilder extends JFrame {
    
    private JLabel plyer1_label;
    private JLabel plyer2_label;
    private JLabel numberRow_label;
    private JCheckBox rule_checkBox;
    private JLabel rule_label;
    
    private JTextField player1_textField;
    private JTextField player2_textField;
    private JTextField row_textField;//For Number of row
    private JTextField rule_textField; // For detect Amount of movement

    private JButton startGame_btn;
    private JButton exit_btn;
    
    public GameBuilder() {
        this.setVisible(true);
        this.setSize(500, 270);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Game Setting");
        this.setResizable(false);
        this.setLayout(null);
        
        plyer1_label = new JLabel("Name of player one: ");
        plyer2_label = new JLabel("Name of player two: ");
        numberRow_label = new JLabel("Number of Rows: ");
        player1_textField = new JTextField();
        player2_textField = new JTextField();
        row_textField = new JTextField();
        rule_textField = new JTextField();
        // set this to enable when select role
        rule_textField.setEnabled(false);
        rule_checkBox = new JCheckBox("Specify the maximum number for remove matches");
        
        startGame_btn = new JButton("Start Nim Game");
        exit_btn = new JButton("Exit");

        //Add Components to GUI
        this.add(plyer1_label);
        this.add(plyer2_label);
        this.add(numberRow_label);
        this.add(player1_textField);
        this.add(player2_textField);
        this.add(row_textField);
        this.add(rule_checkBox);
        this.add(rule_textField);
        this.add(startGame_btn);
        this.add(exit_btn);

        // Determine the height and width and position for each components
        plyer1_label.setBounds(10, 10, 120, 30);
        player1_textField.setBounds(140, 15, 180, 25);
        plyer2_label.setBounds(10, 40, 120, 40);
        player2_textField.setBounds(140, 50, 180, 25);
        numberRow_label.setBounds(10, 85, 180, 25);
        row_textField.setBounds(140, 85, 180, 25);
        rule_checkBox.setBounds(12, 120, 320, 25);
        rule_textField.setBounds(342, 120, 60, 25);
        
        startGame_btn.setBounds(120, 180, 160, 25);
        exit_btn.setBounds(290, 180, 160, 25);
        
        addActionListenerForCheckBox();
        addActionListenerForExit();
        // this is a frame
        addActionListenerForStartGame(this);
        
    }
    
    private void addActionListenerForCheckBox() {
        rule_checkBox.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                rule_textField.setEnabled(!rule_textField.isEnabled());
            }
        });
    }
    
    private void addActionListenerForExit() {
        exit_btn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    private void addActionListenerForStartGame(JFrame frame) {
        startGame_btn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name1 = player1_textField.getText();
                    String name2 = player2_textField.getText();
                    int row = Integer.parseInt(row_textField.getText());
                    
                    if(row >= 1 && row <= 7){
                        int maxMoves = 0;
                    if (rule_checkBox.isSelected()) {
                        maxMoves = Integer.parseInt(rule_textField.getText());
                        if(maxMoves <1 || maxMoves > 2*row -1 ){
                            JOptionPane.showMessageDialog(null,"maxmoves should between 1 .. " + (2*row -1));
                            rule_textField.setText("");
                            return;
                        }
                        
                        
                    }
                    Joueur player1 = new Joueur(name1, 0);
                    Joueur player2 = new Joueur(name2, 0);
                    Tas tas = new Tas(row, maxMoves);
                    
                    frame.setVisible(false);
                    frame.dispose();
                    ConstructeurJeu nim_GUI = new ConstructeurJeu(player1, player2, tas);
                    }else{
                        JOptionPane.showMessageDialog(null, "Please input number of rows between 1 .. 7");
                        
                    }
                    
                    
                    
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Please check your inputs");
                    row_textField.setText("");
                    rule_textField.setText("");
                }
            }
        });
    }
    
}

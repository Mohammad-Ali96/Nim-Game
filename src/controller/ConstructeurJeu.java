package controller;

import model.Joueur;
import model.Tas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ConstructeurJeu extends JFrame {

    private Joueur player1;
    private Joueur player2;
    private Tas tas;

    private ArrayList<JButton> buttons1;
    private ArrayList<JButton> buttons2;
    private ArrayList<JLabel> labels;

    private JLabel plyer1_label;
    private JLabel plyer2_label;
    private JLabel plyer1Role_label;
    private JLabel plyer2Role_label;

    private JButton player1Switching_btn;
    private JButton player2Switching_btn;

    private boolean isPlayer1Playing;
    private boolean isPlayer1Played;
    private boolean isPlayer2Played;

    private int row1;
    private int row2;

    private int numOfMoves1 = 0;
    private int numOfMoves2 = 0;

    public ConstructeurJeu(Joueur player1, Joueur player2, Tas tas) {
        this.player1 = player1;
        this.player2 = player2;
        this.tas = tas;

        isPlayer1Playing = true;
        isPlayer1Played = false;
        isPlayer2Played = false;

        this.setVisible(true);
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Nim Game");
        this.setResizable(false);
        this.setLayout(null);

        plyer1_label = new JLabel("player one: " + player1.getName());
        plyer2_label = new JLabel("player two: " + player2.getName());

        plyer1Role_label = new JLabel("<html><body bgcolor='green'><pre>    </pre></body></html>");
        plyer2Role_label = new JLabel("<html><body bgcolor='red'><pre>    </pre></body></html>");

        player1Switching_btn = new JButton("Switch playing to " + player2.getName());
        player2Switching_btn = new JButton("Switch playing to " + player1.getName());
        player1Switching_btn.setEnabled(false);
        player2Switching_btn.setEnabled(false);

        buttons1 = new ArrayList<>();
        buttons2 = new ArrayList<>();
        labels = new ArrayList<>();
        int yy = 40;
        for (int i = 0; i < tas.getNumberOfRows(); i++) {
            int xx = 700;
            JButton btn1 = new JButton("Row " + (i + 1));
            JButton btn2 = new JButton("Row " + (i + 1));
            btn2.setEnabled(false);
            buttons1.add(btn1);
            buttons2.add(btn2);
            this.add(btn1);
            this.add(btn2);
            btn2.setBounds(xx + 100, yy + 40, 80, 20);
            btn1.setBounds(xx - (tas.getNumberOfRows() * 50), yy + 40, 80, 20);

            for (int j = 1; j <= tas.getCoupInRow(i).getMatchesNum(); j++) {
                JLabel label = new JLabel();
                URL iconImage = getClass().getResource("../images/match.png");
                ImageIcon image = new ImageIcon(iconImage);
                label.setIcon(image);
                label.setVisible(true);
                labels.add(label);
                this.add(label);

                label.setBounds(xx, yy, 60, 90);
                xx -= 20;
            }
            yy += 100;
        }

        this.add(plyer1_label);
        this.add(plyer2_label);
        this.add(plyer1Role_label);
        this.add(plyer2Role_label);
        this.add(player1Switching_btn);
        this.add(player2Switching_btn);

        plyer1_label.setBounds(120, 20, 200, 40);
        plyer2_label.setBounds(1050, 20, 200, 40);

        plyer1Role_label.setBounds(80, 20, 40, 40);
        plyer2Role_label.setBounds(1000, 20, 40, 40);

        player1Switching_btn.setBounds(120, 120, 200, 24);
        player2Switching_btn.setBounds(950, 120, 200, 24);

        JFrame tmp = this;
        for (int i = 0; i < buttons1.size(); i++) {
            buttons1.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // remove matches
                    if (!isPlayer1Played) {
                        isPlayer1Played = true;
                        player1Switching_btn.setEnabled(true);
                        row1 = buttons1.indexOf(e.getSource());
                        System.out.println("__________" + row1);

                    }

                    if (row1 == buttons1.indexOf(e.getSource())) {

                        if (tas.getMaxMoves() > 0) {
                            if (numOfMoves1 < tas.getMaxMoves()) {
                                tas.getCoupInRow(row1).removeOneMatch();
                                numOfMoves1++;
                                paintMatches(tmp);
                            } else {
                                JOptionPane.showMessageDialog(null, "You can't remove either matches");
                            }

                        } else {
                            tas.getCoupInRow(row1).removeOneMatch();
                            numOfMoves1++;
                            paintMatches(tmp);
                        }

                    }

                }
            });
        }

        for (int i = 0; i < buttons2.size(); i++) {
            buttons2.get(i).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    // remove matches
                    // remove matches
                    if (!isPlayer2Played) {
                        isPlayer2Played = true;
                        player2Switching_btn.setEnabled(true);
                        row2 = buttons2.indexOf(e.getSource());
                        System.out.println("__________" + row2);

                    }

                    if (row2 == buttons2.indexOf(e.getSource())) {

                        if (tas.getMaxMoves() > 0) {
                            if (numOfMoves2 < tas.getMaxMoves()) {
                                tas.getCoupInRow(row2).removeOneMatch();
                                numOfMoves2++;
                                paintMatches(tmp);
                            } else {
                                JOptionPane.showMessageDialog(null, "You can't remove either matches");
                            }

                        } else {
                            tas.getCoupInRow(row2).removeOneMatch();
                            numOfMoves2++;
                            paintMatches(tmp);
                        }

                    }

                }
            });
        }

        switchingListiner(this);

    }

    private void switchingListiner(JFrame frame) {
        player1Switching_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (checkIfGameEnd()) {
                    JOptionPane.showMessageDialog(null, "The player one " + player1.getName()
                            + " won !!\n Game over " + player2.getName() + " ):");

                    player1.setWinningRounds(player1.getWinningRounds() + 1);
                    System.out.println(player1.getName() + "  " + player1.getWinningRounds());
                    initNimGame(frame);

                } else {
                    switchPlaying();
                }

            }

        });

        player2Switching_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (checkIfGameEnd()) {
                    JOptionPane.showMessageDialog(null, "The player tow " + player2.getName()
                            + " won !!\n Game over " + player1.getName() + " ):");

                    player2.setWinningRounds(player2.getWinningRounds() + 1);
                    System.out.println(player2.getName() + "  " + player2.getWinningRounds());
                    initNimGame(frame);

                } else {
                    switchPlaying();
                }

            }
        });

    }

    private void switchPlaying() {
        if (isPlayer1Playing) {
            if (isPlayer1Played) {
                // switch to player two
                isPlayer1Playing = false;
                isPlayer1Played = false;
                row1 = -1;
                numOfMoves1 = 0;
                player1Switching_btn.setEnabled(false);
                player2Switching_btn.setEnabled(false);

                plyer1Role_label.setText("<html><body bgcolor='red'><pre>    </pre></body></html>");
                plyer2Role_label.setText("<html><body bgcolor='green'><pre>    </pre></body></html>");

                for (int i = 0; i < buttons1.size(); i++) {
                    buttons1.get(i).setEnabled(false);
                }
                for (int i = 0; i < buttons2.size(); i++) {

                    if (tas.getCoupInRow(i).getMatchesNum() > 0) {
                        buttons2.get(i).setEnabled(true);
                    }
                }

            } else {

            }

        } else {
            if (isPlayer2Played) {
                // switch to player one
                isPlayer1Playing = true;
                isPlayer2Played = false;
                row2 = -1;
                numOfMoves2 = 0;

                player1Switching_btn.setEnabled(false);
                player2Switching_btn.setEnabled(false);

                plyer1Role_label.setText("<html><body bgcolor='green'><pre>    </pre></body></html>");
                plyer2Role_label.setText("<html><body bgcolor='red'><pre>    </pre></body></html>");

                for (int i = 0; i < buttons1.size(); i++) {
                    if (tas.getCoupInRow(i).getMatchesNum() > 0) {
                        buttons1.get(i).setEnabled(true);
                    }
                }
                for (int i = 0; i < buttons2.size(); i++) {
                    buttons2.get(i).setEnabled(false);
                }

            } else {

            }
        }
    }

    public void paintMatches(JFrame frame) {
        for (int i = 0; i < labels.size(); i++) {
            labels.get(i).setVisible(false);

        }

        int yy = 40;
        for (int i = 0; i < tas.getNumberOfRows(); i++) {
            int xx = 700;

            for (int j = 1; j <= tas.getCoupInRow(i).getMatchesNum(); j++) {
                JLabel label = new JLabel();
                
                URL iconImage = getClass().getResource("../images/match.png");
                ImageIcon image = new ImageIcon(iconImage);
                label.setIcon(image);
                label.setVisible(true);
                labels.add(label);
                frame.add(label);

                label.setBounds(xx, yy, 60, 90);
                xx -= 20;
            }
            yy += 100;
        }
    }

    private boolean checkIfGameEnd() {
        int sum = 0;
        for (int i = 0; i < tas.getNumberOfRows(); i++) {
            sum += tas.getCoupInRow(i).getMatchesNum();
        }
        return sum > 0 ? false : true;
    }

    private void initNimGame(JFrame frame) {
        int confirm = JOptionPane.showConfirmDialog(null, "Do you want to continue playing,"
                + " press ok to contine or no or cancel for exit and show results");

        if (confirm == 1 || confirm == 2) {
            String msg = "The player one " + player1.getName() + " wons in " + player1.getWinningRounds()
                    + " matches and losses in " + player2.getWinningRounds() + " matches "
                    + "The player two " + player2.getName() + " wons in " + player2.getWinningRounds()
                    + " matches and losses in " + player1.getWinningRounds() + " matches ";
            JOptionPane.showMessageDialog(null, msg);
            writeToFile(msg, "result.txt");
            System.exit(0);
        } else {

            int row = -1;

            while (row == -1) {
                try {
                    String s = JOptionPane.showInputDialog("Please Enter the number of rows");

                    row = Integer.parseInt(s);
                } catch (Exception ee) {
                    row = -1;
                    JOptionPane.showMessageDialog(null, "Please input just number");

                }
            }

            if (row >= 1 && row <= 7) {
                Tas tmpTas = new Tas(row, tas.getMaxMoves());
                frame.setVisible(false);
                frame.dispose();
                ConstructeurJeu nim_GUI = new ConstructeurJeu(player1, player2, tmpTas);
            } else {
                JOptionPane.showMessageDialog(null, "Please input number of rows between 1 .. 7");

            }

        }
    }

    public void writeToFile(String txt, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(txt);
            fileWriter.close();
        } catch (Exception r) {

        }

    }

}

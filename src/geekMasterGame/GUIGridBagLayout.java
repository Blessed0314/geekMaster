package geekMasterGame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is used for ...
 * @autor Christian Daniel Villegas christian.villegas@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class GUIGridBagLayout extends JFrame {

    private PanelImage panelImage;
    private Header headerProject;
    private JPanel panelActive, panelInactive, panelUsed;
    private JLabel dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8, dice9, dice10;
    private JButton roll, rules, reset, exit, powers;
    private ImageIcon imageIcon, background;
    private Image image;
    private TitledBorder titledBorder;
    private Listener listener;
    private Actions diceActions;
    private String[] faces;
    private GridBagConstraints constraints;

    private ModelGame modelGame;

    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout() {
        initGUI();
        //Default JFrame configuration
        this.setTitle("Play Craps");
        this.setUndecorated(true);
        this.setBackground(new Color(255, 255, 255, 192));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        //Set background image
        background = new ImageIcon(getClass().getResource("/resources/fondo.png"));
        image = background.getImage();
        //Create Listener Object and Control Object
        listener = new Listener();
        modelGame = new ModelGame();
        diceActions = new Actions();
        //Set up JComponents

        //First row from GridBagLayout
        headerProject = new Header("Geek Master Game", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        add(headerProject, constraints);

        rules = new JButton("                           Rules                           ");
        powers = new JButton("                          Powers                          ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(rules, constraints);
        constraints.anchor = GridBagConstraints.LINE_END;
        add(powers, constraints);

        reset = new JButton("                            Reset                            ");
        exit = new JButton("                            Exit                            ");
        exit.addActionListener(listener);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(reset, constraints);
        constraints.anchor = GridBagConstraints.LINE_END;
        add(exit, constraints);

        imageIcon = new ImageIcon(getClass().getResource("/resources/Meeple.png"));
        dice1 = new JLabel(imageIcon);
        dice1.addMouseListener(diceActions);
        dice2 = new JLabel(imageIcon);
        dice2.addMouseListener(diceActions);
        dice3 = new JLabel(imageIcon);
        dice3.addMouseListener(diceActions);
        dice4 = new JLabel(imageIcon);
        dice4.addMouseListener(diceActions);
        dice5 = new JLabel(imageIcon);
        dice5.addMouseListener(diceActions);
        dice6 = new JLabel(imageIcon);
        dice6.addMouseListener(diceActions);
        dice7 = new JLabel(imageIcon);
        dice7.addMouseListener(diceActions);
        panelActive = new PanelImage(image);
        panelActive.setPreferredSize(new Dimension(500, 250));
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Active Dices: ");
        titledBorder.setTitleColor(Color.WHITE);
        panelActive.setBorder(titledBorder);
        panelActive.add(dice1);
        panelActive.add(dice2);
        panelActive.add(dice3);
        panelActive.add(dice4);
        panelActive.add(dice5);
        panelActive.add(dice6);
        panelActive.add(dice7);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        add(panelActive, constraints);

        imageIcon = new ImageIcon(getClass().getResource("/resources/Dragon.png"));
        dice8 = new JLabel(imageIcon);
        dice9 = new JLabel(imageIcon);
        dice10 = new JLabel(imageIcon);
        panelInactive = new PanelImage(image);
        panelInactive.setPreferredSize(new Dimension(500, 250));
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Inactive Dices: ");
        titledBorder.setTitleColor(Color.WHITE);
        panelInactive.setBorder(titledBorder);
        panelInactive.add(dice8);
        dice8.addMouseListener(diceActions);
        panelInactive.add(dice9);
        dice9.addMouseListener(diceActions);
        panelInactive.add(dice10);
        dice10.addMouseListener(diceActions);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        add(panelInactive, constraints);

        panelUsed = new PanelImage(image);
        panelUsed.setPreferredSize(new Dimension(500, 250));
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE), "Used Dices: ");
        titledBorder.setTitleColor(Color.WHITE);
        panelUsed.setBorder(titledBorder);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        add(panelUsed, constraints);

        roll = new JButton("Roll");
        roll.addActionListener(listener);
        roll.setBackground(Color.RED);
        roll.setForeground(Color.WHITE);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.PAGE_START;
        add(roll, constraints);
    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == roll) {
                modelGame.getActiveDices();
                faces = modelGame.getFaces();
                imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[0] + ".png"));
                dice1.setIcon(imageIcon);
                imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[1] + ".png"));
                dice2.setIcon(imageIcon);
                imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[2] + ".png"));
                dice3.setIcon(imageIcon);
                imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[3] + ".png"));
                dice4.setIcon(imageIcon);
                imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[4] + ".png"));
                dice5.setIcon(imageIcon);
                imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[5] + ".png"));
                dice6.setIcon(imageIcon);
                imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[6] + ".png"));
                dice7.setIcon(imageIcon);
                imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[7] + ".png"));
                dice8.setIcon(imageIcon);
                imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[8] + ".png"));
                dice9.setIcon(imageIcon);
                imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[9] + ".png"));
                dice10.setIcon(imageIcon);
            }
            else if (e.getSource()==exit){
                System.exit(0);
            } else if (e.getSource()==reset) {

            }

        }
    }

    private class Actions implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getSource() == dice1) {
                if (modelGame.getFlag()==0 && faces[0] != "Dragon" && faces[0] != "Point"){
                    panelActive.remove(dice1);
                    panelUsed.add(dice1);
                    JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[0]));
                    modelGame.activeToUsed(faces[0]);
                }else if (modelGame.getFlag()==1) {
                    faces[0] = modelGame.meeplePower(faces[0]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[0] + ".png"));
                    dice1.setIcon(imageIcon);
                    modelGame.resetFlag();
                }

            } else if (e.getSource()== dice2) {
                if(modelGame.getFlag()==0 && faces[1] != "Dragon" && faces[1] != "Point"){
                    panelActive.remove(dice2);
                    panelUsed.add(dice2);
                    JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[1]));
                    modelGame.activeToUsed(faces[1]);
                }else if (modelGame.getFlag()==1){
                    faces[1] = modelGame.meeplePower(faces[1]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[1] + ".png"));
                    dice2.setIcon(imageIcon);
                    modelGame.resetFlag();
                }
            } else if (e.getSource()== dice3) {
                if(modelGame.getFlag()==0 && faces[2] != "Dragon" && faces[2] != "Point"){
                    panelActive.remove(dice3);
                    panelUsed.add(dice3);
                    JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[2]));
                    modelGame.activeToUsed(faces[2]);
                }else if (modelGame.getFlag()==1){
                    faces[2] = modelGame.meeplePower(faces[2]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[2] + ".png"));
                    dice3.setIcon(imageIcon);
                    modelGame.resetFlag();
                }

            } else if (e.getSource()== dice4) {
                if(modelGame.getFlag()==0 && faces[3] != "Dragon" && faces[3] != "Point"){
                    panelActive.remove(dice4);
                    panelUsed.add(dice4);
                    JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[3]));
                    modelGame.activeToUsed(faces[3]);
                }else if (modelGame.getFlag()==1){
                    faces[3] = modelGame.meeplePower(faces[3]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[3] + ".png"));
                    dice4.setIcon(imageIcon);
                    modelGame.resetFlag();
                }

            } else if (e.getSource()== dice5) {
                if(modelGame.getFlag()==0 && faces[4] != "Dragon" && faces[4] != "Point"){
                    panelActive.remove(dice5);
                    panelUsed.add(dice5);
                    JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[4]));
                    modelGame.activeToUsed(faces[4]);
                }else if (modelGame.getFlag()==1){
                    faces[4] = modelGame.meeplePower(faces[4]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[4] + ".png"));
                    dice5.setIcon(imageIcon);
                    modelGame.resetFlag();
                }

            } else if (e.getSource()== dice6) {
                if(modelGame.getFlag()==0 && faces[5] != "Dragon" && faces[5] != "Point"){
                    panelActive.remove(dice6);
                    panelUsed.add(dice6);
                    JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[5]));
                    modelGame.activeToUsed(faces[5]);
                }else if (modelGame.getFlag()==1){
                    faces[5] = modelGame.meeplePower(faces[5]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[5] + ".png"));
                    dice6.setIcon(imageIcon);
                    modelGame.resetFlag();
                }

            } else if (e.getSource()== dice7) {
                if(modelGame.getFlag()==0 && faces[6] != "Dragon" && faces[6] != "Point"){
                    panelActive.remove(dice7);
                    panelUsed.add(dice7);
                    JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[6]));
                    modelGame.activeToUsed(faces[6]);
                }else if (modelGame.getFlag()==1){
                    faces[6] = modelGame.meeplePower(faces[6]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[6] + ".png"));
                    dice7.setIcon(imageIcon);
                    modelGame.resetFlag();
                }
            } else if (e.getSource()== dice8){
                if(modelGame.getFlag()==2){
                    panelInactive.remove(dice8);
                    faces[7] = modelGame.heartPower(faces[7]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[7] + ".png"));
                    dice8.setIcon(imageIcon);
                    panelActive.add(dice8);
                    modelGame.setFlagsInactiveDices(0);
                    modelGame.resetFlag();
                }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[0]==1) {
                    faces[7] = modelGame.meeplePower(faces[7]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[7] + ".png"));
                    dice8.setIcon(imageIcon);
                    modelGame.resetFlag();
                } else if (modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[0]==1 && faces[7] != "Dragon" && faces[7] != "Point") {
                    panelActive.remove(dice8);
                    panelUsed.add(dice8);
                    JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[7]));
                    modelGame.activeToUsed(faces[7]);
                }
            }else if (e.getSource()== dice9){
                if(modelGame.getFlag()==2){
                    panelInactive.remove(dice9);
                    faces[8] = modelGame.heartPower(faces[8]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[8] + ".png"));
                    dice9.setIcon(imageIcon);
                    panelActive.add(dice9);
                    modelGame.setFlagsInactiveDices(1);
                    modelGame.resetFlag();
                }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[1]==1) {
                    faces[8] = modelGame.meeplePower(faces[8]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[8] + ".png"));
                    dice9.setIcon(imageIcon);
                    modelGame.resetFlag();
                }else if (modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[1]==1 && faces[8] != "Dragon" && faces[8] != "Point") {
                    panelActive.remove(dice9);
                    panelUsed.add(dice9);
                    JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[8]));
                    modelGame.activeToUsed(faces[8]);
                }
            }else if (e.getSource()== dice10){
                if(modelGame.getFlag()==2){
                    panelInactive.remove(dice10);
                    faces[9] = modelGame.heartPower(faces[9]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[9] + ".png"));
                    dice10.setIcon(imageIcon);
                    panelActive.add(dice10);
                    modelGame.setFlagsInactiveDices(2);
                    modelGame.resetFlag();
                }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[2]==1) {
                    faces[9] = modelGame.meeplePower(faces[9]);
                    imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[9] + ".png"));
                    dice10.setIcon(imageIcon);
                    modelGame.resetFlag();
                }else if (modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[2]==1 && faces[9] != "Dragon" && faces[9] != "Point") {
                    panelActive.remove(dice10);
                    panelUsed.add(dice10);
                    JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[9]));
                    modelGame.activeToUsed(faces[9]);
                }
            }
            panelUsed.revalidate();
            panelUsed.repaint();
            panelActive.revalidate();
            panelActive.repaint();
            panelInactive.revalidate();
            panelInactive.repaint();
        }



        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
package geekMasterGame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * This class is used for ...
 * @autor Christian Daniel Villegas christian.villegas@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class GUIGridBagLayout extends JFrame {

    private PanelImage panelImage;
    private Header headerProject;
    private JPanel panelActive, panelInactive, panelUsed;
    private JLabel dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8, dice9, dice10, powerRules, viewRules, getPoints;
    private JButton roll, rules, points, exit, powers, newRound, newGame;
    private ImageIcon imageIcon, background;
    private Image image;
    private TitledBorder titledBorder;
    private Listener listener;
    private Actions diceActions;
    private String[] faces;
    private GridBagConstraints constraints;
    private JTextArea stateGame;
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
        rules.addActionListener(listener);
        powers = new JButton("                          Powers                          ");
        powers.addActionListener(listener);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(rules, constraints);
        constraints.anchor = GridBagConstraints.LINE_END;
        add(powers, constraints);

        points = new JButton("                   How get points                      ");
        points.addActionListener(listener);
        exit = new JButton("                            Exit                            ");
        exit.addActionListener(listener);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(points, constraints);
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

        roll = new JButton("       Roll       ");
        roll.addActionListener(listener);
        roll.setBackground(Color.RED);
        roll.setForeground(Color.WHITE);
        newRound = new JButton("New Round");
        newRound.addActionListener(listener);
        newRound.setEnabled(false);
        newGame = new JButton(" New Game ");
        newGame.addActionListener(listener);
        newGame.setEnabled(false);
        newGame.setBackground(Color.GREEN);
        newGame.setForeground(Color.WHITE);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        add(roll, constraints);
        constraints.anchor = GridBagConstraints.WEST;
        add(newRound,constraints);
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        add(newGame,constraints);

        stateGame = new JTextArea(15,39);
        stateGame.setBorder(BorderFactory.createTitledBorder("Estado del juego: "));
        stateGame.setEditable(false);
        constraints.anchor = GridBagConstraints.EAST;
        add(stateGame, constraints);


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

            if (e.getSource() == roll && modelGame.getFlagPause() == true) {
                modelGame.newLaunch();
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
                modelGame.setFlagPause();
                roll.setEnabled(false);
            }
            else if (e.getSource()== exit){
                System.exit(0);
            } else if (e.getSource()== newRound) {
                imageIcon = new ImageIcon(getClass().getResource("/resources/Meeple.png"));
                dice1.setIcon(imageIcon);
                dice2.setIcon(imageIcon);
                dice3.setIcon(imageIcon);
                dice4.setIcon(imageIcon);
                dice5.setIcon(imageIcon);
                dice6.setIcon(imageIcon);
                dice7.setIcon(imageIcon);
                panelActive.add(dice1);
                panelActive.add(dice2);
                panelActive.add(dice3);
                panelActive.add(dice4);
                panelActive.add(dice5);
                panelActive.add(dice6);
                panelActive.add(dice7);

                imageIcon = new ImageIcon(getClass().getResource("/resources/Dragon.png"));
                dice8.setIcon(imageIcon);
                dice9.setIcon(imageIcon);
                dice10.setIcon(imageIcon);
                panelInactive.add(dice8);
                panelInactive.add(dice9);
                panelInactive.add(dice10);

                panelUsed.revalidate();
                panelUsed.repaint();
                panelActive.revalidate();
                panelActive.repaint();
                panelInactive.revalidate();
                panelInactive.repaint();

                modelGame.setFlagPause();
                roll.setEnabled(true);
                newRound.setEnabled(false);
            } else if (e.getSource()==powers) {
                imageIcon = new ImageIcon(getClass().getResource("/resources/Powers.png"));
                powerRules = new JLabel();
                powerRules.setIcon(imageIcon);
                JOptionPane.showMessageDialog(null,powerRules,"Power Rules: ",JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource()==rules) {
                imageIcon = new ImageIcon(getClass().getResource("/resources/Rules1.png"));
                viewRules = new JLabel();
                viewRules.setIcon(imageIcon);
                JOptionPane.showMessageDialog(null,viewRules,"Rules of game: ",JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource()==points) {
                imageIcon = new ImageIcon(getClass().getResource("/resources/Rules2.png"));
                getPoints = new JLabel();
                getPoints.setIcon(imageIcon);
                JOptionPane.showMessageDialog(null,getPoints,"How get points: ",JOptionPane.INFORMATION_MESSAGE);
            } else if (e.getSource()==newGame) {
                imageIcon = new ImageIcon(getClass().getResource("/resources/Meeple.png"));
                dice1.setIcon(imageIcon);
                dice2.setIcon(imageIcon);
                dice3.setIcon(imageIcon);
                dice4.setIcon(imageIcon);
                dice5.setIcon(imageIcon);
                dice6.setIcon(imageIcon);
                dice7.setIcon(imageIcon);
                panelActive.add(dice1);
                panelActive.add(dice2);
                panelActive.add(dice3);
                panelActive.add(dice4);
                panelActive.add(dice5);
                panelActive.add(dice6);
                panelActive.add(dice7);

                imageIcon = new ImageIcon(getClass().getResource("/resources/Dragon.png"));
                dice8.setIcon(imageIcon);
                dice9.setIcon(imageIcon);
                dice10.setIcon(imageIcon);
                panelInactive.add(dice8);
                panelInactive.add(dice9);
                panelInactive.add(dice10);

                panelUsed.revalidate();
                panelUsed.repaint();
                panelActive.revalidate();
                panelActive.repaint();
                panelInactive.revalidate();
                panelInactive.repaint();

                modelGame.resetGame();
                newGame.setEnabled(false);
                roll.setEnabled(true);
            }
        }
    }

    private class Actions implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (modelGame.getFlagPause()==false){
                if (e.getSource() == dice1 && modelGame.getFlagsUsedDices()[0]==0) {
                    if (modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[0]==1 && faces[0] != "Dragon" && faces[0] != "Point" ){
                        panelActive.remove(dice1);
                        panelUsed.add(dice1);
                        JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[0]));
                        modelGame.activeToUsed(faces[0]);
                        modelGame.setFlagsUsedDices(0);
                    }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[0]==1) {
                        faces[0] = modelGame.meeplePower(faces[0]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[0] + ".png"));
                        dice1.setIcon(imageIcon);
                    } else if (modelGame.getFlag()==3 && modelGame.getFlagsInactiveDices()[0]==1) {
                        modelGame.rocketPower(faces[0]);
                        panelActive.remove(dice1);
                        panelInactive.add(dice1);
                        modelGame.setFlagsInactiveDices(0);
                    } else if(modelGame.getFlag()==2){
                        panelInactive.remove(dice1);
                        faces[0] = modelGame.heartPower(faces[0]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[0] + ".png"));
                        dice1.setIcon(imageIcon);
                        panelActive.add(dice1);
                        modelGame.setFlagsInactiveDices(0);
                    } else if (modelGame.getFlag()==4 && modelGame.getFlagsInactiveDices()[0]==1) {
                        String result = modelGame.superHeroPower(faces[0]);
                        if (result.length()>=10 ){
                            JOptionPane.showMessageDialog(null, result);
                        }else{
                            System.out.println("cambie el dado 1");
                            faces[0] = result;
                            imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[0] + ".png"));
                            dice1.setIcon(imageIcon);
                        }
                    }

                } else if (e.getSource()== dice2 && modelGame.getFlagsUsedDices()[1]==0) {
                    if(modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[1]==1 && faces[1] != "Dragon" && faces[1] != "Point"){
                        panelActive.remove(dice2);
                        panelUsed.add(dice2);
                        JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[1]));
                        modelGame.activeToUsed(faces[1]);
                        modelGame.setFlagsUsedDices(1);
                    }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[1]==1){
                        faces[1] = modelGame.meeplePower(faces[1]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[1] + ".png"));
                        dice2.setIcon(imageIcon);
                    }else if (modelGame.getFlag()==3 && modelGame.getFlagsInactiveDices()[1]==1) {
                        modelGame.rocketPower(faces[1]);
                        panelActive.remove(dice2);
                        panelInactive.add(dice2);
                        modelGame.setFlagsInactiveDices(1);
                    }else if(modelGame.getFlag()==2){
                        panelInactive.remove(dice2);
                        faces[1] = modelGame.heartPower(faces[1]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[1] + ".png"));
                        dice2.setIcon(imageIcon);
                        panelActive.add(dice2);
                        modelGame.setFlagsInactiveDices(1);
                    }else if (modelGame.getFlag()==4 && modelGame.getFlagsInactiveDices()[1]==1) {
                        String result = modelGame.superHeroPower(faces[1]);
                        if (result.length()>=10 ){
                            JOptionPane.showMessageDialog(null, result);
                        }else{
                            faces[1] = result;
                            imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[1] + ".png"));
                            dice2.setIcon(imageIcon);
                        }
                    }

                } else if (e.getSource()== dice3 && modelGame.getFlagsUsedDices()[2]==0) {
                    if(modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[2]==1 && faces[2] != "Dragon" && faces[2] != "Point"){
                        panelActive.remove(dice3);
                        panelUsed.add(dice3);
                        JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[2]));
                        modelGame.activeToUsed(faces[2]);
                        modelGame.setFlagsUsedDices(2);
                    }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[2]==1){
                        faces[2] = modelGame.meeplePower(faces[2]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[2] + ".png"));
                        dice3.setIcon(imageIcon);
                    }else if (modelGame.getFlag()==3 && modelGame.getFlagsInactiveDices()[2]==1) {
                        modelGame.rocketPower(faces[2]);
                        panelActive.remove(dice3);
                        panelInactive.add(dice3);
                        modelGame.setFlagsInactiveDices(2);
                    }else if(modelGame.getFlag()==2){
                        panelInactive.remove(dice3);
                        faces[2] = modelGame.heartPower(faces[2]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[2] + ".png"));
                        dice3.setIcon(imageIcon);
                        panelActive.add(dice3);
                        modelGame.setFlagsInactiveDices(2);
                    }else if (modelGame.getFlag()==4 && modelGame.getFlagsInactiveDices()[2]==1) {
                        String result = modelGame.superHeroPower(faces[2]);
                        if (result.length()>=10 ){
                            JOptionPane.showMessageDialog(null, result);
                        }else{
                            faces[2] = result;
                            imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[2] + ".png"));
                            dice3.setIcon(imageIcon);
                        }
                    }

                } else if (e.getSource()== dice4 && modelGame.getFlagsUsedDices()[3]==0) {
                    if(modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[3]==1 && faces[3] != "Dragon" && faces[3] != "Point"){
                        panelActive.remove(dice4);
                        panelUsed.add(dice4);
                        JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[3]));
                        modelGame.activeToUsed(faces[3]);
                        modelGame.setFlagsUsedDices(3);
                    }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[3]==1){
                        faces[3] = modelGame.meeplePower(faces[3]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[3] + ".png"));
                        dice4.setIcon(imageIcon);
                    }else if (modelGame.getFlag()==3 && modelGame.getFlagsInactiveDices()[3]==1) {
                        modelGame.rocketPower(faces[3]);
                        panelActive.remove(dice4);
                        panelInactive.add(dice4);
                        modelGame.setFlagsInactiveDices(3);
                    }else if(modelGame.getFlag()==2){
                        panelInactive.remove(dice4);
                        faces[3] = modelGame.heartPower(faces[3]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[3] + ".png"));
                        dice4.setIcon(imageIcon);
                        panelActive.add(dice4);
                        modelGame.setFlagsInactiveDices(3);
                    }else if (modelGame.getFlag()==4 && modelGame.getFlagsInactiveDices()[3]==1) {
                        String result = modelGame.superHeroPower(faces[3]);
                        if (result.length()>=10 ){
                            JOptionPane.showMessageDialog(null, result);
                        }else{
                            faces[3] = result;
                            imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[3] + ".png"));
                            dice4.setIcon(imageIcon);
                        }
                    }

                } else if (e.getSource()== dice5 && modelGame.getFlagsUsedDices()[4]==0) {
                    if(modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[4]==1 && faces[4] != "Dragon" && faces[4] != "Point"){
                        panelActive.remove(dice5);
                        panelUsed.add(dice5);
                        JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[4]));
                        modelGame.activeToUsed(faces[4]);
                        modelGame.setFlagsUsedDices(4);
                    }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[4]==1){
                        faces[4] = modelGame.meeplePower(faces[4]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[4] + ".png" ));
                        dice5.setIcon(imageIcon);
                    }else if (modelGame.getFlag()==3 && modelGame.getFlagsInactiveDices()[4]==1) {
                        modelGame.rocketPower(faces[4]);
                        panelActive.remove(dice5);
                        panelInactive.add(dice5);
                        modelGame.setFlagsInactiveDices(4);
                    }else if(modelGame.getFlag()==2){
                        panelInactive.remove(dice5);
                        faces[4] = modelGame.heartPower(faces[4]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[4] + ".png"));
                        dice5.setIcon(imageIcon);
                        panelActive.add(dice5);
                        modelGame.setFlagsInactiveDices(4);
                    }else if (modelGame.getFlag()==4 && modelGame.getFlagsInactiveDices()[4]==1) {
                        String result = modelGame.superHeroPower(faces[4]);
                        if (result.length()>=10 ){
                            JOptionPane.showMessageDialog(null, result);
                        }else{
                            faces[4] = result;
                            imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[4] + ".png"));
                            dice5.setIcon(imageIcon);
                        }
                    }

                } else if (e.getSource()== dice6 && modelGame.getFlagsUsedDices()[5]==0) {
                    if(modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[5]==1 && faces[5] != "Dragon" && faces[5] != "Point"){
                        panelActive.remove(dice6);
                        panelUsed.add(dice6);
                        JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[5]));
                        modelGame.activeToUsed(faces[5]);
                        modelGame.setFlagsUsedDices(5);
                    }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[5]==1){
                        faces[5] = modelGame.meeplePower(faces[5]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[5] + ".png"));
                        dice6.setIcon(imageIcon);
                    }else if (modelGame.getFlag()==3 && modelGame.getFlagsInactiveDices()[5]==1) {
                        modelGame.rocketPower(faces[5]);
                        panelActive.remove(dice6);
                        panelInactive.add(dice6);
                        modelGame.setFlagsInactiveDices(5);
                    }else if(modelGame.getFlag()==2){
                        panelInactive.remove(dice6);
                        faces[5] = modelGame.heartPower(faces[5]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[5] + ".png"));
                        dice6.setIcon(imageIcon);
                        panelActive.add(dice6);
                        modelGame.setFlagsInactiveDices(5);
                    }else if (modelGame.getFlag()==4 && modelGame.getFlagsInactiveDices()[5]==1) {
                        String result = modelGame.superHeroPower(faces[5]);
                        if (result.length()>=10 ){
                            JOptionPane.showMessageDialog(null, result);
                        }else{
                            faces[5] = result;
                            imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[5] + ".png"));
                            dice6.setIcon(imageIcon);
                        }
                    }

                } else if (e.getSource()== dice7 && modelGame.getFlagsUsedDices()[6]==0) {
                    if(modelGame.getFlag()==0 &&  modelGame.getFlagsInactiveDices()[6]==1 && faces[6] != "Dragon" && faces[6] != "Point"){
                        panelActive.remove(dice7);
                        panelUsed.add(dice7);
                        JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[6]));
                        modelGame.activeToUsed(faces[6]);
                        modelGame.setFlagsUsedDices(6);
                    }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[6]==1){
                        faces[6] = modelGame.meeplePower(faces[6]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[6] + ".png"));
                        dice7.setIcon(imageIcon);
                    }else if (modelGame.getFlag()==3 && modelGame.getFlagsInactiveDices()[6]==1) {
                        modelGame.rocketPower(faces[6]);
                        panelActive.remove(dice7);
                        panelInactive.add(dice7);
                        modelGame.setFlagsInactiveDices(6);
                    }else if(modelGame.getFlag()==2){
                        panelInactive.remove(dice7);
                        faces[6] = modelGame.heartPower(faces[6]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[6] + ".png"));
                        dice7.setIcon(imageIcon);
                        panelActive.add(dice7);
                        modelGame.setFlagsInactiveDices(6);
                    }else if (modelGame.getFlag()==4 && modelGame.getFlagsInactiveDices()[6]==1) {
                        String result = modelGame.superHeroPower(faces[6]);
                        if (result.length()>=10 ){
                            JOptionPane.showMessageDialog(null, result);
                        }else{
                            faces[6] = result;
                            imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[6] + ".png"));
                            dice7.setIcon(imageIcon);
                        }
                    }

                } else if (e.getSource()== dice8 && modelGame.getFlagsUsedDices()[7]==0){
                    if (modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[7]==1 && faces[7] != "Dragon" && faces[7] != "Point") {
                        panelActive.remove(dice8);
                        panelUsed.add(dice8);
                        JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[7]));
                        modelGame.activeToUsed(faces[7]);
                        modelGame.setFlagsUsedDices(7);
                    }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[7]==1) {
                        faces[7] = modelGame.meeplePower(faces[7]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[7] + ".png"));
                        dice8.setIcon(imageIcon);
                    }else if(modelGame.getFlag()==2){
                        panelInactive.remove(dice8);
                        faces[7] = modelGame.heartPower(faces[7]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[7] + ".png"));
                        dice8.setIcon(imageIcon);
                        panelActive.add(dice8);
                        modelGame.setFlagsInactiveDices(7);
                    }else if (modelGame.getFlag()==3 && modelGame.getFlagsInactiveDices()[7]==1) {
                        modelGame.rocketPower(faces[7]);
                        panelActive.remove(dice8);
                        panelInactive.add(dice8);
                        modelGame.setFlagsInactiveDices(7);
                    }else if (modelGame.getFlag()==4 && modelGame.getFlagsInactiveDices()[7]==1) {
                        String result = modelGame.superHeroPower(faces[7]);
                        if (result.length()>=10 ){
                            JOptionPane.showMessageDialog(null, result);
                        }else{
                            faces[7] = result;
                            imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[7] + ".png"));
                            dice8.setIcon(imageIcon);
                        }
                    }

                }else if (e.getSource()== dice9 && modelGame.getFlagsUsedDices()[8]==0){
                    if (modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[8]==1 && faces[8] != "Dragon" && faces[8] != "Point") {
                        panelActive.remove(dice9);
                        panelUsed.add(dice9);
                        JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[8]));
                        modelGame.activeToUsed(faces[8]);
                        modelGame.setFlagsUsedDices(8);
                    }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[8]==1) {
                        faces[8] = modelGame.meeplePower(faces[8]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[8] + ".png"));
                        dice9.setIcon(imageIcon);
                    }else if(modelGame.getFlag()==2){
                        panelInactive.remove(dice9);
                        faces[8] = modelGame.heartPower(faces[8]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[8] + ".png"));
                        dice9.setIcon(imageIcon);
                        panelActive.add(dice9);
                        modelGame.setFlagsInactiveDices(8);
                    }else if (modelGame.getFlag()==3 && modelGame.getFlagsInactiveDices()[8]==1) {
                        modelGame.rocketPower(faces[8]);
                        panelActive.remove(dice9);
                        panelInactive.add(dice9);
                        modelGame.setFlagsInactiveDices(8);
                    }else if (modelGame.getFlag()==4 && modelGame.getFlagsInactiveDices()[8]==1) {
                        String result = modelGame.superHeroPower(faces[8]);
                        if (result.length()>=10 ){
                            JOptionPane.showMessageDialog(null, result);
                        }else{
                            faces[8] = result;
                            imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[8] + ".png"));
                            dice9.setIcon(imageIcon);
                        }
                    }

                }else if (e.getSource()== dice10 && modelGame.getFlagsUsedDices()[9]==0){
                    if (modelGame.getFlag()==0 && modelGame.getFlagsInactiveDices()[9]==1 && faces[9] != "Dragon" && faces[9] != "Point") {
                        panelActive.remove(dice10);
                        panelUsed.add(dice10);
                        JOptionPane.showMessageDialog(null,modelGame.getStateToString(faces[9]));
                        modelGame.activeToUsed(faces[9]);
                        modelGame.setFlagsUsedDices(9);
                    }else if (modelGame.getFlag()==1 && modelGame.getFlagsInactiveDices()[9]==1) {
                        faces[9] = modelGame.meeplePower(faces[9]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[9] + ".png"));
                        dice10.setIcon(imageIcon);
                    }else if(modelGame.getFlag()==2){
                        panelInactive.remove(dice10);
                        faces[9] = modelGame.heartPower(faces[9]);
                        imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[9] + ".png"));
                        dice10.setIcon(imageIcon);
                        panelActive.add(dice10);
                        modelGame.setFlagsInactiveDices(9);
                    }else if (modelGame.getFlag()==3 && modelGame.getFlagsInactiveDices()[9]==1) {
                        modelGame.rocketPower(faces[9]);
                        panelActive.remove(dice10);
                        panelInactive.add(dice10);
                        modelGame.setFlagsInactiveDices(9);
                    }else if (modelGame.getFlag()==4 && modelGame.getFlagsInactiveDices()[9]==1) {
                        String result = modelGame.superHeroPower(faces[9]);
                        if (result.length()>=10 ){
                            JOptionPane.showMessageDialog(null, result);
                        }else{
                            faces[9] = result;
                            imageIcon = new ImageIcon(getClass().getResource("/resources/" + faces[9] + ".png"));
                            dice10.setIcon(imageIcon);
                        }
                    }
                }
                String [] myArray = modelGame.getArrayActiveDices();
                for (int i = 0; i < myArray.length; i++) {
                    System.out.println(myArray[i]);
                    if (myArray.length-1 == i){
                        System.out.println("\n");
                    }
                }
                panelUsed.revalidate();
                panelUsed.repaint();
                panelActive.revalidate();
                panelActive.repaint();
                panelInactive.revalidate();
                panelInactive.repaint();

                if (modelGame.getStateGame()==true){

                    modelGame.setScore();
                    modelGame.incRound();
                    System.out.println(modelGame.getStatusRound());
                    if (modelGame.getStatusRound() == 5){
                        String stateFinal = modelGame.validateGame();
                        JOptionPane.showMessageDialog(null,stateFinal + ", Presione New Game para jugar nuevamente o Exit");
                        newGame.setEnabled(true);
                    }else{
                        newRound.setEnabled(true);
                    }


                }
            }
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
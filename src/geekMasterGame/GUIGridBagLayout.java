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


    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("Play Craps");
        this.setUndecorated(true);
        this.setBackground(new Color(255,255,255,192));
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
        GridBagConstraints constraints = new GridBagConstraints();
        //Set background image
        background = new ImageIcon(getClass().getResource("/resources/fondo.png"));
        image = background.getImage();
        //Create Listener Object and Control Object
        //Set up JComponents

        //First row from GridBagLayout
        headerProject = new Header("Geek Master Game", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        add(headerProject,constraints);

        rules = new JButton("                           Rules                           ");
        powers = new JButton("                           Powers                          ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill= GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(rules,constraints);
        constraints.anchor = GridBagConstraints.LINE_END;
        add(powers,constraints);

        reset = new JButton("                            Reset                            ");
        exit = new JButton("                            Exit                            ");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(reset,constraints);
        constraints.anchor = GridBagConstraints.LINE_END;
        add(exit,constraints);

        imageIcon = new ImageIcon(getClass().getResource("/resources/1.png"));
        dice1 = new JLabel(imageIcon);
        dice2 = new JLabel(imageIcon);
        dice3 = new JLabel(imageIcon);
        dice4 = new JLabel(imageIcon);
        dice5 = new JLabel(imageIcon);
        dice6 = new JLabel(imageIcon);
        dice7 = new JLabel(imageIcon);
        panelActive = new PanelImage(image);
        panelActive.setPreferredSize(new Dimension(500,250) );
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),"Active Dices: ");
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
        add(panelActive,constraints);

        imageIcon = new ImageIcon(getClass().getResource("/resources/2.png"));
        dice8 = new JLabel(imageIcon);
        dice9 = new JLabel(imageIcon);
        dice10 = new JLabel(imageIcon);
        panelInactive = new PanelImage(image);
        panelInactive.setPreferredSize(new Dimension(500,250) );
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),"Inactive Dices: ");
        titledBorder.setTitleColor(Color.WHITE);
        panelInactive.setBorder(titledBorder);
        panelInactive.add(dice8);
        panelInactive.add(dice9);
        panelInactive.add(dice10);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        add(panelInactive,constraints);

        panelUsed = new PanelImage(image);
        panelUsed.setPreferredSize(new Dimension(500,250) );
        titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),"Used Dices: ");
        titledBorder.setTitleColor(Color.WHITE);
        panelUsed.setBorder(titledBorder);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        add(panelUsed,constraints);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha {

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static main.MyContainer.PANEL_GAME;
import static main.MyContainer.PANEL_HELP;
import static main.MyFrame.H_FARME;
import static main.MyFrame.W_FARME;

/**
 *
 * @author buitr
 */
public class PanelMenu extends JPanel implements ActionListener {

    private JButton jbStart;
    private JButton jbHelp;
    private JButton jbExit;
    private JButton jbHScore;
    private JButton jbSetting;
    public static final String SETTING = "setting";
    public static final String START = "start";
    public static final String HELP = "help";
    public static final String HS = "highscore";
    public static final String EXIT = "exit";
    private GameManager gameManager;

    private MyContainer myContainer;

    public PanelMenu(MyContainer myContainer) {
        initPanelMenu();
        initComs();
        initListener();
        this.myContainer = myContainer;
    }

    public final Image[] image = {
        new ImageIcon(getClass().getResource("/images/menupanel.jpg")).getImage(),};

    public final Icon[] icons = {
        new ImageIcon(getClass().getResource("/images/startButton1.png")),
        new ImageIcon(getClass().getResource("/images/helpButton1.png")),
        new ImageIcon(getClass().getResource("/images/HightScore.png")),
        new ImageIcon(getClass().getResource("/images/exitButton1.png")),
        new ImageIcon(getClass().getResource("/images/startButton2.png")),
        new ImageIcon(getClass().getResource("/images/helpButton2.png")),
        new ImageIcon(getClass().getResource("/images/HightScore2.png")),
        new ImageIcon(getClass().getResource("/images/exitButton2.png"))};

    private void initPanelMenu() {
        setBackground(Color.green);
        setLayout(null);
    }

    private void initComs() {
        jbStart = new JButton(icons[0]);
        jbStart.setRolloverIcon(icons[4]);
        jbStart.setSize(icons[0].getIconWidth(), icons[0].getIconHeight());
        jbStart.setLocation(20, H_FARME - jbStart.getHeight() - 80);
        add(jbStart);

        jbHelp = new JButton(icons[1]);
        jbHelp.setRolloverIcon(icons[5]);
        jbHelp.setSize(icons[1].getIconWidth(), icons[0].getIconHeight());
        jbHelp.setLocation(jbStart.getX() + jbStart.getWidth() + 20, jbStart.getY());
        add(jbHelp);

        jbHScore = new JButton(icons[2]);
        jbHScore.setRolloverIcon(icons[6]);
        jbHScore.setSize(icons[6].getIconWidth(), icons[0].getIconHeight());
        jbHScore.setLocation(jbHelp.getX() + jbHelp.getWidth() + 20, jbStart.getY());
        add(jbHScore);

        jbExit = new JButton(icons[3]);
        jbExit.setRolloverIcon(icons[7]);
        jbExit.setSize(icons[3].getIconWidth(), icons[0].getIconHeight());
        jbExit.setLocation(jbHScore.getX() + jbHScore.getWidth() + 20, jbStart.getY());
        add(jbExit);
        
        jbSetting = new JButton(new ImageIcon(getClass().getResource("/images/setting.png")));
        jbSetting.setSize(jbSetting.getIcon().getIconWidth(), jbSetting.getIcon().getIconHeight());
        jbSetting.setLocation(W_FARME - jbSetting.getWidth() - 20, 20);
        add(jbSetting);
        
        
        
    }

    public void initListener() {
        jbStart.addActionListener(this);
        jbStart.setActionCommand(START);
        jbHelp.addActionListener(this);
        jbHelp.setActionCommand(HELP);
        jbHScore.addActionListener(this);
        jbHScore.setActionCommand(HS);
        jbExit.addActionListener(this);
        jbExit.setActionCommand(EXIT);
        jbSetting.addActionListener(this);
        jbSetting.setActionCommand(SETTING);
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image[0], 0, 0, W_FARME, H_FARME, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String run = e.getActionCommand();
        switch (run) {
            case START: {
                Clip clip = Sound.getSound(getClass().getResource("/sounds/click.wav"));
                clip.start();

                myContainer.showCard(PANEL_GAME);
                break;
            }
            case HELP: {
                Clip clip = Sound.getSound(getClass().getResource("/sounds/click.wav"));
                clip.start();
                myContainer.showCard(PANEL_HELP);
                break;
            }
            case HS: {
                Clip clip = Sound.getSound(getClass().getResource("/sounds/click.wav"));
                clip.start();
                myContainer.showCard(MyContainer.PANEL_HIGHSCORE);
                break;
            }
            case EXIT: {
                Clip clip = Sound.getSound(getClass().getResource("/sounds/click.wav"));
                clip.start();
                System.exit(0);
                
            }
            case SETTING: {
                Clip clip = Sound.getSound(getClass().getResource("/sounds/click.wav"));
                clip.start();
                myContainer.showCard(MyContainer.PANEL_SETTING);
                break;
            }
        }
    }
}

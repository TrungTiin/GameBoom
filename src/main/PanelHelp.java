/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static main.MyContainer.PANEL_MENU;
import static main.MyFrame.H_FARME;
import static main.MyFrame.W_FARME;
/**
 *
 * @author buitr
 */
public class PanelHelp extends JPanel implements ActionListener {
    private JButton jbBack;
    private MyContainer myContainer;
    public static final String BACK="back";
    public final Icon[] icons={
            new ImageIcon(getClass().getResource("/images/skipButton1.png")),
            new ImageIcon(getClass().getResource("/images/skipButton2.png")),
    };
    public final Image[] images={
            new ImageIcon(getClass().getResource("/images/backgroundHelp.png")).getImage(),
    };
    public PanelHelp(MyContainer myContainer){
        setLayout(null);
        initComs();
        initListener();
        this.myContainer=myContainer;
    }
    public void initComs() {
        jbBack= new JButton(icons[0]);
        jbBack.setRolloverIcon(icons[1]);
        jbBack.setSize(icons[0].getIconWidth(),icons[0].getIconHeight());
        jbBack.setLocation(450,H_FARME-icons[0].getIconHeight()-33);
        add(jbBack);
    }
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        Graphics2D g2d= (Graphics2D) g;
        g2d.drawImage(images[0],0,0,W_FARME,H_FARME,null);
    }

    public void initListener(){
        jbBack.addActionListener(this);
        jbBack.setActionCommand(BACK);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String run=e.getActionCommand();
        switch (run){
            case BACK:{
                Clip clip= Sound.getSound(getClass().getResource("/sounds/click.wav"));
                clip.start();
                myContainer.showCard(PANEL_MENU);
                break;
            }
            default:
                break;
        }
    }
}


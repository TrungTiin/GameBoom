package main;

import sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyContainer extends JPanel {

    public static final String PANEL_SETTING = "PanelSetting";

    public static final String PANEL_GAME = "PanelGame";
    public static final String PANEL_MENU = "PanelMenu";
    public static final String PANEL_HELP = "PanelHelp";
    public static final String PANEL_HIGHSCORE = "PanelHighscore";
//    static static final String PANEL_SETTING = "PanelSetting";
    private PanelSetting panelSetting;
    private PanelGame panelGame;
    private PanelMenu panelMenu;
    private PanelHelp panelHelp;
    private PanelHighScore panelHighscore;
    private CardLayout cardLayout;
    private Clip clip;

    public MyContainer() {
        cardLayout = new CardLayout();
        panelGame = new PanelGame(this);
        panelHelp = new PanelHelp(this);
        panelMenu = new PanelMenu(this);
        panelHighscore = new PanelHighScore(this);
        panelSetting = new PanelSetting(this);
        setLayout(cardLayout);
        add(panelGame, PANEL_GAME);
        add(panelMenu, PANEL_MENU);
        add(panelHelp, PANEL_HELP);
        add(panelHighscore, PANEL_HIGHSCORE);
        add(panelSetting, PANEL_SETTING);

        cardLayout.show(this, PANEL_MENU);
        clip = Sound.getSound(getClass().getResource("/sounds/soundMenu.wav"));
        clip.start();
        clip.loop(100);
        addKeyListener(panelGame);
        setFocusable(true);
    }

    public void showCard(String name) {
        if (name.equals(PANEL_GAME)) {
            cardLayout.show(this, name);
            panelGame.initPanelGame();
            clip.stop();
        } else if (name.equals(PANEL_HELP)) {
            cardLayout.show(this, name);
        } else if (name.equals(PANEL_MENU)) {
            cardLayout.show(this, PANEL_MENU);
        } else if (name.equals(PANEL_HIGHSCORE)) {
            cardLayout.show(this, PANEL_HIGHSCORE);
        } else if (name.equals(PANEL_SETTING)) {
            cardLayout.show(this, PANEL_SETTING);
        }
    }
}

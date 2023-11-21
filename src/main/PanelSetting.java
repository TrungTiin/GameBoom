/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sound.sampled.Clip;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static main.MyContainer.PANEL_MENU;
import static main.MyFrame.H_FARME;
import static main.MyFrame.W_FARME;
import static main.PanelHelp.BACK;
import sounds.Sound;

/**
 *
 * @author buitr
 */
public class PanelSetting extends JPanel implements ActionListener {

    private JSlider volumeSlider;
    private JLabel volumeLabel;

    private JSlider speedSlider;
    private JLabel speedLabel;

    private JSlider boomSlider;
    private JLabel boomLabel;

    private JSlider bossSlider;
    private JLabel bossLabel;
    private JButton jbExit;
    public static final String EXIT = "exit";
    public static int bossCount;
    public static int playerSpeed;
    public static int initialBossCount = 2; // Giá trị mặc định cho số lượng boss
    public static int initialSpeed = 2; // Giá trị mặc định cho tốc độ
    public static int initialBoomCount = 1; // Giá trị mặc định cho số lượng boom


    private MyContainer myContainer;
//    private JTable table;
//    private DefaultTableModel model;

    public PanelSetting(MyContainer myContainer) {
        this.setLayout(null); // Thay đổi layout thành null

        initPanelMenu();
        initComs();
        initListener();
        this.myContainer = myContainer;
        volumeLabel = new JLabel("Volume");
        volumeLabel.setBounds(W_FARME / 2 - 250, 100 - 30, 500, 30);
        this.add(volumeLabel);

        volumeSlider = createSlider(100, 0, 100, 50); // min value, max value, initial value
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                adjustVolume();
            }
        });
        this.add(volumeSlider);
        bossLabel = new JLabel("Boss");
        bossLabel.setBounds(W_FARME / 2 - 250, 200 - 30, 500, 30);
        this.add(bossLabel);

        bossSlider = createSlider(200, 1, 5, 1); // min value, max value, initial value
        bossSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                adjustBoss();
            }
        });
        this.add(bossSlider);
        speedLabel = new JLabel("Speed");
        speedLabel.setBounds(W_FARME / 2 - 250, 300 - 30, 500, 30);
        this.add(speedLabel);

        speedSlider = createSlider(300, 1, 5, 1); // min value, max value, initial value
        speedSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                adjustSpeed();
            }
        });
        this.add(speedSlider);

        boomLabel = new JLabel("Boom");
        boomLabel.setBounds(W_FARME / 2 - 250, 400 - 30, 500, 30);
        this.add(boomLabel);

        boomSlider = createSlider(400, 1, 5, 1); // min value, max value, initial value
        boomSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                adjustBoomCount();
            }
        });
        this.add(boomSlider);

    }

    private void adjustBoss() {
        bossCount = bossSlider.getValue(); // Get the slider value
    }

    private void adjustSpeed() {
        playerSpeed = speedSlider.getValue(); // Get the slider value
    }

    private void adjustBoomCount() {
        initialBoomCount = boomSlider.getValue(); // Get the slider value
    }

    private JSlider createSlider(int y, int min, int max, int initial) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, initial);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBounds(W_FARME / 2 - 250, y, 500, 50); // Đặt vị trí và kích thước của thanh trượt
        return slider;
    }

    private void adjustVolume() {
        float volume = volumeSlider.getValue() / 100f; // Convert the slider value to a float between 0 and 1
        Sound.setMasterVolume(volume);
    }

    public final Image[] image = {
        new ImageIcon(getClass().getResource("/images/setting-background.jpg")).getImage(),};

    public final Icon[] icons = {
        new ImageIcon(getClass().getResource("/images/return.png")),
        new ImageIcon(getClass().getResource("/images/return.png")),
        new ImageIcon(getClass().getResource("/images/refresh.png")),
        new ImageIcon(getClass().getResource("/images/delete.png")),};

    private void initPanelMenu() {
        setBackground(Color.green);
    }

    private void initComs() {
        jbExit = new JButton(icons[0]);
        jbExit.setRolloverIcon(icons[1]);
        // Tạo một JPanel mới để chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(jbExit);
        // Thêm panel nút vào panel chính
        buttonPanel.setBounds(0, H_FARME - 100, W_FARME, 200); // Đặt vị trí và kích thước của panel nút
        this.add(buttonPanel);
    }

    public void initListener() {
        jbExit.addActionListener(this);
        jbExit.setActionCommand(BACK);
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
            case BACK: {
                Clip clip = Sound.getSound(getClass().getResource("/sounds/click.wav"));
                clip.start();
                myContainer.showCard(PANEL_MENU);
                break;
            }
            default:
                break;
        }
    }

}

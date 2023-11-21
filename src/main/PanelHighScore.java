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

import static main.MyContainer.PANEL_MENU;
import static main.MyFrame.H_FARME;
import static main.MyFrame.W_FARME;
import static main.PanelHelp.BACK;
import sounds.Sound;

/**
 *
 * @author buitr
 */
public class PanelHighScore extends JPanel implements ActionListener {

    private JButton jbRefresh;
    private JButton jbClear;
    public static final String REFRESH = "refresh";
    public static final String CLEAR = "clear";

    private JButton jbExit;
    public static final String EXIT = "exit";

    private MyContainer myContainer;
    private JTable table;
    private DefaultTableModel model;

    public PanelHighScore(MyContainer myContainer) {
    this.setLayout(null); // Thay đổi layout thành null

    initPanelMenu();
    initComs();
    initListener();
    this.myContainer = myContainer;
    // Tạo tên cột
    String[] columnNames = {"ID", "Tên người chơi", "Điểm"};

    // Tạo một model bảng mặc định
    model = new DefaultTableModel(columnNames, 0);

    // Tạo một bảng và đặt model của nó
    table = new JTable(model);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(500,300)); // Đặt kích thước ưu tiên của scroll pane

    // Thêm scroll pane vào panel này
    scrollPane.setBounds(W_FARME / 2 - 250, H_FARME / 2 - 250, 500, 550); // Đặt vị trí và kích thước của scroll pane
    this.add(scrollPane);

    // Tải dữ liệu từ cơ sở dữ liệu
    loadDataFromDatabase();
}

    public final Image[] image = {
        new ImageIcon(getClass().getResource("/images/background_hightscore.png")).getImage(),};

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
    jbRefresh = new JButton(icons[2]);
    jbClear = new JButton(icons[3]);
    // Tạo một JPanel mới để chứa các nút
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(jbExit);
    buttonPanel.add(jbRefresh);
    buttonPanel.add(jbClear);

    // Thêm panel nút vào panel chính
    buttonPanel.setBounds(0, H_FARME - 150, W_FARME, 50); // Đặt vị trí và kích thước của panel nút
    this.add(buttonPanel);
}

    public void initListener() {
        jbExit.addActionListener(this);
        jbExit.setActionCommand(BACK);
        jbRefresh.addActionListener(this);
        jbRefresh.setActionCommand(REFRESH);

        jbClear.addActionListener(this);
        jbClear.setActionCommand(CLEAR);

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
            case REFRESH: {
                loadDataFromDatabase();
                break;
            }
            case CLEAR: {
                clearHighScores();
                break;
            }
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

    private void loadDataFromDatabase() {
        // Clear the existing data
        model.setRowCount(0);

        try {
            Connection con = MyConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT playername, score FROM HighScore ORDER BY score DESC");
            // Add rows to the model
            int id = 1;
            while (rs.next()) {
                String playerName = rs.getString("playername");
                int score = rs.getInt("score");
                model.addRow(new Object[]{id, playerName, score});
                id++;
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearHighScores() {
        try {
            Connection con = MyConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM HighScore";
            stmt.executeUpdate(sql);
            loadDataFromDatabase(); // Refresh the table
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

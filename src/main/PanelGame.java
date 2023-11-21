/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.sun.jdi.connect.spi.Connection;
import sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.sql.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;
import static main.BitMap.SIZE;
import static main.MyFrame.H_FARME;
import static main.MyFrame.W_FARME;

/**
 *
 * @author buitr
 */
public class PanelGame extends JPanel implements KeyListener, Runnable {

    private Image winImage;
    private Image loseImage;
    private boolean isWin = false;
    private boolean isLose = false;
    int score = 0;
    private String playerName;
    private long startTime;

    private GameManager gameManager = new GameManager();
    private MyContainer myContainer;
    private Player player;
    private BitSet bitSet = new BitSet(256);
    boolean isRunning = true;
    public static final int TIME_DAT = 20;

    public PanelGame(MyContainer myContainer) {
        this.myContainer = myContainer;
        
    }

    private void pauseGame() {
        int response = JOptionPane.showConfirmDialog(this, "Game paused. Do you want to continue?", "Pause", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.NO_OPTION) {
            // Go back to menu
            myContainer.showCard(MyContainer.PANEL_MENU);
        }
    }
    
    public void initPanelGame() {
        isWin = false;
        isLose = false;
        gameManager.initGame();
        Thread t = new Thread(this);
        t.start();
        setFocusable(true);
        winImage = new ImageIcon(getClass().getResource("/images/youwin1.png")).getImage();
        loseImage = new ImageIcon(getClass().getResource("/images/youlose1.png")).getImage();

        // Prompt for player name at the start of the game
        playerName = JOptionPane.showInputDialog(null, "Enter your name", "Player Name", JOptionPane.PLAIN_MESSAGE);
        // Record the start time
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        score = gameManager.getScore(); // Update score
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gameManager.draw(g2d);

        // Draw player name
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Player: " + playerName, 10, 20); // Draw at top left corner

        // Draw score
        String scoreText = "Score: " + score;
        int x = (getWidth() - g2d.getFontMetrics().stringWidth(scoreText)) / 2;
        int y = g2d.getFontMetrics().getAscent();
        g2d.drawString(scoreText, x, y);

        // Draw elapsed time
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Convert to seconds
        String timeText = "Time: " + elapsedTime + "s";
        g2d.drawString(timeText, getWidth() - g2d.getFontMetrics().stringWidth(timeText) - 10, 20); // Draw at top right corner
    }

    @Override
    public void run() {
        int time = 0;
        int t = 0;
        while (isRunning) {
            t++;
            if (bitSet.get(KeyEvent.VK_LEFT)) {
                gameManager.movePlayer(Player.LEFT);
            } else if (bitSet.get(KeyEvent.VK_RIGHT)) {
                gameManager.movePlayer(Player.RIGHT);
            } else if (bitSet.get(KeyEvent.VK_UP)) {
                gameManager.movePlayer(Player.UP);
            } else if (bitSet.get(KeyEvent.VK_DOWN)) {
                gameManager.movePlayer(Player.DOWN);
            }
//            else if (bitSet.get(KeyEvent.VK_ESCAPE)) {
//                pauseGame();
//            }
            try {
                if (bitSet.get(KeyEvent.VK_SPACE)) {
                    if (t - time >= TIME_DAT) {
                        gameManager.myPlayerBoom(t);
                    }
                    time = t;
                }
            } catch (Exception e) {
            }
            isRunning = gameManager.AI(t);
            if (isRunning == false && gameManager.isCheckDieWin() == false) {
                // Save the player's name and score to the database
                gameManager.saveScore(playerName, gameManager.getScore());

                Clip clip = Sound.getSound(getClass().getResource("/sounds/die.wav"));
                clip.start();

                int result = JOptionPane.showConfirmDialog(null, createPanelWithImage(false), "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    bitSet.clear();
                    gameManager.initGame();
                    isRunning = true;
                    score = 0;
                } else {
                    myContainer.showCard(MyContainer.PANEL_MENU);
                }
            }
            if (isRunning == false && gameManager.isCheckDieWin() == true) {
                gameManager.saveScore(playerName, gameManager.getScore());
                this.isWin = true;

                Clip clip = Sound.getSound(getClass().getResource("/sounds/win.wav"));
                clip.start();

                int result = JOptionPane.showConfirmDialog(null, createPanelWithImage(true), "Next Level ?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    bitSet.clear();
                    isRunning = true;
                } else {
                    myContainer.showCard(MyContainer.PANEL_MENU);
                }
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
        }
    }

    public JPanel createPanelWithImage(boolean isWin) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        if (isWin && this.isWin) { // Thêm điều kiện kiểm tra this.isWin
            JLabel winImageLabel = new JLabel(new ImageIcon(winImage));
            panel.add(winImageLabel, BorderLayout.CENTER);
        } else {
            JLabel loseImageLabel = new JLabel(new ImageIcon(loseImage));
            panel.add(loseImageLabel, BorderLayout.CENTER);
        }

        return panel;
    }
    

    public void saveScore(String playerName, int score) {
        try {
            java.sql.Connection con = MyConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO HighScore (playername, score) VALUES ('" + playerName + "', " + score + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        bitSet.set(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        bitSet.clear();
        e.getKeyCode();
    }
}

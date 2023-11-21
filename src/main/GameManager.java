/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import sounds.Sound;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;
import static main.BitMap.SIZE;
import static main.MyFrame.H_FARME;
import static main.MyFrame.W_FARME;

/**
 *
 * @author buitr
 */
public class GameManager {

    private Player player;
    private ArrayList<BitMap> arrBitMap;
    private ArrayList<Boom> arrBoom;
    private ArrayList<Boss> arrBoss;
    private ArrayList<WaveBoom> arrWaveBoom;
    private ArrayList<Item> arrItem;
    public static final int TIME_BANG = 120;
    public static final int TIME_WAVE = 15;
    private int timeDie;
    private boolean checkDieWin;
    private Random random = new Random();
    private Clip clip1;
    private ArrayList<Integer> timeBoom;
    private ArrayList<Integer> timeWave;
    private int score = 0;

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }
    public final Image[] MY_IMAGE = {
        new ImageIcon(getClass().getResource("/images/background.jpg")).getImage()
    };

    public GameManager() {
        score = 0; // Initialize score to 0
        initGame();
    }

    public boolean isCheckDieWin() {
        return checkDieWin;
    }

    public void setCheckDieWin(boolean checkDieWin) {
        this.checkDieWin = checkDieWin;
    }

    public void initGame() {
        Clip clip = Sound.getSound(getClass().getResource("/sounds/start.wav"));
        clip.start();
        clip1 = Sound.getSound(getClass().getResource("/sounds/soundGame.wav"));
        clip1.start();
        clip1.loop(100);
        timeBoom = new ArrayList<>();
        timeWave = new ArrayList<>();
        arrBoom = new ArrayList<>();
        arrBoss = new ArrayList<>();
        arrWaveBoom = new ArrayList<>();
        arrItem = new ArrayList<>();
        player = new Player(W_FARME / 2, H_FARME - 90 - SIZE, Player.DOWN, 1, PanelSetting.initialSpeed, PanelSetting.initialBoomCount, this);
        arrBitMap = new ArrayList<>();
        readTxtMap();
        initBoss();
        initItem();
    }

    public void initBoss() {
        if (!arrBitMap.isEmpty()) {
            for (int i = 0; i < PanelSetting.bossCount; i++) {
                int orient = random.nextInt(4);
                BitMap point = arrBitMap.get(random.nextInt(arrBitMap.size()));
                while (point.bit != 0) {
                    point = arrBitMap.get(random.nextInt(arrBitMap.size()));
                }
                int xRaw = point.getX();
                int yRaw = point.getY();
                Boss boss = new Boss(xRaw, yRaw, orient);
                arrBoss.add(boss);
            }
        }
    }

    public void initItem() {
        for (int i = 0; i < arrBitMap.size(); i++) {
            int show = random.nextInt(100) + 1;
            if (show > 80 && (arrBitMap.get(i).bit == 2
                    || arrBitMap.get(i).bit == 4 || arrBitMap.get(i).bit == 5)) {
                int xRaw = arrBitMap.get(i).getX();
                int yRaw = arrBitMap.get(i).getY();
                Item item = new Item(xRaw, yRaw);
                arrItem.add(item);
            }
        }
    }

    public void movePlayer(int newOrient) {
        player.changeOrient(newOrient);
        player.move(arrBitMap, arrBoom, 1);
        player.moveItem(arrItem);
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(MY_IMAGE[0], 0, 0, W_FARME, H_FARME, null);
        try {
            for (Boom boom : arrBoom) {
                boom.draw(g2d);
            }
            for (WaveBoom waveBoom : arrWaveBoom) {
                waveBoom.draw(g2d, arrBitMap);
            }
            for (Item item : arrItem) {
                item.draw(g2d);
            }
            for (BitMap bitMap : arrBitMap) {
                bitMap.draw(g2d);
            }

            for (Boss boss : arrBoss) {
                boss.drawBoss(g2d);
            }
            player.draw(g2d);
        } catch (ConcurrentModificationException e) {

        }
    }

    public void myPlayerBoom(int t) {
        Boom boom = player.DatBoom(arrBoom);
        if (arrBoom.size() < player.getSoBoom()) {
            arrBoom.add(boom);
            Clip clip = Sound.getSound(getClass().getResource("/sounds/set_boom.wav"));
            clip.start();
            timeBoom.add(t);
        }
    }

    public boolean AI(int t) {
        for (int i = arrBoss.size() - 1; i >= 0; i--) {
            arrBoss.get(i).moveBoss(arrBitMap, arrBoom, t);
        }
        for (int i = 0; i < arrBoom.size(); i++) {
            if (t - timeBoom.get(i) >= TIME_BANG) {
                WaveBoom waveBoom = arrBoom.get(i).boomBang();
                arrBoom.remove(i);
                Clip clip = Sound.getSound(getClass().getResource("/sounds/boom_bang.wav"));
                clip.start();
                arrWaveBoom.add(waveBoom);
                timeBoom.remove(i);
                try {
                    waveBoom.checkBoomToBoom(arrBoom, timeBoom);
                } catch (IndexOutOfBoundsException e) {
                }
                timeWave.add(t);
            }
        }
        for (int i = 0; i < arrWaveBoom.size(); i++) {

            if (t - timeWave.get(i) >= TIME_WAVE) {
                arrWaveBoom.remove(i);
                timeWave.remove(i);
            }

        }
        for (int i = 0; i < arrWaveBoom.size(); i++) {
            arrWaveBoom.get(i).checkBoomToBoss(arrBoss);
        }
        if (player.checkDieToBoss(arrBoss) == true) {
            clip1.stop();
            setCheckDieWin(false);
            return false;
        }
        for (int i = 0; i < arrWaveBoom.size(); i++) {
            if (arrWaveBoom.get(i).checkBoomToPlayer(arrWaveBoom, player) == true) {
                clip1.stop();
                timeDie = t;
                setCheckDieWin(false);
                return false;
            }
        }
        if (arrBoss.isEmpty()) {
            setCheckDieWin(true);
            clip1.stop();

            // Show a dialog to the user
            int dialogResult = JOptionPane.showConfirmDialog(null, "Next Map ?", "Congratulations!", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                // Load the next level
                loadNextLevel("/textmap/map2.txt");

            }

            return false;
        }
        return true;

    }

    public void readTxtMap() {
        String path = getClass().getResource("/textmap/map1.txt").getPath();
        int intLine = 0;
        try {
            FileInputStream inputStream = new FileInputStream(path);
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    arrBitMap.add(new BitMap(i * SIZE, intLine * SIZE, Integer.parseInt(String.valueOf(line.charAt(i)))));
                }
                line = reader.readLine();
                intLine++;
            }
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadNextLevel(String mapFile) {
        // Clear the current map
        arrBitMap.clear();

        // Reset game objects
        timeBoom = new ArrayList<>();
        timeWave = new ArrayList<>();
        arrBoom = new ArrayList<>();
        arrBoss = new ArrayList<>(); // Reset boss list
        arrWaveBoom = new ArrayList<>();
        arrItem = new ArrayList<>();
        String path = getClass().getResource(mapFile).getPath();
        int intLine = 0;
        try {
            FileInputStream inputStream = new FileInputStream(path);
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    arrBitMap.add(new BitMap(i * SIZE, intLine * SIZE, Integer.parseInt(String.valueOf(line.charAt(i)))));
                }
                line = reader.readLine();
                intLine++;
            }
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize bosses and items for the new level
        initBoss(); // Initialize new bosses
        initItem();
    }

    public void saveScore(String playerName, int score) {
        try {
            Connection con = MyConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "UPDATE HighScore SET score = GREATEST(score, " + score + ") WHERE playername = '" + playerName + "'";
            int updated = stmt.executeUpdate(sql);
            if (updated == 0) {
                sql = "INSERT INTO HighScore (playername, score) VALUES ('" + playerName + "', " + score + ")";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

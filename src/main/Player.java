/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static main.BitMap.SIZE;
import static main.PanelSetting.initialBoomCount;
import static main.PanelSetting.playerSpeed;

/**
 *
 * @author buitr
 */
public class Player {
    private int x;
    private int y;
    private int orient;
    private int initialBossCount = 2;
    private int initialSpeed = 2;
    private int initialBoomCount = 1;
    public Image image;
    private int timeMove;
    private int lenghBoomBang = 1;
    private boolean isPlayerRun = false;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    private int imageIndex = 0;
    private GameManager gameManager;

    
    public final Image[] IMAGE_BONGMO = {
        new ImageIcon(getClass().getResource("/images/BM_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/BM_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/BM_3.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/BM_4.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/BM_5.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/BM_6.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/BM_7.png")).getImage(),};
    public final Image[] IMAGES_HIEUUNG = {
        new ImageIcon(getClass().getResource("/images/hieuUng_11.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/hieuUng_12.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/hieuUng_13.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/hieuUng_14.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/hieuUng_15.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/hieuUng_16.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/hieuUng_17.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/hieuUng_18.png")).getImage(),};
    public final Image[] IMAGES_PLAYER_LEFT = {
        new ImageIcon(getClass().getResource("/images/player_left_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_left_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_left_3.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_left_4.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_left_5.png")).getImage(),};
    public final Image[] IMAGES_PLAYER_RIGHT = {
        new ImageIcon(getClass().getResource("/images/player_right_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_right_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_right_3.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_right_4.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_right_5.png")).getImage(),};
    public final Image[] IMAGES_PLAYER_UP = {
        new ImageIcon(getClass().getResource("/images/player_up_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_up_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_up_3.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_up_4.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_up_5.png")).getImage(),};
    public final Image[] IMAGES_PLAYER_DOWN = {
        new ImageIcon(getClass().getResource("/images/player_down_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_down_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_down_3.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_down_4.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/player_down_5.png")).getImage(),};

    public Player(int x, int y, int orient, int timeMove, int initialSpeed, int initialBoomCount, GameManager gameManager) {
    this.x = x;
    this.y = y;
    this.orient = orient;
    this.timeMove = timeMove;
    this.initialSpeed = initialSpeed;
    this.initialBoomCount = initialBoomCount;
    PanelSetting.playerSpeed = this.initialSpeed;
    PanelSetting.initialBoomCount = this.initialBoomCount;
    this.gameManager = gameManager;
}

    public int getSoBoom() {
        return initialBoomCount;
    }
    public void setSpeed(int speed) {
        PanelSetting.playerSpeed += 1;
    }

    public void setSoBoom(int soBoom) {
        PanelSetting.initialBoomCount += 1;
    }
    public int getSpeed() {
        return playerSpeed;
    }


    public void changeOrient(int newOrient) {
        orient = newOrient;
        isPlayerRun = true;
    }

    public boolean isIrun() {
        return isPlayerRun;
    }

    public void draw(Graphics2D g2d) {
        switch (orient) {
            case LEFT: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_PLAYER_LEFT[0], x, y, SIZE + 5, SIZE + 15, null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_PLAYER_LEFT[imageIndex / 10 % IMAGES_PLAYER_LEFT.length], x, y, SIZE + 5, SIZE + 15, null);
                    g2d.drawImage(IMAGE_BONGMO[imageIndex / 7 % IMAGE_BONGMO.length], x + 35, y + 15, SIZE, SIZE, null);
                }
                break;
            }
            case RIGHT: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_PLAYER_RIGHT[0], x, y, SIZE + 5, SIZE + 15, null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_PLAYER_RIGHT[imageIndex / 10 % IMAGES_PLAYER_RIGHT.length], x, y, SIZE + 5, SIZE + 15, null);
                    g2d.drawImage(IMAGE_BONGMO[imageIndex / 7 % IMAGE_BONGMO.length], x - 35, y + 15, SIZE, SIZE, null);
                }
                break;
            }
            case UP: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_PLAYER_UP[0], x, y, SIZE + 5, SIZE + 15, null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_PLAYER_UP[imageIndex / 10 % IMAGES_PLAYER_UP.length], x, y, SIZE + 5, SIZE + 15, null);
                    g2d.drawImage(IMAGE_BONGMO[imageIndex / 7 % IMAGE_BONGMO.length], x, y + 25, SIZE, SIZE, null);
                }
                break;
            }
            case DOWN: {
                if (!isIrun()) {
                    g2d.drawImage(IMAGES_PLAYER_DOWN[0], x, y, SIZE + 5, SIZE + 15, null);
                } else {
                    imageIndex++;
                    g2d.drawImage(IMAGES_PLAYER_DOWN[imageIndex / 10 % IMAGES_PLAYER_LEFT.length], x, y, SIZE + 5, SIZE + 15, null);
                    g2d.drawImage(IMAGE_BONGMO[imageIndex / 7 % IMAGE_BONGMO.length], x, y - 30, SIZE, SIZE, null);
                }
            }
            break;
        }
        isPlayerRun = false;
        imageIndex++;
        g2d.drawImage(IMAGES_HIEUUNG[imageIndex / 7 % IMAGES_HIEUUNG.length], x - 5, y, SIZE + 20, SIZE + 20, null);
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y + 25, SIZE - 10, SIZE - 10);
        return rectangle;
    }

    public boolean checkItem(ArrayList<Item> arrItem) {
        for (int i = 0; i < arrItem.size(); i++) {
            if (getRect().intersects(arrItem.get(i).getRect())) {
                arrItem.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean checkMoveMap(ArrayList<BitMap> arrBitMap) {
        for (BitMap bitMap : arrBitMap) {
            if (bitMap.bit == 5 || bitMap.bit == 1 || bitMap.bit == 2 || bitMap.bit == 3
                    || bitMap.bit == 4 || bitMap.bit == 6 || bitMap.bit == 7 || bitMap.bit == 8
                    || bitMap.bit == 9) {
                Rectangle rectangle = getRect().intersection(bitMap.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setMoveBoom(ArrayList<Boom> arrBoom) {
        for (int i = 0; i < arrBoom.size(); i++) {
            Rectangle rectangle = getRect().intersection(arrBoom.get(i).getRect());
            if (rectangle.isEmpty() == true) {
                arrBoom.get(i).setCheckBoom(0);
            }
        }
    }

    public boolean checkMoveBoom(ArrayList<Boom> arrBoom) {
        setMoveBoom(arrBoom);
        for (int i = 0; i < arrBoom.size(); i++) {
            Rectangle rectangle = getRect().intersection(arrBoom.get(i).getRect());
            if (rectangle.isEmpty() == false && arrBoom.get(i).isCheckBoom() == 0) {
                return false;
            }
        }
        return true;
    }

    public void move(ArrayList<BitMap> arrBitMap, ArrayList<Boom> arrBoom, int t) {
        if (t % timeMove != 0) {
            return;
        }
        int xRaw = x;
        int yRaw = y;
        switch (orient) {
            case LEFT:
                xRaw -= playerSpeed;
                break;
            case RIGHT:
                xRaw += playerSpeed;
                break;
            case UP:
                yRaw -= playerSpeed;
                break;
            case DOWN:
                yRaw += playerSpeed;
            default:
        }
        int xRaw1 = x;
        int yRaw1 = y;
        x = xRaw;
        y = yRaw;
        boolean checkMovePlayer = checkMoveMap(arrBitMap);
        boolean checkMovePlayerToBoom = checkMoveBoom(arrBoom);
        if (checkMovePlayer == true) {
            x = xRaw1;
            y = yRaw1;
        }
        if (checkMovePlayerToBoom == false) {
            x = xRaw1;
            y = yRaw1;
        }
    }

    public boolean checkDieToBoss(ArrayList<Boss> arrBoss){
        boolean isBossKilled = false;
        for (int i=0;i<arrBoss.size();i++){
            Rectangle rectangle=getRect().intersection(arrBoss.get(i).getRect());
            if (rectangle.isEmpty()== false){  
                isBossKilled = true;
            }
        }
        return isBossKilled;
    }

    public Boom DatBoom(ArrayList<Boom> arrBoom) {
        int xRaw = this.x + SIZE / 2;
        int yRaw = this.y + SIZE / 2;
        int xBoom = xRaw - xRaw % SIZE + 15;
        int yBoom = yRaw - yRaw % SIZE;
        int lengBoom = this.lenghBoomBang;
        Boom boom = new Boom(xBoom, yBoom, lengBoom);
        return boom;
    }

    public void moveItem(ArrayList<Item> arrItem){
        for (int i=0;i<arrItem.size();i++){
            Rectangle rectangle=getRect().intersection(arrItem.get(i).getRect());
            if (rectangle.isEmpty()==false){
                switch (arrItem.get(i).getBitItem()) {
                    case 0:
                        setSoBoom(initialBoomCount);
                        System.out.println("Số Boom: "+getSoBoom());
                        arrItem.remove(i);
                        gameManager.addScore(10); // Cộng điểm khi nhặt item loại 0
                        break;
                    case 1:
                        lenghBoomBang++;
                        System.out.println("Độ dài Boom: "+lenghBoomBang);
                        arrItem.remove(i);
                        gameManager.addScore(10); // Cộng điểm khi nhặt item loại 1
                        break;
                    case 2:
                        setSpeed(getSpeed()+1);
                        System.out.println("Tốc độ: "+playerSpeed);
                        arrItem.remove(i);
                        gameManager.addScore(10); // Cộng điểm khi nhặt item loại 2
                        break;
                    default:
                        break;
                }
                Clip clip= Sound.getSound(getClass().getResource("/sounds/item.wav"));
                clip.start();
            }
        }
    }
}

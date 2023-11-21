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
import java.awt.Rectangle;

/**
 *
 * @author buitr
 */
public class WaveBoom {

    private int x;
    private int y;
    public int lengh = 2;
    private int lenghLeft = lengh;
    private int lenghRight = lengh;
    private int lenghUp = lengh;
    private int lenghDown = lengh;
    private int xBossDie;
    private int yBossDie;
    private int imageIndex = 0;
    private GameManager gameManager;

    public Rectangle getRect() {
        int size = SIZE;
        return new Rectangle(x * size, y * size, size, size);
    }
    public final Image[] WAVE_BOOM = {
        new ImageIcon(getClass().getResource("/images/bombbang_left_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/bombbang_right_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/bombbang_up_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/bombbang_down_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/bombbang_mid_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/bombbang_left_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/bombbang_right_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/bombbang_up_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/bombbang_down_1.png")).getImage(),};

    public final Image[] BOSS_DIE = {
        new ImageIcon(getClass().getResource("/images/boss_die_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/boss_die_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/boss_die_3.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/boss_die_4.png")).getImage(),};

    public final Image[] DEL_MAP = {
        new ImageIcon(getClass().getResource("/images/del_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/del_2.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/del_3.png")).getImage(),
        new ImageIcon(getClass().getResource("/images/del_4.png")).getImage(),};

    public WaveBoom(int x, int y, int lenghWave) {
        this.x = x;
        this.y = y;
        this.lenghLeft = lenghWave;
        this.lenghRight = lenghWave;
        this.lenghDown = lenghWave;
        this.lenghUp = lenghWave;
    }

    public boolean checkBoomToPlayer(ArrayList<WaveBoom> arrWaveBoom, Player player) {
        for (int i = 0; i < arrWaveBoom.size(); i++) {
            Rectangle rectangle1 = getRect(x + 10, y + 20).intersection(player.getRect());
            if (rectangle1.isEmpty() == false) {
                return true;
            }
            for (int j = 1; j <= lenghLeft; j++) {
                int xRaw = x - j * SIZE + 10;
                int yRaw = y + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(player.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }
            }
            for (int j = 1; j <= lenghRight; j++) {
                int xRaw = x + j * SIZE + 10;
                int yRaw = y + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(player.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }
            }
            for (int j = 1; j <= lenghUp; j++) {
                int xRaw = x + 10;
                int yRaw = y - j * SIZE + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(player.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }
            }
            for (int j = 1; j <= lenghDown; j++) {
                int xRaw = x + 10;
                int yRaw = y + j * SIZE + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(player.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }
            }
        }
        return false;
    }

    public void checkBoomToBoss(ArrayList<Boss> arrBoss) {
        for (int i = 0; i < arrBoss.size(); i++) {
            try {
                Rectangle rectangle1 = getRect(x + 10, y + 20).intersection(arrBoss.get(i).getRect());
                if (rectangle1.isEmpty() == false) {
                    xBossDie = arrBoss.get(i).getX();
                    yBossDie = arrBoss.get(i).getY();
                    arrBoss.remove(i);
                    Clip clip = Sound.getSound(getClass().getResource("/sounds/bang_bang.wav"));
                    clip.start();
                }
                for (int j = 1; j <= lenghLeft; j++) {
                    int xRaw = x - j * SIZE + 10;
                    int yRaw = y + 20;
                    Rectangle rectangle0 = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangle0.isEmpty() == false) {
                        xBossDie = arrBoss.get(i).getX();
                        yBossDie = arrBoss.get(i).getY();
                        arrBoss.remove(i);
                        Clip clip = Sound.getSound(getClass().getResource("/sounds/bang_bang.wav"));
                        clip.start();
                    }
                }
                for (int j = 1; j <= lenghRight; j++) {
                    int xRaw = x + j * SIZE + 10;
                    int yRaw = y + 20;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        xBossDie = arrBoss.get(i).getX();
                        yBossDie = arrBoss.get(i).getY();
                        arrBoss.remove(i);
                        Clip clip = Sound.getSound(getClass().getResource("/sounds/bang_bang.wav"));
                        clip.start();
                    }
                }
                for (int j = 1; j <= lenghUp; j++) {
                    int xRaw = x + 10;
                    int yRaw = y - j * SIZE + 20;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        xBossDie = arrBoss.get(i).getX();
                        yBossDie = arrBoss.get(i).getY();

                        arrBoss.remove(i);
                        Clip clip = Sound.getSound(getClass().getResource("/sounds/bang_bang.wav"));
                        clip.start();

                    }
                }
                for (int j = 1; j <= lenghDown; j++) {
                    int xRaw = x + 10;
                    int yRaw = y + j * SIZE + 20;
                    Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangle.isEmpty() == false) {
                        xBossDie = arrBoss.get(i).getX();
                        yBossDie = arrBoss.get(i).getY();
                        arrBoss.remove(i);
                        Clip clip = Sound.getSound(getClass().getResource("/sounds/bang_bang.wav"));
                        clip.start();
                    }
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }

    public void checkBoomToBoom(ArrayList<Boom> arrBoom, ArrayList<Integer> timeBoom) {
        for (int i = 0; i < arrBoom.size(); i++) {
            Rectangle rectangle1 = getRect(x + 10, y + 20).intersection(arrBoom.get(i).getRect());
            if (rectangle1.isEmpty() == false) {
                timeBoom.set(i, 0);
            }
            for (int j = 1; j <= lenghLeft; j++) {
                int xRaw = x - j * SIZE + 10;
                int yRaw = y + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    timeBoom.set(i, 0);
                }
            }
            for (int j = 1; j <= lenghRight; j++) {
                int xRaw = x + j * SIZE + 10;
                int yRaw = y + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    timeBoom.set(i, 0);
                }
            }
            for (int j = 1; j <= lenghUp; j++) {
                int xRaw = x + 10;
                int yRaw = y - j * SIZE + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    timeBoom.set(i, 0);
                }
            }
            for (int j = 1; j <= lenghDown; j++) {
                int xRaw = x + 10;
                int yRaw = y + j * SIZE + 20;
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    timeBoom.set(i, 0);
                }
            }
        }
    }

    public void draw(Graphics2D g2d, ArrayList<BitMap> arrBitMap) {
        drawMid(g2d, arrBitMap);
        drawLeft(g2d, arrBitMap);
        drawRight(g2d, arrBitMap);
        drawUp(g2d, arrBitMap);
        drawDown(g2d, arrBitMap);
        if (xBossDie != 0 || yBossDie != 0) {
            imageIndex++;
            Image image = BOSS_DIE[imageIndex / 50 % BOSS_DIE.length];
            g2d.drawImage(image, xBossDie, yBossDie, null);
        }
    }

    public void drawMid(Graphics2D g2d, ArrayList<BitMap> arrBitMap) {
        g2d.drawImage(WAVE_BOOM[4], x + 10, y + 20, null);
    }

    public Rectangle getRect(int xRaw, int yRaw) {
        Rectangle rectangle = new Rectangle(xRaw + 5, yRaw + 3, SIZE - 10, SIZE - 10);
        return rectangle;
    }

    public void drawLeft(Graphics2D g2d, ArrayList<BitMap> arrBitMap) {
        for (int j = 1; j <= lenghLeft; j++) {
            int xRaw = x - j * SIZE + 10;
            int yRaw = y + 20;
            if (j == lenghLeft) {
                g2d.drawImage(WAVE_BOOM[0], xRaw + 5, yRaw - 6, null);
            } else {
                g2d.drawImage(WAVE_BOOM[5], xRaw, yRaw - 6, null);
            }
            for (int i = 0; i < arrBitMap.size(); i++) {
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBitMap.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrBitMap.get(i).bit == 2 || arrBitMap.get(i).bit == 4
                            || arrBitMap.get(i).bit == 5) {
                        arrBitMap.remove(i);
                        lenghLeft = lenghLeft - (lenghLeft - j);

                    } else if (arrBitMap.get(i).bit != 2 && arrBitMap.get(i).bit != 4
                            && arrBitMap.get(i).bit != 5 && arrBitMap.get(i).bit != 0) {
                        lenghLeft = lenghLeft - (lenghLeft - j);
                    }
                }
            }
        }
    }

    public void drawRight(Graphics2D g2d, ArrayList<BitMap> arrBitMap) {
        for (int j = 1; j <= lenghRight; j++) {
            int xRaw = x + j * SIZE + 10;
            int yRaw = y + 20;
            if (j == lenghRight) {
                g2d.drawImage(WAVE_BOOM[1], xRaw - 3, yRaw - 6, null);
            } else {
                g2d.drawImage(WAVE_BOOM[6], xRaw, yRaw - 6, null);
            }
            for (int i = 0; i < arrBitMap.size(); i++) {
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBitMap.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrBitMap.get(i).bit == 2 || arrBitMap.get(i).bit == 4
                            || arrBitMap.get(i).bit == 5) {
                        arrBitMap.remove(i);
                        lenghRight = lenghRight - (lenghRight - j);

                    } else if (arrBitMap.get(i).bit != 2 && arrBitMap.get(i).bit != 4
                            && arrBitMap.get(i).bit != 5 && arrBitMap.get(i).bit != 0) {
                        lenghRight = lenghRight - (lenghRight - j);
                    }
                }

            }
        }
    }

    public void drawUp(Graphics2D g2d, ArrayList<BitMap> arrBitMap) {
        for (int j = 1; j <= lenghUp; j++) {
            int xRaw = x + 10;
            int yRaw = y - j * SIZE + 20;
            if (j == lenghUp) {
                g2d.drawImage(WAVE_BOOM[2], xRaw, yRaw + 5, null);
            } else {
                g2d.drawImage(WAVE_BOOM[7], xRaw, yRaw, null);
            }
            for (int i = 0; i < arrBitMap.size(); i++) {
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBitMap.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrBitMap.get(i).bit == 2 || arrBitMap.get(i).bit == 4
                            || arrBitMap.get(i).bit == 5) {
                        arrBitMap.remove(i);
                        imageIndex++;
                        g2d.drawImage(DEL_MAP[imageIndex / 20 % DEL_MAP.length], arrBitMap.get(i).getX(), arrBitMap.get(i).getY(), null);
                        lenghUp = lenghUp - (lenghUp - j);

                    } else if (arrBitMap.get(i).bit != 2 && arrBitMap.get(i).bit != 4
                            && arrBitMap.get(i).bit != 5 && arrBitMap.get(i).bit != 0) {
                        lenghUp = lenghUp - (lenghUp - j);
                    }
                }
            }

        }
    }

    public void drawDown(Graphics2D g2d, ArrayList<BitMap> arrBitMap) {
        for (int j = 1; j <= lenghDown; j++) {
            int xRaw = x + 10;
            int yRaw = y + j * SIZE + 20;
            if (j == lenghDown) {
                g2d.drawImage(WAVE_BOOM[3], xRaw, yRaw - 3, null);
            } else {
                g2d.drawImage(WAVE_BOOM[8], xRaw, yRaw, null);
            }
            for (int i = 0; i < arrBitMap.size(); i++) {
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrBitMap.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrBitMap.get(i).bit == 2 || arrBitMap.get(i).bit == 4
                            || arrBitMap.get(i).bit == 5) {
                        arrBitMap.remove(i);
                        imageIndex++;
                        g2d.drawImage(DEL_MAP[imageIndex / 20 % DEL_MAP.length], arrBitMap.get(i).getX(), arrBitMap.get(i).getY(), null);
                        lenghDown = lenghDown - (lenghDown - j);

                    } else if (arrBitMap.get(i).bit != 2 && arrBitMap.get(i).bit != 4
                            && arrBitMap.get(i).bit != 5 && arrBitMap.get(i).bit != 0) {
                        lenghDown = lenghDown - (lenghDown - j);
                    }
                }
            }
        }
    }

    public int checkBoomToBitMap(ArrayList<BitMap> arrBitMap) {
        int destroyedBitMaps = 0;
        for (int i = 0; i < arrBitMap.size(); i++) {
            BitMap bitMap = arrBitMap.get(i);
            // Check if the WaveBoom intersects with the BitMap
            if (this.getRect().intersects(bitMap.getRect())) {
                // Check if the BitMap is a destructible object (like a tree, red mushroom, or green mushroom)
                if (bitMap.bit == 1 || bitMap.bit == 3 || bitMap.bit == 4) {
                    // Remove the BitMap from the array
                    arrBitMap.remove(i);
                    i--; // Decrement i since we removed an element
                    destroyedBitMaps++;
                    gameManager.addScore(5);

                }
            }
        }
        return destroyedBitMaps;
    }

}

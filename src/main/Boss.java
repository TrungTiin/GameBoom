/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import static main.BitMap.SIZE;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author buitr
 */
public class Boss {
    private int x;
    private int y;
    private int orient;
    private Image image;
    private int imageIndex=0;
    private Random random= new Random();
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;

    public final Image[] MY_BOSS={
            new ImageIcon(getClass().getResource("/images/boss_left.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/boss_right.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/boss_up.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/boss_down.png")).getImage(),
    };
    public final Image[] IMAGES_HIEUUNG={
            new ImageIcon(getClass().getResource("/images/hieuUng_0.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/hieuUng_1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/hieuUng_2.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/hieuUng_3.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/hieuUng_4.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/hieuUng_5.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/hieuUng_6.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/hieuUng_7.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/hieuUng_8.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/hieuUng_9.png")).getImage(),

    };

    public Boss(int x, int y, int orient) {
        this.x = x;
        this.y = y;
        this.orient = orient;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void changeOrient(int newOrient){
        orient=newOrient;
    }

    public void creatOrient(){
        int percent= random.nextInt(100);
        if (percent>95){
            int newOrient=random.nextInt(4);
            changeOrient(newOrient);
            image= MY_BOSS[newOrient];
        }
    }

    public void drawBoss(Graphics2D g2d){
        imageIndex++;
        g2d.drawImage(IMAGES_HIEUUNG[imageIndex/7% IMAGES_HIEUUNG.length],x,y,SIZE,SIZE,null);
        g2d.drawImage(image,x,y,SIZE,SIZE,null);

}


    public boolean checkMoveBoom(ArrayList<Boom> arrBoom){
        for (int i=0;i<arrBoom.size();i++){
            Rectangle rectangle= getRect().intersection(arrBoom.get(i).getRect());
            if (rectangle.isEmpty()==false && arrBoom.get(i).isCheckBoom()==0){
                return false;
            }
        }
        return true;
    }

    public void moveBoss(ArrayList<BitMap> arrBitMap,ArrayList<Boom> arrBoom,int t) {
        int speed = 2;
        int xRaw = x;
        int yRaw = y;
        switch (orient) {
            case LEFT:
                xRaw -= speed;
                break;
            case RIGHT:
                xRaw += speed;
                break;
            case UP:
                yRaw -= speed;
                break;
            case DOWN:
                yRaw += speed;
            default:
        }
        int xRaw1 = x;
        int yRaw1 = y;
        x = xRaw;
        y = yRaw;
        boolean checkMoveBoss = checkMove(arrBitMap);
        boolean checkMoveBossBoom= checkMoveBoom(arrBoom);
        if (checkMoveBoss==true){
            x=xRaw1;
            y=yRaw1;
        }
        if (checkMoveBossBoom==false){
            x=xRaw1;
            y=yRaw1;
        }
        creatOrient();
    }

    public boolean checkMove(ArrayList<BitMap> arrBitMap) {
        for (BitMap bitMap: arrBitMap){
            if (bitMap.bit == 5 || bitMap.bit == 1 ||bitMap.bit == 2 ||bitMap.bit == 3 ||
                    bitMap.bit == 4 || bitMap.bit== 6 ||  bitMap.bit== 7 || bitMap.bit== 8
                    || bitMap.bit== 9) {
                Rectangle rectangle = getRect().intersection(bitMap.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }
            }
        }
        return  false;
    }


    public Rectangle getRect() {
        Rectangle rectangle= new Rectangle(x,y+15,SIZE-10,SIZE-5);
        return rectangle;
    }
}

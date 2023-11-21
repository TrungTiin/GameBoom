/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author buitr
 */
public class BitMap {
    private int x;
    private int y;
    public int bit;
    public static final int SIZE=45;
    public final Image[] MY_IMAGE={
            new ImageIcon(getClass().getResource("/images/goccay1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/cay1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/da1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/namdo1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/namxanh1.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/vienDuoi.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/vienTren.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/vienPhai.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/vienTrai.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/background.jpg")).getImage(),
            new ImageIcon(getClass().getResource("/images/gocTrenTrai.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/gocTrenPhai.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/gocDuoiTrai.png")).getImage(),
            new ImageIcon(getClass().getResource("/images/gocDuoiPhai.png")).getImage(),
    };

    public BitMap(int x, int y, int bit) {
        this.x = x;
        this.y = y;
        this.bit = bit;
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

    public Rectangle getRect(){
        Rectangle rectangle=new Rectangle(x,y+15,SIZE-10,SIZE-10);
        return rectangle;
    }
    public void draw(Graphics2D g2d){
            if (bit !=0) {
            g2d.drawImage(MY_IMAGE[bit - 1], x,y,SIZE+2,SIZE+2,null);
             }
            if (x== 0&& y==0){
                g2d.drawImage(MY_IMAGE[10],x,y,null);
            }
            if (x== 16*SIZE && y==0){
                g2d.drawImage(MY_IMAGE[11],x,y,null);
            }
            if (x== 0 && y==14*SIZE){
                g2d.drawImage(MY_IMAGE[12],x,y,null);
            }
            if (x== 16*SIZE && y== 14*SIZE){
                g2d.drawImage(MY_IMAGE[13],x,y,null);
            }
        }

}


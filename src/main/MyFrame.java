    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.*;

/**
 *
 * @author buitr
 */
public class MyFrame extends JFrame {
    public static final int W_FARME = 770;
    public static final int H_FARME = 705;

    public MyFrame(){
        initMyFrame();
    }

    private void initMyFrame() {
        setTitle("Boom");
        setSize(W_FARME,H_FARME);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new MyContainer());
    }
}


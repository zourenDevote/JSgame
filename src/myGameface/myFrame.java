package myGameface;

import Figuer.GameMan;

import javax.sound.midi.MidiChannel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class myFrame extends JFrame {

    /**
     * 特殊字段
     * 玩家类GameMan
     * 左右启动和上下启动
     */
    final private GameMan man;

    /**
     * 构造器
     */
    public myFrame(){
        this.setTitle("疯狂的僵尸");
        this.setSize(new Dimension(800,600));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        man = new GameMan(100,100,20,this.getWidth(),this.getHeight());

    }

    /**
     * 显示界面
     */
    public void setUIS(){

        this.setVisible(true);
        Graphics g = this.getGraphics();
        myFrame myframe = this;
        KeyLis keyLis = new KeyLis(man,g);
        GameDoing game = new GameDoing(man,g);
        game.start();
        g.setColor(Color.BLACK);
        this.addKeyListener(new KeyAdapter() {
            int value;
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                value = e.getKeyCode();
                System.out.println(value);
                switch (value) {
                    case KeyEvent.VK_W: {
                        man.yMove(false, 1);
                        break;
                    }
                    case KeyEvent.VK_S: {
                        man.yMove(true, 1);
                        break;
                    }
                    case KeyEvent.VK_A: {
                        man.xMove(false, 1);
                        break;
                    }
                    case KeyEvent.VK_D: {
                        man.xMove(true, 1);
                        break;
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent e){
                value = e.getKeyCode();
                switch (value){
                    case KeyEvent.VK_W:{
                        man.yMove(false,0);
                        break;
                    }
                    case KeyEvent.VK_S:{
                        man.yMove(true,0);
                        break;
                    }
                    case KeyEvent.VK_A:{
                        man.xMove(false,0);
                        break;
                    }
                    case KeyEvent.VK_D:{
                        man.xMove(true,0);
                        break;
                    }
                }
            }
        });
        this.addKeyListener(keyLis);
        this.requestFocus(true);
    }



    /**
     * 重写父类的paint方法
     * 在paint中画出框格，生命值，蓝量、技能、设置窗格、加载动画
     * @param g
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
    }



}

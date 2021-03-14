package myGameface;

import Figuer.GameMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyLis implements java.awt.event.KeyListener {

    /**
     * 属性字段
     */
    final private GameMan man;


    /**
     * 画布对象g
     */
    private Graphics g;
    private int value;


    public KeyLis(GameMan man, Graphics g){
        this.man = man;
        this.g = g;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 当空格键按下，获取value值
     * 松开后，发送子弹
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) { value = e.getKeyCode(); }
    @Override
    public void keyReleased(KeyEvent e) {
        if(value==KeyEvent.VK_SPACE){
            man.creatBullet(man.getDirection());
//            System.out.println("创建成功！");
        }else if(value==KeyEvent.VK_B){
            man.changeSpeed(1);
        } else if (value==KeyEvent.VK_V){
            man.changeSpeed(-1);
        }
    }
}

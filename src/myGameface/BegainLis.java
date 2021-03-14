package myGameface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 监听开始界面的
 * 1、当监听到开始按钮按下时，弹出新的窗口，关闭现有的窗口
 * 2、当听到设置按钮时，弹出另一个新的窗口
 * 3、当监听到退出游戏的界面操作时，结束操作，关闭游戏
 */
public class BegainLis implements ActionListener {


    private BegainFace face;
    private myFrame gameGui;
    public BegainLis(BegainFace face){
        this.face = face;
        gameGui = new myFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "开始游戏":{
                face.setVisible(false);
                gameGui.setUIS();
                break;
            }
            case "设置":{
                face.inSetFace();
                break;
            }
            case "退出游戏":{
                JOptionPane JO = new JOptionPane();
                JO.showConfirmDialog(null,"确认退出");
                int value = JO.getOptionType();
                if(value!=JOptionPane.CANCEL_OPTION){
                    face.dispose();
                    if(gameGui!=null){
                        gameGui.dispose();
                    }
                    System.exit(0);//进程退出
                }
                break;
            }
        }
    }
}

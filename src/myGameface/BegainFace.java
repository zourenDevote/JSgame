package myGameface;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.security.DrbgParameters;

public class BegainFace extends JFrame {


    /**
     * 界面布局
     * *************************************
     * *                *                  *
     * *                *                  *
     * *   开始游戏      *     排行榜         *
     * *                *                  *
     * *     设置        *                  *
     * *                *                  *
     * *   退出游戏       *                  *
     * *                *                  *
     * *                *                  *
     * *************************************
     */
    public void setUI(){
        this.setTitle("疯狂的僵尸");
        this.setSize(new Dimension(500,400));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JLayeredPane jlp = new JLayeredPane();  //第二底层容器

        ImageIcon icon = new ImageIcon("src\\Photo\\back01.jpg"); //设置的背景图片
        Image shrinkImage = icon.getImage().getScaledInstance(this.getWidth(),this.getHeight(),0);
        JLabel backShowLab = new JLabel(new ImageIcon(shrinkImage));
        backShowLab.setBounds(0,0,this.getWidth(),this.getHeight());
        JPanel jp = new JPanel();
        jp.setBounds(0,0,this.getWidth(),this.getHeight());
        jp.add(backShowLab);
        //将JLabel置底，将mainContainPan置于JLabel上显示
        jlp.add(jp,JLayeredPane.DEFAULT_LAYER);

        //添加布局
        JButton btn1 = new JButton("开始游戏");
        btn1.setBounds(50,110,150,30);
        JButton btn2 = new JButton("设置");
        btn2.setBounds(50,170,150,30);
        JButton btn3 = new JButton("退出游戏");
        btn3.setBounds(50,230,150,30);
        jlp.add(btn1,JLayeredPane.DRAG_LAYER);
        jlp.add(btn2,JLayeredPane.DRAG_LAYER);
        jlp.add(btn3,JLayeredPane.DRAG_LAYER);
        this.setLayeredPane(jlp);


        //添加监听器
        BegainLis lis = new BegainLis(this);
        btn1.addActionListener(lis);
        btn2.addActionListener(lis);
        btn3.addActionListener(lis);



        this.setVisible(true);


    }

    /**
     * 打开设置界面
     */
    public void inSetFace(){
        this.setVisible(false);
        SetClaFace setface = new SetClaFace(this);
        setface.setUI();
    }


    /**
     * 主函数进入入口
     * @param args
     */
    public static void main(String[] args){
        BegainFace beg = new BegainFace();
        beg.setUI();
    }



}

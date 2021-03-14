package myGameface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetClaFace extends JFrame {


    /**
     * 更换背景需要用到文件地址
     * 跳转界面需要用到BegainFace
     */
    private String filename;
    private BegainFace face;

    public SetClaFace(BegainFace face){
        this.face = face;
        filename = "src\\Photo\\setFaceph.jpg";
    }

    /**
     * setUI方法，展示设置界面
     * 界面长宽：w：200 h：500
     * 按钮间隔 280/3
     * 按钮长宽 w：100 h：20
     * 第一个按钮起始位置 x：50 y：100
     * *****************
     *
     *      成就
     *
     *      音效
     *
     *     更换背景
     *
     *    返回主菜单
     *
     * *****************
     */
    public void setUI(){

        //设置JFrame的属性
        this.setTitle("游戏设置");
        this.setSize(300,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //在JLayeredPane容器上添加不同深度的控件
        JLayeredPane jsp = new JLayeredPane();

        //添加背景图片
        ImageIcon icon = new ImageIcon(filename);
        Image img = icon.getImage().getScaledInstance(this.getWidth(),this.getHeight(),0);
        JLabel lab = new JLabel(new ImageIcon(img));
        lab.setBounds(0,0,this.getWidth(),this.getHeight());
        JPanel pan = new JPanel();
        pan.setBounds(0,0,this.getWidth(),this.getHeight());
        pan.add(lab);
        jsp.add(pan,JLayeredPane.DEFAULT_LAYER);

        //添加按钮;
        JButton btn_1 = new JButton("背景音乐");
        btn_1.setBounds(40,40,200,40);
        jsp.add(btn_1,JLayeredPane.DRAG_LAYER);
        JButton btn_2 = new JButton("音效");
        btn_2.setBounds(40,150,200,40);
        jsp.add(btn_2,JLayeredPane.DRAG_LAYER);
        JButton btn_3 = new JButton("更换背景");
        btn_3.setBounds(40,260,200,40);
        jsp.add(btn_3,JLayeredPane.DRAG_LAYER);
        JButton btn_4 = new JButton("返回主菜单");
        btn_4.setBounds(40,370,200,40);
        jsp.add(btn_4,JLayeredPane.DRAG_LAYER);

        this.setLayeredPane(jsp);

        //添加监听器
        btn_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFace();
            }
        });

        this.setVisible(true);
    }

    /**
     * 关闭界面
     */
    public void closeFace(){
        this.dispose();
        face.setVisible(true);
    }

}

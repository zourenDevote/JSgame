package myGameface;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.security.DrbgParameters;

public class BegainFace extends JFrame {


    /**
     * ���沼��
     * *************************************
     * *                *                  *
     * *                *                  *
     * *   ��ʼ��Ϸ      *     ���а�         *
     * *                *                  *
     * *     ����        *                  *
     * *                *                  *
     * *   �˳���Ϸ       *                  *
     * *                *                  *
     * *                *                  *
     * *************************************
     */
    public void setUI(){
        this.setTitle("���Ľ�ʬ");
        this.setSize(new Dimension(500,400));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JLayeredPane jlp = new JLayeredPane();  //�ڶ��ײ�����

        ImageIcon icon = new ImageIcon("src\\Photo\\back01.jpg"); //���õı���ͼƬ
        Image shrinkImage = icon.getImage().getScaledInstance(this.getWidth(),this.getHeight(),0);
        JLabel backShowLab = new JLabel(new ImageIcon(shrinkImage));
        backShowLab.setBounds(0,0,this.getWidth(),this.getHeight());
        JPanel jp = new JPanel();
        jp.setBounds(0,0,this.getWidth(),this.getHeight());
        jp.add(backShowLab);
        //��JLabel�õף���mainContainPan����JLabel����ʾ
        jlp.add(jp,JLayeredPane.DEFAULT_LAYER);

        //��Ӳ���
        JButton btn1 = new JButton("��ʼ��Ϸ");
        btn1.setBounds(50,110,150,30);
        JButton btn2 = new JButton("����");
        btn2.setBounds(50,170,150,30);
        JButton btn3 = new JButton("�˳���Ϸ");
        btn3.setBounds(50,230,150,30);
        jlp.add(btn1,JLayeredPane.DRAG_LAYER);
        jlp.add(btn2,JLayeredPane.DRAG_LAYER);
        jlp.add(btn3,JLayeredPane.DRAG_LAYER);
        this.setLayeredPane(jlp);


        //��Ӽ�����
        BegainLis lis = new BegainLis(this);
        btn1.addActionListener(lis);
        btn2.addActionListener(lis);
        btn3.addActionListener(lis);



        this.setVisible(true);


    }

    /**
     * �����ý���
     */
    public void inSetFace(){
        this.setVisible(false);
        SetClaFace setface = new SetClaFace(this);
        setface.setUI();
    }


    /**
     * �������������
     * @param args
     */
    public static void main(String[] args){
        BegainFace beg = new BegainFace();
        beg.setUI();
    }



}

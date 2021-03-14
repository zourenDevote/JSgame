package myGameface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetClaFace extends JFrame {


    /**
     * ����������Ҫ�õ��ļ���ַ
     * ��ת������Ҫ�õ�BegainFace
     */
    private String filename;
    private BegainFace face;

    public SetClaFace(BegainFace face){
        this.face = face;
        filename = "src\\Photo\\setFaceph.jpg";
    }

    /**
     * setUI������չʾ���ý���
     * ���泤��w��200 h��500
     * ��ť��� 280/3
     * ��ť���� w��100 h��20
     * ��һ����ť��ʼλ�� x��50 y��100
     * *****************
     *
     *      �ɾ�
     *
     *      ��Ч
     *
     *     ��������
     *
     *    �������˵�
     *
     * *****************
     */
    public void setUI(){

        //����JFrame������
        this.setTitle("��Ϸ����");
        this.setSize(300,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //��JLayeredPane��������Ӳ�ͬ��ȵĿؼ�
        JLayeredPane jsp = new JLayeredPane();

        //��ӱ���ͼƬ
        ImageIcon icon = new ImageIcon(filename);
        Image img = icon.getImage().getScaledInstance(this.getWidth(),this.getHeight(),0);
        JLabel lab = new JLabel(new ImageIcon(img));
        lab.setBounds(0,0,this.getWidth(),this.getHeight());
        JPanel pan = new JPanel();
        pan.setBounds(0,0,this.getWidth(),this.getHeight());
        pan.add(lab);
        jsp.add(pan,JLayeredPane.DEFAULT_LAYER);

        //��Ӱ�ť;
        JButton btn_1 = new JButton("��������");
        btn_1.setBounds(40,40,200,40);
        jsp.add(btn_1,JLayeredPane.DRAG_LAYER);
        JButton btn_2 = new JButton("��Ч");
        btn_2.setBounds(40,150,200,40);
        jsp.add(btn_2,JLayeredPane.DRAG_LAYER);
        JButton btn_3 = new JButton("��������");
        btn_3.setBounds(40,260,200,40);
        jsp.add(btn_3,JLayeredPane.DRAG_LAYER);
        JButton btn_4 = new JButton("�������˵�");
        btn_4.setBounds(40,370,200,40);
        jsp.add(btn_4,JLayeredPane.DRAG_LAYER);

        this.setLayeredPane(jsp);

        //��Ӽ�����
        btn_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFace();
            }
        });

        this.setVisible(true);
    }

    /**
     * �رս���
     */
    public void closeFace(){
        this.dispose();
        face.setVisible(true);
    }

}

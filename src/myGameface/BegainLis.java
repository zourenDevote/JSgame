package myGameface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ������ʼ�����
 * 1������������ʼ��ť����ʱ�������µĴ��ڣ��ر����еĴ���
 * 2�����������ð�ťʱ��������һ���µĴ���
 * 3�����������˳���Ϸ�Ľ������ʱ�������������ر���Ϸ
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
            case "��ʼ��Ϸ":{
                face.setVisible(false);
                gameGui.setUIS();
                break;
            }
            case "����":{
                face.inSetFace();
                break;
            }
            case "�˳���Ϸ":{
                JOptionPane JO = new JOptionPane();
                JO.showConfirmDialog(null,"ȷ���˳�");
                int value = JO.getOptionType();
                if(value!=JOptionPane.CANCEL_OPTION){
                    face.dispose();
                    if(gameGui!=null){
                        gameGui.dispose();
                    }
                    System.exit(0);//�����˳�
                }
                break;
            }
        }
    }
}

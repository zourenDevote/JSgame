package Figuer;

import java.awt.*;

/**
 * ���˽ӿ��࣬����Ա�����Զ���ʵ��һ��enemy
 */
public interface MyEnemy {

    /**
     * �����Ƿ񻹴��
     * ���Ļ�������true�������Ļ�������false
     */
    boolean isAlive();

    /**
     * �ж��Ƿ�����Χ������ײ
     * �����ײ������λ��·��
     */
    void isCrash();

    /**
     * ������ҵ��ƶ���ʽ
     */
    void moveToMan();

    /**
     * ������ʽ
     */
    int attack();

    /**
     * �ж��Ƿ��ӵ�����
     */
    void isCrashToBullet();

    /**
     * ���˵�GUI��������
     * �����˵���״
     */
    void drawEnemy(Graphics g);


}

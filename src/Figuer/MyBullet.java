package Figuer;

import java.awt.*;

/**
 * �ӵ���--����˵������
 * �����߿��������޸�������ӵ�����
 * �����ڿ��Կ���1��ֱ�����ӵ�
 *            2����ը������
 *            3���Զ�׷��������
 *            4��......
 */
public interface MyBullet {

    /**
     * �ӵ����ƶ�·��
     */
    void bulletMove();

    /**
     * GUI
     * �ӵ��ش�����״
     */
    void drawBullet(Graphics g);

    /**
     * �ӵ���enemy�����Ϊ
     * ����������
     * �����Ǳ�ը
     * ���ܾ���һ���ӵ�������һ�ξ͹���
     * Ҳ������һ��ԭ�ӵ�.....
     */
    void ifCrashEnemy();

}

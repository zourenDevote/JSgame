package Figuer;

import java.awt.*;

/**
 * 敌人接口类，程序员可以自定义实现一个enemy
 */
public interface MyEnemy {

    /**
     * 敌人是否还存活
     * 存活的话，返回true，死亡的话，返回false
     */
    boolean isAlive();

    /**
     * 判断是否与周围物体碰撞
     * 如果碰撞，更改位置路线
     */
    void isCrash();

    /**
     * 朝着玩家的移动方式
     */
    void moveToMan();

    /**
     * 攻击方式
     */
    int attack();

    /**
     * 判断是否被子弹射中
     */
    void isCrashToBullet();

    /**
     * 敌人的GUI交互类型
     * 即敌人的形状
     */
    void drawEnemy(Graphics g);


}

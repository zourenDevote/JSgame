package Figuer;

import java.awt.*;

/**
 * 子弹类--或者说武器类
 * 开发者可以自行修改里面地子弹类型
 * 类似于可以开发1、直线型子弹
 *            2、爆炸型武器
 *            3、自动追踪类武器
 *            4、......
 */
public interface MyBullet {

    /**
     * 子弹地移动路线
     */
    void bulletMove();

    /**
     * GUI
     * 子弹地窗口形状
     */
    void drawBullet(Graphics g);

    /**
     * 子弹打到enemy后的行为
     * 可能是扩撒
     * 可能是爆炸
     * 可能就是一颗子弹，打中一次就够了
     * 也可能是一颗原子弹.....
     */
    void ifCrashEnemy();

}

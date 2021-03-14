package Figuer;

import Tools.Vet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 玩家类
 * 1、玩家的位置
 * 2、玩家的生命值
 * 3、玩家的武器类型
 * 4、玩家的移动收到限制
 * 5、玩家被攻击
 */
public class GameMan {


    private BufferedImage buf;

    /**
     * 小人的位置
     * class Position
     * 子弹类
     * class Bullet
     * 敌人类
     * class Enemy
     */
    class Position{
        int x;
        int y;

        Position(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 发射的子弹
     * class Bullet
     * 发射位置x，y
     * 发射方向div;
     */
    class Bullet implements MyBullet{
        int x;
        int y;
        int xDmove;
        int yDmove;

        Bullet(int x,int y,int xDmove,int yDmove){
            this.x = x;
            this.y = y;
            //确保子弹有一个移动方向，不至于停在原地不动
            if(xDmove!=0||yDmove!=0){
                this.xDmove = xDmove;
                this.yDmove = yDmove;
            }else {
                this.xDmove = Math.random()>0.3?2:(Math.random()>0.5?-2:0);
                this.yDmove = Math.random()>0.3?2:(Math.random()>0.5?-2:xDmove==0?-1:0);
            }
        }

        @Override
        public void bulletMove() {
            x = x+3*xDmove;
            y = y+3*yDmove;
        }

        @Override
        public void drawBullet(Graphics g) {

            g.fillArc(x+5,y+5,10,10,0,  360);
        }

        @Override
        public void ifCrashEnemy() {

        }
    }

    class Enemy implements MyEnemy{

        /**
         * enemy‘s position and enemy's life
         */
        int x;
        int y;
        int life;
        /**
         * 构造器
         * @param x
         * @param y
         */
        Enemy(int x,int y){
            this.x = x;
            this.y = y;
            life = 1;
        }
        /**
         * 敌人朝着玩家移动
         */
        public void moveToMan(){
            isCrash();
            Vet ve = new Vet(x,y,position.x,position.y);
            x = (int)(x+(ve.getX()*1.1));
            y = (int)(y+(ve.getY()*1.1));
        }

        @Override
        public int attack() {
            return 0;
        }

        /**
         * 位置检测碰撞波动随机波动
         */
        public void wavePosition(int weka){
            x = Math.random()>0.5?x-weka:x+weka;
            y = Math.random()>0.5?y-weka:y+weka;
        }

        public void drawEnemy(Graphics g){
            g.setColor(Color.RED);
            g.fillOval(x,y,20,20);
        }

        /**
         * 判断是否与子弹发生碰撞
         * @return
         */
        public void isCrashToBullet(){
            for(int i=0;i<bullet.size();i++){
                Bullet bul = bullet.get(i);
                if(x<bul.x+15&&x> bul.x-5&&y<bul.y+15&&y> bul.y-5){
                    life--;
                }
                if(!isAlive()){
                    bullet.remove(i);
                }else {
                    continue;
                }

            }
        }
        public boolean isAlive(){
            return life>0;
        }

        @Override
        public void isCrash() {
            for(int i=0;i<enemy.size();i++){
                Enemy temp = enemy.get(i);
                if(temp == this){
                    continue;
                }
                if(temp.x>x-10&&temp.x<x+10&&temp.y>y-10&&temp.y<y+10){
                    temp.wavePosition(10);
                }
            }
        }
    }


    /**
     * 默认字段
     * DefautLift:默认生命值是20
     * Defautposition:默认位置为（100，100）
     * Up = 1; 发射方向为向上发射
     * Down = 2; 发射方向为向下发射
     * Left = 3; 发射方向为向左发射
     * Right = 4; 发射方向为向右发射
     */
    final private int DefautLife = 20;
    final private Position Defautpostion = new Position(100,100);
    public final static int Up = 1;
    public final static int Down = 2;
    public final static int Left = 3;
    public final static int Right = 4;


    /**
     * 属性
     * 当前小人的位置position
     * 当前小人的生命值life
     * 当前小人已放出的子弹数组
     * 当前存储敌人的数量的数组
     * 当前小人的正面朝向
     * 当前小人的移动速度是低速还是中速
     * 当前小人沿着X轴的移动速度XMove
     * 当前小人沿着Y轴的移动速度YMove
     * 速度移动该变量Dmove
     */
    private Position position;
    private int life;
    private ArrayList<Bullet> bullet;
    private ArrayList<Enemy> enemy;
    private int Direction;
    private int Weight;
    private int Height;
    private boolean isFast = false;
    private int XMove;
    private int YMove;
    private int Dmove;
    /**
     * 构造器
     * @param x 玩家x轴上的位置
     * @param y 玩家y轴上的位置
     * @param life 玩家的初始声明值
     */
    public GameMan(int x,int y,int life,int weight,int height){
        position = new Position(x,y);
        this.life = life;
        bullet = new ArrayList<>();
        enemy = new ArrayList<>();
        this.Weight = weight;
        this.Height = height;
        Direction = Up;
        buf = new BufferedImage(Weight,Height,BufferedImage.TYPE_4BYTE_ABGR);
        new EnemyCreat().start();
        XMove = YMove = 0;
        Dmove = 1;
    }

    /**
     * 无参构造器
     */
    public GameMan(){
        this.life = DefautLife;
        this.position = Defautpostion;
    }

    /**
     * 获取当前小人正面指向的方位
     * @return
     */
    public int getDirection(){
        return Direction;
    }

    /**
     * 改变当前小人的方位
     * @param dir
     */
    public void changeDirection(int dir){
        this.Direction = dir;
    }

    /**
     * 获取当前小人的移动速度
     */
    public boolean isFast(){
        return isFast;
    }

    /**
     * 改变当前小人的移动速度
     */
    public void changeSpeed(int i){
        Dmove = Dmove+i;
    }

    /**
     * 位置改变
     * 首先判断position的位置是否超出边界，超出边界的话，就不能改变
     */
    public void positinoMove(){
        Position pos = position;
        if(pos.x+XMove>20&&pos.x+XMove<Weight-40){
            position.x = pos.x+XMove;
        }
        if(pos.y+YMove>20&&pos.y+YMove<Height-40){
            position.y = pos.y+YMove;
        }
        else {
            return;
        }

    }

    /**
     * 改变X轴的移动速度
     * @param bool
     */
    public void xMove(boolean bool,int weight){
        if(weight==0){
            changeXMove(0);
        }else {
            if(bool){
                changeXMove(Dmove);
            }else {
                changeXMove(-Dmove);
            }
        }
    }

    /**
     * 改变X轴的移动速度
     * @param movedir
     */
    private void changeXMove(int movedir){
        XMove = movedir;
    }

    /**
     * 改变Y轴的移动速度
     * @param bool
     */
    public void yMove(boolean bool,int weight){
        if(weight==0){
            changeYMove(0);
        }else {
            if(bool){
                changeYMove(Dmove);
            }else {
                changeYMove(-Dmove);
            }
        }

    }

    /**
     * 改变Y轴的移动速度
     * @param movedir
     */
    private void changeYMove(int movedir){
        YMove = movedir;
    }


    /**
     * 画出小人的位置
     * 以及画出小人的子弹轨迹
     */
    public void drawMan(Graphics g){


        Graphics bufg = buf.getGraphics();
        Position pos = position;
        bufg.clearRect(0,0,Weight,Height);
        bufg.setColor(Color.BLUE);
        bufg.fillOval(pos.x, pos.y, 20,20);
        bufg.setColor(Color.WHITE);
        for(int i=0;i<bullet.size();i++){
            bullet.get(i).drawBullet(bufg);
            bullet.get(i).bulletMove();
        }
        delBullet();
        for(int i=0;i<enemy.size();i++){
            enemy.get(i).drawEnemy(bufg);
            enemy.get(i).moveToMan();
            enemy.get(i).isCrashToBullet();
        }
        delEnemy();
        g.drawImage(buf,0,0,null);

    }

    /**
     * 删除敌人
     */
    private void delEnemy() {
        for(int i=0;i<enemy.size();i++){
            if(enemy.get(i).isAlive()){
                continue;
            }else {
                enemy.remove(i);
            }
        }
    }


    /**
     * 删除子弹
     */
    private void delBullet(){
        for(int i=0;i<bullet.size();i++){
            Bullet pos = bullet.get(i);
            if(pos.x>Weight||pos.x<0||pos.y<0||pos.y>Height) bullet.remove(i);
        }
    }




    /**
     * 发出子弹
     * 从小人的位置向某个方向发射子弹
     */
    public void creatBullet(int dir){
        Bullet bul = new Bullet(position.x,position.y,XMove,YMove);
        bullet.add(bul);
    }

    /**
     * 判断Enemy是否与玩家相撞
     */
    public void isCrash(){
        for(int i=0;i<enemy.size();i++){
            Enemy ene = enemy.get(i);
            Position pos = position;
            if(pos.x>ene.x-10&&pos.x< ene.x+10&&pos.y>ene.y-10&&pos.y<ene.y+10){
                life--;
            }else {
                continue;
            }
        }
    }

    /**
     * 判断玩家是否还存活
     * @return
     */
    public boolean isAlive(){
        return life>0;
    }


    /**
     * 敌军生成器
     * 每隔十秒生成五个敌军
     * 敌军自动朝我走
     */
    class EnemyCreat extends Thread{

        @Override
        public void run() {
            while (true) {
                //随机生成五个敌人
                for (int i = 0; i < 5; i++) {
                    int ranx = (int) (Math.random() * Weight);
                    int rany = (int) (Math.random() * Height);
                    Enemy newEnemy = new Enemy(ranx, rany);
                    enemy.add(newEnemy);
                }
                //停顿十秒
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

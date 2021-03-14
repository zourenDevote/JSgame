package Figuer;

import Tools.Vet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * �����
 * 1����ҵ�λ��
 * 2����ҵ�����ֵ
 * 3����ҵ���������
 * 4����ҵ��ƶ��յ�����
 * 5����ұ�����
 */
public class GameMan {


    private BufferedImage buf;

    /**
     * С�˵�λ��
     * class Position
     * �ӵ���
     * class Bullet
     * ������
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
     * ������ӵ�
     * class Bullet
     * ����λ��x��y
     * ���䷽��div;
     */
    class Bullet implements MyBullet{
        int x;
        int y;
        int xDmove;
        int yDmove;

        Bullet(int x,int y,int xDmove,int yDmove){
            this.x = x;
            this.y = y;
            //ȷ���ӵ���һ���ƶ����򣬲�����ͣ��ԭ�ز���
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
         * enemy��s position and enemy's life
         */
        int x;
        int y;
        int life;
        /**
         * ������
         * @param x
         * @param y
         */
        Enemy(int x,int y){
            this.x = x;
            this.y = y;
            life = 1;
        }
        /**
         * ���˳�������ƶ�
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
         * λ�ü����ײ�����������
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
         * �ж��Ƿ����ӵ�������ײ
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
     * Ĭ���ֶ�
     * DefautLift:Ĭ������ֵ��20
     * Defautposition:Ĭ��λ��Ϊ��100��100��
     * Up = 1; ���䷽��Ϊ���Ϸ���
     * Down = 2; ���䷽��Ϊ���·���
     * Left = 3; ���䷽��Ϊ������
     * Right = 4; ���䷽��Ϊ���ҷ���
     */
    final private int DefautLife = 20;
    final private Position Defautpostion = new Position(100,100);
    public final static int Up = 1;
    public final static int Down = 2;
    public final static int Left = 3;
    public final static int Right = 4;


    /**
     * ����
     * ��ǰС�˵�λ��position
     * ��ǰС�˵�����ֵlife
     * ��ǰС���ѷų����ӵ�����
     * ��ǰ�洢���˵�����������
     * ��ǰС�˵����泯��
     * ��ǰС�˵��ƶ��ٶ��ǵ��ٻ�������
     * ��ǰС������X����ƶ��ٶ�XMove
     * ��ǰС������Y����ƶ��ٶ�YMove
     * �ٶ��ƶ��ñ���Dmove
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
     * ������
     * @param x ���x���ϵ�λ��
     * @param y ���y���ϵ�λ��
     * @param life ��ҵĳ�ʼ����ֵ
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
     * �޲ι�����
     */
    public GameMan(){
        this.life = DefautLife;
        this.position = Defautpostion;
    }

    /**
     * ��ȡ��ǰС������ָ��ķ�λ
     * @return
     */
    public int getDirection(){
        return Direction;
    }

    /**
     * �ı䵱ǰС�˵ķ�λ
     * @param dir
     */
    public void changeDirection(int dir){
        this.Direction = dir;
    }

    /**
     * ��ȡ��ǰС�˵��ƶ��ٶ�
     */
    public boolean isFast(){
        return isFast;
    }

    /**
     * �ı䵱ǰС�˵��ƶ��ٶ�
     */
    public void changeSpeed(int i){
        Dmove = Dmove+i;
    }

    /**
     * λ�øı�
     * �����ж�position��λ���Ƿ񳬳��߽磬�����߽�Ļ����Ͳ��ܸı�
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
     * �ı�X����ƶ��ٶ�
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
     * �ı�X����ƶ��ٶ�
     * @param movedir
     */
    private void changeXMove(int movedir){
        XMove = movedir;
    }

    /**
     * �ı�Y����ƶ��ٶ�
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
     * �ı�Y����ƶ��ٶ�
     * @param movedir
     */
    private void changeYMove(int movedir){
        YMove = movedir;
    }


    /**
     * ����С�˵�λ��
     * �Լ�����С�˵��ӵ��켣
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
     * ɾ������
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
     * ɾ���ӵ�
     */
    private void delBullet(){
        for(int i=0;i<bullet.size();i++){
            Bullet pos = bullet.get(i);
            if(pos.x>Weight||pos.x<0||pos.y<0||pos.y>Height) bullet.remove(i);
        }
    }




    /**
     * �����ӵ�
     * ��С�˵�λ����ĳ���������ӵ�
     */
    public void creatBullet(int dir){
        Bullet bul = new Bullet(position.x,position.y,XMove,YMove);
        bullet.add(bul);
    }

    /**
     * �ж�Enemy�Ƿ��������ײ
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
     * �ж�����Ƿ񻹴��
     * @return
     */
    public boolean isAlive(){
        return life>0;
    }


    /**
     * �о�������
     * ÿ��ʮ����������о�
     * �о��Զ�������
     */
    class EnemyCreat extends Thread{

        @Override
        public void run() {
            while (true) {
                //��������������
                for (int i = 0; i < 5; i++) {
                    int ranx = (int) (Math.random() * Weight);
                    int rany = (int) (Math.random() * Height);
                    Enemy newEnemy = new Enemy(ranx, rany);
                    enemy.add(newEnemy);
                }
                //ͣ��ʮ��
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

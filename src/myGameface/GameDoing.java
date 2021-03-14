package myGameface;

import Figuer.GameMan;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GameDoing extends Thread{

    private Graphics g;
    private GameMan man;
    public GameDoing(GameMan man,Graphics g){
        this.g = g;
        this.man = man;
    }

    /**
     * 重写run方法
     */
    @Override
    public  void run(){
        while (true){
            man.drawMan(g);
            man.isCrash();
            man.positinoMove();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            if(!man.isAlive()){
//                System.out.println("玩家死亡");
//                break;
//            }

        }
    }



}

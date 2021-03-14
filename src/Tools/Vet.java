package Tools;


/**
 * 构造一个向量的单位向量
 */
public class Vet {

    /**
     * 向量
     */
    class ve{
        double x; //向量的X桌标
        double y; //向量的Y坐标
        double size;//向量的模长
    }

    ve unitVector;

    public Vet(int startx,int starty,int endx,int endy){
        unitVector = new ve();
        unitVector.x = endx - startx;
        unitVector.y = endy - starty;
        unitVector.size = Math.pow(Math.pow(unitVector.x,2)+Math.pow(unitVector.y,2),0.5);
        unitVector.x = unitVector.x/ unitVector.size;
        unitVector.y = unitVector.y/ unitVector.size;
        unitVector.size = 1;
    }

    /**
     * 获取x轴坐标
     */
    public double getX(){
        return unitVector.x;
    }

    /**
     * 获取y轴的坐标
     */
    public double getY(){
        return unitVector.y;
    }

    /**
     * 获取向量的模长
     */
    public double getModel(){
        return unitVector.size;
    }





}

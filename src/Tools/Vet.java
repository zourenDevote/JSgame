package Tools;


/**
 * ����һ�������ĵ�λ����
 */
public class Vet {

    /**
     * ����
     */
    class ve{
        double x; //������X����
        double y; //������Y����
        double size;//������ģ��
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
     * ��ȡx������
     */
    public double getX(){
        return unitVector.x;
    }

    /**
     * ��ȡy�������
     */
    public double getY(){
        return unitVector.y;
    }

    /**
     * ��ȡ������ģ��
     */
    public double getModel(){
        return unitVector.size;
    }





}

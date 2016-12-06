package vviv;

/**
 * Created by valen on 09/11/2016.
 */
public class Coordonnees{

    private float x;

    private float y;

    public Coordonnees(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordonnees{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double distance(Coordonnees c){
        float xtemp = this.x - c.x;
        float ytemp = this.y - c.y;
        return Math.sqrt(xtemp*xtemp + ytemp*ytemp);
    }
}

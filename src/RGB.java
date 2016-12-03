/**
 * Created by Matthew on 03/12/2016.
 */
public class RGB {

    private int R, G, B;

    public RGB(int R, int G, int B){
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    @Override
    public String toString(){
        return "(" + getR() + "," + getG() + "," + getB() + ")";
    }
}

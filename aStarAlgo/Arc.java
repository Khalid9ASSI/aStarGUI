package aStarAlgo;
public class Arc {
    private Sommet A;
    private Sommet B;
    private double W;// poid
    public Arc(Sommet A, Sommet B, double W) {
        this.A = A;
        this.B = B;
        this.W = W;
    }
    
    public Sommet getA() {
        return this.A;
    }

    public void setA(Sommet A) {
        this.A = A;
    }

    public Sommet getB() {
        return this.B;
    }

    public void setB(Sommet B) {
        this.B = B;
    }

    public double getW() {
        return this.W;
    }

    public void setW(double W) {
        this.W = W;
    }

    

}
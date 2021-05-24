package aStarAlgo;
public class Pair implements Comparable<Pair>{
    private Sommet A;
    private double W;

    public Pair (Sommet A, double w) {
        this.A = A;
        this.W = w;
    }
    

    @Override
    public int compareTo(Pair o) {
        return  Double.compare(this.W, o.getW());
    }

   public Sommet getA() {
       return A;
   }

   public double getW() {
       return W;
   }

   public void setA(Sommet a) {
       A = a;
   }

   public void setW(int w) {
       W = w;
   }
}
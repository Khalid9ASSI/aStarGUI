package aStarAlgo;
public class Sommet {
    private int id;
    private String nom;
    private double g;
    private double h;
    private double h_star;
    private double f;
    
    public Sommet(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    public Sommet() {
	
	}
    
    public void calculateF(int g, int h) {
        this.f = g + h;

    }

    public void setH_star(Double hstar) {
        this.h_star = hstar;
    }

    public double getH_star() {
        return this.h_star;
    }

    public boolean isAdmissible() {
        return this.h <= this.h_star;//after we have h and h star we just wanna check all sommet 
        //to find out if its admissible or n
        //ps h star should be calculated at the very beginning while h is given by user
        //we re gonna calc h star with dijkstra or smthng so that we always have the shortest path to destination
        //then check g as we move on + h and update it 
    }
    public double getG() {
        return this.g;
    }

    public double getH() {
        return this.h;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getF() {
        return f;
    }

    @Override
    public String toString() {//for debugging
        return "{" +
            " id='" + id + "'" +
            ", nom='" + nom + "'" +
            ", g='" + g + "'" +
            ", h='" + h + "'" +
            ", h_star='" + h_star + "'" +
            ", f='" + f + "'" +
            "}";
    }
}

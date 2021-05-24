package aStarAlgo;
import java.util.ArrayList;
import javax.swing.JTextField;
public class mainClass {
	
	
	public static ArrayList<String> runAlgorithm(JTextField[][] tfs, String depart, String arrive) {
		
		int nbrSmts= tfs[0].length-1; // nbr des sommets
		
		ArrayList<Double> heur=new ArrayList<>();
		ArrayList<String> nmSmts=new ArrayList<>();
		ArrayList<Arc> arcs= new ArrayList<>();
		ArrayList<ArrayList<String>> tmpArcs = new ArrayList<>();
	    ArrayList<String> res = new ArrayList<>();
		//get name on line and name on column then u map v map then add arc

		for(int i=0; i<nbrSmts; i++) {
			for(int j=0; j<= nbrSmts; j++) {//last sommet is nbrSmts-1
				if(j==0 && i!=0) {
					nmSmts.add(tfs[i][j].getText()) ; // Les noms 
					
				}
				
				else if(j == nbrSmts && i != 0) {
					
					heur.add(Double.parseDouble(tfs[i][j].getText()));
				}

				
				else if(i!=j && i!=0 && tfs[i][j].getText()!=null && !tfs[i][j].getText().isEmpty()) {
					//i=1 j =3
					String u = tfs[i][0].getText();//name sommet 1
					String v = tfs[0][j].getText();
					String w = tfs[i][j].getText();
					ArrayList<String> tmp = new ArrayList<>();
					tmp.add(u); tmp.add(v); tmp.add(w);
					tmpArcs.add(tmp);
					
					//[[u,v,w] ]
					
				}
				
			}
		
		
		
		}
		
	    Graph g = new Graph(nbrSmts-1, nmSmts, heur);//creation graphe avec nos parametres GUI
	    for(int i = 0; i < tmpArcs.size(); i++) {
	    	Sommet U = g.mappingSommet(tmpArcs.get(i).get(0));//mapping sommet/nom
			Sommet V = g.mappingSommet(tmpArcs.get(i).get(1));
			double W = Double.parseDouble(tmpArcs.get(i).get(2));
			Arc a = new Arc(U, V, W);
			g.addArc(a);
	    }

		/////////////////////////////////////////////////////////////////////////
	    
	    Sommet next = g.mappingSommet(depart);//sommet a traiter
	    Sommet last = g.mappingSommet(arrive);//sommet final
	       
	       ArrayList<String> Path = new ArrayList<>();
	       Path.add(next.getNom());
	    	//start sommet neighbors
	       while (next.getH() != 0.0) {
	          //h!=0 means we re not on last
	    	   next = g.findNextSommet(next);//find next Sommet using H
	           if (next.getNom() != "min") Path.add(next.getNom());
	       }

	       String tmpRes = "Votre chemin avec l'heuristique que vous avez donnez est :" + Path;
	       res.add(tmpRes);
	        //whats left shows if its admissible or not
	        g.DijkstraDistance(last.getId());//get h star for all sommets
	        Boolean admissible = true;
	        Sommet X = new Sommet();
	        for(Sommet s:g.getSommets()) {
	            if (!s.isAdmissible()) {
	                admissible = false;
	                X = s;
	                break;
	            }
	        }
	        if (admissible) {
	            tmpRes = "Cette heuristique est admissible! ";
	        }
	        else {
	            tmpRes = "Cette heuristique n'est pas admissible! "  + X.getNom() + " a pour H : " + X.getH() + " superieur a H* qui est egale a : " + X.getH_star();
	        }
	        res.add(tmpRes);
		return res;//output to show
	}
	
	

} 

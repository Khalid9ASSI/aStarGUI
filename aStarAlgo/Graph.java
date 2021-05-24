package aStarAlgo;
import java.util.ArrayList;
import java.util.PriorityQueue;
public class Graph {
    private ArrayList<Sommet> Sommets;
    private ArrayList<Arc> arcs;
    private ArrayList<ArrayList<Pair>> AdjacencyList ;
    private ArrayList<Boolean> Processed;

    public Graph(int n,ArrayList<String> nmSmts,ArrayList<Double> heur) {//n taille
    	
        AdjacencyList = new ArrayList<ArrayList<Pair>>();
       // Scanner sc = new Scanner(System.in);
        Processed = new ArrayList<Boolean>();
        Sommets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            
          ////  System.out.println("Nom du Sommet?");
            Sommet s = new Sommet(i, nmSmts.get(i));
           // System.out.println("Valeur de l'heuristique du sommet?");
            
            s.setH(heur.get(i));// heuristique donnee
            Sommets.add(s);//sommets de 0 a n-1
           // System.out.println(s.toString());
            AdjacencyList.add(new ArrayList<Pair>());
            Processed.add(false);
        }
        arcs = new ArrayList<>();
       /* for(Sommet k:Sommets) {
            System.out.println(k.toString());
        }*/
        
    }

    public void addArc(Arc arc) {
        arcs.add(arc);
        
        AdjacencyList.get(arc.getA().getId()).add(new Pair(arc.getB(), arc.getW()));//directed graph
        //make undirected graph
        AdjacencyList.get(arc.getB().getId()).add(new Pair(arc.getA(), arc.getW()));//undirected
    }

    public ArrayList<ArrayList<Pair>> getAdjacencyList() {
        /*int counter = 0;
       for (ArrayList<Pair> s:AdjacencyList) {
          for(Pair p:s) {
              System.out.println(counter + " /" + p.getA().getNom()+ " /" + p.getW());//adjacency list done
              //index have where the adjacents
          }
          counter++;
       }*/
        return AdjacencyList;
    }

    public Sommet findNextSommet(Sommet A) {
        //donne le sommet actuelle pour trouver le bonne sommet prochain
        Sommet min = new Sommet(-1, "min");
        min.setF(999999999.0);//infinity
        ArrayList<ArrayList<Pair>> adjList = this.getAdjacencyList();
       // System.out.println(adjList.get(0).get(0).getA().toString());
        for (Pair p:adjList.get(A.getId())) {
            if (!Processed.get(p.getA().getId())) { //if the adjacent node is something we didnt process before
                //to dodge loops on already processed nodes since always not a solution
                p.getA().setG(A.getG() + p.getW());// previous node + weight
                double tmp = A.getG() + p.getA().getH() + p.getW();
                p.getA().setF(tmp);
                //System.out.println(tmp);
                if(tmp < min.getF()) {
                    min = p.getA();
                }
            }
           //System.out.println(p.getA().toString());
            //System.out.println(min.toString());
            //add processed array so that we manage undirected arrays
        }
        Processed.set(A.getId(), true);//we finished this sommet (A) so we mark it as processed
        //System.out.println(min.getF());
        return min;
    }
    public Sommet mappingSommet(String tmp) {
        for (Sommet s:Sommets) {
            if (s.getNom().equals(tmp)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Sommet> getSommets() {
        return Sommets;
    }

    public void DijkstraDistance(int x) {//x = end sommet, dijkstra finds shortest path from a sommet x 
        //to any other sommet
        ArrayList<Double> distance = new ArrayList<>();//distance to sommet
        ArrayList<Boolean> tmpProcessed = new ArrayList<>();
        for (int i = 0; i < Sommets.size(); i++) {
            distance.add(999999.0);//initialize
            tmpProcessed.add(false);

        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        distance.set(x, 0.0);//from x to x is 0
        Pair sommDist = new Pair(Sommets.get(x), 0.0);//pair for pq 
        pq.add(sommDist);//pair of sommet and current distance to given node
        while (!pq.isEmpty()) {
            Pair tmp = pq.poll();//retrieve first element nd removes it
            int currentIndex = tmp.getA().getId();
            if(!tmpProcessed.get(currentIndex)) {
                tmpProcessed.set(currentIndex, true);
                for (int i = 0; i < AdjacencyList.get(currentIndex).size(); i++) {
                    Pair adjPair = AdjacencyList.get(currentIndex).get(i);
                    int adjId = adjPair.getA().getId();
                    if (distance.get(currentIndex) + adjPair.getW() < distance.get(adjId)) {
                        distance.set(adjId, distance.get(currentIndex) + adjPair.getW());
                        Pair sommDist2 = new Pair(adjPair.getA(), distance.get(adjId));
                        pq.add(sommDist2);
                    }
                }
            }

        }
        for (int i = 0; i < distance.size(); i++) {//set h* to each sommet class
            Sommets.get(i).setH_star(distance.get(i));
        }

    }
}


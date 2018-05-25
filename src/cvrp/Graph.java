package cvrp;

import java.util.*;
import tsp.Arc;

public class Graph{
  protected int V;
  protected int E;
  protected ArrayList<Arc> adj[];
  public double dist[];
  public long prev[];
  public LinkedList<Arc> edgesList = new LinkedList<Arc>();

  public Graph(int V){
    this.V = V;
    this.E = 0;
    this.dist = new double[V];
    this.prev = new long[V];
    adj = (ArrayList<Arc>[]) new ArrayList[V];
    for (int v = 0; v < V; v++) {
      adj[v] = new ArrayList<Arc>();
    }
  }

  public void addEdge(int v, int w, double weight){
    this.E++;
    Arc item = new Arc(v, w, weight);
    adj[v].add(item);
    edgesList.add(item);
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    String NEWLINE = System.getProperty("line.separator");
    s.append(V + " vertices, " + E + " edges " + NEWLINE);
    for (int v = 0; v < V; v++) {
      s.append(String.format("%d: ", v));
      for (Arc e : adj[v]) {
        s.append(e + "  ");
      }
      s.append(NEWLINE);
    }
    return s.toString();
  }

  public Iterable<Arc> adj(int v) {
    if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
    return this.adj[v];
  }

  public int BellmanFord(){
    Arrays.fill(dist, Double.MAX_VALUE);
    Arrays.fill(prev, -1);
    dist[0] = 0;
    for(int i = 0; i < this.V - 1; i++){
      for(Arc e : adj(i)){
        if(dist[e.getTarget()] > dist[e.getSource()] + e.getValue()){
          dist[e.getTarget()] = dist[e.getSource()] + e.getValue();
          prev[e.getTarget()] = e.getSource();
        }
      }
    }

    for(int i = 0; i < this.V; i++){
      for(Arc e : adj(i)){
        if(dist[e.getTarget()] > dist[e.getSource()] + e.getValue())
          return 1;
      }
    }
    return 0;
  }

}

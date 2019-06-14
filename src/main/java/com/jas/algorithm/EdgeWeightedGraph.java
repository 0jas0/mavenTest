package com.jas.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class EdgeWeightedGraph {
    class Edge{
        private int v;
        private int w;
        private double weight;

        public Edge(int v, int w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int getOne() {
            return v;
        }

        public int getOther(int v){
            if (this.v == v){
                return w;
            }
            return v;
        }
    }

    private int vertex;
    private List<Edge>[] adj;
    private int edgeNum;

    public EdgeWeightedGraph(int vertex) {
        this.vertex = vertex;
        adj = new LinkedList[vertex];
        for (int i = 0; i < vertex; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(Edge edge){
        int one = edge.getOne();
        int other = edge.getOther(one);
        adj[one].add(edge);
        adj[other].add(edge);
        edgeNum++;
    }

    public List<Edge> adj(int v){
        return adj[v];
    }

    public List<Edge> getAllEdge(){
        List<Edge> res = new LinkedList<>();
        for (int v = 0 ; v < vertex; v++){
            for (Edge edge : adj(v)){
                if (edge.getOther(v) > v){
                    res.add(edge);
                }
            }
        }
        return res;
    }
}

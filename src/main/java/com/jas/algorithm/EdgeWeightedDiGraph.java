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
public class EdgeWeightedDiGraph {
    class DirectorEdge{
        private int from;
        private int to;
        private int weight;

        public DirectorEdge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

    }

    private int vertex;
    private List<DirectorEdge>[] adj;
    private int edgeNum;

    public EdgeWeightedDiGraph(int vertex) {
        this.vertex = vertex;
        adj = new LinkedList[vertex];
        for (int i = 0; i < vertex; i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(DirectorEdge edge){
        int from = edge.getFrom();
        adj[from].add(edge);
        edgeNum++;
    }

    public List<DirectorEdge> adj(int v){
        return adj[v];
    }

    public List<DirectorEdge> getAllEdge(){
        List<DirectorEdge> res = new LinkedList<>();
        for (int v = 0 ; v < vertex; v++){
            for (DirectorEdge edge : adj(v)){
                res.add(edge);
            }
        }
        return res;
    }
}

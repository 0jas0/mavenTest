package com.jas.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class EdgeWeightedGraph {
    class Edge implements Comparable<Edge>{
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

        @Override
        public int compareTo(Edge edge) {
            return this.weight > edge.weight ? 1 : this.weight ==  edge.weight ? 0 : -1;
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

    public int getVertex() {
        return vertex;
    }



    /**
     * 由一张图生成最小生成一棵树
     * 主要思想：每次都会为生长的一棵树添加一条边，一开始这棵树只有一个顶点，然后加到V-1个边，
     *  每次都是将树中的顶点与不在树中的顶点的权重最小的边加入到树中
     */
    class LazyPrimMST{
        private boolean[] marked;
        private MaxOrMinPQ<Edge> minPQ;
        private Queue<Edge> mst;

        public LazyPrimMST(EdgeWeightedGraph graph) {
            marked = new boolean[graph.getVertex()];
            minPQ = new MaxOrMinPQ<>(graph.getVertex(), false);
            mst = new ConcurrentLinkedQueue<>();
            visit(graph, 0);
            while (!minPQ.isEmpty()){
                Edge edge = minPQ.del();
                int v = edge.getOne();
                int w = edge.getOther(v);
                if (marked[v] && marked[w]){
                    continue;
                }
                mst.add(edge);
                if (!marked[v]){
                    visit(graph, v);
                }
                if (!marked[w]){
                    visit(graph, w);
                }
            }
        }

        private void visit(EdgeWeightedGraph graph, int v) {
            marked[v] = true;
            for (Edge edge : adj[v]){
                if (!marked[edge.getOther(v)]){
                    minPQ.insert(edge);
                }
            }
        }

        /**
         * 获取最小生成树的边
         * @return
         */
        public Queue<Edge> getMst() {
            return mst;
        }
    }

    /**
     * 由一张图生成最小生成一棵树
     *
     *  主要思想：是按照边的权的权值降序排列，将他们加入到最小队列中并且加入的边不能与已经加入的边构成环
     *  知道V-1个边加入为止。
     */
    class KruskalMST{
        private Queue<Edge> mst;

        public KruskalMST(EdgeWeightedGraph weightedGraph) {
            mst = new ConcurrentLinkedQueue<>();
            List<Edge> allEdge = weightedGraph.getAllEdge();
            Collections.sort(allEdge);
            UF uf = new UF(weightedGraph.getVertex());
            while (!allEdge.isEmpty() && mst.size() < weightedGraph.getVertex() -1){
                Edge edge = allEdge.remove(0);
                int v = edge.getOne();
                int w = edge.getOther(v);
                if (uf.connected(v, w)){
                    continue;
                }
                mst.add(edge);
                uf.union(v, w);
            }
        }

        public Queue<Edge> getMst() {
            return mst;
        }
    }
}

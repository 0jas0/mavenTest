package com.jas.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * 使用领接表实现图
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class Graph {
    private int vertex;// 顶点
    private int edge; // 边
    private List<Integer>[] adj; // 邻接表

    public Graph(int vertex) {
        this.vertex = vertex;
        adj = new LinkedList[vertex];
        for (int i = 0; i < vertex; i++){
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 添加一条边
     * @param v 顶点v
     * @param w 顶点w
     */
    public void addEdge(int v, int w){
        // 在v的邻接表中添加w
        adj[v].add(w);
        // 在w的领接表中添加v
        adj[w].add(v);
        edge++;
    }

    /**
     * 顶点v的邻接表
     * @param v 顶点v
     * @return
     */
    public List<Integer> adj(int v){
        return adj[v];
    }


    public int getVertex(){
        return vertex;
    }

    public int getEdge(){
        return edge;
    }

    /**
     * 深度搜索图
     */
    class DepthFirstSearch{
        private boolean[] marked;
        private int count;

        /**
         *
         * @param graph 图graph
         * @param v 顶点v
         */
        public DepthFirstSearch(Graph graph, int v) {
            // 顶点 v 与哪些顶点相连
            marked = new boolean[graph.vertex];
            dfs(graph, v);

        }
        public void dfs(Graph graph, int v){
            marked[v] = true;
            count++;
            for (int w : adj(v)){
                // 如果没有遍历到
                if (!marked[v]){
                    dfs(graph, w);
                }
            }
        }

        public boolean isMark(int w){
            return marked[w];
        }

        public int getCount(){
            return count;
        }
    }

    /**
     * 节点 s 所能到达的节点
     */
    class DepthFirstPaths{
        private boolean[] marked;
        private int[] edgeTo;// 从起点到一个已知顶点路径上的顶点
        private final int s;

        public DepthFirstPaths(Graph graph, int s) {
            marked = new boolean[graph.getVertex()];
            edgeTo = new int[graph.getVertex()];
            this.s = s;
            dfs(graph, s);
        }

        public void dfs(Graph graph, int s){
            marked[s] = true;
            for (int w : graph.adj(s)){
                edgeTo[w] = s;
            }
        }

        public boolean hasPathTo(int v){
            return marked[v];
        }

        /**
         * 到顶点 v 所有经多的路径
         * @param v
         * @return
         */
        public List<Integer> pathTo(int v){
            if (!hasPathTo(v)){
                return null;
            }
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = edgeTo[v]; i != s; i = edgeTo[v]){
                list.addFirst(i);
            }
            list.add(s);
            return list;
        }
    }
}

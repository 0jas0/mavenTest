package com.jas.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 使用领接表实现有向图
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class DiGraph {
    private int vertex; // 顶点
    private int edge; // 边
    private List<Integer>[] adj; // 领接表

    public DiGraph(int vertex) {
        this.vertex = vertex;
        // 初始化领接表
        adj = new LinkedList[vertex];
        for (int i = 0; i < vertex; i++){
            adj[i] = new LinkedList<Integer>();
        }
    }


    /**
     * 连接v顶点到w顶点v->w
     * @param v
     * @param w
     */
    public void addEdge(int v, int w){
        adj[v].add(w);
        edge++;
    }


    /**
     * 反转一个有向图
     * @return
     */
    public DiGraph reverse(){
        DiGraph diGraph = new DiGraph(vertex);
        for (int v = 0; v < vertex; v++){
            if (adj[v].isEmpty()){
                continue;
            }
            for (int w : adj[v]){
                diGraph.addEdge(w, v);
            }
        }
        return diGraph;
    }

    public int getVertex() {
        return vertex;
    }

    public int getEdge() {
        return edge;
    }

    /**
     * 有向图的深度遍历
     */
    class DirectedDFS{
        private boolean[] marked;
        public DirectedDFS(DiGraph diGraph, int s){
            marked = new boolean[diGraph.getVertex()];
            dfs(diGraph, s);
        }
        public void dfs(DiGraph diGraph, int s){
            marked[s] = true;
            for (int w : adj[s]){
                if (!marked[w]){
                    dfs(diGraph, w);
                }
            }
        }

        /**
         * 判断有向图是否可达顶点v
         * @param v
         * @return
         */
        public boolean isMarked(int v){
            return marked[v];
        }
    }

    class DirectedCycle{
        private boolean[] marked;
        private int[] edgeTo;
        private boolean[] onStack;
        private Stack<Integer> cycle;

        public DirectedCycle(DiGraph diGraph) {
            marked = new boolean[diGraph.getVertex()];
            onStack = new boolean[diGraph.getVertex()];
            edgeTo = new int[diGraph.getVertex()];
            for (int v = 0; v < diGraph.getVertex(); v++){
                if (!marked[v]){
                    dfs(diGraph, v);
                }
            }
        }

        private void dfs(DiGraph diGraph, int v) {
            marked[v] = true;
            onStack[v] = true;
            for (int w : adj[v]){

                if (hasCycle()){
                    return;
                }
                if (!marked[w]){
                    edgeTo[w] = v;
                    dfs(diGraph, w);
                }
                if (onStack[w]){
                    cycle = new Stack<>();
                    for (int i = edgeTo[w]; i != v; i = edgeTo[i]){
                        cycle.push(i);
                    }
                    cycle.push(w);
                    cycle.push(v);
                }
            }
            onStack[v] = false;
        }

        public boolean hasCycle(){
            return cycle != null;
        }

        public Stack<Integer> getCycle(){
            return cycle;
        }
    }

    /**
     * 深度优先搜索顶点的排序
     */
    class DepthFirstOrder{
        private boolean[] marked;
        private Queue<Integer> pre; // 先序排列
        private Queue<Integer> post; // 后序排列
        private Stack<Integer> revertPost;// 逆后序排列

        public DepthFirstOrder(DiGraph diGraph) {
            pre = new ConcurrentLinkedQueue<>();
            post = new ConcurrentLinkedQueue<>();
            revertPost = new Stack<>();
            marked = new boolean[diGraph.getVertex()];
            for (int v = 0; v < diGraph.getVertex(); v++){
                if (!marked[v]){
                    dfs(diGraph, v);
                }
            }
        }

        private void dfs(DiGraph diGraph, int v) {
            // 添加先序
            pre.add(v);
            for (int w : adj[v]){
                if (!marked[w]){
                    dfs(diGraph, w);
                }
            }
            post.add(v);
            revertPost.add(v);
        }

        public Queue<Integer> getPre() {
            return pre;
        }

        public Queue<Integer> getPost() {
            return post;
        }

        public Stack<Integer> getRevertPost() {
            return revertPost;
        }
    }

    /**
     * 拓扑排序
     */
    class TopoLogical{
        private Stack<Integer> order;

        public TopoLogical(DiGraph diGraph) {
            DirectedCycle cycle = new DirectedCycle(diGraph);
            if (!cycle.hasCycle()){
                DepthFirstOrder depthFirstOrder = new DepthFirstOrder(diGraph);
                order = depthFirstOrder.getRevertPost();
            }
        }

        public Stack<Integer> getOrder() {
            return order;
        }

        public boolean isDAG(){
            return order != null;
        }
    }
}

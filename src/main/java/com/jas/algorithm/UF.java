package com.jas.algorithm;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class UF {
    private int[] ids;
    private int count;// 连通量

    public UF(int max) {
        ids = new int[max];
        this.count = 0;
        for (int i = 0; i < max; i++){
            ids[i] = i;
        }
    }

    /**
     * 判断两个节点是否是连通的
     * @param v
     * @param w
     * @return
     */
    public boolean connected(int v, int w){
        while (ids[v] != v){
            v = ids[v];
        }
        while (ids[w] != w){
            w = ids[w];
        }
        return ids[v] == ids[w];
    }

    public void union(int v, int w) {
        ids[v] = w;
    }
}

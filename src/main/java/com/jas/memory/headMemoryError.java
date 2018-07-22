package com.jas.memory;

import java.util.LinkedList;
import java.util.List;

/**
 *  -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class headMemoryError {
    static class OOMObject{
    }

    public static void main(String[] args) {
        List<OOMObject> list = new LinkedList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}

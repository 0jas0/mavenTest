package com.jas.memory;

import java.util.LinkedList;
import java.util.List;

/**
 *  -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class headMemoryError {
    static class OOMObject{
        public byte[] placeholder = new byte[64*1024];
    }

    public static void main(String[] args) throws InterruptedException {
        List<OOMObject> list = new
                LinkedList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}

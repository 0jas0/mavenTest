package com.jas.memory;

import java.util.LinkedList;
import java.util.List;

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

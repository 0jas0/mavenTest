package com.jas.memory;

import java.util.LinkedList;
import java.util.List;

/**
 *  -XX:PermSize=2M -XX:MaxPermSize=2M
 */
public class methodAreaMemoryError {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}

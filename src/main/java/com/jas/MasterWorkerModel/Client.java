package com.jas.MasterWorkerModel;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import javax.sound.midi.Soundbank;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/12/7.
 */
public class Client {
    public static void main(String[] args) {
        Master master = new Master(new Worker(),10);
        for(int i=0;i<100;i++){
            Task task = new Task();
            task.setId(i);
            task.setName("No."+i+"任务");
            master.submitTask(task);
        }
        master.begin();
        long start = System.currentTimeMillis();
        while (true){
            if(master.isEnd()){
                long time = System.currentTimeMillis() - start;
                System.out.println("执行时间(毫秒):"+time);
                ConcurrentHashMap<String, Object> resultMap = master.getResultMap();
                for (Map.Entry<String,Object> entry : resultMap.entrySet()){
                    System.out.println(entry.getKey()+"------"+entry.getValue());
                }
                break;
            }
        }
    }
}

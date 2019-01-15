package com.jas.concurrent;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Administrator on 2017/12/5.
 */
public class ConcurrentQueue {
    private class person implements Comparable{
        private int age;
        private String name;

        public person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Object o) {
            return age - ((person) o).age;
        }

        @Override
        public String toString() {
            return "person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.add("aaa");
        queue.offer("bbb");
        queue.add("ccc");
        queue.offer("ddd");
        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.size());

        /*LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.add("a");
        linkedBlockingQueue.add("b");
        linkedBlockingQueue.add("c");
        linkedBlockingQueue.offer("d");
        linkedBlockingQueue.add("e");
        System.out.println(linkedBlockingQueue.size());*/

       /* ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(4);
        arrayBlockingQueue.offer("a");
        arrayBlockingQueue.offer("b");
        arrayBlockingQueue.offer("c");
        arrayBlockingQueue.offer("d");
        arrayBlockingQueue.offer("e");
        System.out.println(arrayBlockingQueue.size());*/

        PriorityBlockingQueue<person> priorityBlockingQueue = new PriorityBlockingQueue<>();
        ConcurrentQueue concurrentQueue = new ConcurrentQueue();
        priorityBlockingQueue.add(concurrentQueue.new person(10,"a"));
        priorityBlockingQueue.add(concurrentQueue.new person(7,"b"));
        priorityBlockingQueue.add(concurrentQueue.new person(13,"c"));
        priorityBlockingQueue.add(concurrentQueue.new person(21,"d"));
        try {
            person take = null;
            while((take = priorityBlockingQueue.take())!=null){
                System.out.println(take);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

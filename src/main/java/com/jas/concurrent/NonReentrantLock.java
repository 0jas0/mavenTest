package com.jas.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class NonReentrantLock {
    class Syn extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (compareAndSetState(1, 0)){
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }

        /**
         * 判断是否已经上锁
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        public Condition newCondition(){
            return new ConditionObject();
        }

        private Syn syn = new Syn();

        public void lock(){
            syn.tryAcquire(1);
        }

        public void unlock(){
            syn.release(1);
        }

        public boolean isLocked(){
           return syn.isHeldExclusively();
        }
    }
}

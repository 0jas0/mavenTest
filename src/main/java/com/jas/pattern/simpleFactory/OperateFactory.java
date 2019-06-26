package com.jas.pattern.simpleFactory;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class OperateFactory {
    public static Operate createOperate(String type){
        switch (type){
            case "+":
                return new AddOperate();
            case "-":
                return new SubOperate();
            case "*":
                return new MultiOperate();
            case "/":
                return new DivOperate();
            default: return null;
        }
    }
}

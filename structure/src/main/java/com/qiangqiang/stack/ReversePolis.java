package com.qiangqiang.stack;

import java.util.Queue;

public class ReversePolis {
    //逆波兰表达式

    public static void main(String[] args) {


    }


    public static int caculate(String[] notion) {
        Stack<Integer> stack = new Stack();
        for (String s : notion) {
            Integer pop1 ;
            Integer pop2 ;
            Integer result;
            switch (s) {
                case "+":
                    pop1 = stack.pop();
                    pop2 = stack.pop();
                    result = pop2 + pop1;
                    stack.push(result);
                    break;
                case "-":
                    pop1 = stack.pop();
                    pop2 = stack.pop();
                    //减法要弹出的第二个在前面
                    result = pop2 - pop1;
                    stack.push(result);
                    break;
                case "*":
                    pop1 = stack.pop();
                    pop2 = stack.pop();
                    result = pop2 * pop1;
                    stack.push(result);
                    break;
                case "/":
                    pop1 = stack.pop();
                    pop2 = stack.pop();
                    //除法要弹出的第二个在前面
                    result = pop2 / pop1;
                    stack.push(result);
                    break;
                default:
                    stack.push(Integer.parseInt(s));
                    break;
            }


        }
        Integer pop = stack.pop();
        return pop;
    }

}

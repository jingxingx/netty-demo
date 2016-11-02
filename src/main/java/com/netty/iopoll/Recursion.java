package com.netty.iopoll;

/**
 * @author jingxingxin
 * @Title: netty-demo
 * @Package com.netty.iopoll
 * @Description:
 * @date 2016/6/24 15:01
 */
public class Recursion {

    private static int first = 1 ;
    private static int sec = 1 ;
    private static int index = 1 ;


    public static void main(String[] args) {
        System.out.println(add(80));
    }

    public static int add(int resultIndex){
        if (resultIndex == 1 || resultIndex == 2){
            return 1;
        }else {
            int temp = first ;
            first = sec ;
            sec = temp + sec ;
        }
        if (index == resultIndex){
            return sec ;
        }
        index ++ ;
        return add(resultIndex);

    }
}

package com.iot.base.socket;

/**
 * @author zhengnaishan
 * @date 2019/4/23 0023
 * @describe :
 */
public class Test2 extends Test1{
    @Override
    protected void hello(){
        System.out.println("hello2");
    }

    public static void main(String[] args) {
        Test2 test = new Test2();
        test.sayHello();
    }
}

package edu.wtbu;

import javafx.scene.input.KeyCode;
import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.Scanner;

public class phonecode {
    public static void main(String[] args) {
        String phone="18772288935";
        tset(phone);
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入验证码：");
        String s = sc.nextLine();
        iseq(s,phone);
    }
    //校验验证码
    public static void iseq(String code,String phone){
        Jedis Jedis=new Jedis("127.0.0.1",6379);
        String code1=Jedis.get("code:"+phone);
        if(code.equals(code1)){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
        Jedis.close();
    }

    //存储验证码并且判断次数
    public static  void tset(String phone){
        String code="";
        String keyphone="Phone:"+phone;
        String keycode="code:"+phone;
        Jedis Jedis=new Jedis("127.0.0.1",6379);
        String count=Jedis.get("Phone:"+phone);
        if(count==null){
            Jedis.setex(keyphone,24*60*60,"1");
            code=getcode();
            Jedis.setex(keycode,2*60,code);
        }else if(Integer.parseInt(count)<=2){
            Jedis.incr(keyphone);
            code=getcode();
            Jedis.setex(keycode,2*60,code);
        }else{
            System.out.println("今天次数已超过三次");
            System.exit(0);
        }
        Jedis.close();
    }

    //生成验证码
    public static String getcode(){
        Random rd= new Random();
        String s="";
        for(int i=0;i<6;i++){
            int i1 = rd.nextInt(10);
            s+=i1;
        }
        return s;
    }

}

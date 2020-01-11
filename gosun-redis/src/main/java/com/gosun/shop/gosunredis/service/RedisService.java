package com.gosun.shop.gosunredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description TODO
 * @Author lxg
 * @Date 2019/11/1
 */
@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * @Author:lxg
     * @Description: redis对字符串操作
     */
    public void testString() {

        redisTemplate.opsForValue().set("第一个","jkdfld");
    }

    public static void main(String[] args) {

//        int[] arr = new int[10];
//
//        //int arr1[] = new int[20];//这种声明数组的方式可以编译，但是不符合规范。避免这样定义
//
//
//        for (int i = 0; i< arr.length; i++){
//            arr[1] = 100;
//            System.out.println("i = "+i + "===="+ arr[i]);
//        }
//
//        for (int i = 0; i< arr.length; i++){
//            arr[1] = 100;
//            System.out.println("i = "+i + "===="+ arr[i]);
//        }
//
//        for (int j = 0; j< 10; ++j){
//            System.out.println("j = "+j);
//        }
//        String s = "234";
//        Long aLong = Long.getLong(s);
//        long l = Long.parseLong(s);
//        Long aLong1 = Long.valueOf(s);
//
//        for (int i = 1; i<10; i++){
//            for (int j = 1; j<=i; j++){
//                System.out.print(j +"*"+ i +"=" + j*i +" ");
//            }
//            System.out.println();
//        }
//
//        int compare = new Comparator<>() {
//            @Override
//            public int compare(Object o, Object t1) {
//                return 0;
//            }
//
//            @Override
//            public boolean equals(Object o) {
//                return false;
//            }
//        }.compare(2, 3);
//
//
//        int [] arr1 = {2,3,4,5,7,5,7,3};
//        List<int[]> ints = Arrays.asList(arr1);//此种方式无法将int数组转为int类型集合，只能转为int数组集合
//
//        int[] src = {1,2,3,4,5,6,7,8,9,10};
//        //将int数组转为list集合
//        List<Integer> list = Arrays.stream( src ).boxed().collect(Collectors.toList());
//        list.add(67);
//
//
//        String[] strArr = {"gfjsdgfds","hiurhug","uruh"};
//
//        for (String str:strArr) {
//            System.out.println(str);
//        }
//        List<String> strings = Arrays.asList(strArr);
//        strings.add("efffsf");//报异常：Exception in thread "main" java.lang.UnsupportedOperationException
//
//        ArrayList<String> objects = new ArrayList<>(Arrays.asList(strArr));
//        objects.add("ddhghu");//正常执行
//        List<String> strings = pastDay(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        for (String s: strings) {
//            System.out.println(s);
//        }
//        System.out.println("======================================================================");
//        List<String> strings1 = pastDay("2019-01-03");
//        for (String s: strings1) {
//            System.out.println(s);
//        }
//        int size = strings1.size();
//        System.out.println(size);

//        LocalDate today = LocalDate.now();
//        for(long i = 0L;i <= 11L; i++){
//            LocalDate localDate = today.minusMonths(i);
////            SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM");
////            String format = sdf.format(localDate.toString());
//            String ss = localDate.toString().substring(0,7);
//            System.out.println(ss);
//            String replace = ss.replace("-", "/");
//            System.out.println("==="+replace);
//            System.out.println(localDate);
//
//            System.out.println("==============");
//        }

//        SimpleDateFormat sdf= new SimpleDateFormat("HH:mm:ss");
//        String format = sdf.format(new Date());
//        System.out.println(format);
//        List<String> s = new ArrayList<>();

        List<String> s = null;
        for (String str: s) {
            System.out.println("====");
        }
        String a = "2019-12-10";
        int i = a.compareTo("2018-12-10");
        System.out.println(i);


    }
    /**
     * 获取过去7天内的日期数组(不包含今天)
     * @return  日期数组
     */
    public static List<String> pastDay(String time){
        List<String> pastDaysList = new ArrayList<>();
        try {
            //我这里传来的时间是个string类型的，所以要先转为date类型的。
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(time);
            for (int i = 1; i <= 7; i++) {
                pastDaysList.add(getPastDate(i,date));
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return pastDaysList;
    }
    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String result = sdf.format(today);
        return result;
    }
}

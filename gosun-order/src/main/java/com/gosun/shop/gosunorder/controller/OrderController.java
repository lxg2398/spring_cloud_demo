package com.gosun.shop.gosunorder.controller;

import com.gosun.shop.gosunorder.bo.OrderBO;
import com.gosun.shop.gosunorder.domain.Orders;
import com.gosun.shop.gosunorder.service.OrderService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单Controller
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired(required = false)
    private OrderService orderService;

    /**
     * 根据ID查询订单的接口
     */
    @GetMapping("{id}")
    public Orders getOrderById(@PathVariable("id") String id){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return orderService.getOrderInfoById(id);
    }

    @PostMapping("create")
    public Map<String,Object> createOrder(@RequestBody OrderBO orderBO){
        Integer row = orderService.createOrder(orderBO);

        Map<String,Object> map = new HashMap<>();

        map.put("code",row > 0 ? "111111" : "000000");//设置一个状态码
        map.put("msg",row > 0 ? "success" : "fail");
        return map;
    }


    public static void main(String[] args) {
//        DecimalFormat df = new DecimalFormat("#0.00");
//        df.setRoundingMode(RoundingMode.DOWN);
//
//        Long a = 256L;
//        Long b = 13L;
//
//        double c = (double)a;
//        System.out.println("c:" + c);
//        double l = b / c;
//        System.out.println("l:"+l);
//        System.out.println(b / a);
//
//        String format = df.format(0.3);
//        System.out.println(format);
//
//        /////////////////////////////////////////////////////////////
//        BigDecimal bigDecimal = new BigDecimal(l*100);
//
//        double v = bigDecimal.setScale(2, RoundingMode.DOWN).doubleValue();
//
//        System.out.println("v:"+v);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
//        try {
//            Date date = simpleDateFormat.parse("2019-04-26 00:00:00");
//            System.out.println(date);
//            String format1 = sdf.format(date);
//            System.out.println("f:"+format1);
//            Date date1 = simpleDateFormat.parse("2019-12-27 00:00:00");
//            System.out.println((date1.getTime() - date.getTime())/(1000*3600*24));
//
//            System.out.println("date"+date.getTime());
//            System.out.println("date1"+date1.getTime());
//
//            System.out.println("差值："+((date1.getTime() - date.getTime()))/(1000*3600*24));
//            System.out.println("差值秒："+((date1.getTime() - date.getTime()))/ 1000);
//
//            String format = simpleDateFormat.format(date);
//            System.out.println(format);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        Long a = 0L;
        double a1 = (double)a;

        boolean b1 = a1 == 0;

        System.out.println(b1);

        System.out.println(a1);

        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");

        List<String> list1 = list.subList(6, 3);

        list1.forEach( str ->{
            System.out.println(str);
        });
        Collection<String> union = CollectionUtils.union(list, list1);
        System.out.println(union.toArray().toString());

        System.out.println("===================================================");
        List<String> betweenDate = getBetweenDate("2018-12-30", "2019-01-02");

        betweenDate.forEach(str -> {
            System.out.println(str);
        });


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format1 = sdf.format(new Date());

//        System.out.println(
//                format1
//        );


        DecimalFormat df = new DecimalFormat("#0.00");
        //df.setRoundingMode(RoundingMode.DOWN);
        df.setRoundingMode(RoundingMode.CEILING);
        String format = df.format(-2.005);

        System.out.println("format:::"+format);


        String b = ",hfddhfgduyfsjfhs,";

        String substring1 = b.substring(1, b.length() - 1);
        System.out.println(substring1);

        String s = "0时114分17秒";
        if (s.substring(0,1).equals("0")){
            String substring = s.substring(2);
            System.out.println(substring);

            String[] arr = s.split("分");

            for (int i =0; i < arr.length;i++){
                System.out.println(arr[i]);
            }
        }

    }
    public static List<String> getBetweenDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);

            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                // 把日期添加到集合
                list.add(sdf2.format(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.DATE, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }

}

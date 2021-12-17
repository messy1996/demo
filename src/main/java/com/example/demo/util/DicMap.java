package com.example.demo.util;

import com.example.demo.mapper.DicMapper;
import com.example.demo.model.entity.DicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class DicMap {
    @Autowired
    private DicMapper pubdicMapper;

    @Autowired
    private static DicMapper dicMapper;

    @PostConstruct
    public void init() {
        dicMapper = pubdicMapper;
        queryDic();
    }

    private static HashMap<String, String> hashMap = new HashMap<>();

    // 静态方法在程序启动的时候只加载一次，这样为了让查询方法只去数据库查询一次
//    static {
////        // 获取应用上下文对象
////        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/*.xml");
////        // 获取dicMapper实例
////        dicMapper = ctx.getBean(DicMapper.class);
//        queryDic();
//    }

    // 从数据库中取值放入到HashMap中
    public static void queryDic() {
        List<DicEntity> dics = dicMapper.selectAll();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dics.size(); i++) {
            DicEntity dic = dics.get(i);
            String tableName = dic.getTableName();
            String fieldName = dic.getFieldName();
            String fieldValue = dic.getFieldValue();
            String key = tableName + "_" + fieldName + "_" + fieldValue;
            String value = dic.getFieldDetail();
            System.out.println(key + "=" + value);
            hashMap.put(key, value);
        }
    }

//    static{
//          hashMap.put("product_type_1","肉类");
//        hashMap.put("product_type_2","蔬菜类");
//        hashMap.put("product_type_3","服装类");
//        hashMap.put("product_type_4","零食");
//        hashMap.put("product_type_5","其他");
//    }

    public static String getFieldDetail(String tableName, String fieldName, String fieldValue) {
        StringBuilder sb = new StringBuilder();
        StringBuilder keySb = sb.append(tableName).append("_").append(fieldName).append("_").append(fieldValue);
        String key = keySb.toString();
        String value = hashMap.get(key);
        return value;
    }

    public static void main(String[] args) {
        String fieldDetail = DicMap.getFieldDetail("product", "type", "1");
        System.out.println("fieldDetail = " + fieldDetail);
    }
}

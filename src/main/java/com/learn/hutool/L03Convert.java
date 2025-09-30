package com.learn.hutool;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.CharsetUtil;
import org.junit.Assert;

import java.util.Date;
import java.util.List;

public class L03Convert {
    public static void main(String[] args) {
        //转换为字符串
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);
        System.out.println("aStr = " + aStr);

        long[] b = {1, 2, 3, 4, 5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        System.out.println("bStr = " + bStr);

        //转换为指定类型数组：
        String[] b1 = {"1", "2", "3", "4"};
        //结果为Integer数组
        Integer[] intArray = Convert.toIntArray(b1);

        long[] c = {1, 2, 3, 4, 5};
        //结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);

        //转换为日期对象：
        String a1 = "2017-05-06";
        Date value = Convert.toDate(a1);

        //转换为集合
        Object[] a2 = {"a", "你", "好", "", 1};
        List<?> list = Convert.convert(List.class, a2);
        //从4.1.11开始可以这么用
        List<?> list2 = Convert.toList(a2);
        System.out.println(list);
        System.out.println(list2);

        //转换为指定类型的集合
        Object[] a3 = { "a", "你", "好", "", 1 };
        List<String> list3 = Convert.convert(new TypeReference<List<String>>() {}, a3);
        System.out.println(list3);

        //转为16进制（Hex）字符串
        String a4 = "我是一个小小的可爱的字符串";
        //结果："e68891e698afe4b880e4b8aae5b08fe5b08fe79a84e58fafe788b1e79a84e5ad97e7aca6e4b8b2"
        String hex = Convert.toHex(a4, CharsetUtil.CHARSET_UTF_8);
        System.out.println("转为16进制（Hex）字符串" + hex);

        //自定义类型转换-ConverterRegistry
        int a5 = 34231;
        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        String result = converterRegistry.convert(String.class, a5);
        Assert.assertEquals("34231", result);

        //注册转换器
        //此处做为示例自定义String转换，因为Hutool中已经提供String转换，请尽量不要替换
        //替换可能引发关联转换异常（例如覆盖String转换会影响全局）
        converterRegistry.putCustom(String.class, CustomConverter.class);
        int a6 = 454553;
        String result6 = converterRegistry.convert(String.class, a6);
        Assert.assertEquals("Custom: 454553", result6);

    }

    //自定义转换器
    public static class CustomConverter implements Converter<String> {
        @Override
        public String convert(Object value, String defaultValue) throws IllegalArgumentException {
            return "Custom: " + value.toString();
        }
    }
}

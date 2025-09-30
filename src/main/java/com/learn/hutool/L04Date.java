package com.learn.hutool;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDateTime;

public class L04Date {
    public static void main(String[] args) {
        String dateStr = "2025-01-23T12:23:56";
        DateTime dt = DateUtil.parse(dateStr);

        // Date对象转换为LocalDateTime
        LocalDateTime of = LocalDateTimeUtil.of(dt);

        // 时间戳转换为LocalDateTime
        of = LocalDateTimeUtil.ofUTC(dt.getTime());

        // 解析ISO时间
        LocalDateTime localDateTime = LocalDateTimeUtil.parse("2020-01-23T12:23:56");
        // 解析自定义格式时间
        localDateTime = LocalDateTimeUtil.parse("2020-01-23", DatePattern.NORM_DATE_PATTERN);
        System.out.println("hihi");

    }
}

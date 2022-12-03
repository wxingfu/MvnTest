package com.wxf.test;

import org.apache.commons.collections.CollectionUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author wxingfu
 * @since 2021/5/25 12:16
 */
public class Test8 {

    public static void main(String[] args) {
        List<String> tHistoryQuestionnaireCodeList = new ArrayList<String>();
        tHistoryQuestionnaireCodeList.add("A");
        tHistoryQuestionnaireCodeList.add("B");
        tHistoryQuestionnaireCodeList.add("C");
        tHistoryQuestionnaireCodeList.add("D");
        tHistoryQuestionnaireCodeList.add("E");

        List<String> tCurrentQuestionnaireCodeList = new ArrayList<String>();

        tCurrentQuestionnaireCodeList.add("A");
        tCurrentQuestionnaireCodeList.add("B");
        tCurrentQuestionnaireCodeList.add("C");
        tCurrentQuestionnaireCodeList.add("D");
        // tCurrentQuestionnaireCodeList.add("E");
        tCurrentQuestionnaireCodeList.add("F");

        Collection<String> intersection = CollectionUtils.intersection(tHistoryQuestionnaireCodeList, tCurrentQuestionnaireCodeList);
        System.out.println(intersection);
        Collection<String> subtract = CollectionUtils.subtract(tHistoryQuestionnaireCodeList, intersection);
        System.out.println(subtract.size() > 0);


        // while(true){
        //     LocalTime now = LocalTime.now();
        //     DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        //     String format = dateTimeFormatter.format(now);
        //     if (format.equals("12:27:00")) {
        //         System.out.println(format);
        //         break;
        //     }else {
        //         System.out.println(false);
        //     }
        // }
    }
}

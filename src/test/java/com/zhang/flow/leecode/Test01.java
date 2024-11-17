package com.zhang.flow.leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test01 {


    public static void main(String[] args) {
        System.out.println(mergeAlternately2("135", "246"));
        System.out.println(mergeAlternately2("135", "24"));
        System.out.println(mergeAlternately2("135", "24678"));
        System.out.println(mergeAlternately2("13578", "246"));
    }

    public static String mergeAlternately(String word1, String word2) {
        char[] charArray1 = word1.toCharArray();
        char[] charArray2 = word2.toCharArray();
        StringBuilder sb = new StringBuilder();
        if(charArray1.length >= charArray2.length ){
            for (int i = 0; i < charArray1.length; i++) {
                sb.append(charArray1[i]);
                if(i < charArray2.length){
                    sb.append(charArray2[i]);
                }
            }
        }else{
            for (int i = 0; i < charArray2.length; i++) {
                if(i < charArray1.length){
                    sb.append(charArray1[i]);
                }
                sb.append(charArray2[i]);

            }
        }
        return sb.toString();
    }


    public static String mergeAlternately2(String word1, String word2) {
        // 使用while来处理
        StringBuilder sb = new StringBuilder();
        int i=0,j=0;
        while(word1.length()>i || word2.length()>j){
            if(i<word1.length()){
                sb.append(word1.charAt(i++));
            }
            if(j<word2.length()){
                sb.append(word2.charAt(j++));
            }
        }

        return sb.toString();
    }
}

package com.zhang.flow;

import com.zhang.flow.event.UserEvent;
import com.zhang.flow.service.User;

public class FastSort {
    private static final User user = new User();


    // 快速排序
    public static void quickSort(int[] arr){
        subSort(arr, 0, arr.length - 1);

    }

    private static void subSort(int[] arr, int start, int end) {
        if(start < end){
            int base = arr[start];
            int low = start + 1;
            int high = end;
            while (true){
                while(low < end && arr[low++] >= base);

            }
        }

    }
}

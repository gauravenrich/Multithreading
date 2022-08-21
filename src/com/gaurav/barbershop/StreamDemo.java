package com.gaurav.barbershop;

import java.util.Arrays;

public class StreamDemo {
    public static void main(String[] args) {
        int[] a={2,1,4,6,8,9};
        Arrays.stream(a).filter(t->t%2==0).forEach(num->System.out.println(num*num));
    }
}


package com.softserve.edu;

/**
 *
 * Dmitriy Voropai
 */
public class Excercise86B {
    public static void main(String[] args) {
        System.out.println(NaturalN(10));
    }
    public static int NaturalN(int n){
        if (n==0 || n<0) {return 1;}
        if (n==1) {return 1;}
        else {return n*(n+1)/2;}
    }
}

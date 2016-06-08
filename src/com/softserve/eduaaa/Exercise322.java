
package com.softserve.eduaaa;

public class Exercise322 {
    public static void main(String[] args) {
       int [] divisors = new int [10001];
             int MaxSumValue = 0;
               int b = 0;
        for (int n = 1; n <= divisors.length-1; n++) { 
            int sum=1;
            for (int i = 2; i <=n ; i++) {
                 if (n%i==0) {
               sum+=i;}  
            }
            divisors[n] = sum;
                for(int k = 0; k<divisors.length; k++){
                    if(MaxSumValue < divisors[n])
                    MaxSumValue = divisors[n]; 
              }
                
                for(int z=0; z<divisors.length; z++) 
                         if(divisors[z] == MaxSumValue){
                         b=z;             
                         }
        }      System.out.println("Value: " +b+  "   Max sum of divisors is:   " +MaxSumValue);
    }
}

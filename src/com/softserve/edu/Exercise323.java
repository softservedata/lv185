
package excercise323;

import java.util.Scanner;

public class Exercise323  {
    public static void main(String[] args) {
        int n;
        System.out.print("Please, inpunt the number - ");
        Scanner sr = new Scanner(System.in);
        n = sr.nextInt();
        if (n<0){System.err.println("This programm calculate n>0 only. Your input is:" +n);}
        for (int i = 1; i < n; i++) {
            int NOD=0;
            for (int j = 1; j <n; j++) {
                if(n%j==0 && i%j==0){NOD=j;}   
            }
            if (NOD<2)  {System.out.println("Number n:  "+n+ "  Number i:"+i+  "   NOD of both:  " +NOD);}
        }
    }}
        

 
            
        
        
    


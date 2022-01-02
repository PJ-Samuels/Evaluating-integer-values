/* 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */
// A sample Java program to sort an array of integers 
// using Arrays.sort(). It by default sorts in 
// ascending order 
import java.util.*;

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }
    public BigInt(int[] arr)
    {
        if(validate(arr)|| validate2(arr))
            throw new IllegalArgumentException("Invalid input");
        if(arr.length < SIZE)
            arr = editArr(arr);
        
        this.digits = arr;
        
        int count = 0;
        for(int i = 0; i < arr.length;i++)
        {
            if(arr[i] == 0)
                count++;
            else  
                break;
        }

        this.numSigDigits = arr.length-count;
    }
    public int[] editArr(int[] arr)
    {
        int[] temp = new int[SIZE];
        int count = 0;
        for( int i = 0; i < temp.length; i++)
        {
            int dif = temp.length-arr.length;
            if(i < dif)
                temp[i] = 0;
            else
            {
                temp[i] = arr[count];
                count++;
            }
        }
        return temp;
        //20 - 17 3
    }
    public boolean validate(int[] arr)
    {
        if((arr == null) && (arr.length >= 0 && arr.length <= SIZE))
            return true;
        
        return false;
    }
    public boolean validate2(int[] arr)
    {
        for( int i = 0; i < arr.length-1; i++)
            if(arr[i] >= 10)
                return true;      
        return false;
    }
    public int getNumSigDigits()
    {
        return numSigDigits;
    }
    public String toString() {
        String ans = "";
        if (digits.length==1 && digits[0]== 0){
            return "0";
        }
        if(digits.length == 0)
            return "0";
        boolean rev = false;
        for( int i = 0; i < digits.length; i++)
        {
            if(digits[i] == 0)
                rev = true;
            else
            {
                rev= false;
                break;
            }
        }
        if(rev == true)
            return "0";

        int temp = this.digits.length-this.numSigDigits;
        if (temp > 0){
            for (int i = temp; i < this.digits.length; i++) {
                ans += this.digits[i];
            }
        }
        else
            for (int i = 0; i < this.digits.length; i++){
                ans += this.digits[i];
            }

        return ans;
    }
    public BigInt(int n)
    {
        if(n < 0)
            throw new IllegalArgumentException("Invalid input");
        else if(n == 0)
        {
            this.digits = new int[0];
            this.numSigDigits = 0;
        }
        else
        {
            int temp = n;
            int count = 0;
            while(temp != 0)
            {
                temp = temp/10;
                count++;
            }
            int[] arr = new int[count];
            int count2 = 0;
            while(n != 0)
            {
                arr[count2] = n%10;
                n = n/10;
                count2++;
            }
            Arrays.sort(arr);
            this.digits = arr;
            this.numSigDigits = arr.length;
        }

        
    }
    public int compareTo(BigInt other)
    {
        String temp1 = this.toString();
        String temp2 = other.toString();
        if(temp1.equals("") || temp2.equals(""))
            return 0;
        if(temp1 == null || temp2 == null)
            throw new IllegalArgumentException("Invalid input");
        if(Integer.parseInt(temp2) > Integer.parseInt(temp1))
            return -1;
        else if(Integer.valueOf(temp2) < Integer.valueOf(temp1))
            return 1;
        else
            return 0;
        
    }
    public BigInt add(BigInt other)
    {
        int sumPerCol = 0;
        int carriedValue = 0;   
        int[] arr1 = this.digits;
        int[] arr2 = other.digits;
        int len;
        if(arr1.length == 0)
            return new BigInt(arr2);
        if(arr2.length == 0)
            return new BigInt(arr1);


        if(arr1.length > arr2.length)
            len = arr1.length;
        else
            len = arr2.length;
        int[] arr3 = new int[len+1];


        for(int i = arr2.length-1 ; i >= 0; i--) {
            sumPerCol = arr1[i] + arr2[i] + carriedValue;             
            if(sumPerCol >= 10) { 
                carriedValue = sumPerCol / 10;
                arr3[i+1] = sumPerCol % 10;
            } else {
                arr3[i+1] = sumPerCol;
                carriedValue = 0;
            }
        }

        arr3[0] = carriedValue;
        if(arr3.length==21 && arr3[0] >= 1)
            throw new ArithmeticException("Invalid");

        return new BigInt(arr3);
        

    }
    public BigInt mul(BigInt other){
        int sumPerCol = 0;
        int carriedValue = 0;   
        int[] arr1 = this.digits;
        int[] arr2 = other.digits;
        int len;
        if(arr1.length == 0)
            return new BigInt(arr2);
        if(arr2.length == 0)
            return new BigInt(arr1);


        if(arr1.length > arr2.length)
            len = arr1.length;
        else
            len = arr2.length;
        int[] arr3 = new int[len+1];


        for(int i = arr2.length-1 ; i >= 0; i--) {
            sumPerCol = arr1[i] + arr2[i] + carriedValue;             
            if(sumPerCol >= 10) { 
                carriedValue = sumPerCol / 10;
                arr3[i+1] = sumPerCol % 10;
            } 
            else {
                arr3[i+1] = sumPerCol;
                carriedValue = 0;
        }
    }
    arr3[0] = carriedValue;
    if(arr3.length==21 && arr3[0] >= 1)
        throw new ArithmeticException("Invalid");
    return new BigInt(arr3);
}

    public static void main(String[] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();

        /*
         * You should uncomment and run each test--one at a time-- after you build the
         * corresponding methods of the class.
         *
         * Note that these unit tests are not complete and you should think of
         * additional tests that you should run to ensure that all your methods work as
         * expected.
         */

        System.out.println("Test 1: result should be 7");
        int[] a1 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();

        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();

        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
    
        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();
        

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        
    }
}

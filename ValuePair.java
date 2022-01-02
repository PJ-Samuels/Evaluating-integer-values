import java.util.*;
public class ValuePair {
    private int a;
    private double b;
    public ValuePair(int a1, double b1)
    {
        if(valid(a1,b1))
        {
            this.a = a1;
            this.b = b1;
        }
    }
    public boolean valid(int a1,double b1)
    {
        if(a1 < 0 || b1 < 0.0)
        {
            System.out.println("Invalid input");
            return false;
        }
        else
            return true;
    }
    public double product() {
        return this.a * this.b;
    }
    public void newPair()
    {
        Scanner scan = new Scanner(System.in);
        int temp1;
        double temp2;
        while(true)
        {
            System.out.println("Enter a valid int for a1");
            temp1 = scan.nextInt();
            System.out.println("Enter a valid double for b1");
            temp2 = scan.nextDouble();
            if(valid(temp1,temp2))
                break;

        }
        this.a = temp1;
        this.b = temp2;
        scan.close();

    }
    public static void main(String[] args)
    {
        int a = 10;
        int b = 10;
        ValuePair pair = new ValuePair(a,b);
        a = pair.getA();
        pair.newPair();

        System.out.println("valid");
    }
    public int getA()
    {
        return this.a;
    }
    public double getB()
    {
        return this.b;
    }
    
}

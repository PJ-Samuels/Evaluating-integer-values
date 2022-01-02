import java.util.*;

public class Histogram { 
    
    private static final int SENTINAL = -999;          // sentinal value to signal endo of input
    private static final int MAX_NUMBERS = 20;         // maximum number of numbers to input
    private static final double UPPER_BOUND = 100.0;   // largest numbers accepted as data
    private static final double LOWER_BOUND = 0.0;     // smallest numbers accepted as adata
    private static final int NUM_BINS = 5;            // number of bins in range [0..100]

    private static final int BIN_SIZE = 100/NUM_BINS;           // size of each bin
   
    /*
     * Method to show an example of using StringBuilder.
     *
     * You will also notice that this method is called from the 
     * main function. 
     *
     */
    public static String getHeaderAsString( String me ) {

	// Create an instance of the StringBuilder class
	// which allows us to create an object of 
	// a series of strings that can then be converted 
	// into one large string via the toString method.
	//
    	StringBuilder sb=new StringBuilder();

        sb.append( System.getProperty("line.separator") );
        sb.append( "Welcome to the Histogram Program " + me + "!" );
	    //me = getFirstName(me);
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "This program will print out a histogram of the numbers" );
        sb.append( System.getProperty("line.separator") );  
        //sb.append( "input by you " + getFirstName(me) + "." );
        sb.append( System.getProperty("line.separator") );
        sb.append( System.getProperty("line.separator") );
        sb.append( "Please enter up to " + MAX_NUMBERS + " doubles or " + SENTINAL + " to stop input!" );
        sb.append( System.getProperty("line.separator") );

        return sb.toString();
    }

    public static int [] calculateHistogram( double [] numbers )
    {
        int bin = NUM_BINS;
        int[] num = new int[bin];
        for(int i = 0; i < numbers.length; i++)
        {
            int index = (int)(numbers[i]-1)/BIN_SIZE;
            if (index >= 0 || index < num.length)
            {
                num[index]++;
            }
        }
        return num;
    }
    public static String toString(int[] histogram)
    {
        String ans = "";
        for(int i =0; i < histogram.length;i++)
        {
            if(i == 0)
                ans += "[";
            else
                ans += "(";
            ans += (i*10)+"..."+((i*10)+10)+"]";
            int num = histogram[i];
            for(int j = 0; j < num; j++)
                ans += "*";
            ans += "\n";
        }
        return ans;
    }
    public static boolean validInput( double num ) {
        double low = LOWER_BOUND;
        double up = UPPER_BOUND;
        if( num <= up && num >= low)
            return true;
        return false;
    }
    public static double[] inputNumbers( Scanner scan ) {
        System.out.println("Enter numbers for the Histogram:");
        int max = MAX_NUMBERS;
        double[] ans = new double[0];
        double temp = 0;
        int sent = SENTINAL;
        int count = 0;
        
        while(count <= max && temp != sent)
        {
            temp = scan.nextDouble();
            if(validInput(temp))
            {
                ans = addVariable(count,ans,temp);
                count++;
            }
        }
        System.out.println(Arrays.toString(ans));
        return ans;

    }
    public static double[] addVariable(int n, double arr[], double x) 
    { 
        double newarr[] = new double[n + 1]; 
        for (int i = 0; i < n; i++) 
            newarr[i] = arr[i]; 
  
        newarr[n] = x; 
  
        return newarr;
    }
    /* 
     * Method to return the first name of the user in case
     * the full name was entered. 
     */
    //public static String getFirstName(String name ) {
        // Note that add the " " to string to be sure
        // there is something to split
	// return (name+" ").split(" ")[0]; 
    // }

    /* 
     * local main test driver
     *
     */
    public static void main (String[] args) {  

	// Connet to the keyboard as the input stream
        Scanner userInput = new Scanner( System.in );
        double[] histo = {1,11 ,11.123, 41, 47, 51, 61.7, 71 ,81, 91, 2.5, 12, 22, 44.3, 42.9, 52, 62, 72 ,82, 92};
        int[] ans = calculateHistogram(histo);
        System.out.println(Arrays.toString(ans));
        
        System.out.print( "And who am I working with today? " );
        String user = userInput.nextLine();

	    String heading = getHeaderAsString( user );

        // Print out welcome message
        System.out.println( heading ); 
        
        // Call the method which prompts the user
        // to input the numbers that will be used
        // to build the histogram.
        double[] numbers = inputNumbers( userInput );

	// Call the method to reate the array histogram
	    int[] histogram = calculateHistogram( numbers );

	// Print the historgram
	    System.out.println( toString( histogram ) );
    }

} 
// CS1C Lab Assignment 8
// Tianrong Xiao Winter 2016 Foothill College

import cs_1c.*;
import java.util.*;
import java.text.*;

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      final int RL_MIN = 2;
      final int RL_MAX = 300;
      final int RL_INCREMENT = 2;
      final int ARRAY_SIZE_MIN = 20000;
      final int ARRAY_SIZE_MAX = 1200000;
      final int ARRAY_SIZE_INCREMENT = 50000;
      int k, randomInt, arraySize, limit;
      long startTime, stopTime; 
      Integer[] testArray;
      ArrayList<TestResult> results = new ArrayList<TestResult>();
      TestResult minRt = null;
      
      // for formatting output neatly
      NumberFormat tidy = NumberFormat.getInstance(Locale.US);
      tidy.setMaximumFractionDigits(4);

      for (arraySize = ARRAY_SIZE_MIN; arraySize <= ARRAY_SIZE_MAX;
         arraySize+=ARRAY_SIZE_INCREMENT) {
         // build arrays for testing
         Integer[] arrayOfInts1 = new Integer[arraySize]; 
         for (k = 0; k < arraySize; k++) {
             randomInt = (int) (Math.random() * arraySize);
             arrayOfInts1[k] = randomInt;
         }  
         
         minRt = new TestResult(0,0,Integer.MAX_VALUE);
         
         for (limit = RL_MIN; limit <= RL_MAX; limit+= RL_INCREMENT) {
            FHsort.setRecursionLimit(limit);

            // copy the test array
            testArray = arrayOfInts1.clone();
             
            startTime = System.nanoTime();  // ------------------ start 
            FHsort.quickSort(testArray);
            stopTime = System.nanoTime();    // ---------------------- stop
             
            System.out.printf("quickSort(%d,%d) Elapsed Time %s seconds%n",
               limit, arraySize, tidy.format( (stopTime - startTime) / 1e9));
             
            TestResult rt = new TestResult(limit, arraySize, stopTime - startTime);
            if (rt.time < minRt.time)
               minRt = rt;
            results.add(rt);
         }
         
        minRt.isMin = true;
      }
      
      System.out.printf("%nTable Summary%n-----------------------%n");
      // output column headers
      System.out.print(String.format("%-10s ", "RL/Size"));
      for (limit = RL_MIN; limit <= RL_MAX; limit+= RL_INCREMENT)
    	  System.out.print(String.format("%-8s", limit));
      System.out.println();
      
      // output results
      Iterator<TestResult> itr = results.iterator();
      TestResult temp;
      int column = 0;
      
      while (itr.hasNext()) {
         temp = (TestResult)itr.next();

         if (column == 0)
            System.out.print(String.format("%-10s %-8s", temp.size, temp));
         else {
            System.out.print(String.format("%-8s", temp));
            if (column >= 149) {
               System.out.println();
               column = -1;
            }
         }
         column++;
      }
   }
}
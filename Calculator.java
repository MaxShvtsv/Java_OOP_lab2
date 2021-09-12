// Lab. 2 - String calculator
// Max Shevtsov
// KM-01

package src.secondlab.Java_OOP_lab2;

import java.util.Arrays;

public class Calculator{
    public static void main(String args[]){

        /* Initialize StringCalculator class. */
        StringCalculator calc = new StringCalculator();

        /* Initialize variables for testing. */
        int a = calc.Add("");
        int b = calc.Add("1");
        int c = calc.Add("1,210");
        int d = calc.Add("10,100,50,60");

        /* Output */
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }
}

/* Class of string calculator. */
class StringCalculator{

    /* Summarize digits in given string.
     * numbers: string, string consists of digits and delimiters.
     * returns: sum of digits.
     */
    public int Add(String numbers){

        //sum: int, sum of digits in given string.
        int sum;

        if (numbers == ""){
            // No numbers in the string.

            sum = 0;
        }else{
            // There are some numbers to process.

            // SplittedDigits: string array, array of splitted digits.
            String splittedDigits[] = numbers.split(",");

            // digitsCount: int, count of digits in the string.
            int digitsCount = splittedDigits.length;

            // intDigitsArray: int array, digits parsed from string to int.
            int intDigitsArray[] = new int[digitsCount];

            for (int i = 0; i < digitsCount; i++){
                intDigitsArray[i] = Integer.parseInt(splittedDigits[i]);
            }

            sum = Arrays.stream(intDigitsArray).sum();
        }

        return sum;
    }
}
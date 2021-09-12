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
        int d = calc.Add("5\n100,10");
        int e = calc.Add("//abc\n10,100\n50abc60");

        /* Output */
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
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
        int sum = 0;

        if (numbers != ""){
            // There are some numbers to process.

            // delimiters: string, string of all delimiters.
            String delimiters = ",|\n";

            if (numbers.charAt(0) == '/' && numbers.charAt(1) == '/'){
                // If the user have set initial delimiter.

                // start: int, number of char where to start slice string
                //             from user delimiter.
                int start = 2;

                // end: int, number of char where to end slice string
                //           from user delimiter.
                int end;

                // Set end var. through the loop.
                for (end = start; numbers.charAt(end) != '\n'; end++);

                // userDelimiter: string, delimiter provided by the user.
                String userDelimiter = numbers.substring(start, end);

                // Slice string from user delimiter.
                numbers = numbers.substring(end + 1, numbers.length());

                // Concatinate delimiters.
                delimiters += "|" + userDelimiter;

            }

            // SplittedDigits: string array, array of splitted digits.
            String splittedDigits[] = numbers.split(delimiters);

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
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
        System.out.println(a);
        int b = calc.Add("1");
        System.out.println(b);
        int c = calc.Add("1,210");
        System.out.println(c);
        int d = calc.Add("5\n100,10");
        System.out.println(d);
        int e = calc.Add("//abc\n10,100\n50abc60");
        System.out.println(e);
        int f = calc.Add("//::\n-10::100\n-50::60");
        System.out.println(f);
        int g = calc.Add("//::\n10::2021\n50::60");
        System.out.println(g);

        /* Output */
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

            Boolean isNegativeExist = false;

            for (int i = 0; i < digitsCount; i++){

                // currentNumber: int, number to be processed.
                int currentNumber = Integer.parseInt(splittedDigits[i]);

                if (currentNumber > 1000){
                    // Numbers bigger then 1000 are ignored.
                    currentNumber = 0;
                }

                if (currentNumber < 0){
                    // Print negatives.

                    if (!isNegativeExist){
                        // Throw an exception about negatives.
                        System.out.print("Negatives are not allowed. Passed negatives: ");
                    }

                    isNegativeExist = true;

                    System.out.printf("'%d'", currentNumber);
                    
                    continue;
                }
                
                intDigitsArray[i] = currentNumber;
            }
            
            if (isNegativeExist){

                System.out.println();

                // No result, because of negatives.
                return 0;
            }
            sum = Arrays.stream(intDigitsArray).sum();
        }

        return sum;
    }
}
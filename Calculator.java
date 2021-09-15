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

        printLine();
        int a = calc.Add("");
        System.out.println(a); // 0
        printLine();
        int b = calc.Add("1");
        System.out.println(b); // 1
        printLine();
        int c = calc.Add("1,210");
        System.out.println(c); // 211
        printLine();
        int d = calc.Add("5\n100,10");
        System.out.println(d); // 115
        printLine();
        int e = calc.Add("//abc\n10,100\n50abc60");
        System.out.println(e); // 220
        printLine();
        int f = calc.Add("//::\n-10::100\n-50::60");
        System.out.println(f); // 0, Exception, with negatives.
        printLine();
        int g = calc.Add("//::\n10::2021\n50::60");
        System.out.println(g); // 120
        printLine();
        int h = calc.Add("//[aaaaa]\n10aaaaa2021,50aaaaa0");
        System.out.println(h); // 60
        printLine();
        int i = calc.Add("//[a][b][c]\n10a20b50c10,100");
        System.out.println(i); // 190
        printLine();
        int j = calc.Add("//[aaa][bb][cccc]\n100aaa5bb105cccc6,0");
        System.out.println(j); // 216
        printLine();
    }

    public static void printLine(){
        System.out.println("---------------------------");
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

                if (numbers.charAt(2) == '['){
                    // Multichar delimiters.

                    // start: int, number of char where to start slice string
                    //             from user delimiter.
                    int start = 3;
                    // end: int, number of char where to end slice string
                    //           from user delimiter.
                    int end;

                    // Set end var. through the loop.
                    for (end = start; numbers.charAt(end) != '\n'; end++);

                    // multipleLengthDelimiter: string, given multilength user delimiter.
                    String multipleLengthDelimiter = numbers.substring(start - 1, end);

                    // bracketsCount: int, count of brackets. Used to determine count of delimiters.
                    int bracketsCount = multipleLengthDelimiter.length() - 
                                        multipleLengthDelimiter.replace("[", "").length();

                    numbers = numbers.substring(end + 1, numbers.length());

                    if (bracketsCount == 1){
                        // Multilength delimiter.
                        
                        // Concatinate delimiters.
                        delimiters += "|" + multipleLengthDelimiter.substring(1, multipleLengthDelimiter.length() - 1);

                    } else{
                        // Multiple delimiters.

                        multipleLengthDelimiter = multipleLengthDelimiter.substring(1, multipleLengthDelimiter.length() - 1);

                        String arrayOfDelimiters[] = multipleLengthDelimiter.split("\\]\\[");

                        for (String delimiter : arrayOfDelimiters){
                            delimiters += "|" + delimiter;
                        }
                    }
                }else{
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

                    System.out.printf("'%d' ", currentNumber);
                    
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
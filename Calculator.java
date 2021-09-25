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
        if (a == 0){
            System.out.println("True");
        }
        printLine();
        int b = calc.Add("1");
        System.out.println(b); // 1
        if (b == 1){
            System.out.println("True");
        }
        printLine();
        int c = calc.Add("1,210");
        System.out.println(c); // 211
        if (c == 211){
            System.out.println("True");
        }
        printLine();
        int d = calc.Add("5\n100,10");
        System.out.println(d); // 115
        if (d == 115){
            System.out.println("True");
        }
        printLine();
        int e = calc.Add("//abc\n10,100\n50abc60");
        System.out.println(e); // 220
        if (e == 220){
            System.out.println("True");
        }
        printLine();
        int f = calc.Add("//::\n-10::100\n-50::60");
        System.out.println(f); // 0, Exception, with negatives.
        if (f == 0){
            System.out.println("True");
        }
        printLine();
        int g = calc.Add("//::\n10::2021\n50::60");
        System.out.println(g); // 120
        if (g == 120){
            System.out.println("True");
        }
        printLine();
        int h = calc.Add("//[aaaaa]\n10aaaaa2021,50aaaaa0");
        System.out.println(h); // 60
        if (h == 60){
            System.out.println("True");
        }
        printLine();
        int i = calc.Add("//[a][b][c]\n10a20b50c10,100");
        System.out.println(i); // 190
        if (i == 190){
            System.out.println("True");
        }
        printLine();
        int j = calc.Add("//[aaa][bb][cccc]\n100aaa5bb105cccc6,0");
        System.out.println(j); // 216
        if (j == 216){
            System.out.println("True");
        }
        printLine();
        int test1 = calc.Add("//[*][**][***]\n2*7**8***10");
        System.out.println(test1); // 27
        if (test1 == 27){
            System.out.println("True");
        }
        printLine();
        int test2 = calc.Add("//[a][**][+++]\n5*5**12+++12");
        System.out.println(test2); // 34
        if (test2 == 34){
            System.out.println("True");
        }
        printLine();
        int test3 = calc.Add("//[ ][**][+++]\n5 5 12 10");
        System.out.println(test3); // 32
        if (test3 == 32){
            System.out.println("True");
        }
        printLine();
    }

    public static void printLine(){
        System.out.println("---------------------------");
    }
}

/* Class of string calculator. */
class StringCalculator{

    String regexSymbols[] = {"*", "+", "?", "\\", ".", "^", "$", "$", "|"};

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

                    // isRegexDelimiter: boolean, determines if there is RegEx reserved chars.
                    Boolean isRegexDelimiter = true;

                    // Set end var. through the loop.
                    for (end = start; numbers.charAt(end) != '\n'; end++);

                    // multipleLengthDelimiter: string, given multilength user delimiter.
                    String multipleLengthDelimiter = numbers.substring(start - 1, end);

                    // bracketsCount: int, count of brackets. Used to determine count of delimiters.
                    int bracketsCount = multipleLengthDelimiter.length() - 
                                        multipleLengthDelimiter.replace("[", "").length();

                    numbers = numbers.substring(end + 1, numbers.length());

                    String gotDelimiter = multipleLengthDelimiter.substring(1, multipleLengthDelimiter.length() - 1);
                    
                    int delimiterLen = gotDelimiter.length();

                    // Process RegEx reserved delimiters.
                    for (int i = 0; i < delimiterLen; i++){
                        for (String regexChar : regexSymbols){
                            if (regexChar.equals(String.valueOf(gotDelimiter.charAt(i)))){
                                isRegexDelimiter = false;
                                if (i == 0){
                                    gotDelimiter = "/" + gotDelimiter;
                                } else{
                                    gotDelimiter = gotDelimiter.substring(0, i) + "/" + gotDelimiter.substring(i);
                                }
                                delimiterLen += 1;
                                i += 1;
                            }
                        }
                    }

                    gotDelimiter = "[" + gotDelimiter + "]";

                    if (bracketsCount == 1){
                        // Multilength delimiter.
                        
                        // Concatinate delimiters.
                        delimiters += "|" + gotDelimiter;

                    } else{
                        // Multiple delimiters.
                        if (!isRegexDelimiter){
                            // We don't remove brackets if there RegEx like delimiter string.
                            multipleLengthDelimiter = gotDelimiter;
    
                            String arrayOfDelimiters[] = multipleLengthDelimiter.split("\\]\\[");
    
                            for (String delimiter : arrayOfDelimiters){
                                delimiters += "|" + delimiter;
                            }
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
                int currentNumber = 0;

                try {
                    currentNumber = Integer.parseInt(splittedDigits[i]);
                } catch (Exception Ex) {
                    currentNumber = 0;
                }

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
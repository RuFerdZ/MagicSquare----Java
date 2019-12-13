import java.util.Scanner;
public class Magic_squares {
    public static void main(String[] args) {
        boolean exit = false;   //this variable controls the re-running of the program
        do {

            Scanner keyboard = new Scanner(System.in);    //initialise Scanner object
            System.out.println("=============================================================");
            System.out.println("                 Welcome to Magic Squares ");
            System.out.println("=============================================================");

            System.out.print("Enter size of Magic Square : ");      //prompt user for the size
            int squareSize = validateNumber();      //method call to get input and then validate an integer

            System.out.println("-------------------------------------------------------------");

            int[][] matrix;    //declare matrix 2D array

            System.out.println("Enter the numbers you want to include in the Magic square...");
            matrix = getValues(squareSize);     //pass square size into the method and then store the return value of the method to Array matrix

            System.out.println("-------------------------------------------------------------");

            displaySquare(matrix, squareSize);          //pass the matrix and size of square to method to display it

            System.out.println("-------------------------------------------------------------");

            checkMagicSquare(matrix, squareSize);       //this method checks if the square is a magic square and display a suitable message

            isLoShu(matrix, squareSize);                //this method checks if the square is a Lo shu square and display a suitable message

            System.out.println("-------------------------------------------------------------");

            //The program asks the user whether he/she wants to re-run the program
            System.out.print("Do you want to play again ? (y/n) : ");
            String state = keyboard.nextLine();
            state = state.toLowerCase();
            boolean choiceValidation = false;

            //checks whether the user wants to re-run the program or not,loops until a valid input is entered
            do {
                if (state.equals("n")) {
                    exit = true;
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Program Exiting....");
                    choiceValidation = true;
                } else if (state.equals("y")) {
                    choiceValidation = true;
                //if the user input is invalid the program will request again from te user for an input
                } else {
                    System.out.print("Invalid Choice!!! Please re-enter : ");
                    state = keyboard.nextLine();
                }
            } while (!choiceValidation);
            System.out.println("=============================================================");

        }while(!exit);
    }

    private static int[][] getValues(int size)
    //this method gets inputs from the user into the array
    //number of inputs depend on the product of sizes
    {
        int[][] numList=new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                System.out.print("Enter number : ");
                numList[row][column]=validateNumber(); //a method call to validate the input and then return the validated number back in order to include in the square
            }

        }
        return numList;        //The 2D array is returned to the main method
    }

    private static void displaySquare(int[][] numList, int size){
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                System.out.print(numList[row][column] + "  ");
            }
            System.out.println();
        }
    }


    private static void checkMagicSquare(int[][]numList, int size){  //this method checks whether it is a magic square or not and display a suitable output
        int[] h_total=new int[size]; //this array stores the totals of each  row
        int[] v_total=new int[size]; //this array stores the totals of each column
        //these variable stores the totals of the 2 diagonals
        int d_total_1=0;
        int d_total_2=0;

        //this loop adds up the totals for each row and each column
        for (int row=0;row<size;row++){
            for (int col=0;col<size;col++){
                h_total[row]=h_total[row]+numList[row][col];
                v_total[col]=v_total[col]+numList[row][col];
            }
        }

        //calculates the totals for each of the two diagonals and store them in the two variables
        for (int diagonalCounter=0;diagonalCounter<size;(diagonalCounter)++) {
            d_total_1 = numList[diagonalCounter][diagonalCounter] + d_total_1;
            d_total_2 = numList[diagonalCounter][size - diagonalCounter - 1] + d_total_2;
        }

        //This section checks whether each of the the totals of each column and row calculated above are equal to each other
        boolean isMagicSquare=true; //this variable represents whether the square is a magic square or not
        int checkAgainstValue=h_total[0];
        for (int count=0;count<size;count++){
            if ((checkAgainstValue!=h_total[count])||(checkAgainstValue!=v_total[count])){
                isMagicSquare=false;
                break;
            }
        }
        //checks whether the two diagonals also equals the above totals
        if ((checkAgainstValue!=d_total_1)||(checkAgainstValue!=d_total_2)){
            isMagicSquare=false;
        }

        if (isMagicSquare){
            //if it is true this message will be displayed
            System.out.println("is a Magic Square : True");
        } else {
            //if it is false this message will be displayed
            System.out.println("is a Magic Square : False");
        }
    }

    private static void isLoShu(int[][]numList, int size){ //this method checks whether it is a Lo shu or not and display a suitable output
        boolean loShu = true;  //this variable represents if it is a lo shu or not

        loop:
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                int counter = 0;
                if (numList[row][column] >= 10 || numList[row][column] < 0) {  //checks whether the values in square is greater than or equal 0 or less than 10
                    loShu = false;
                    break loop;
                }

                //this section checks if any value in the square is repeated more than once
                for (int innerRow = 0; innerRow < size; innerRow++) {
                    for (int innerColumn = 0; innerColumn < size; innerColumn++) {
                        if (numList[row][column] == numList[innerRow][innerColumn]) {
                            counter = counter + 1;
                        }
                        if (counter > 1) {
                            loShu = false;
                            break loop;
                        }
                    }
                }
            }
        }
        //displays a suitable message according to the state of the boolean variable "lo shu"
        if (!loShu) {
            System.out.println("is Lo Shu : False");
        } else {
            System.out.println("is Lo Shu : True");
        }
    }


    private static int validateNumber() {
        //this method validates integers been input by the user
        //if an invalid value is entered , it will request the user again for an input until a valid input is entered
        int Return_number = 0;
        Scanner sc = new Scanner(System.in);
        boolean validateNumber = false;
        while (!validateNumber) {
            try {
                String number = sc.next();
                Return_number = Integer.parseInt(number);
                validateNumber = true;
            } catch (NumberFormatException ex) {
                System.out.print("Incorrect Character please Enter an Integer : ");
            }
        }
        return Return_number;  //the validated integer value is returned to the main program
    }
}

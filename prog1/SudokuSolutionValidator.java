/*
 * Harmony Mothershed 
 * hmothe1@lsu.edu
 * PA-1 (Multithreading)
 * Feng Chen
 */

import java.io.*;
import java.util.*;

public class SudokuSolutionValidator {
    public static int [][] arr = new int[9][9];

    public static class RowColumnObject{
        int row, column;
        RowColumnObject(int row, int column){
            this.row = row;
            this.column = column;
        }
    }

//rows are UP DOWN
//RowCheck class checks the rows for any duplicate values of 1-9 and print the validity
//of the row
    public static class RowCheck extends RowColumnObject implements Runnable{
        private boolean rowValid = true;

        // constructor for RowCheck object
        RowCheck(int row, int column){
            super(row, column);
        }
        //Override RowColumnObject
        @Override
        public void run(){
            int i = row;
            int [] array = new int[9];
            // iterate through the row to count the occurrences of each number        
            for (int j = 0; j < 9; j++){
                int num = arr[i][j];
                if (num >= 1 && num <= 9){
                    array[num - 1]++;
                    if(array[num - 1] > 1){
                        rowValid = false;
                        System.out.println("Row " +i+ ": Invalid");
                        return;
                    }
                }
            }
            System.out.println("Row " +i+ ": Valid");
        }
        // Validate rows
        public boolean isValid(){
            return rowValid;
        }
    }   // End of RowCheck

//columns are LEFT RIGHT
//ColumnCheck class checks the columns for any duplicate values of 1-9 and print the 
//validity of the row
    public static class ColumnCheck extends RowColumnObject implements Runnable{
        private boolean columnValid = true;

        //constructor for ColumnCheck object
        ColumnCheck(int row, int column){
            super(row, column);
        }
        //Override RowColumnObject
        @Override
        public void run(){
            int j = column;
            int[] array = new int[9];   

            //for-each loop
            // loop through each row of the array to check the values of the current column
            for(int[] row : arr){   
                int num = row[j];
                if(num >= 1 && num <= 9){
                    array[num-1]++;
                }
            }
            // check the count of each number in the array
            for (int a : array){
                if(a > 1){
                    columnValid = false;
                }
            }
            if (columnValid == false){
                System.out.println("Column " +j+ ": Invalid");
                return;
            }
            else{
                System.out.println("Column " +j+ ": Valid");
                return;
            }
        }
        // Validate columns
        public boolean isValid(){
            return columnValid;
        }
    }  // End of ColumnCheck

//subgrids are 3x3    
//GridCheck class checks the 3x3 subgrid of the sudoku puzzle for any duplicate values of 1-9
    public static class GridCheck extends RowColumnObject implements Runnable{
        private boolean gridValid = true;

        //constructor of GridCheck object
        GridCheck(int row, int column){
            super(row, column);
        }
        //Override RowColumnObject
        @Override
        public void run(){
            //compute the starting indices of the 3x3 subgrid based on the row and column
            int i0 = (row/3) * 3;
            int j0 = (column/3) * 3;
            int [] ax = new int[9];
            //loop through each elements in the subgrid and increment the count of each value
            for(int i = i0; i < i0 + 3; i++){
                for(int j = j0;j < j0 + 3; j++){
                    ax[arr[i][j] - 1]++;
                }
            }
            // check if any value is a duplicate, so if counter us greater than 1
            for(int a: ax){
                if (a > 1){
                    gridValid = false;
                    break;
                }
            }

            if(!gridValid){
                System.out.println("Subgrid R" +i0+ +(i0+1)+ +(i0+2)+ "C" +j0+ +(j0+1)+ +(j0+2)+ ": Invalid");
                    return;
            }
            else{
                gridValid = true;
                    System.out.println("Subgrid R" +i0+ +(i0+1)+ +(i0+2)+ "C" +j0+ +(j0+1)+ +(j0+2)+ ": Valid");
            }
                    
        }
        //Validate grids
        public boolean isValid(){
            return gridValid;
        }
    }       // End of GridCheck

    // MAIN CLASS
    public static void main(String[] args) throws FileNotFoundException{
        //Ask user for .txt file and insert into a 2D array
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert file name in .txt format: ");
        String input = sc.nextLine();
        Scanner scanner = new Scanner(new File(input));
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                arr[i][j] = scanner.nextInt();
            }
        }

        // Set print out to be printed onto results.txt file
        // When running the program again the output will always be updated in the text file
        PrintStream results = new PrintStream(new File("results.txt"));
        System.setOut(results);

        // initialize the number of valid rows, columns, and grids
        int validRows = 0;
        int validColumns = 0;
        int validGrids = 0;

        // THREAD 1 --> RowCheck
        for(int i = 0; i < 9; i++){
            // create and start RowCheck thread
            RowCheck rc = new RowCheck(i, 0);
            Thread rt = new Thread(rc);
            rt.start();
            try{ 
                rt.join(); 
            }catch(InterruptedException e){ 
                e.printStackTrace();
            }
            if(rc.isValid()){
                validRows++;
            }
        } // end of THREAD 1

        // THREAD 2 --> ColumnCheck
        for(int j = 0; j < 9; j++){
            // create and start ColumnCheck thread
            ColumnCheck cc = new ColumnCheck(0, j);
            Thread ct = new Thread(cc);
            ct.start();
            try{
                ct.join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            if(cc.isValid()){
                validColumns++;
            }
        } // end of THREAD 2

        // THREAD 3 --> GridCheck
        for(int i = 0; i < 9; i+=3){                    // increment by 3 instead of 1 so it can go
            for(int j = 0; j < 9; j+=3){                // grid-by-grid and not element-by-element
                // create and start GridCheck thread
                GridCheck gc = new GridCheck(i, j);
                Thread gt = new Thread(gc);
                gt.start();
                try{
                    gt.join();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(gc.isValid()){
                    validGrids++;
                }
            }
        } // end of THREAD 3

        // print the number of valid rows, columns, and grids
        System.out.println("Valid rows: " +validRows);
        System.out.println("Valid columns: " +validColumns);
        System.out.println("Valid subgrids: " +validGrids);

        // check if the sudoku solution is valid
        if (validRows == 9 && validColumns == 9 && validGrids == 9){
            System.out.print("This sudoku solution is VALID");
        }
        else{
            System.out.print("This sudoku solution is INVALID");
        }

    }// end of MAIN

} // end of program

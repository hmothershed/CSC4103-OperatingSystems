Harmony Mothershed
hmothe1@lsu.edu

To compile and execute the code use the following commands:
    javac SudokuSolutionValidator.java
    java SudokuSolutionValidator

The program will prompt the user to insert a .txt file. To accomplish this, 
use following command:
    sudoku.txt 

After execution, the results will be saved in a .txt file. To open the file, 
use the following command:
    cat results.txt

To ensure the program works for an invalid puzzle do the following commands:
    java SudokuSolutionValidator
    badsudoku.txt       (Enter when prompted for .txt file)
    cat results.txt     (Enter after execution)

NOTE THAT: When rerunning the program, the results of the previous puzzle will be cleared from 
"results.txt" file and the new results will be in the file.


Below are the expected input and output of SUDOKU.TXT:
//input                                         //output
6 2 4 5 3 9 1 8 7                               Row 0: Valid
5 1 9 7 2 8 6 3 4                               Row 1: Valid
8 3 7 6 1 4 2 9 5                               Row 2: Valid
1 4 3 8 6 5 7 2 9                               Row 3: Valid
9 5 8 2 4 7 3 6 1                               Row 4: Valid
7 6 2 3 9 1 4 5 8                               Row 5: Valid
3 7 1 9 5 6 8 4 2                               Row 6: Valid
4 9 6 1 8 2 5 7 3                               Row 7: Valid
2 8 5 4 7 3 9 1 6                               Row 8: Valid                                   
                                                Column 0: Valid
                                                Column 1: Valid
                                                Column 2: Valid
                                                Column 3: Valid
                                                Column 4: Valid
                                                Column 5: Valid
                                                Column 6: Valid
                                                Column 7: Valid
                                                Column 8: Valid
                                                Subgrid R012C012: Valid
                                                Subgrid R012C345: Valid
                                                Subgrid R012C678: Valid
                                                Subgrid R345C012: Valid
                                                Subgrid R345C345: Valid
                                                Subgrid R345C678: Valid
                                                Subgrid R678C012: Valid
                                                Subgrid R678C345: Valid
                                                Subgrid R678C678: Valid
                                                Valid rows: 9
                                                Valid columns: 9
                                                Valid subgrids: 9
                                                This sudoku solution is VALID




Below are the expected input and output of BADSUDOKU.TXT:
//input                                         //output
6 2 4 5 3 9 1 8 4                               Row 0: Invalid
5 1 9 7 2 8 6 3 4                               Row 1: Valid
8 3 7 6 1 4 2 9 5                               Row 2: Valid
1 4 8 8 6 5 7 2 9                               Row 3: Invalid
9 5 8 2 4 7 3 6 1                               Row 4: Valid
7 6 2 3 9 1 4 5 8                               Row 5: Valid
3 7 1 9 5 6 8 4 2                               Row 6: Valid
4 9 6 1 8 2 5 7 3                               Row 7: Valid
2 8 5 4 7 3 9 1 6                               Row 8: Valid
                                                Column 0: Valid
                                                Column 1: Valid
                                                Column 2: Invalid
                                                Column 3: Valid
                                                Column 4: Valid
                                                Column 5: Valid
                                                Column 6: Valid
                                                Column 7: Valid
                                                Column 8: Invalid
                                                Subgrid R012C012: Valid
                                                Subgrid R012C345: Valid
                                                Subgrid R012C678: Invalid
                                                Subgrid R345C012: Invalid
                                                Subgrid R345C345: Valid
                                                Subgrid R345C678: Valid
                                                Subgrid R678C012: Valid
                                                Subgrid R678C345: Valid
                                                Subgrid R678C678: Valid
                                                Valid rows: 7
                                                Valid columns: 7
                                                Valid subgrids: 7
                                                This sudoku solution is INVALID

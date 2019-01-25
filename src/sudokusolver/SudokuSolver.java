/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.util.Scanner;

/**
 *
 * @author root
 */
public class SudokuSolver {

    int board[][];
    public static void main(String args[])
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Input Sudoku(0 for empty block)");
        int boardd[][]=new int[9][9];
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                boardd[i][j]=in.nextInt();
        SudokuSolver ob=new SudokuSolver();
        ob.solve(boardd);
        System.out.println("\n\n\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(ob.board[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    private boolean solve(int[][] board) {
        this.board=board;
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (this.board[row][column] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        this.board[row][column] = k;
                        if (isValid(this.board, k, row, column) && solve(this.board)) {
                            return true;
                        }
                        this.board[row][column] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int board[][], int k, int i, int j) {
        board[i][j] = k;
        boolean cond = rowCons(board, i) && colCons(board, j) && subCons(board, i, j);
        //System.out.println(i+"\t"+j+"\t"+k+"\t"+rowCons(board, i));
        //System.out.println(i+"\t"+j+"\t"+k+"\t"+colCons(board, j));
        //System.out.println(i+"\t"+j+"\t"+k+"\t"+subCons(board, i, j));
        //System.out.println("\n"+i+"\t"+j+"\t"+k+"\t"+cond);
        return cond;
    }

    private static boolean rowCons(int[][] board, int i) {
        boolean out = true;
        int occ[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int j = 0; j < 9; j++) {
            if (board[i][j] != 0) {
                occ[board[i][j] - 1] += 1;
            }
        }
        for (int j = 0; j < 9; j++) {
            if (occ[j] > 1) {
                out = false;
            }
        }
        return out;
    }

    private static boolean colCons(int[][] board, int j) {
        boolean out = true;
        int occ[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 9; i++) {
            if (board[i][j] != 0) {
                occ[board[i][j] - 1] += 1;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (occ[i] > 1) {
                out = false;
            }
        }
        return out;
    }

    private static boolean subCons(int[][] board, int i, int j) {
        boolean out = true;
        int occ[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        int rs, re, cs, ce;
        rs = (i / 3) * 3;
        re = rs + 3;
        cs = (j / 3) * 3;
        ce = cs + 3;
        //System.out.println(rs+"\t"+cs);
        for (int m = rs; m < re; m++) {
            for (int n = cs; n < ce; n++) {
                //System.out.println("In loop "+m+"\t"+n);
                if (board[m][n] != 0) {
                    occ[board[m][n] - 1] += 1;
                }
            }
        }
        for (int k = 0; k < 9; k++) {
            if (occ[k] > 1) {
                out = false;
            }
        }
        return out;
    }
}
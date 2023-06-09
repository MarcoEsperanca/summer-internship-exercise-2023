package com.premiumminds.internship.snail;

import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {

  /**
   * Method to get snailshell pattern
   * 
   * @param matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public int[] getSnailShellAux(int[][] matrix) {
        this.matrix = matrix;
        List<Integer> result = new ArrayList<>();

        if (matrix.length == 0) {
            return new int[0];
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int startRow = 0;
        int endRow = rows - 1;
        int startCol = 0;
        int endCol = cols - 1;

        while (startRow <= endRow && startCol <= endCol) {
            traverseRight(startRow, endRow, startCol, endCol, result);
            startRow++;

            traverseDown(startRow, endRow, endCol, result);
            endCol--;

            if (startRow <= endRow) {
                traverseLeft(endRow, startCol, endCol, result);
                endRow--;
            }

            if (startCol <= endCol) {
                traverseUp(endRow, startRow, startCol, result);
                startCol++;
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public Future<int[]> getSnailShell(int[][] matrix) {
        return new Future<int[]>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return true;
            }

            @Override
            public int[] get() throws InterruptedException, ExecutionException {
                return getSnailShellAux(matrix);
            }

            @Override
            public int[] get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return getSnailShellAux(matrix);
            }
        };
    }

    private void traverseRight(int startRow, int endRow, int startCol, int endCol, List<Integer> result) {
        for (int i = startCol; i <= endCol; i++) {
            result.add(matrix[startRow][i]);
        }
    }

    private void traverseDown(int startRow, int endRow, int col, List<Integer> result) {
        for (int i = startRow; i <= endRow; i++) {
            result.add(matrix[i][col]);
        }
    }

    private void traverseLeft(int row, int startCol, int endCol, List<Integer> result) {
        for (int i = endCol; i >= startCol; i--) {
            result.add(matrix[row][i]);
        }
    }

    private void traverseUp(int row, int startRow, int col, List<Integer> result) {
        for (int i = row; i >= startRow; i--) {
            result.add(matrix[i][col]);
        }
    }




  
}

/***************************************************************
* file: GameOfLife.java
* author: S. Sawyer
* class: CS 1400 – Intro Programming and Problem Solving
*
* assignment: Program 6
* date last modified: 11/29/2024
*
* purpose: Recreate Conway's Game of Life and executes to generate each generation of a certain instance of said game of life.
*
****************************************************************/
import java.io.*;
import java.util.Scanner;



public class GameOfLife
{

	private char[][] gameBoard = null;
	private int rows;
	private int cols;

	public GameOfLife(File inFile) {


		try (Scanner scnr = new Scanner(inFile)) {

			rows = scnr.nextInt();
			cols = scnr.nextInt();
			gameBoard = new char[rows][cols];

			//places each number in a 2D array as a char
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < cols; j++) {
					char cell = scnr.next().charAt(0);
					gameBoard[i][j] = cell;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found.");
		}
	}

//method: getColumns
//purpose: returns the number of columns in the game board
	public int getColumns() {
		return cols;
	}

//method: getRows
//purpose: returns the number of rows in the game board
	public int getRows() {
		return rows;
	}

//method: getCell
//purpose: gets the cell of the game board at a specific instance and converts it into an int value
//also returns 0 if the cell requested is out of bounds
	public int getCell(int column, int row) {
		if(column >= cols || row >= rows || column < 0 || row < 0) {
			return 0;
		}

		return gameBoard[row][column] - '0';
	}

//method: setCell
//purpose: takes in an int value and sets the cell at the index to be the char equivilant of said int
	public void setCell(int column, int row, int value) {
		gameBoard[row][column] = (char)(value + '0');
	}

//method: computeNextGeneration
//purpose: recursively computes and prints all generations up to the number inputed in the parameter
//generation 1 is always the origional game board
	public void computeNextGeneration(int generation) {
	    
		if(generation > 1) {
			computeNextGeneration(generation - 1);
		} else if(generation == 1) {
			System.out.println("\nGeneration " + generation);
			System.out.println();
			print();
			return;
		}
	    
		System.out.println("\nGeneration " + generation);
		System.out.println();
	    
		//copy of game board as an int array
		int[][] temp = new int[rows + 2][cols + 2];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				temp[i ][j ] = getCell(j, i);
			}
		}

		//all the cells that need to be checked by the program
		int[][] adjacentCells = {{-1, -1}, {-1, 0}, {-1, +1},
			{0, -1}, {0, +1},
			{+1, -1}, {+1, 0}, {+1, +1},
		};
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(temp[i][j] == 1) {
					int count = 0;
					for(int[] adjacent : adjacentCells) {
						int x = i + adjacent[0];
						int y = j + adjacent[1];

						if(x >= 0 && x < rows && y >= 0 && y < cols && temp[x][y] == 1) count++;
					}

					if(count > 3 || count < 2) 
						setCell(j, i, 0);
				} else {
					int count = 0;
					for(int[] adjacent : adjacentCells) {
						int x = i + adjacent[0];
						int y = j + adjacent[1];

						if(x >= 0 && x < rows && y >= 0 && y < cols && temp[x][y] == 1) count++;
					}

					if(count == 3)
						setCell(j, i, 1);
				}
			}
		}


		print();
	}

//method: print
//purpose: prints out the entire game board
	public void print() {
		for(int i = 0; i < gameBoard.length; i++) {
			for(int j = 0; j < gameBoard[0].length; j++) {
				System.out.print(gameBoard[i][j]);
			}
			System.out.println();
		}
	} 
}
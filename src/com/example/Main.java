package com.example;

import java.util.*;

public class Main {

    //declaring globally for all classes/methods
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        //Two dimensional character array (5x5) to represent gameboard
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9):");
            int playerPos = scan.nextInt();
            //System.out.println(playerPos);
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position unavailable, enter a valid position");
                playerPos = scan.nextInt();
            }

            placeMark(gameBoard, playerPos, "player");
            String result = checkWinner();
            if(result.length() > 0) {
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placeMark(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if(result.length() > 0) {
                printGameBoard(gameBoard);
                System.out.println(result);
                break;
            }
        }


    }

    //For every character array > for every character
    //Once every character has been printed for that row, println is used to create a new line for the next row
    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placeMark(char[][] gameBoard, int pos, String user) {
        //initializing variable
        char symbol = ' ';

        //after correct symbol determined for user, the users' position is added to an ArrayList of integers to keep track of who's where,
        //and ultimately determine the winner in the checkWinner method
        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }

        //adding symbol to gameboard by overwriting the blank space with appropriate symbol
        switch(pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {

        //The Arrays.asList method is used to convert an array like (1,2,3) and convert it to a list
        //List interface inherits from the Collection class, so it allows positional access and insertion, can store duplicates, has many more methods than arrays, etc
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List middleCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);


        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(middleCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for(List l : winning) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations you won!";
            } else if(cpuPositions.contains(l)) {
                return "CPU wins! Better luck next time.";
            } else if(playerPositions.size() + cpuPositions.size() == 9) {
                return "Draw";
            }
        }

        return "";
    }
}

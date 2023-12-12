package com.oda;

import com.oda.GUI.ClickFieldEvent;
import com.oda.GUI.GUI;

import java.util.ArrayList;
import java.util.List;

public class WinCheck {
    static byte winCondition;
    static boolean[] won;
    static int gridSize = GUI.getBoard().length;

    public static void winConditionCheck(int x, int y, int userID){
        byte[][] board = GUI.getBoard();
        if (board[x+1][y+1] < gridSize){
            if(board[x][y] == board[x+1][y+1] && board[x][y] == board[x+2][y+2]){
                won[userID] = true;
            }
        }
    }
    public static void test(){
        GUI.addListener(new ClickFieldEvent() {
            @Override
            public void onClickEvent(int x, int y, int userID) {
                winCheckNew(GUI.getBoard());
            }
        });
    }

    public static int winCheck(byte[][] board){
        System.err.println("test");
        int l = board.length;
        for(int x = 0; x < l; x++){
            for(int y = 0; y < l; y++){
                if(x > 1 && y> 1 && board[x][y] != 0){
                    if(board[x][y] == board[x - 1][y - 1] && board[x][y] == board[x - 2][y - 2]){
                        System.out.println("win " + board[x][y]);
                        return 0;
                    }
                    if(board[x][y-2] == board[x - 1][y - 1] && board[x][y] == board[x - 2][y - 2]){
                        System.out.println("win " + board[x][y]);
                        return 0;
                    }
                }
            }
        }
        System.err.println("No win");
        return 0;
    }

    public static int winCheckNew(byte[][] board){
        List<String> templates = new ArrayList<>();
        templates.add("#/-#/--#");
        templates.add("--#/-#/#");
        templates.add("###");
        templates.add("#/#/#");
        int l = board.length;
        for(int x = 0; x < l -2; x++){
            for(int y = 0; y < l -2; y++){
                for(String template : templates){
                    int x0 = 0;
                    int y0 = 0;
                    int id0 = -1;
                    boolean exitEarly = false;
                    System.out.println("");
                    System.out.println(template);
                    System.out.println("---------------------");
                    for(char c : template.toCharArray()){
                        if(c == '#'){
                            if(id0 == -1) id0 = board[x+x0][y+y0];
                            if(id0 <= 0) {
                                exitEarly = true;
                                break;
                            }
                            System.out.print(x + "/" + y + " (" + x0 + "/" + y0 + ") ");
                            if(board[x+x0][y+y0] != id0) {
                                exitEarly = true;
                                System.out.println("Incorrect");
                                break;
                            } else {
                                System.out.println("Correct");
                            }
                            x0++;
                        } else if(c == '/'){
                            System.out.println("Linebreak");
                            x0 = 0;
                            y0++;
                        } else {
                            x0++;
                        }
                    }
                    if(!exitEarly) {
                        System.out.println(x + "/" + y);
                        System.out.println(id0 + " won!");
                        return 0;
                    }
                }
            }
        }
        System.out.println("Nothing found");
        return 0;
    }
}

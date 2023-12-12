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
                winCheck(GUI.getBoard());
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
                    int id0 = board[x][y];
                    for(char c : template.toCharArray()){
                        if(c == '#'){
                            x0++;
                        } else if(c == '/'){
                            x0 = 0;
                            y0++;
                        }
                    }
                }
            }
        }
        return 0;
    }
}

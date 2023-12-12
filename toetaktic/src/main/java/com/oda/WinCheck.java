package com.oda;

import com.oda.GUI.ClickFieldEvent;
import com.oda.GUI.GUI;

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
                System.out.println(x + "/" + y + "/" + userID);
            }
        });
    }
}

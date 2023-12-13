package com.oda;

import com.oda.GUI.ClickFieldEvent;
import com.oda.GUI.GUI;

import java.util.ArrayList;
import java.util.List;

public class WinCheck {
    public static void init(){
        GUI.addListener(new ClickFieldEvent() {
            @Override
            public void onClickEvent(int x, int y, int userID) {
                int result = winCheck(GUI.getBoard());
                if(result != -1){

                }
            }
        });
    }

    public static int winCheck(byte[][] board){
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
                    for(char c : template.toCharArray()){
                        if(c == '#'){
                            if(id0 == -1) id0 = board[x+x0][y+y0];
                            if(id0 <= 0) {
                                exitEarly = true;
                                break;
                            }
                            if(board[x+x0][y+y0] != id0) {
                                exitEarly = true;
                                break;
                            }
                            x0++;
                        } else if(c == '/'){
                            x0 = 0;
                            y0++;
                        } else {
                            x0++;
                        }
                    }
                    if(!exitEarly) {
                        return id0;
                    }
                }
            }
        }
        return -1;
    }
}

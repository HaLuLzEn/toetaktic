package com.oda;

import com.oda.GUI.ClickFieldEvent;
import com.oda.GUI.GUI;

public class WinCheck {
    public static void test(){
        GUI.addListener(new ClickFieldEvent() {
            @Override
            public void onClickEvent(int x, int y, int userID) {

            }
        });
    }
}

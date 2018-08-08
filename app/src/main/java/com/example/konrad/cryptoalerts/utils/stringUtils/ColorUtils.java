package com.example.konrad.cryptoalerts.utils.stringUtils;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

public class ColorUtils {
    public static int INCREASE = Color.rgb(50,205,50);
    public static int DECREASE = Color.rgb(220,20,60);

    public static int LIGHT_GRAY = Color.rgb(220,220,220);
    public static int WHITE = Color.rgb(255,255,255);



    public static void setChangeText(TextView view, double change){
        view.setTextColor(change<0.0 ? DECREASE : INCREASE);

        String sChange = String.valueOf(change);
        view.setText(sChange);
    }

    public static void setRowBackgroundColor(View view, int position){
        view.setBackgroundColor(position % 2 == 0 ? WHITE : LIGHT_GRAY);
    }
}

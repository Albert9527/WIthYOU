package com.zcz1024.withyou.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DiaoLogUtil {

    private static int sure ;

    public static void showWindow(Context context,String msg){
        sure = -1;
        AlertDialog.Builder alertLog = new AlertDialog.Builder(context);
        alertLog.setMessage(msg);

        alertLog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sure = 1;
            }
        });

        alertLog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sure = 0;
            }
        });
        alertLog.show();
    }

    public static int getSure(){
        return sure;
    }

}

package com.zcz1024.withyou.Util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zcz1024.withyou.R;

import java.util.HashMap;
import java.util.Map;

public class CreatDialogUtil extends Dialog {
    private Activity context;

    private Button btn_submit,bt_applycancel;
    private EditText et_Active_apply_reason;
    private TextView tv_Active_apply_name;
    private String actName;

    private View.OnClickListener clickListener;

    public CreatDialogUtil(Activity context) {
        super(context);
        this.context = context;
    }

    public CreatDialogUtil(Activity context, int theme, View.OnClickListener clickListener,String actName) {
        super(context,theme);
        this.context = context;
        this.clickListener = clickListener;
        this.actName = actName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //指定布局
        this.setContentView(R.layout.active_apply_dialog);
        tv_Active_apply_name = findViewById(R.id.tv_active_apply_name);
        et_Active_apply_reason = findViewById(R.id.et_active_apply_reason);

        tv_Active_apply_name.setText(actName);


        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */

        final Window dialogwindow = this.getWindow();

        final WindowManager manager = context.getWindowManager();

        Display display = manager.getDefaultDisplay();// 获取屏幕宽、高用

        WindowManager.LayoutParams params = dialogwindow.getAttributes();// 获取对话框当前的参数值
        params.height = (int) (display.getHeight() * 0.7); // 高度设置为屏幕的0.6
        params.width = (int) (display.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogwindow.setAttributes(params);

        //根据id在布局中找到控件对象
        btn_submit = findViewById(R.id.btn_active_apply_submit);
        bt_applycancel = findViewById(R.id.bt_active_apply_cancel);

        btn_submit.setOnClickListener(clickListener);
        bt_applycancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                //点击自定义按钮关闭弹窗
                CreatDialogUtil.this.dismiss();
            }
        });
        this.setCancelable(true);
    }

    public Map<String,String> getApplymap(String qctiveId) {
       Map<String,String> map = new HashMap<>();
       if (et_Active_apply_reason.getText().toString()!=null){
       map.put("reson",et_Active_apply_reason.getText().toString());
       map.put("team",qctiveId);
       map.put("user", AcountInfo.geteditInfo(context,"userid"));
       }
       return map;
    }

}

package com.example.togashikouichi.a5_4_activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ActivityEx extends Activity implements View.OnClickListener {

    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static String TAG_WEB = "web";
    private final static String TAG_MAP = "map";
    private final static String TAG_CALL = "call";
    private final static String TAG_DIAL = "dial";
    private final static String TAG_SETUP = "setup";
    private final static String TAG_HELLO = "hello";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);


        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);


        layout.addView(makeButton("web view", TAG_WEB));
        layout.addView(makeButton("map view tokyo", TAG_MAP));
        layout.addView(makeButton("tel call start", TAG_CALL));
        layout.addView(makeButton("show tel view", TAG_DIAL));
        layout.addView(makeButton("show setting", TAG_SETUP));
        layout.addView(makeButton("show hello world", TAG_HELLO));

    }

    //ボタン作成
    private Button makeButton (String text, String tag) {
        Button button = new Button(this);
        button.setText(text);
        button.setTag(tag);
        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        return button;
    }

    public void onClick (View v){
        String tag = (String)v.getTag();
        try{
            if (TAG_WEB.equals(tag)) {
                Intent intent = new Intent("android.intent.action.View", Uri.parse("http://npaka.net"));
                startActivity(intent);
            } else if (TAG_MAP.equals(tag)) {
                Intent intent = new Intent("android.intent.action.View", Uri.parse("geo:0,0?q=Tokyo"));
                startActivity(intent);
            } else if (TAG_CALL.equals(tag)) {
                Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:117"));
                startActivity(intent);
            } else if (TAG_DIAL.equals(tag)) {
                Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:117"));
                startActivity(intent);
            } else if (TAG_SETUP.equals(tag)) {
                Intent intent = new Intent("android.setting.SETTING");
                startActivity(intent);
            } else if (TAG_HELLO.equals(tag)) {
                startActivity(this, "new.npaka.helloworld", "net.npkaka.helloworld");
            }
        } catch (Exception e) {

        }
    }

    private static void startActivity(Activity activity, String pakageName, String className) throws Exception {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName(pakageName, className));
        intent.removeCategory(Intent.CATEGORY_DEFAULT);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    private void toast(String text) {
        if (text == null) text = "";
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}

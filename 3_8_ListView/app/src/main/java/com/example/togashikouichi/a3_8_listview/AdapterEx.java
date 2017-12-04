package com.example.togashikouichi.a3_8_listview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by TogashiKouichi on 2017/11/07.
 */

public class AdapterEx extends Activity
        implements View.OnClickListener{
    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static String TAG_LISTVIEW = "listview";
    private final static String TAG_GRIDVIEW = "gridview";
    private final static String TAG_VIEWPAGER = "viewpager";
    public static ArrayList<AdapterItem> items;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        items = new ArrayList<AdapterItem>();
        for (int i = 0; i < 30; i++){
            AdapterItem item = new AdapterItem();
            item.icon = res2bmp(this, R.drawable.sample);
            item.text = "項目" + i;
            items.add(item);
        }

        //レイアウト
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        //ボタン
        layout.addView(makeButton("listView", TAG_LISTVIEW));
        layout.addView(makeButton("グリットview", TAG_GRIDVIEW));
        layout.addView(makeButton("view pager", TAG_VIEWPAGER));
    }

    //ボタン生成
    private Button makeButton(String text, String tag){
        Button button = new Button(this);
        button.setText(text);
        button.setTag(tag);
        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        return button;
    }

    //button action
    public void onClick(View v){
        String tag = (String)v.getTag();
        if (TAG_LISTVIEW.equals(tag)) {
            Intent intent = new Intent(this, ListViewActivity.class);
            startActivity(intent);
        } else if (TAG_GRIDVIEW.equals(tag)) {
            Intent intent = new Intent(this, GridViewActivity.class);
            startActivity(intent);
        } else if (TAG_VIEWPAGER.equals(tag)) {
            Intent intent = new Intent(this, ViewPagerActivity.class);
            startActivity(intent);
        }
    }

    private Bitmap res2bmp(Context context, int resID) {
        return BitmapFactory.decodeResource(
                context.getResources(), resID
        );
    }
}

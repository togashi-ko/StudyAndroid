package com.example.togashikouichi.a3_8_listview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewActivity extends Activity {
    private ArrayList<AdapterItem> items;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        items = AdapterEx.items;

        ListView listView = new ListView(this);
        listView.setScrollingCacheEnabled(false);
        listView.setAdapter(new MyAdapter());
        setContentView(listView);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount(){
            return items.size();
        }

        @Override
        public  AdapterItem getItem(int pos){
            return items.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return pos;
        }

        @Override
        public View getView(int pos, View view, ViewGroup parent) {
            Context context = ListViewActivity.this;
            AdapterItem item = items.get(pos);

            if (view == null) {
                LinearLayout layout = new LinearLayout(context);
                layout.setBackgroundColor(Color.WHITE);
                layout.setPadding(10, 10, 10, 10);
                layout.setGravity(Gravity.CENTER_VERTICAL);
                view = layout;

                ImageView imageView = new ImageView(context);
                imageView.setTag("icon");
                imageView.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
                layout.addView(imageView);

                TextView textView = new TextView(context);
                textView.setTag("text");
                textView.setTextColor(Color.BLACK);
                textView.setPadding(10, 20, 10, 20);
                layout.addView(textView);
            }

            ImageView imageView = (ImageView)view.findViewWithTag("icon");
            imageView.setImageBitmap(item.icon);
            TextView textView = (TextView)view.findViewWithTag("text");
            textView.setText(item.text);
            return view;
        }
    }

}

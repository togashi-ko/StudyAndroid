package com.example.togashikouichi.a4_3_preferenceex;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class PreferenceEx extends Activity implements View.OnClickListener {
    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static int MP = LinearLayout.LayoutParams.MATCH_PARENT;
    private final static String TAG_WRITE = "write";
    private final static String TAG_READ = "read";

    private EditText editText;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        editText = new EditText(this);
        editText.setText("");
        editText.setLayoutParams(new LinearLayout.LayoutParams(MP,WC));
        layout.addView(editText);

        layout.addView(makeButton("kakikomi", TAG_WRITE));
        layout.addView(makeButton("yomikomi", TAG_READ));

        setContentView(R.layout.activity_preference_ex);
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

    public void onClick(View v) {
        String tag = (String)v.getTag();

        SharedPreferences pref = getSharedPreferences(
                "PreferencesEx", MODE_PRIVATE
        );

        if (TAG_WRITE.equals(tag)) {

            SharedPreferences.Editor editor = pref.edit();
            editor.putString("text", editText.getText().toString());
            editor.commit();

        } else if (TAG_READ.equals(tag)) {
            editText.setText(pref.getString("text",""));
        }
    }

}

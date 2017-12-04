package com.example.togashikouichi.a5_2_activityvalue;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyActivity extends Activity implements View.OnClickListener {

    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static int REQUEST_TEXT = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my);
        super.onCreate(bundle);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        Button button = new Button(this);
        button.setText("other activity");
        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));

        textView = new TextView(this);
        textView.setText("this is test");
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(16);
        textView.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        layout.addView(textView);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MyActivity.class);
        intent.putExtra("text", textView.getText().toString());
        startActivityForResult(intent, REQUEST_TEXT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_TEXT &&
                resultCode == RESULT_OK) {
            String text = "";
            Bundle extras = intent.getExtras();
            if (extras != null) text = extras.getString("text");

            textView.setText(text);
        }
    }
}

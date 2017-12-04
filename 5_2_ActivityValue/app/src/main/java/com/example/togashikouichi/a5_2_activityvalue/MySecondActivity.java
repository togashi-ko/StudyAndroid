package com.example.togashikouichi.a5_2_activityvalue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MySecondActivity extends Activity implements View.OnClickListener {
    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static int MP = LinearLayout.LayoutParams.MATCH_PARENT;
    private EditText editText;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setResult(Activity.RESULT_CANCELED);

        String text = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) text = extras.getString("text");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        Button button = new Button(this);
        button.setText("ok");
        button.setOnClickListener(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        layout.addView(button);

        editText = new EditText(this);
        editText.setText(text);
        editText.setLayoutParams(new LinearLayout.LayoutParams(MP,WC));
        layout.addView(editText);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("text", editText.getText().toString());
        setResult(Activity.RESULT_OK, intent);

        finish();
    }
}

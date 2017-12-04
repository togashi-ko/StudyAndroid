package com.example.togashikouichi.a4_1_file_controle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileEx extends Activity
        implements View.OnClickListener {

    private final static int WC = LinearLayout.LayoutParams.WRAP_CONTENT;
    private final static int MP = LinearLayout.LayoutParams.MATCH_PARENT;
    private final static String TAG_WRITE = "write";
    private final static String TAG_READ = "read";
    private EditText editTextView;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(Window.FEATURE_NO_TITLE);

        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.WHITE);
        layout.setOrientation(LinearLayout.VERTICAL);

        setContentView(layout);

        editTextView = new EditText(this);
        editTextView.setText("");
        editTextView.setLayoutParams(new LinearLayout.LayoutParams(MP,WC));
        layout.addView(editTextView);

        layout.addView(makeButton("kakikomi", TAG_WRITE));
        layout.addView(makeButton("yomikomi", TAG_READ));

    }

    //ボタン作成くらす
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

        if (TAG_WRITE.equals(tag)) {
            try {
                String str = editTextView.getText().toString();
                data2file(this, str.getBytes(), "test.txt");
            } catch (Exception e) {

                editTextView.setText("書き込み失敗");
            }
        } else  if (TAG_READ.equals(tag)) {
            try {
                String str = new String(file2data(this, "test.txt"));
                editTextView.setText(str);
            } catch (Exception e) {
                editTextView.setText("読み込みに失敗しました");
            }
        }
    }

    private static void data2file (Context context, byte[] w, String filename) throws Exception {
        OutputStream out = null;
        try {
            //ファイル出力ストリーム解放
            out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            //バイト配列書き込み
            out.write(w, 0, w.length);
            //ファイル出力ストリーム閉
            out.close();
        } catch (Exception e) {
            try {
                if (out != null) out.close();
            } catch (Exception e2) {
            }
            throw e;
        }
    }

    private static byte[] file2data (Context context, String fileName) throws Exception {

        int size;
        byte[] w = new byte[1024];
        InputStream in = null;
        ByteArrayOutputStream out = null;

        try {
            //ファイル入力ストリーム解放
            in = context.openFileInput(fileName);
            //バイト配列読み込み
            out = new ByteArrayOutputStream();

            while (true) {
                size = in.read();
                if (size <= 0) break;
                out.write(w, 0, size);
            }
            out.close();

            in.close();

            return out.toByteArray();

        } catch (Exception e) {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
            } catch (Exception e2) {
            }
            throw e;
        }
    }

}

package com.jsjlzj.wayne.ui.basis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jsjlzj.wayne.R;
import com.jsjlzj.wayne.ui.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class JsjlAgreementActivity extends AppCompatActivity implements View.OnClickListener {

    public static void go2this(Activity context, boolean isStore) {
        Intent intent = new Intent(context, JsjlAgreementActivity.class);
        intent.putExtra("isStore", isStore);
        context.startActivity(intent);
    }

    public static void go2this2(Activity context) {
        Intent intent = new Intent(context, JsjlAgreementActivity.class);
        intent.putExtra("isBack", true);
        context.startActivity(intent);
    }

    private boolean isStore, isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        isStore = getIntent().getBooleanExtra("isStore", true);
        isBack = getIntent().getBooleanExtra("isBack", false);

        textView = findViewById(R.id.text);
        findViewById(R.id.btnBack).setOnClickListener(this);
        findViewById(R.id.btnConfirm).setOnClickListener(this);
        initAssets();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnConfirm:
                if (!isBack)
                    MainActivity.go2this(this, isStore);
                else finish();
                break;
        }
    }

    private TextView textView;
    private StringBuffer stringBuffer = new StringBuffer();

    public void initAssets() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = getAssets().open("jsjlAgreement.txt");
                    String str = getString(inputStream);
                    String[] arr = str.split("\\s+");
                    for (int i = 0; i < arr.length; i++) {
                        stringBuffer.append(arr[i] + "\n");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(stringBuffer);
                        }
                    });
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }).start();
    }

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;

        try {
            inputStreamReader = new InputStreamReader(inputStream, "GBK");//"UTF-8"
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

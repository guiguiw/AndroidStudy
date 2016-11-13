package com.example.liwensheng.datasave;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by liWensheng on 2016/11/11.
 */

public class editActivity extends AppCompatActivity implements View.OnClickListener{

    private Button save, load, clr;
    private TextView textView;
    private String FileName = "wsndnmsl.txt";

    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        findView();
        bindButton();
    }

    private void findView() {
        save = (Button) findViewById(R.id.save);
        load = (Button) findViewById(R.id.load);
        clr = (Button) findViewById(R.id.clr);
        textView = (TextView) findViewById(R.id.editText);
    }

    private void bindButton() {
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        clr.setOnClickListener(this);
    }

    private void writeText() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(FileName, MODE_PRIVATE);
            String content = textView.getText().toString();
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            Log.e("TAG", "Fail to save file");
        }
    }

    private void readText() {
        try  {
            FileInputStream fileInputStream = openFileInput(FileName);
            String content = "";
            byte[] buff = new byte[1024];
            int hasRead = 0;

            while((hasRead = fileInputStream.read(buff)) > 0) {
                content += new String(buff, 0, hasRead);
            }
            fileInputStream.close();
            textView.setText(content);
        }catch (Exception e) {
            Log.e("TAG", "Fail to read file");
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                writeText();
                break;
            case R.id.load:
                readText();
                break;
            case R.id.clr:
                textView.setText("");
                break;
        }
    }
}

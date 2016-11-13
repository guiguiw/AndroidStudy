package com.example.liwensheng.datasave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by liWensheng on 2016/11/11.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView password, confirm;
    private Button ok, clear;

    private boolean flag;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        findView();
        setView();
        bindButton();

    }

    private void findView() {
        password = (TextView) findViewById(R.id.password);
        confirm = (TextView) findViewById(R.id.confirm);
        ok = (Button) findViewById(R.id.ok);
        clear = (Button) findViewById(R.id.clear);
        sharedPreferences = getSharedPreferences("Password", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    private void bindButton() {
        ok.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String pw1 = password.getText().toString();
        String pw2 = confirm.getText().toString();

        switch (view.getId()) {
            case R.id.ok:
                //judge is empty
                if (pw1.length() == 0) {
                    Toast.makeText(MainActivity.this, "Password cannot be empty.", Toast.LENGTH_SHORT).show();
                }
                else if (pw2.length() == 0) {

                }
                //judge when regist
                if (!pw1.equals(pw2) && !flag) {
                    Toast.makeText(MainActivity.this, "Password Mismatch.", Toast.LENGTH_SHORT).show();
                }
                else if (pw1.equals(pw2) && !flag){
                    editor.putString("password", pw1);
                    editor.putBoolean("isRegist", true);
                    editor.commit();
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, editActivity.class);
                    startActivity(intent);
                }
                //judge when log up
                else if (flag) {
                    String realPassword = sharedPreferences.getString("password", "");
                    if (realPassword.length() != 0) {
                        if (!pw1.equals(realPassword)) {
                            Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            startEditActivity();
                        }
                    }
                }
                break;
            case R.id.clear:
                password.setText("");
                confirm.setText("");
                break;
        }
    }

    private void register() {

    }

    private void setView() {
        flag =  sharedPreferences.getBoolean("isRegist", false);
        if (flag)
            confirm.setVisibility(View.GONE);
    }

    private void startEditActivity() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, editActivity.class);
        startActivity(intent);
    }
}

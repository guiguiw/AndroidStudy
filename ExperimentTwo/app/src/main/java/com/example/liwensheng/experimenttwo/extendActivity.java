package com.example.liwensheng.experimenttwo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by liWensheng on 16/10/2.
 */

public class extendActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extend_layout);

        Button loginButton = (Button) findViewById(R.id.login2);
        final TextInputLayout usernameText = (TextInputLayout) findViewById(R.id.more_username);
        final TextInputLayout passwordText = (TextInputLayout) findViewById(R.id.more_password);

        final EditText username = (EditText) findViewById(R.id.username2);
        final EditText password = (EditText) findViewById(R.id.password2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().toString().equals("Android")
                        && password.getText().toString().equals("123456")) {

                    usernameText.setErrorEnabled(false);
                    passwordText.setErrorEnabled(false);

                    Snackbar.make(v, "登陆成功", Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(extendActivity.this ,"Snackbar被选中了", Toast.LENGTH_SHORT).show();
                        }
                    }).setDuration(5000).show();
                }

                else {
                    if (username.getText().toString().length()!= 0 && password.getText().toString().length()!=0) {
                        usernameText.setErrorEnabled(false);
                        passwordText.setErrorEnabled(false);
                        Snackbar.make(v, "登陆失败", Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(extendActivity.this ,"Snackbar被选中了", Toast.LENGTH_SHORT).show();
                            }
                        }).setDuration(5000).show();
                    }
                    else {
                        if (username.getText().toString().length() == 0) {
                            usernameText.setErrorEnabled(true);
                            usernameText.setError("用户名不能为空");
                        }
                        else {
                            usernameText.setErrorEnabled(false);
                        }

                        if (password.getText().toString().length() == 0) {
                            passwordText.setErrorEnabled(true);
                            passwordText.setError("密码不能为空");
                        }
                        else {
                            passwordText.setErrorEnabled(false);
                        }
                    }
                }
            }
        });

        /*
        * RadioButton 选择项切换:选择项切换之后,弹出 Toast 信息“XX 身份被选中”
        * */
        final RadioGroup radioSelect = (RadioGroup) findViewById(R.id.radiogroup2);
        radioSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectButton = (RadioButton) findViewById(checkedId);

                Snackbar.make(group, selectButton.getText().toString() + "身份被选中", Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(extendActivity.this ,"Snackbar被选中了", Toast.LENGTH_SHORT).show();
                    }
                }).setDuration(5000).show();
            }
        });

        /*
        * 点击注册按钮
        * */
        Button buttonRegister = (Button) findViewById((R.id.register2));
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId =  radioSelect.getCheckedRadioButtonId();
                RadioButton checkedButton = (RadioButton) findViewById(checkedId);

                Snackbar.make(v, checkedButton.getText().toString() + "身份注册功能尚未开启", Snackbar.LENGTH_SHORT).setAction("按钮", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(extendActivity.this ,"Snackbar被选中了", Toast.LENGTH_SHORT).show();
                    }
                }).setDuration(5000).show();
            }
        });
    }
}

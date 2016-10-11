package com.example.liwensheng.experimentone;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by liWensheng on 16/9/22.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        /*
        * 点击登陆按钮
        * */
        Button buttonLogin = (Button) findViewById(R.id.login);
        final EditText username = (EditText) findViewById(R.id.username);
        final  EditText password = (EditText) findViewById(R.id.password);

        /*
        * 如果用户名为空,弹出 Toast 信息“用户名不能为空”; 如果密码为空,弹出 Toast 信息“密码不能为空”;
        * 如果用户名输入为“Android”,密码为“123456”,弹出如下对话框:
        * */
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText().toString())) {
                    Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty((password.getText().toString()))) {
                    Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("提示");

                    if (username.getText().toString().equals("Android") &&
                            password.getText().toString().equals("123456")) {
                        builder.setMessage("登陆成功");
                    }
                    else {
                        builder.setMessage("登陆失败");
                    }

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,
                                    "对话框\"取消\"按钮被选中" ,Toast.LENGTH_SHORT ).show();
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,
                                    "对话框\"确定\"按钮被选中" ,Toast.LENGTH_SHORT ).show();
                        }
                    });
                    builder.create();
                    builder.show();
                }
            }
        });

        /*
        * RadioButton 选择项切换:选择项切换之后,弹出 Toast 信息“XX 身份被选中”
        * */
        final RadioGroup radioSelect = (RadioGroup) findViewById(R.id.radiogroup1);
        radioSelect.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkButton = (RadioButton) findViewById(checkedId);

                Toast.makeText(MainActivity.this,
                        checkButton.getText().toString()+"身份被选中", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        * 点击注册按钮
        * */
        Button buttonRegister = (Button) findViewById((R.id.regiater));
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId =  radioSelect.getCheckedRadioButtonId();
                RadioButton checkedButton = (RadioButton) findViewById(checkedId);

                Toast.makeText(MainActivity.this,
                        checkedButton.getText().toString()+"身份注册功能尚未开启",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}

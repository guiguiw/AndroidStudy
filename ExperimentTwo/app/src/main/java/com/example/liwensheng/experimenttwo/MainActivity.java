package com.example.liwensheng.experimenttwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by liWensheng on 16/10/2.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Button basicButton = (Button) findViewById(R.id.basicButton);
        basicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, basicActivity.class);
                startActivity(intent);
            }
        });

        Button extendButton = (Button) findViewById(R.id.extendButton);
        extendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, extendActivity.class);
                startActivity(intent);
            }
        });
    }
}

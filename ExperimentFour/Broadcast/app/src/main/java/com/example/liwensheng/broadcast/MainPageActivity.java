package com.example.liwensheng.broadcast;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by liWensheng on 16/10/19.
 */

public class MainPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);



        Button staticPage = (Button) findViewById(R.id.staticRegist);
        staticPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this,StaticPageActivity.class);
                startActivity(intent);
            }
        });

        Button dynamicPage = (Button) findViewById(R.id.dynamicRegister);
        dynamicPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this,DynamicActivity.class);
                startActivity(intent);
            }
        });
    }
}

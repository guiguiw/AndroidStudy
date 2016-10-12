package com.example.liwensheng.experimentthree;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liWensheng on 16/10/11.
 */

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String Num = intent.getStringExtra("phone");
        String color = intent.getStringExtra("color");
        String home = intent.getStringExtra("home");

        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        relativeLayout.setBackgroundColor(Color.parseColor(color));


        TextView detailName = (TextView)findViewById(R.id.detailName);
        detailName.setText(name);

        TextView phoneNum = (TextView) findViewById(R.id.phoneNumber);
        phoneNum.setText(Num);

        TextView hometown = (TextView) findViewById(R.id.hometown);
        hometown.setText(home);


        final List<Map<String, Object>> data = new ArrayList<>();
        String[] Info = new  String[] {"编辑联系人", "分享联系人", "加入黑名单", "删除联系人"};
        for (int i = 0; i < Info.length; i++) {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("info", Info[i]);
            data.add(temp);
        }
        final ListView listview = (ListView)findViewById(R.id.moreList);
        final SimpleAdapter simpleadapter = new SimpleAdapter(this, data, R.layout.detail_item,
                new String[]{"info"}, new int[] {R.id.moreInfo});
        listview.setAdapter(simpleadapter);


        clickStar();
        clickBack(intent);
    }

    boolean flag = false;

    private void clickStar() {
        //点击星触发事件
        final ImageView imageView = (ImageView)findViewById(R.id.star);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    //如果之前为空心的
                    imageView.setImageDrawable(getResources().getDrawable(R.mipmap.full_star));
                    flag = !flag;
                }
                else  {
                    imageView.setImageDrawable(getResources().getDrawable(R.mipmap.empty_star));
                    flag = !flag;
                }
            }
        });
    }

    private void clickBack(final Intent intent) {
        ImageView imageView = (ImageView)findViewById(R.id.backButton);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.this.setResult(0, intent);
                DetailActivity.this.finish();
            }
        });
    }

}

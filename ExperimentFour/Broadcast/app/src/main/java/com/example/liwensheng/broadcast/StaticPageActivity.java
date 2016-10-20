package com.example.liwensheng.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liWensheng on 16/10/19.
 */

public class StaticPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_layout);

        //加载ListView
        final List<Map<String, Object>> data = new ArrayList<>();

        final String[] fruitName = {"Apple", "Banana", "Cherry", "Coco", "Kiwi",
                "Orange", "Pear", "Strawberry", "Watermelon"};

        final int[] fruitImg = {R.mipmap.apple, R.mipmap.banana, R.mipmap.cherry, R.mipmap.coco,
        R.mipmap.kiwi, R.mipmap.orange, R.mipmap.pear, R.mipmap.strawberry, R.mipmap.watermelon};

        for (int i = 0; i < fruitImg.length; i++) {
            Map<String, Object>temp = new HashMap<>();
            temp.put("fruitName", fruitName[i]);
            temp.put("fruitImg", fruitImg[i]);
            data.add(temp);
        }

        final ListView listView = (ListView) findViewById(R.id.staticList);
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.static_item,
                new String[] {"fruitName", "fruitImg"}, new int[] {R.id.FruitName, R.id.FruitImg});
        listView.setAdapter(simpleAdapter);

        final String STATICATION = "com.example.liwensheng.broadcast.staticreceiver";
        //发送广播
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) findViewById(R.id.FruitName);
                Intent intent = new Intent(STATICATION);
                Bundle bundle = new Bundle();
                bundle.putString("fruitName",fruitName[position]);
                bundle.putInt("fruitImg",fruitImg[position]);
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });
    }
}

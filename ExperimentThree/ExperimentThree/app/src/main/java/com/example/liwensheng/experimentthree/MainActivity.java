package com.example.liwensheng.experimentthree;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by liWensheng on 16/10/11.
 */

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        /*
         * 初始化主界面
         * */

        final List<Map<String, Object>> data = new ArrayList<>();
        final String[] arrayName = new String[] {"Aaron","Elvis","David", "Edwin", "Frank",
                "Joshua", "Ivan", "Mark", "Joseph", "Phoebe" };
        for (int i = 0; i < arrayName.length; i++) {
            Map<String, Object> temp = new LinkedHashMap<>();
            String firstName = arrayName[i].substring(0,1);
            temp.put("name", arrayName[i]);
            temp.put("first", firstName);
            data.add(temp);
        }
        final ListView listview = (ListView)findViewById(R.id.contentList);
        final SimpleAdapter simpleadapter = new SimpleAdapter(this, data, R.layout.main_item,
                new String[]{"first", "name"}, new int[] {R.id.firstName, R.id.name});
        listview.setAdapter(simpleadapter);


        /*
        * 点击listview事件处理
        * */
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //单击事件处理
                String name = data.get(position).get("name").toString();
                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("phone", getPhoneNumber(name));
                intent.putExtra("color", getColor(name));
                intent.putExtra("home", getHomeTown(name));
                intent.setClass(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //长按事件处理
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("删除联系人");
                builder.setMessage("确定删除联系人" + data.get(position).get("name").toString() + "?");

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);
                        simpleadapter.notifyDataSetChanged();
                    }
                });
                builder.create();
                builder.show();
                return true;
            }
        });
    }

    private String getPhoneNumber(String name) {
        Map<String, Object>phone = new LinkedHashMap<>();
        phone.put("Aaron", "17715523654");
        phone.put("Elvis", "18825653224");
        phone.put("David", "15052116654");
        phone.put("Edwin", "18854211875");
        phone.put("Frank", "13955188541");
        phone.put("Joshua", "13621574410");
        phone.put("Ivan", "15684122771");
        phone.put("Mark", "17765213579");
        phone.put("Joseph", "13315466578");
        phone.put("Phoebe", "17895466428");

        return phone.get(name).toString();
    }

    private String getColor(String name) {
        Map<String, Object>phone = new LinkedHashMap<>();
        phone.put("Aaron", "#BB4C3B");
        phone.put("Elvis", "#c48d30");
        phone.put("David", "#4469b0");
        phone.put("Edwin", "#20A17B");
        phone.put("Frank", "#BB4C3B");
        phone.put("Joshua", "#c48d30");
        phone.put("Ivan", "#4469b0");
        phone.put("Mark", "#20A17B");
        phone.put("Joseph", "#BB4C3B");
        phone.put("Phoebe", "#c48d30");

        return phone.get(name).toString();
    }

    private  String getHomeTown(String name) {
        Map<String, Object>phone = new LinkedHashMap<>();
        phone.put("Aaron", "江苏苏州电信");
        phone.put("Elvis", "广东揭阳移动");
        phone.put("David", "江苏无锡移动");
        phone.put("Edwin", "山东青岛移动");
        phone.put("Frank", "安徽合肥移动");
        phone.put("Joshua", "#江苏苏州移动");
        phone.put("Ivan", "山东烟台联通");
        phone.put("Mark", "广东珠海电信");
        phone.put("Joseph", "河北石家庄电信");
        phone.put("Phoebe", "山东东营移动");

        return phone.get(name).toString();
    }
}

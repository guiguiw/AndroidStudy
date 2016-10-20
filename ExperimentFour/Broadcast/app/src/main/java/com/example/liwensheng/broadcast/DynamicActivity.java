package com.example.liwensheng.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by liWensheng on 16/10/20.
 */

public class DynamicActivity extends AppCompatActivity {
    private static final  String DYNAMICTION = "com.example.liwensheng.broadcast.dynamic";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_layout);

        final EditText editText = (EditText) findViewById(R.id.dynamicText);
        final Button register = (Button)findViewById(R.id.register);
        final Button send = (Button)findViewById(R.id.send);



        final IntentFilter intentFilter = new IntentFilter();
        final DynamicReceiver dynamicReceiver = new DynamicReceiver();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (register.getText().toString().equals("Register Broadcast")) {
                    intentFilter.addAction(DYNAMICTION);
                    registerReceiver(dynamicReceiver, intentFilter);
                    register.setText("Unregister Broadcast");
                }
                else {
                    unregisterReceiver(dynamicReceiver);
                    register.setText("Register Broadcast");
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DYNAMICTION);
                Bundle bundle = new Bundle();
                bundle.putString("mes", editText.getText().toString());
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });
    }
}

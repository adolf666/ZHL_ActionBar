package com.adolf.zhl_actionbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getCusActionBar().addRightButton("编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"编辑",Toast.LENGTH_SHORT).show();
            }
        });

        getCusActionBar().addRightButton("删除", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"删除",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

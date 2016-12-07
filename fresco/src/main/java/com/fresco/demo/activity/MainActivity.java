package com.fresco.demo.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fresco.demo.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView img1, img2, img3, img4;
    private Button btn1, btn2, btn3, btn4;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "QQ:305413135，欢迎大家交流学习！", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        list = new ArrayList<>();
        list.add("http://static.happiyi.com/party/BeJjabw3r7fm");
        list.add("http://static.happiyi.com/party/eNmdwmN2zJ8j");
        list.add("http://static.happiyi.com/party/NiNGw2KcdfN3");
        list.add("http://static.happiyi.com/party/mh48NYFYpcFH");

        img1 = (SimpleDraweeView) findViewById(R.id.img1);
        img2 = (SimpleDraweeView) findViewById(R.id.img2);
        img3 = (SimpleDraweeView) findViewById(R.id.img3);
        img4 = (SimpleDraweeView) findViewById(R.id.img4);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        ViewGroup.LayoutParams para1 = img1.getLayoutParams();
        para1.width = wm.getDefaultDisplay().getWidth() * 1 / 2;
        para1.height = para1.width - 24 * (int) getResources().getDimension(R.dimen.dp);
        img1.setLayoutParams(para1);
        ViewGroup.LayoutParams para2 = img2.getLayoutParams();
        para2.width = wm.getDefaultDisplay().getWidth() * 1 / 2;
        para2.height = para2.width - 24 * (int) getResources().getDimension(R.dimen.dp);
        img2.setLayoutParams(para2);
        img3.setLayoutParams(para1);

//        fresco:roundAsCircle="true"
//        fresco:roundingBorderColor="#FF0000"
//        fresco:roundingBorderWidth="3dp"

        img4.setLayoutParams(para2);

//        RoundingParams roundingParams = RoundingParams.asCircle();
//        roundingParams.setBorder(android.R.color.holo_red_dark, 3 * (int) getResources().getDimension(R.dimen.dp));
//        roundingParams.setRoundAsCircle(true);
//        roundingParams.setBorderColor(android.R.color.holo_red_dark);
//        roundingParams.setBorderWidth(3 * (int) getResources().getDimension(R.dimen.dp));
//        img4.getHierarchy().setRoundingParams(roundingParams);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Uri uri1 = Uri.parse(list.get(0));
                img1.setImageURI(uri1);
                break;
            case R.id.btn2:
                Uri uri2 = Uri.parse(list.get(1));
                img2.setImageURI(uri2);
                break;
            case R.id.btn3:
                Uri uri3 = Uri.parse(list.get(2));
                img3.setImageURI(uri3);
                break;
            case R.id.btn4:
                Uri uri4 = Uri.parse("res://com.fresco.demo/" + R.mipmap.ic_launcher);
                img4.setImageURI(uri4);
                break;
        }
    }
}

package com.example.admin.myfinalproject;

/**
 * Created by admin on 2016/6/6.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.audiofx.BassBoost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    public static Activity a;

    Button bt_up;
    Button bt_up_left;
    Button bt_up_right;
    Button bt_down;
    Button bt_down_left;
    Button bt_down_right;

    Button bt_work;
    Button bt_limit;
    Button bt_people;
    Button bt_school;
    Button bt_service;

    Button bt_light;

    Button bt_commit;

    SeekBar seekBar;

    Button textView;

    String progress = "1";
    String position = Setting.NULL;
    String light = Setting.NULL;
    String info = Setting.NULL;

    private ArrayList<String> infos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        a = this;

    }


    private void initView() {
        bt_up = (Button) findViewById(R.id.bt_up);
        bt_up_left = (Button) findViewById(R.id.bt_up_left);
        bt_up_right = (Button) findViewById(R.id.bt_up_right);
        bt_down = (Button) findViewById(R.id.bt_down);
        bt_down_left = (Button) findViewById(R.id.bt_down_left);
        bt_down_right = (Button) findViewById(R.id.bt_down_right);

        bt_work = (Button) findViewById(R.id.bt_work);
        bt_limit = (Button) findViewById(R.id.bt_limit);
        bt_people = (Button) findViewById(R.id.bt_people);
        bt_school = (Button) findViewById(R.id.bt_school);
        bt_service = (Button) findViewById(R.id.bt_service);

        bt_light = (Button) findViewById(R.id.bt_light);

        textView = (Button) findViewById(R.id.textView);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        bt_commit = (Button) findViewById(R.id.bt_commit);

        bt_up.setOnClickListener(this);
        bt_up_left.setOnClickListener(this);
        bt_up_right.setOnClickListener(this);
        bt_down.setOnClickListener(this);
        bt_down_left.setOnClickListener(this);
        bt_down_right.setOnClickListener(this);
        bt_work.setOnClickListener(this);
        bt_limit.setOnClickListener(this);
        bt_people.setOnClickListener(this);
        bt_school.setOnClickListener(this);
        bt_service.setOnClickListener(this);
        bt_light.setOnClickListener(this);
        bt_commit.setOnClickListener(this);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(position + ":" + (20 - progress) + ":" + info + ":" + light);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        textView.setText(Setting.NULL + ":20:" + Setting.NULL + ":" + Setting.NULL);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_up:
                initPositionButton();
                if (Setting.UP.equals(position)) {
                    position = Setting.NULL;
                    v.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    v.setBackgroundColor(Color.parseColor("#7e7e7e"));
                    position = Setting.UP;
                }
                break;
            case R.id.bt_up_left:
                initPositionButton();
                if (Setting.UPLEFT.equals(position)) {
                    position = Setting.NULL;
                    v.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    v.setBackgroundColor(Color.parseColor("#7e7e7e"));
                    position = Setting.UPLEFT;
                }
                break;
            case R.id.bt_up_right:
                initPositionButton();
                if (Setting.UPRIGHT.equals(position)) {
                    position = Setting.NULL;
                    v.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    v.setBackgroundColor(Color.parseColor("#7e7e7e"));
                    position = Setting.UPRIGHT;
                }
                break;
            case R.id.bt_down:
                initPositionButton();
                if (Setting.DOWN.equals(position)) {
                    position = Setting.NULL;
                    v.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    v.setBackgroundColor(Color.parseColor("#7e7e7e"));
                    position = Setting.DOWN;
                }
                break;
            case R.id.bt_down_left:
                initPositionButton();
                if (Setting.DOWNLEFT.equals(position)) {
                    position = Setting.NULL;
                    v.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    v.setBackgroundColor(Color.parseColor("#7e7e7e"));
                    position = Setting.DOWNLEFT;
                }
                break;
            case R.id.bt_down_right:
                initPositionButton();
                if (Setting.DOWNRIGHT.equals(position)) {
                    position = Setting.NULL;
                    v.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    v.setBackgroundColor(Color.parseColor("#7e7e7e"));
                    position = Setting.DOWNRIGHT;
                }
                break;

            case R.id.bt_light:
                if (Setting.LIGHT.equals(light)) {
                    light = Setting.NULL;
                    v.setBackgroundColor(Color.parseColor("#f0f0f0"));
                } else {
                    v.setBackgroundColor(Color.parseColor("#7e7e7e"));
                    light = Setting.LIGHT;
                }
                break;

            case R.id.bt_work:
                if (infos.contains(Setting.WORK)) {
                    v.setBackgroundColor(Color.parseColor("#f0f0f0"));
                    infos.remove(Setting.WORK);
                } else {
                    v.setBackgroundColor(Color.parseColor("#7e7e7e"));
                    infos.add(Setting.WORK);
                }
                break;
            case R.id.bt_limit:
                addInfo(v, Setting.LIMIT);
                break;
            case R.id.bt_people:
                addInfo(v, Setting.PEOPLE);
                break;
            case R.id.bt_school:
                addInfo(v, Setting.SCHOOL);
                break;
            case R.id.bt_service:
                addInfo(v, Setting.SERVICE);
                break;

            case R.id.bt_commit:
                Intent intent = new Intent();
                progress = (seekBar.getProgress() == 0 ? 1 : seekBar.getProgress()) + "";
                intent.putExtra("command", position + ":" + progress + ":" + info + ":" + light);
                intent.putStringArrayListExtra("infos", infos);
                intent.setClass(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }

        progress = (seekBar.getProgress() == 0 ? 20 : (20 - seekBar.getProgress())) + "";
        textView.setText(position + ":" + progress + ":" + info + ":" + light);
    }


    private void initPositionButton() {
        bt_up.setBackgroundResource(R.drawable.app_bt_selector);
        bt_up_left.setBackgroundResource(R.drawable.app_bt_selector);
        bt_up_right.setBackgroundResource(R.drawable.app_bt_selector);
        bt_down.setBackgroundResource(R.drawable.app_bt_selector);
        bt_down_left.setBackgroundResource(R.drawable.app_bt_selector);
        bt_down_right.setBackgroundResource(R.drawable.app_bt_selector);
    }


    private void initInfoButton() {
        bt_work.setBackgroundResource(R.drawable.app_bt_selector);
        bt_limit.setBackgroundResource(R.drawable.app_bt_selector);
        bt_people.setBackgroundResource(R.drawable.app_bt_selector);
        bt_school.setBackgroundResource(R.drawable.app_bt_selector);
        bt_service.setBackgroundResource(R.drawable.app_bt_selector);
    }


    public void addInfo(View view, String Info) {
        if (infos.contains(Info)) {
            view.setBackgroundColor(Color.parseColor("#f0f0f0"));
            infos.remove(Info);
        } else {
            view.setBackgroundColor(Color.parseColor("#7e7e7e"));
            infos.add(Info);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_ui) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_control) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        progress = (seekBar.getProgress() == 0 ? 1 : seekBar.getProgress()) + "";
        intent.putExtra("command", position + ":" + progress + ":" + info + ":" + light);
        intent.putStringArrayListExtra("infos", infos);
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);


    }


}

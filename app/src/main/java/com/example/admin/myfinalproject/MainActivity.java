package com.example.admin.myfinalproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView iv_up;
    ImageView iv_up_left;
    ImageView iv_up_right;
    ImageView iv_down;
    ImageView iv_down_left;
    ImageView iv_down_right;

    ImageView iv_light_red;
    ImageView iv_light_yellow;
    ImageView iv_light_green;

    ImageView iv_work_bg;
    ImageView iv_limit_bg;
    ImageView iv_people_bg;
    ImageView iv_school_bg;
    ImageView iv_service_bg;

    TextView tv_time;
    TextView tv_advice;

    String progress;
    String position = Setting.NULL;
    String info = Setting.NULL;
    String light = Setting.NULL;

    private boolean isLimit = false;
    private ArrayList<String> infos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initData() {
        Intent intent = getIntent();
        try {
            if (intent != null) {
                String command = intent.getStringExtra("command");
                ArrayList<String> infos = intent.getStringArrayListExtra("infos");
                if (infos != null) {
                    this.infos = infos;
                    for (String i : infos){
                        Log.e("acc", i);
                        if (Setting.LIMIT.equals(i)){
                            isLimit = true;
                        }
                    }
                }
                Log.e("info", command + "");
                String split[] = command.split(":");
                position = split[0];
                progress = split[1];
                info = split[2];
                light = split[3];
            }
        } catch (Exception e){}
    }


    private void init() {

        if (Setting.UP.equals(position)) {
            doFlash(iv_up);
        } else if (Setting.UPLEFT.equals(position)) {
            doFlash(iv_up_left);
        } else if (Setting.UPRIGHT.equals(position)) {
            doFlash(iv_up_right);
        } else if (Setting.DOWN.equals(position)) {
            doFlash(iv_down);
        } else if (Setting.DOWNLEFT.equals(position)) {
            doFlash(iv_down_left);
        } else if (Setting.DOWNRIGHT.equals(position)) {
            doFlash(iv_down_right);
        }

        for (int i = 0; i < infos.size(); i++){
            String info = infos.get(i);
            if (Setting.WORK.equals(info)) {
                setLight(iv_work_bg);
            } else if (Setting.LIMIT.equals(info)) {
                setLight(iv_limit_bg);
            } else if (Setting.PEOPLE.equals(info)) {
                setLight(iv_people_bg);
            } else if (Setting.SCHOOL.equals(info)) {
                setLight(iv_school_bg);
            } else if (Setting.SERVICE.equals(info)) {
                setLight(iv_service_bg);
            }
        }

        if (Setting.LIGHT.equals(light)) {
            doLight();
        } else {
            if (isLimit) {
                tv_advice.setText(String.format("建议速度<=%dkm/h", 20));
            } else {
                tv_advice.setText(String.format("建议速度<=%dkm/h", 40));
            }
        }
    }

    int i = 0;


    private void doLight() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (i < 20) {
                                iv_light_red.setVisibility(View.INVISIBLE);
                                iv_light_yellow.setVisibility(View.VISIBLE);
                                iv_light_green.setVisibility(View.VISIBLE);

                                tv_time.setText(String.format("00:%02d", 20 - i));

                                if (i < 10) {
                                    tv_advice.setText(String.format("建议速度<=%dkm/h", 20));
                                } else {
                                    tv_advice.setText(String.format("建议速度<=%dkm/h", 10));
                                }
                            } else if (i < 30) {
                                iv_light_red.setVisibility(View.VISIBLE);
                                iv_light_yellow.setVisibility(View.VISIBLE);
                                iv_light_green.setVisibility(View.INVISIBLE);

                                tv_time.setText(String.format("00:%02d", 30-i));

                                if (isLimit) {
                                    tv_advice.setText(String.format("建议速度<=%dkm/h", 20));
                                } else {
                                    tv_advice.setText(String.format("建议速度<=%dkm/h", 40));
                                }
                            } else if (i < 33) {
                                iv_light_red.setVisibility(View.VISIBLE);
                                iv_light_yellow.setVisibility(View.INVISIBLE);
                                iv_light_green.setVisibility(View.VISIBLE);

                                tv_time.setText(String.format("00:%02d", 33 - i));

                                tv_advice.setText(String.format("建议速度<=%dkm/h", 10));
                            }

                            if (i == 32) {
                                i = -1;
                            }
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    i++;
                }
            }
        }.start();
    }




    private void setLight(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }



    private void doFlash(final View view) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            if (view.getVisibility() == View.VISIBLE) {
                                view.setVisibility(View.INVISIBLE);
                            } else {
                                view.setVisibility(View.VISIBLE);
                            }
                            playSound();
                        }
                    });

                    try {
                        Thread.sleep((21 - Integer.valueOf(progress)) * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    private void initView() {
        iv_up = (ImageView) findViewById(R.id.iv_up);
        iv_up_left = (ImageView) findViewById(R.id.iv_up_left);
        iv_up_right = (ImageView) findViewById(R.id.iv_up_right);
        iv_down = (ImageView) findViewById(R.id.iv_down);
        iv_down_left = (ImageView) findViewById(R.id.iv_down_left);
        iv_down_right = (ImageView) findViewById(R.id.iv_down_right);

        iv_light_red = (ImageView) findViewById(R.id.iv_light_red);
        iv_light_yellow = (ImageView) findViewById(R.id.iv_light_yellow);
        iv_light_green = (ImageView) findViewById(R.id.iv_light_green);

        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_advice = (TextView) findViewById(R.id.tv_advice);

        iv_work_bg = (ImageView) findViewById(R.id.iv_work_bg);
        iv_limit_bg = (ImageView) findViewById(R.id.iv_limit_bg);
        iv_people_bg = (ImageView) findViewById(R.id.iv_people_bg);
        iv_school_bg = (ImageView) findViewById(R.id.iv_school_bg);
        iv_service_bg = (ImageView) findViewById(R.id.iv_service_bg);

    }



    private void playSound() {
        try {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.di_sound);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            mp.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (id == R.id.action_ui) {
            return true;
        } else if (id == R.id.action_control) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), SettingActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        try {
            SettingActivity.a.finish();
            finish();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

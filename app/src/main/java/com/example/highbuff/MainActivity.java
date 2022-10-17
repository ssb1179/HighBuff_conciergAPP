package com.example.highbuff;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageButton webBtn;
    private int blind_num = 0;
    private int info_num = 0;
    private int tv_num = 0;
    private int audio_num = 0;
    // 시간
    private TextView dateText;
    private TextView timeText;
    private BackgroundThread backgroundThread;

    public String getTime() {
        Long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String getDate = dateFormat.format(date);
        return getDate;
    }

    public String getDate() {
        Long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        String getDate = dateFormat.format(date);
        return getDate;
    }

    //밑으로 시계 설정
    private final MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity != null) {

                activity.handleMessage(msg);
            }
        }
    }

    private void handleMessage(Message msg) {
        dateText.setText(DateFormat.getDateTimeInstance().format(new Date()));
    }

    public class BackgroundThread extends Thread {
        boolean running = false;

        public void setRunning(boolean b) {
            running = b;
        }

        @Override
        public void run() {
            super.run();

            while (running) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.sendMessage(mHandler.obtainMessage());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        backgroundThread = new BackgroundThread();
        backgroundThread.setRunning(true);
        backgroundThread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        boolean retry = true;
        backgroundThread.setRunning(false);

        while (retry) {
            try {
                backgroundThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //밑으로 버튼 설정
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateText = findViewById(R.id.date);


//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        clear("ligthFile");
        clear("airconFile");

        //findViewById는 레이아웃에서 버튼을 찾아오는 부분 파라미터로 버튼 id를 넣어준다
        //Intent 초기화 할때 파라미터로 getApplicationContext(), 넘길 클래스.class
        //를 해준다

        //하이버프 버튼 클릭시 웹뷰로 전환
        webBtn = findViewById(R.id.btn_highbuff);
        webBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                startActivity(intent);
            }
        });

        ToggleButton living_Btn = findViewById(R.id.ligth_living_btn);
        ToggleButton bed_Btn = findViewById(R.id.ligth_bed_btn);
        ToggleButton room_Btn = findViewById(R.id.ligth_room_btn);
        ToggleButton kitchen_Btn = findViewById(R.id.ligth_kitchen_btn);

        //전등 버튼 클릭시 전등조절로 전환
        ImageView ligth_image = findViewById(R.id.ligth_image);
        ligth_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ligth_Activity.class);
                startActivity(intent);
            }
        });

        //에어컨 버튼 클릭시 냉난방조절로 전환
        ImageView aircon_image = findViewById(R.id.aircon_image);
        aircon_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), aircon_Activity.class);
                startActivity(intent);
            }
        });

        //차 버튼 클릭시 차량출고 확인으로 전환
        ImageView car_image = findViewById(R.id.car_icon);
        car_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), car_Activity.class);
                startActivity(intent);
            }
        });


        //블라인드 버튼 클릭시 전환
        ImageView blind_image = findViewById(R.id.blind_image);
        ImageView blind_reversal_image = findViewById(R.id.blind_reversal_image);
        View.OnClickListener blindListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blind_num == 0) {
                    blind_image.setVisibility(View.INVISIBLE);
                    blind_reversal_image.setVisibility(View.VISIBLE);
                    blind_num = 1;
                    Toast.makeText(getApplicationContext(), "Open the Blind", Toast.LENGTH_SHORT).show();

                } else if (blind_num == 1) {
                    blind_image.setVisibility(View.VISIBLE);
                    blind_reversal_image.setVisibility(View.INVISIBLE);
                    blind_num = 0;
                    Toast.makeText(getApplicationContext(), "Close the Blind", Toast.LENGTH_SHORT).show();
                }
            }
        };

        blind_image.setOnClickListener(blindListener);
        blind_reversal_image.setOnClickListener(blindListener);

        //인포 버튼 클릭시 전환
        ImageView info_image = findViewById(R.id.info_image);
        ImageView info_reversal_image = findViewById(R.id.info_reversal_image);
        View.OnClickListener infoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info_num == 0) {
                    info_image.setVisibility(View.INVISIBLE);
                    info_reversal_image.setVisibility(View.VISIBLE);
                    info_num = 1;
                    Toast.makeText(getApplicationContext(), "Connecting Information", Toast.LENGTH_SHORT).show();

                } else if (info_num == 1) {
                    info_image.setVisibility(View.VISIBLE);
                    info_reversal_image.setVisibility(View.INVISIBLE);
                    info_num = 0;
                    Toast.makeText(getApplicationContext(), "Disconnect Information", Toast.LENGTH_SHORT).show();
                }
            }
        };

        info_image.setOnClickListener(infoListener);
        info_reversal_image.setOnClickListener(infoListener);


        //tv버튼 클릭시 전환
        ImageView tv_image = findViewById(R.id.tv_image);
        ImageView tv_reversal_image = findViewById(R.id.tv_reversal_image);
        View.OnClickListener tvListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_num == 0) {
                    tv_image.setVisibility(View.INVISIBLE);
                    tv_reversal_image.setVisibility(View.VISIBLE);
                    tv_num = 1;
                    Toast.makeText(getApplicationContext(), "Turn On The TV", Toast.LENGTH_SHORT).show();

                } else if (tv_num == 1) {
                    tv_image.setVisibility(View.VISIBLE);
                    tv_reversal_image.setVisibility(View.INVISIBLE);
                    tv_num = 0;
                    Toast.makeText(getApplicationContext(), "Turn Off The TV", Toast.LENGTH_SHORT).show();
                }
            }
        };

        tv_image.setOnClickListener(tvListener);
        tv_reversal_image.setOnClickListener(tvListener);

        //오디오 버튼
        ImageView audio_image = findViewById(R.id.audio_image);
        ImageView audio_reversal_image = findViewById(R.id.audio_reversal_image);
        View.OnClickListener audioListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audio_num == 0) {
                    audio_image.setVisibility(View.INVISIBLE);
                    audio_reversal_image.setVisibility(View.VISIBLE);
                    audio_num = 1;
                    Toast.makeText(getApplicationContext(), "Turn On The Audio", Toast.LENGTH_SHORT).show();

                } else if (audio_num == 1) {
                    audio_image.setVisibility(View.VISIBLE);
                    audio_reversal_image.setVisibility(View.INVISIBLE);
                    audio_num = 0;
                    Toast.makeText(getApplicationContext(), "Turn Off The Audio", Toast.LENGTH_SHORT).show();
                }
            }
        };

        audio_image.setOnClickListener(audioListener);
        audio_reversal_image.setOnClickListener(audioListener);

    }


    public void clear(String key) {
        SharedPreferences prefs = getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.clear();
        edit.commit();

    }


}
//가로모드 고정은 androidManlfest.xml파일에서
//Activity 태그안에 android:screenOrientation="landscape" 를 추가해준다

//이미지버튼의 크기조정은 Activity_main.xml 에서
//application태그에 android:scaleType="centerInside" 를 추가해준다

//이미지 버튼의 배경 투명은 Activity_main.xml 에서
//application태그에 android:background="@android:color/transparent" 를 추가해준다

//이미지 버튼이니 버튼객체가 아닌 이미지버튼 객체로 만들어야한다
//이때 R.id.~~ 는 xml에서 설정한 버튼이름이다
//이 밑으로 이미지버튼에 url 연결하기
//        ImageButton img = (ImageButton) findViewById(R.id.btn_highbuff);
//        img = (ImageButton) findViewById(R.id.btn_highbuff);
//
//        img.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
//                startActivity(intent);
//
//            }
//        });

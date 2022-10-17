package com.example.highbuff;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ligth_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ligth_view);
        //클래스가 실행되면 light_view.xml로 이동된다
        //setContentView() 메소드를 통해 이동하게 됨



        ToggleButton living_Btn = findViewById(R.id.ligth_living_btn);
        ToggleButton bed_Btn = findViewById(R.id.ligth_bed_btn);
        ToggleButton room_Btn = findViewById(R.id.ligth_room_btn);
        ToggleButton kitchen_Btn = findViewById(R.id.ligth_kitchen_btn);

        Button all_On_Btn = findViewById(R.id.ligth_allOn_btn);
        Button all_Off_Btn = findViewById(R.id.ligth_allOff_btn);


        //불러오기
        living_Btn.setChecked(updateState("거실"));
        bed_Btn.setChecked(updateState("안방"));
        room_Btn.setChecked(updateState("방"));
        kitchen_Btn.setChecked(updateState("부엌"));

        //저장하기
        CompoundButton.OnCheckedChangeListener checkSaveListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView == living_Btn) {
                    btnStateSave("거실", isChecked);

                } else if (buttonView == bed_Btn) {
                    btnStateSave("안방", isChecked);

                } else if (buttonView == room_Btn) {
                    btnStateSave("방", isChecked);

                } else if (buttonView == kitchen_Btn) {
                    btnStateSave("부엌", isChecked);

                }
            }
        };

        living_Btn.setOnCheckedChangeListener(checkSaveListener);
        bed_Btn.setOnCheckedChangeListener(checkSaveListener);
        room_Btn.setOnCheckedChangeListener(checkSaveListener);
        kitchen_Btn.setOnCheckedChangeListener(checkSaveListener);


//        living_Btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                btnStateSave("거실", isChecked);
//
//            }
//        });

        //전체 소등,점등 리스너
        View.OnClickListener allListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.ligth_allOn_btn) {
                    living_Btn.setChecked(true);
                    bed_Btn.setChecked(true);
                    room_Btn.setChecked(true);
                    kitchen_Btn.setChecked(true);

                } else if (v.getId() == R.id.ligth_allOff_btn) {
                    living_Btn.setChecked(false);
                    bed_Btn.setChecked(false);
                    room_Btn.setChecked(false);
                    kitchen_Btn.setChecked(false);
                }
            }
        };

        all_On_Btn.setOnClickListener(allListener);
        all_Off_Btn.setOnClickListener(allListener);

    }

    //버튼 상태 저장 메소드
    private void btnStateSave(String key, boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences("ligthFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value); // 키와 값을 boolean으로 저장
        editor.apply(); // 실제로 저장
        editor.commit();
    }

    //버튼 상태 불러오는 메소드
    private boolean updateState(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("ligthFile", MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }



}

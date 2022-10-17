package com.example.highbuff;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class aircon_Activity extends AppCompatActivity {
    private TextView living_temperature;
    private TextView bed_temperature;
    private TextView room_temperature;
    private TextView kitchen_temperature;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aircon_view);

        ToggleButton living_Btn = findViewById(R.id.aircon_living_btn);
        ToggleButton bed_Btn = findViewById(R.id.aircon_bed_btn);
        ToggleButton room_Btn = findViewById(R.id.aircon_room_btn);
        ToggleButton kitchen_Btn = findViewById(R.id.aircon_kitchen_btn);


        Button all_On_Btn = findViewById(R.id.aircon_allOn_btn);
        Button all_Off_Btn = findViewById(R.id.aircon_allOff_btn);

        //전체켜기, 끄기 리스너
        View.OnClickListener allListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.aircon_allOn_btn) {
                    living_Btn.setChecked(true);
                    bed_Btn.setChecked(true);
                    room_Btn.setChecked(true);
                    kitchen_Btn.setChecked(true);

                } else if (v.getId() == R.id.aircon_allOff_btn) {
                    living_Btn.setChecked(false);
                    bed_Btn.setChecked(false);
                    room_Btn.setChecked(false);
                    kitchen_Btn.setChecked(false);


                }
            }
        };

        all_On_Btn.setOnClickListener(allListener);
        all_Off_Btn.setOnClickListener(allListener);


        living_temperature = findViewById(R.id.aircon_living_temperature);
        bed_temperature = findViewById(R.id.aircon_bed_temperature);
        room_temperature = findViewById(R.id.aircon_room_temperature);
        kitchen_temperature = findViewById(R.id.aricon_kitchen_temperature);

        TextView living_UP = findViewById(R.id.aircon_living_UP);
        TextView living_DOWN = findViewById(R.id.aircon_living_DOWN);
        TextView bed_UP = findViewById(R.id.aircon_bed_UP);
        TextView bed_DOWN = findViewById(R.id.aircon_bed_DOWN);
        TextView room_UP = findViewById(R.id.aircon_room_UP);
        TextView room_DOWN = findViewById(R.id.aircon_room_DOWN);
        TextView kitchen_UP = findViewById(R.id.aircon_kitchen_UP);
        TextView kitchen_DOWN = findViewById(R.id.aircon_kitchen_DOWN);

        if (living_Btn.isChecked()) {
            
        }

        //온도 올리고 내리는 리스너
        View.OnClickListener livingListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTemperature[] = ((String) living_temperature.getText()).split("º");
                int intTemperature = Integer.valueOf(strTemperature[0]);

                if (living_Btn.isChecked()) {
                    if (v.getId() == R.id.aircon_living_UP) {
                        intTemperature++;
                        living_temperature.setText(intTemperature + "º");

                    } else if (v.getId() == R.id.aircon_living_DOWN) {
                        intTemperature--;
                        living_temperature.setText(intTemperature + "º");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "거실 에어컨을 켜주세요", Toast.LENGTH_SHORT).show();
                }
            }
        };

        View.OnClickListener bedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTemperature[] = ((String) bed_temperature.getText()).split("º");
                int intTemperature = Integer.valueOf(strTemperature[0]);

                if (bed_Btn.isChecked()) {
                    if (v.getId() == R.id.aircon_bed_UP) {
                        intTemperature++;
                        bed_temperature.setText(intTemperature + "º");

                    } else if (v.getId() == R.id.aircon_bed_DOWN) {
                        intTemperature--;
                        bed_temperature.setText(intTemperature + "º");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "안방 에어컨을 켜주세요", Toast.LENGTH_SHORT).show();
                }

            }
        };

        View.OnClickListener roomListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTemperature[] = ((String) room_temperature.getText()).split("º");
                int intTemperature = Integer.valueOf(strTemperature[0]);

                if (room_Btn.isChecked()) {
                    if (v.getId() == R.id.aircon_room_UP) {
                        intTemperature++;
                        room_temperature.setText(intTemperature + "º");

                    } else if (v.getId() == R.id.aircon_room_DOWN) {
                        intTemperature--;
                        room_temperature.setText(intTemperature + "º");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "방 에어컨을 켜주세요", Toast.LENGTH_SHORT).show();
                }
            }
        };

        View.OnClickListener kitchenListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTemperature[] = ((String) kitchen_temperature.getText()).split("º");
                int intTemperature = Integer.valueOf(strTemperature[0]);

                if (kitchen_Btn.isChecked()) {
                    if (v.getId() == R.id.aircon_kitchen_UP) {
                        intTemperature++;
                        kitchen_temperature.setText(intTemperature + "º");

                    } else if (v.getId() == R.id.aircon_kitchen_DOWN) {
                        intTemperature--;
                        kitchen_temperature.setText(intTemperature + "º");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "부엌 에어컨을 켜주세요", Toast.LENGTH_SHORT).show();
                }
            }
        };

        living_UP.setOnClickListener(livingListener);
        living_DOWN.setOnClickListener(livingListener);

        bed_UP.setOnClickListener(bedListener);
        bed_DOWN.setOnClickListener(bedListener);

        room_UP.setOnClickListener(roomListener);
        room_DOWN.setOnClickListener(roomListener);

        kitchen_UP.setOnClickListener(kitchenListener);
        kitchen_DOWN.setOnClickListener(kitchenListener);


        //버튼 상태 불러오기
        living_Btn.setChecked(updateState("거실"));
        bed_Btn.setChecked(updateState("안방"));
        room_Btn.setChecked(updateState("방"));
        kitchen_Btn.setChecked(updateState("부엌"));

        //버튼상태 저장하기
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

        //온도상태 불러오기
        living_temperature.setText(textUpdateState("living온도"));
        bed_temperature.setText(textUpdateState("bed온도"));
        room_temperature.setText(textUpdateState("room온도"));
        kitchen_temperature.setText(textUpdateState("kitchen온도"));


    }

    @Override
    protected void onStop() {
        super.onStop();
        textStateSave("living온도", living_temperature);
        textStateSave("bed온도", bed_temperature);
        textStateSave("room온도", room_temperature);
        textStateSave("kitchen온도", kitchen_temperature);
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        textStateSave("living온도", living_temperature);
//    }

    //버튼 상태 저장 메소드
    private void btnStateSave(String key, boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences("airconFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value); // 키와 값을 boolean으로 저장
        editor.apply(); // 실제로 저장
        editor.commit();
    }

    //버튼 상태 불러오는 메소드
    private boolean updateState(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("airconFile", MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    //온도 저장 메소드
    private void textStateSave(String key, TextView value) {
        SharedPreferences sharedPreferences = getSharedPreferences("airconFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String str = value.getText().toString();
        editor.putString(key, str);
        editor.apply(); // 실제로 저장
        editor.commit();
    }

    //온도 불러오는 메소드
    private String textUpdateState(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("airconFile", MODE_PRIVATE);
        return sharedPreferences.getString(key, "17º");
    }
}

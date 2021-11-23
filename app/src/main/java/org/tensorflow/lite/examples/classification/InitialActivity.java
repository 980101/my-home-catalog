package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        // 바로 가기
        Button btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToMain = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentToMain);
            }
        });

        // 맞춤 가구 둘러보기
        Button btn_custom = findViewById(R.id.btn_custom);
        btn_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToCamera = new Intent(getApplicationContext(), ClassifierActivity.class);
                startActivity(intentToCamera);
            }
        });
    }
}
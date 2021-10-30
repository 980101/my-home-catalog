package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 리사이클러뷰에 표시할 데이터 리스트 생성
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format("TEXT %d", i));
        }

        // 리사이클러뷰에 LinearLayoutManager 객체 지정
        RecyclerView recyclerView = findViewById(R.id.list_funiture);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 리사이클러뷰에 ItemAdapter 객체 지정
        ItemAdapter adapter = new ItemAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
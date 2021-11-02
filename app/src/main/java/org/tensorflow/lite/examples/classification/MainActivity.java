package org.tensorflow.lite.examples.classification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<ItemData> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();

        // DB: 데이터베이스 연동
        database = FirebaseDatabase.getInstance();

        // DB : 데이터베이스 테이블 연결
        databaseReference = database.getReference("item");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            // 데이터를 받아오는 경우 호출
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                    arrayList.add(itemData);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            // 데이터를 읽어오지 못한 경우 호출
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("MainActivity", String.valueOf(error.toException()));
            }
        });

        // 리사이클러뷰에 LinearLayoutManager 객체 지정
        recyclerView = findViewById(R.id.list_funiture);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 리사이클러뷰에 ItemData 객체 지정
        adapter = new ItemAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
    }
}
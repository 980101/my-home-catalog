package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomActivity extends AppCompatActivity implements CustomAdapter.OnListItemSelectedInterface{

    private ArrayList<CustomData> arrayList;
    private CustomAdapter customAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private String furniture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        recyclerView = findViewById(R.id.rv_custom);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        arrayList = new ArrayList<>();

        // 버튼 데이터 설정
        arrayList.add(new CustomData(R.drawable.ic_furnitures, "All"));
        arrayList.add(new CustomData(R.drawable.ic_chair, "Chair"));
        arrayList.add(new CustomData(R.drawable.ic_bed, "Bed"));
        arrayList.add(new CustomData(R.drawable.ic_sofa, "Sofa"));
        arrayList.add(new CustomData(R.drawable.ic_dresser, "Dresser"));
        arrayList.add(new CustomData(R.drawable.ic_table, "Table"));

        customAdapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(customAdapter);

        // '다음' 버튼 이벤트
        Button btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToCamera = new Intent(getApplicationContext(), ClassifierActivity.class);
                intentToCamera.putExtra("type", furniture);
                startActivity(intentToCamera);
            }
        });
    }

    @Override
    public void onItemSelected(View v, int position) {
        CustomAdapter.CustomViewHolder viewHolder = (CustomAdapter.CustomViewHolder)recyclerView.findViewHolderForAdapterPosition(position);
        furniture = viewHolder.tv_name.getText().toString();
    }
}
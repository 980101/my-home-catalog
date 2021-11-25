package org.tensorflow.lite.examples.classification;

import static java.sql.DriverManager.println;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<ItemData> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference styleList;
    private View pressedStyleBtn;
    private View pressedTypeBtn;
    private String pickedStyle, pickedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Intent 데이터 받아오기
        pickedStyle = getIntent().getStringExtra("style") != null ? getIntent().getStringExtra("style") : "all";
        pickedType = getIntent().getStringExtra("type");

        // 가구명 배열
        String types[] = {"bed", "chair", "dresser", "sofa", "table"};
        // 스타일 배열
        String styles[] = {"natural", "modern", "classic", "industrial", "zen"};

        arrayList = new ArrayList<>();

        // DB: 데이터베이스 연동
        database = FirebaseDatabase.getInstance();

        // DB : 데이터베이스 테이블 연결
        databaseReference = database.getReference("all");

        // 타이틀 설정
        TextView tv_title = findViewById(R.id.tv_title);
        chgTitle(tv_title, pickedStyle);

//        System.out.println(getIntent().getStringExtra("type"));

        // 스타일 별 데이터 출력 - 초기화면
        // 여기에 intent 값 체크해서 변경해주기
        if (pickedStyle != null && pickedStyle != "all") {
            Arrays.fill(styles, null);
            styles[0] = pickedStyle;
        }

        for (int i = 0; i < styles.length; i++) {
            if (styles[i] == null) {
                break;
            } else {
                databaseReference.child(styles[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                    // 데이터 받아오는 경우 호출
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (int j = 0; j < types.length; j++) {
                            for (DataSnapshot postSnapshot: snapshot.child(types[j]).getChildren()) {
                                ItemData itemData = postSnapshot.getValue(ItemData.class);
                                arrayList.add(itemData);
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }

                    // 데이터 읽어오지 못한 경우 호출
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("MainActivity", String.valueOf(error.toException()));
                    }
                });
            }
        }

        // 이벤트 리스너 : 스타일 버튼

        // 버튼 색상 체크 변수
        pressedStyleBtn = null;

        View.OnClickListener onClickListnerByStyle = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pressedStyleBtn == null) {
                    pressedStyleBtn = v;
                } else if (pressedStyleBtn != v) {
                    pressedStyleBtn.setBackground(getDrawable(R.drawable.btn_style_unclicked));
                    pressedStyleBtn = v;
                }

                v.setBackground(getDrawable(R.drawable.btn_style_clicked));

                Arrays.fill(types, null);
                types[0] = "bed";
                types[1] = "chair";
                types[2] = "sofa";
                types[3] = "dresser";
                types[4] = "table";

                Arrays.fill(styles, null);

                switch (v.getId()) {
                    case R.id.btn_style_natural:
                        styles[0] = "natural";
                        pickedStyle = "natural";
                        break;
                    case R.id.btn_style_modern:
                        styles[0] = "modern";
                        pickedStyle = "modern";
                        break;
                    case R.id.btn_style_classic:
                        styles[0] = "classic";
                        pickedStyle = "classic";
                        break;
                    case R.id.btn_style_industrial:
                        styles[0] = "industrial";
                        pickedStyle = "industrial";
                        break;
                    case R.id.btn_style_zen:
                        styles[0] = "zen";
                        pickedStyle = "zen";
                        break;
                    case R.id.btn_style_all:
                        styles[0] = "natural";
                        styles[1] = "modern";
                        styles[2] = "classic";
                        styles[3] = "industrial";
                        styles[4] = "zen";
                        pickedStyle = "all";
                        break;
                }

                // pickedStyle이 이전과 다를 때만 실행하기로 수정
                chgTitle(tv_title, pickedStyle);

                arrayList.clear();

                for (int i = 0; i < styles.length; i++) {
                    // 유효한 스타일이라면,
                    if (styles[i] == null) {
                        break;
                    } else {
                        styleList = databaseReference.child(styles[i]);

                        for (int j = 0; j < types.length; j++) {
                            styleList.child(types[j]).addListenerForSingleValueEvent(new ValueEventListener() {

                                // 데이터를 받아오는 경우 호출
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                        ItemData itemData = postSnapshot.getValue(ItemData.class);
                                        arrayList.add(itemData);
                                    }
                                    adapter.notifyDataSetChanged();
                                }

                                // 데이터를 받지 못한 경우 호출
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.e("MainActivity", String.valueOf(error.toException()));
                                }
                            });
                        }
                    }
                }
            }
        };

        Button btn_style_all = findViewById(R.id.btn_style_all);
        btn_style_all.setOnClickListener(onClickListnerByStyle);
        Button btn_style_natural = findViewById(R.id.btn_style_natural);
        btn_style_natural.setOnClickListener(onClickListnerByStyle);
        Button btn_style_modern = findViewById(R.id.btn_style_modern);
        btn_style_modern.setOnClickListener(onClickListnerByStyle);
        Button btn_style_classic = findViewById(R.id.btn_style_classic);
        btn_style_classic.setOnClickListener(onClickListnerByStyle);
        Button btn_style_industrial = findViewById(R.id.btn_style_industrial);
        btn_style_industrial.setOnClickListener(onClickListnerByStyle);
        Button btn_style_zen = findViewById(R.id.btn_style_zen);
        btn_style_zen.setOnClickListener(onClickListnerByStyle);

        // 초기 스타일의 버튼 배경 설정
        switch (pickedStyle) {
            case "all" :
                btn_style_all.setBackground(getDrawable(R.drawable.btn_style_clicked));
                pressedStyleBtn = btn_style_all;
                break;
            case "natural" :
                btn_style_natural.setBackground(getDrawable(R.drawable.btn_style_clicked));
                pressedStyleBtn = btn_style_natural;
                break;
            case "modern" :
                btn_style_modern.setBackground(getDrawable(R.drawable.btn_style_clicked));
                pressedStyleBtn = btn_style_modern;
                break;
            case "classic" :
                btn_style_classic.setBackground(getDrawable(R.drawable.btn_style_clicked));
                pressedStyleBtn = btn_style_classic;
                break;
            case "industrial" :
                btn_style_industrial.setBackground(getDrawable(R.drawable.btn_style_clicked));
                pressedStyleBtn = btn_style_industrial;
                break;
            case "zen" :
                btn_style_zen.setBackground(getDrawable(R.drawable.btn_style_clicked));
                pressedStyleBtn = btn_style_zen;
                break;
        }

        // 이벤트 리스너 : 가구 버튼
        pressedTypeBtn = null;

        View.OnClickListener onClickListenerByType = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pressedTypeBtn == null) {
                    pressedTypeBtn = v;
                } else if (pressedTypeBtn != v) {
                    pressedTypeBtn.setBackground(getDrawable(R.drawable.btn_style_unclicked));
                    pressedTypeBtn = v;
                }

                pressedTypeBtn.setBackground(getDrawable(R.drawable.btn_style_clicked));

                Arrays.fill(types, null);

                switch (v.getId()) {
                    case R.id.btn_type_chair:
                        types[0] = "chair";
                        break;
                    case R.id.btn_type_bed:
                        types[0] = "bed";
                        break;
                    case R.id.btn_type_sofa:
                        types[0] = "sofa";
                        break;
                    case R.id.btn_type_dresser:
                        types[0] = "dresser";
                        break;
                    case R.id.btn_type_table:
                        types[0] = "table";
                        break;
                    case R.id.btn_type_all:
                        types[0] = "chair";
                        types[1] = "bed";
                        types[2] = "sofa";
                        types[3] = "dresser";
                        types[4] = "table";
                        break;
                }

                arrayList.clear();

                for (int i = 0; i < styles.length; i++) {
                    // 유효한 스타일이라면,
                    if (styles[i] == null) {
                        break;
                    } else {
                        styleList = databaseReference.child(styles[i]);

                        for (int j = 0; j < types.length; j++) {
                            if (types[j] == null) {
                                break;
                            } else {
                                styleList.child(types[j]).addListenerForSingleValueEvent(new ValueEventListener() {

                                    // 데이터를 받아오는 경우 호출
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                            ItemData itemData = postSnapshot.getValue(ItemData.class);
                                            arrayList.add(itemData);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }

                                    // 데이터를 받지 못한 경우 호출
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Log.e("MainActivity", String.valueOf(error.toException()));
                                    }
                                });
                            }
                        }
                    }
                }
            }
        };

        Button btn_type_all = findViewById(R.id.btn_type_all);
        btn_type_all.setOnClickListener(onClickListenerByType);
        Button btn_type_chair = findViewById(R.id.btn_type_chair);
        btn_type_chair.setOnClickListener(onClickListenerByType);
        Button btn_type_bed = findViewById(R.id.btn_type_bed);
        btn_type_bed.setOnClickListener(onClickListenerByType);
        Button btn_type_sofa = findViewById(R.id.btn_type_sofa);
        btn_type_sofa.setOnClickListener(onClickListenerByType);
        Button btn_type_dresser = findViewById(R.id.btn_type_dresser);
        btn_type_dresser.setOnClickListener(onClickListenerByType);
        Button btn_type_table = findViewById(R.id.btn_type_table);
        btn_type_table.setOnClickListener(onClickListenerByType);

        // 초기 가구의 버튼 배경 설정
        btn_type_all.setBackground(getDrawable(R.drawable.btn_style_clicked));
        pressedTypeBtn = btn_type_all;

        // 리사이클러뷰에 LinearLayoutManager 객체 지정
        recyclerView = findViewById(R.id.list_furniture);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 리사이클러뷰에 ItemData 객체 지정
        adapter = new ItemAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
    }

    // 타이틀 변경 메서드
    public void chgTitle(TextView title, String style) {
        switch (style) {
            case "all" :
                title.setText("모든 스타일");
                break;
            case "natural" :
                title.setText("내추럴");
                break;
            case "modern" :
                title.setText("모던");
                break;
            case "classic" :
                title.setText("클래식");
                break;
            case "industrial" :
                title.setText("인더스트리얼");
                break;
            case "zen" :
                title.setText("젠");
                break;
        }
    }
}
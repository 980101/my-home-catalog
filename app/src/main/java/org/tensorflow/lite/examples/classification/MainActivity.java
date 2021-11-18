package org.tensorflow.lite.examples.classification;

import static java.sql.DriverManager.println;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<ItemData> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference styleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 타이틀
        TextView tv_title = findViewById(R.id.tv_title);
        Intent intent = getIntent();
        tv_title.setText(intent.getStringExtra("style"));

        // 가구명 배열
        final String names[] = {"bed", "chair", "dresser", "sofa", "table"};
        // 스타일 배열
        final String styles[] = {"natural", "modern", "classic", "industrial", "zen"};

        arrayList = new ArrayList<>();

        // DB: 데이터베이스 연동
        database = FirebaseDatabase.getInstance();

        // DB : 데이터베이스 테이블 연결
        databaseReference = database.getReference("all");

        // 스타일 별 데이터 출력 - 초기화면
        for (int i = 0; i < styles.length; i++) {
            databaseReference.child(styles[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                // 데이터 받아오는 경우 호출
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (int j = 0; j < names.length; j++) {
                        for (DataSnapshot postSnapshot: snapshot.child(names[j]).getChildren()) {
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

        // 스타일 버튼 : all
        final Button btn_style_all = findViewById(R.id.btn_style_all);
        btn_style_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrays.fill(styles, null);
                styles[0] = "natural";
                styles[1] = "modern";
                styles[2] = "classic";
                styles[3] = "industrial";
                styles[4] = "zen";

                for (int i = 0; i < styles.length; i++) {
                    databaseReference.child(styles[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                        // 데이터 받아오는 경우 호출
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (int j = 0; j < names.length; j++) {
                                for (DataSnapshot postSnapshot: snapshot.child(names[j]).getChildren()) {
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
        });

        // 스타일 버튼 : natural
        final Button btn_style_natural = findViewById(R.id.btn_style_natural);
        btn_style_natural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrays.fill(styles, null);
                styles[0] = "natural";

                arrayList.clear();

                for (int i = 0; i < styles.length; i++) {
                    if (styles[i] == null) {
                        break;
                    } else {
                        databaseReference.child(styles[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                            // 데이터 받아오는 경우 호출
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (int j = 0; j < names.length; j++) {
                                    for (DataSnapshot postSnapshot: snapshot.child(names[j]).getChildren()) {
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
            }
        });

        // 스타일 버튼 : modern
        final Button btn_style_modern = findViewById(R.id.btn_style_modern);
        btn_style_modern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrays.fill(styles, null);
                styles[0] = "modern";

                arrayList.clear();

                for (int i = 0; i < styles.length; i++) {
                    if (styles[i] == null) {
                        break;
                    } else {
                        databaseReference.child(styles[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                            // 데이터 받아오는 경우 호출
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (int j = 0; j < names.length; j++) {
                                    for (DataSnapshot postSnapshot: snapshot.child(names[j]).getChildren()) {
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
            }
        });

        // 스타일 버튼 : classic
        final Button btn_style_classic = findViewById(R.id.btn_style_classic);
        btn_style_classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrays.fill(styles, null);
                styles[0] = "classic";

                arrayList.clear();

                for (int i = 0; i < styles.length; i++) {
                    if (styles[i] == null) {
                        break;
                    } else {
                        databaseReference.child(styles[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                            // 데이터 받아오는 경우 호출
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (int j = 0; j < names.length; j++) {
                                    for (DataSnapshot postSnapshot: snapshot.child(names[j]).getChildren()) {
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
            }
        });

        // 스타일 버튼 : industrial
        final Button btn_style_industrial = findViewById(R.id.btn_style_industrial);
        btn_style_industrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrays.fill(styles, null);
                styles[0] = "classic";

                arrayList.clear();

                for (int i = 0; i < styles.length; i++) {
                    if (styles[i] == null) {
                        break;
                    } else {
                        databaseReference.child(styles[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                            // 데이터 받아오는 경우 호출
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (int j = 0; j < names.length; j++) {
                                    for (DataSnapshot postSnapshot: snapshot.child(names[j]).getChildren()) {
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
            }
        });

        // 스타일 버튼 : zen
        final Button btn_style_zen = findViewById(R.id.btn_style_zen);
        btn_style_zen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrays.fill(styles, null);
                styles[0] = "zen";

                arrayList.clear();

                for (int i = 0; i < styles.length; i++) {
                    if (styles[i] == null) {
                        break;
                    } else {
                        databaseReference.child(styles[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                            // 데이터 받아오는 경우 호출
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (int j = 0; j < names.length; j++) {
                                    for (DataSnapshot postSnapshot: snapshot.child(names[j]).getChildren()) {
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
            }
        });

        // 가구 버튼 : all
        final Button btn_type_all = findViewById(R.id.btn_type_all);
        btn_type_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                // 모든 스타일에 한 해 진행
                for (int i = 0; i < styles.length; i++) {
                    // 유효한 스타일이라면,
                    if (styles[i] == null) {
                        break;
                    } else {
                        styleList = databaseReference.child(styles[i]);

                        for (int j = 0; j < names.length; j++) {
                            styleList.child(names[j]).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                        ItemData itemData = postSnapshot.getValue(ItemData.class);
                                        arrayList.add(itemData);
                                    }
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.e("MainActivity", String.valueOf(error.toException()));
                                }
                            });
                        }
                    }
                }
            }
        });

        // 가구 버튼 : chair
        final Button btn_type_chair = findViewById(R.id.btn_type_chair);
        btn_type_chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                // 모든 스타일에 한 해 진행
                for (int i = 0; i < styles.length; i++) {
                    // 유효한 스타일이라면,
                    if (styles[i] == null) {
                        break;
                    } else {
                        styleList = databaseReference.child(styles[i]);

                        styleList.child("chair").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                                    arrayList.add(itemData);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e("MainActivity", String.valueOf(error.toException()));
                            }
                        });
                    }
                }
            }
        });

        // 가구 버튼 : bed
        final Button btn_type_bed = findViewById(R.id.btn_type_bed);
        btn_type_bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                // 모든 스타일에 한 해 진행
                for (int i = 0; i < styles.length; i++) {
                    // 유효한 스타일이라면,
                    if (styles[i] == null) {
                        break;
                    } else {
                        styleList = databaseReference.child(styles[i]);

                        styleList.child("bed").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                                    arrayList.add(itemData);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e("MainActivity", String.valueOf(error.toException()));
                            }
                        });
                    }
                }
            }
        });

        // 가구 버튼 : sofa
        final Button btn_type_sofa = findViewById(R.id.btn_type_sofa);
        btn_type_sofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                // 모든 스타일에 한 해 진행
                for (int i = 0; i < styles.length; i++) {
                    // 유효한 스타일이라면,
                    if (styles[i] == null) {
                        break;
                    } else {
                        styleList = databaseReference.child(styles[i]);

                        styleList.child("sofa").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                                    arrayList.add(itemData);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e("MainActivity", String.valueOf(error.toException()));
                            }
                        });
                    }
                }
            }
        });

        // 가구 버튼 : dresser
        final Button btn_type_dresser = findViewById(R.id.btn_type_dresser);
        btn_type_dresser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                // 모든 스타일에 한 해 진행
                for (int i = 0; i < styles.length; i++) {
                    // 유효한 스타일이라면,
                    if (styles[i] == null) {
                        break;
                    } else {
                        styleList = databaseReference.child(styles[i]);

                        styleList.child("dresser").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                                    arrayList.add(itemData);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e("MainActivity", String.valueOf(error.toException()));
                            }
                        });
                    }
                }
            }
        });

        // 가구 버튼 : table
        final Button btn_type_table = findViewById(R.id.btn_type_table);
        btn_type_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                // 모든 스타일에 한 해 진행
                for (int i = 0; i < styles.length; i++) {
                    // 유효한 스타일이라면,
                    if (styles[i] == null) {
                        break;
                    } else {
                        styleList = databaseReference.child(styles[i]);

                        styleList.child("table").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                                    arrayList.add(itemData);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e("MainActivity", String.valueOf(error.toException()));
                            }
                        });
                    }
                }
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
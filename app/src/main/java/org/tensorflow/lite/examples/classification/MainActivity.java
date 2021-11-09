package org.tensorflow.lite.examples.classification;

import static java.sql.DriverManager.println;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

        // 가구명 배열
        String names[] = {"bed", "chair", "dresser", "sofa", "table"};

        arrayList = new ArrayList<>();

        // DB: 데이터베이스 연동
        database = FirebaseDatabase.getInstance();

        // DB : 데이터베이스 테이블 연결
        databaseReference = database.getReference("all");

        for (int i = 0; i < names.length; i++) {
            databaseReference.child(names[i]).addListenerForSingleValueEvent(new ValueEventListener() {

                // 데이터를 받아오는 경우 호출
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
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
        }

        // 가구 버튼 : chair
        final Button btn_type_chair = findViewById(R.id.btn_type_chair);
        btn_type_chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("chair").addListenerForSingleValueEvent(new ValueEventListener() {

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
            }
        });

        // 가구 버튼 : bed
        final Button btn_type_bed = findViewById(R.id.btn_type_bed);
        btn_type_bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("bed").addListenerForSingleValueEvent(new ValueEventListener() {

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
            }
        });

        // 가구 버튼 : sofa
        final Button btn_type_sofa = findViewById(R.id.btn_type_sofa);
        btn_type_sofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("sofa").addListenerForSingleValueEvent(new ValueEventListener() {

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
            }
        });

        // 가구 버튼 : dresser
        final Button btn_type_dresser = findViewById(R.id.btn_type_dresser);
        btn_type_dresser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("dresser").addListenerForSingleValueEvent(new ValueEventListener() {

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
            }
        });

        // 가구 버튼 : table
        final Button btn_type_table = findViewById(R.id.btn_type_table);
        btn_type_table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("table").addListenerForSingleValueEvent(new ValueEventListener() {

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
            }
        });

        // 가구 버튼 : all
        final Button btn_type_all = findViewById(R.id.btn_type_all);
        btn_type_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                for (int i = 0; i < names.length; i++) {
                    databaseReference.child(names[i]).addListenerForSingleValueEvent(new ValueEventListener() {

                        // 데이터를 받아오는 경우 호출
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
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
                }
            }
        });

        // 스타일 버튼 : 모두
        final Button btn_style_all = findViewById(R.id.btn_style_all);
        btn_style_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                for (int i = 0; i < names.length; i++) {
                    databaseReference.child(names[i]).addListenerForSingleValueEvent(new ValueEventListener() {

                        // 데이터를 받아오는 경우 호출
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
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
                }
            }
        });

        // 스타일 버튼 : 내추럴
        final Button btn_style_natural = findViewById(R.id.btn_style_natural);
        btn_style_natural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                for (int i = 0; i < names.length; i++) {
                    databaseReference.child(names[i]).addListenerForSingleValueEvent(new ValueEventListener() {

                        // 데이터를 받아오는 경우 호출
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                if (postSnapshot.child("style").getValue().equals("natural")) {
                                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                                    arrayList.add(itemData);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        // 데이터를 읽어오지 못한 경우 호출
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("MainActivity", String.valueOf(error.toException()));
                        }
                    });
                }
            }
        });

        // 스타일 버튼 : 모던
        final Button btn_style_modern = findViewById(R.id.btn_style_modern);
        btn_style_modern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                for (int i = 0; i < names.length; i++) {
                    databaseReference.child(names[i]).addListenerForSingleValueEvent(new ValueEventListener() {

                        // 데이터를 받아오는 경우 호출
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                if (postSnapshot.child("style").getValue().equals("modern")) {
                                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                                    arrayList.add(itemData);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        // 데이터를 읽어오지 못한 경우 호출
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("MainActivity", String.valueOf(error.toException()));
                        }
                    });
                }
            }
        });

        // 스타일 버튼 : 클래식
        final Button btn_style_classic = findViewById(R.id.btn_style_classic);
        btn_style_classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                for (int i = 0; i < names.length; i++) {
                    databaseReference.child(names[i]).addListenerForSingleValueEvent(new ValueEventListener() {

                        // 데이터를 받아오는 경우 호출
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                if (postSnapshot.child("style").getValue().equals("classic")) {
                                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                                    arrayList.add(itemData);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        // 데이터를 읽어오지 못한 경우 호출
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("MainActivity", String.valueOf(error.toException()));
                        }
                    });
                }
            }
        });

        // 스타일 버튼 : 젠
        final Button btn_style_zen = findViewById(R.id.btn_style_zen);
        btn_style_zen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                for (int i = 0; i < names.length; i++) {
                    databaseReference.child(names[i]).addListenerForSingleValueEvent(new ValueEventListener() {

                        // 데이터를 받아오는 경우 호출
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                if (postSnapshot.child("style").getValue().equals("zen")) {
                                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                                    arrayList.add(itemData);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        // 데이터를 읽어오지 못한 경우 호출
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("MainActivity", String.valueOf(error.toException()));
                        }
                    });
                }
            }
        });

        // 스타일 버튼 : 인더스트리얼
        final Button btn_style_industrial = findViewById(R.id.btn_style_industrial);
        btn_style_industrial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();

                for (int i = 0; i < names.length; i++) {
                    databaseReference.child(names[i]).addListenerForSingleValueEvent(new ValueEventListener() {

                        // 데이터를 받아오는 경우 호출
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                                if (postSnapshot.child("style").getValue().equals("industrial")) {
                                    ItemData itemData = postSnapshot.getValue(ItemData.class);
                                    arrayList.add(itemData);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        // 데이터를 읽어오지 못한 경우 호출
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("MainActivity", String.valueOf(error.toException()));
                        }
                    });
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
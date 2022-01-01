package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ItemData> arrayList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        arrayList = new ArrayList<>();

        // json 파일의 데이터 가져오기
        MyJson myJson = new MyJson();
        String data = myJson.getData(this);

        try {
            // 데이터의 형변환 (String -> jsonArray)
            JSONArray dataArray = new JSONArray(data);

            // 각 요소로 분리 ( jsonArray -> jsonObject )
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject item = dataArray.getJSONObject(i);

                String image = item.getString("Image");
                String name = item.getString("Name");
                String price = item.getString("Price");
                String link = item.getString("Link");

                ItemData itemData = new ItemData(image, name, price, link);
                arrayList.add(itemData);
            }

        } catch (JSONException e) {
            Log.e("TAG", "Error in Comparing: " + e.getLocalizedMessage());
        }

        recyclerView = findViewById(R.id.list_favorites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FavoritesAdapter(arrayList, this);
        recyclerView.setAdapter(adapter);
    }
}
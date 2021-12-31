package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // json 파일의 데이터 가져오기
        MyJson myJson = new MyJson();
        String data = myJson.getData(this);

        try {
            // 데이터의 형변환 (String -> jsonArray)
            JSONArray dataArray = new JSONArray(data);

            // 각 요소로 분리 ( jsonArray -> jsonObject )
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject item = dataArray.getJSONObject(i);
                System.out.println(item.getString("Image"));
                System.out.println(item.getString("Name"));
                System.out.println(item.getString("Price"));
                System.out.println(item.getString("Link"));
            }

        } catch (JSONException e) {
            Log.e("TAG", "Error in Comparing: " + e.getLocalizedMessage());
        }
    }
}
package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;


public class DetailActivity extends AppCompatActivity {

    private String image, name, price, link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Intent 데이터 받아오기
        image = getIntent().getStringExtra("image");
        name = getIntent().getStringExtra("name");
        price = getIntent().getStringExtra("price");
        link = getIntent().getStringExtra("link");

        // 이미지 설정
        ImageView iv_image = findViewById(R.id.iv_detail);
        Glide.with(iv_image).load(image).into(iv_image);

        // 이름 설정
        TextView tv_name = findViewById(R.id.tv_detail_name);
        tv_name.setText(name);

        // 가격 설정
        TextView tv_price = findViewById(R.id.tv_detail_price);
        tv_price.setText(price);
    }

    // '구매하기' 버튼의 이벤트 함수
    public void goToBuy (View view) {
        goToUrl(link);
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    // '저장' 버튼의 이벤트 함수
    public void saveItem (View view) {

        // 저장할 데이터 설정
        JSONObject jsonObject =  new JSONObject();

        try {
            jsonObject.put("Name", name);
            jsonObject.put("Price", price);
        } catch (JSONException e) {
            Log.e("TAG", "Error: " + e.getLocalizedMessage());
        }

        MyJson item = new MyJson();
        item.saveData(this, jsonObject);
    }
}
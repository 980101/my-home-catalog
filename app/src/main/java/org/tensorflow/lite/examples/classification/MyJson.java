package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MyJson {

    static String fileName = "savedItem.json";

    public static void saveData(Context context, JSONObject mJsonResponse) {
        try {
            // Json Array 생성
            JSONArray jsonArray = new JSONArray();

            // 메소드 getData()에서 기존 데이터 가져오기
            String prev = getData(context);
            JSONArray prevArray = null;

            // 기존 데이터를 Json Array 형태로 변형
            try {
                prevArray = new JSONArray(prev);
            } catch (JSONException e) {
                Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            }

            // 저장될 데이터를 jsonArray에 할당
            jsonArray = prevArray;
            jsonArray.put(mJsonResponse);

            // jsonArray의 내용을 Json File(savedItem.json)에 저장
            FileWriter file = new FileWriter(context.getFilesDir().getPath() + "/" + fileName);
            file.write(jsonArray.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }

    public static String getData(Context context) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String response = stringBuilder.toString();
            return response;
        } catch (IOException e) {
            Log.e("TAG", "Error in Reading: " + e.getLocalizedMessage());
            return null;
        }
    }
}

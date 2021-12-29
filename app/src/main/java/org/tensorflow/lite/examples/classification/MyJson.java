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
        boolean isExist = false;

        // Json Array 생성
        JSONArray jsonArray = new JSONArray();

        // 기존 데이터를 가져오기
        String prev = getData(context);

        if (prev == null) {
            jsonArray.put(mJsonResponse);
        } else {
            JSONArray prevArray = null;

            try {
                // 기존 데이터를 Json Array 형태로 변형
                prevArray = new JSONArray(prev);

                // 중복 체크
                String name = mJsonResponse.getString("Name");  // 저장할 데이터의 Name 값

                for (int i = 0; i < prevArray.length(); i++) {
                    JSONObject object = prevArray.getJSONObject(i);
                    String prevName = object.getString("Name"); // 저장된 데이터의 Name 값

                    if (prevName.equals(name)) isExist = true;
                }

            } catch (JSONException e) {
                Log.e("TAG", "Error in Comparing: " + e.getLocalizedMessage());
            }

            jsonArray = prevArray;  // 기존 데이터

            if (!isExist) jsonArray.put(mJsonResponse); // 새로운 데이터
        }

        // jsonArray의 내용을 Json File(savedItem.json)에 쓰기
        try {
            FileWriter fw = new FileWriter(context.getFilesDir().getPath() + "/" + fileName);
            fw.write(jsonArray.toString());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            Log.e("TAG", "Error in Writing: " + e.getLocalizedMessage());
        }
    }

    public static String getData(Context context) {
        try {
            File file = new File(context.getFilesDir(), fileName);
            if (!file.exists()) {
                // 파일이 없는 경우 처리
                return null;
            }
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

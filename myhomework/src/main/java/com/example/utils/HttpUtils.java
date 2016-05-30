package com.example.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/5/4.
 */
public class HttpUtils {
    private static String TAG = "HttpUtils";

    public static String getJsonWithPath(String path) {

        StringBuffer sb = new StringBuffer();
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader br = null;

        try {
            url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }
            } else {
                Log.e(TAG, " NET IS ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(br);
            conn.disconnect();
        }
        Log.i("TAG", "---" + sb.toString());
        return sb.toString();
    }

    public static Bitmap getImageWithPath(String path)
    {
        Bitmap map=null;
        URL url = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return BitmapFactory.decodeStream(conn.getInputStream());
            } else {
                Log.e(TAG, " NET IS ERROR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return map;
    }


    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package com.example.utils;

import android.os.Handler;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * get请求方式
 * Created by Administrator on 2016/5/23.
 */
public class HttpUtil {
    /***
     * 开启线程池管理线程
     */
    private static ExecutorService executorService;
    private  static Handler mHandler = new Handler();
    static {
        //创建一个定长的线程池
         executorService = Executors.newFixedThreadPool(4);
    }
    public static  void requestGet(final String path){

        //创建线程池，管理线程
        executorService.execute(new Runnable() {
            @Override
            public void run() {
               String result = httpGet(path);
                mHandler.post(new Runnable() {//在主线程中执行这个run方法
                    @Override
                    public void run() {
                        //运行在主线程中的run方法

                    }
                });
            }
        });
    }

    /***
     * get 请求方式
     * @param path
     * @return
     */
    private static String httpGet(String path) {
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            if (200 == conn.getResponseCode()) {
                inputStream = conn.getInputStream();
                int len = 0;
                byte b[] = new byte[1024];
                StringBuffer buffer = new StringBuffer();
                while ((len = inputStream.read(b)) != -1) {
                    buffer.append(new String(b, 0, len));
                }
                return buffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(inputStream);
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }


    private static void close(Closeable close) {
        if (close != null) {
            try {
                close.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

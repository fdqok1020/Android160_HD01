package com.example.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownLoadService extends Service {
    private MyHandler myHandler;
    private Messenger messenger;
    //通知栏
    private NotificationManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        myHandler = new MyHandler();
        messenger = new Messenger(myHandler);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        //messenger.getBinder()返回给客户端一个binder对象
        return messenger.getBinder();
    }


    public  class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Messenger mes = msg.replyTo;
            switch (msg.what){
                case 1:
                    final  String strURL = (String) msg.obj;
                    progressNotification(strURL,mes);
                    break;
            }
        }
    }

    private void progressNotification(final String strURL, final Messenger messenger) {
        final Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("下载管理");


        //开始下载
        new Thread(new Runnable() {
            int progress = 0;
            InputStream is = null;
            HttpURLConnection conn = null;
            ByteArrayOutputStream baos = null;
            @Override
            public void run() {
                //向主线程发消息表示开始下载
                Message msg2 = new Message();
                msg2.what = 2;
                try {
                    messenger.send(msg2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }


                try {
                    URL url = new URL(strURL);
                    conn = (HttpURLConnection) url.openConnection();
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        is = conn.getInputStream();
                        baos = new ByteArrayOutputStream();
                        //文件的总长度
                        int totalLength = conn.getContentLength();
                        //当前的文件的大小
                        int currentLength = 0;
                        byte[] buff = new byte[1024];
                        int len = 0;
                        while ((len = is.read(buff)) != -1) {
                            baos.write(buff, 0, len);
                            baos.flush();
                            currentLength += len;
                            progress = (currentLength * 100) / totalLength;
                            //设置通知栏中的进度条的值
                            builder.setProgress(100, progress, false);
                            manager.notify(6, builder.getNotification());
                            Message message = new Message();
                            message.what = 3;
                            message.arg1 = progress;
                            messenger.send(message);
                        }
                        byte[] b = baos.toByteArray();

                        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);

                        builder.setContentText("下载完成");
                        manager.notify(6, builder.getNotification());
                        Message message = new Message();
                        message.what =4;
                        message.obj = bitmap;
                        messenger.send(message);
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}

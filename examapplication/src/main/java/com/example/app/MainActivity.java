package com.example.app;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.constant.URLConstant;

public class MainActivity extends AppCompatActivity {

    //ui
    private Button btn;
    private ProgressBar pb;
    private ImageView iv;
    private Messenger messengerClient;
    private Messenger messengerServer;
    private ProgressDialog dialog;
    private boolean isConnected;

  public   class ClientHandle extends Handler{
      @Override
      public void handleMessage(Message msg) {
          switch (msg.what){
              case 2:
                  dialog = new ProgressDialog(MainActivity.this);
                  dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                  dialog.setMessage("正在下载");
                  break;
              case 3:
                  pb.setProgress(msg.arg1);
                  break;
              case 4:
                  Bitmap bitmap = (Bitmap) msg.obj;
                  iv.setImageBitmap(bitmap);
                  dialog.dismiss();
                  break;
          }
      }
  }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_download);
        iv = (ImageView) findViewById(R.id.iv);
        pb = (ProgressBar) findViewById(R.id.progress);

        ClientHandle handle = new ClientHandle();
        //用来接收server中的消息
        messengerClient = new Messenger(handle);



    }

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            //用来向server中发送消息
            messengerServer = new Messenger(binder);
            isConnected = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;

        }
    };
    public void click(View view) {
        Intent intent = new Intent(this,DownLoadService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);
        Message message = new Message();
        message.what = 1;
        message.obj = URLConstant.IMGPATH;
        //Messengerclient表示的是客户端的Messenger，可以通过来自于客户端的Message的replyTo属性获得，
        //其内部指向了客户端的ClientHandler实例，可以用Messengerclient向客户端发送消息
        message.replyTo = messengerClient;


        Log.i("我跳转没有", "跳转了");
       if (isConnected){
           try {
               //将这个消息通过建立连接的conn中从服务端传过来的Ibinder对象传递出去
               messengerServer.send(message);
           } catch (RemoteException e) {
               e.printStackTrace();
           }
       }

    }






}

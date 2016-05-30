package com.example.qianfeng.handlerapplication;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView iv;
    private Button btn;
    private String path="http://image.tianjimedia.com/uploadImages/2011/109/8MJ3G4ADBG9J.jpg";
    private ProgressDialog dialog;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 1:
                    Bitmap p = (Bitmap) msg.obj;
                    iv.setImageBitmap(p);
                    break;
                case 2:
                    dialog=ProgressDialog.show(MainActivity.this,null,"图片正在下载");
                    break;
                case 3:
                    dialog.dismiss();
                    break;
//                case 4:
//                    int i = msg.arg1;
//                    dialog.setProgress(i);
//
//                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        iv = (ImageView) findViewById(R.id.iv);

    }

    @Override
    public void onClick(View v) {
        new Thread(){
            @Override
            public void run() {
                //子线程开始下载任务时弹出对话框
                //发送一个空消息
              handler.sendEmptyMessage(2);
                ByteArrayOutputStream baos = null;
                BufferedInputStream bis = null;
                URL url=null;
                HttpURLConnection conn=null;
                try{

                    url=new URL(path);
                    conn= (HttpURLConnection) url.openConnection();
                    if(conn.getResponseCode()==200){
                        bis = new BufferedInputStream(conn.getInputStream());
                        baos = new ByteArrayOutputStream();
                        byte[] b = new byte[10240];
                        int len =0;
                        //文件的总大小
                        long totalSize=conn.getContentLength();
                        long currentDownLoadSize=0;
                        int present=0;
                        while((len = bis.read(b))!=-1){
                            baos.write(b,0,len);
                            baos.flush();
                            currentDownLoadSize+=len;
                            present= (int) ((currentDownLoadSize*100)/totalSize);
//                            Message message = new Message();
//                            message.what= 4;
//                            message.arg1 = present;
//                            handler.sendMessage(message);
                            if(present ==100){
                                Bitmap bp = BitmapFactory.decodeStream(conn.getInputStream());
                                Message message1=new Message();
                                message1.what=1;
                                message1.obj=bp;
                                Thread.sleep(1000);
                                handler.sendMessage(message1);
                        }


                        }

                    }
                    //再发一个消息关闭弹出框
                    //使用sendToTarget 时  消息必须使用Message msg=handler.obtainMessage()的形式创建
                    Message msg=handler.obtainMessage();
                    msg.what=3;
                    msg.sendToTarget();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}

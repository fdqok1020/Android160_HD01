package com.example.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.utils.HttpUtils;

/**
 * Created by Administrator on 2016/5/5.
 */
public class BitmapTask extends AsyncTask<String,Void,Bitmap> {
    private String path;

    private ImageView iv;

    public  BitmapTask(ImageView iv) {
        this.iv=iv;

    }



    @Override
    protected Bitmap doInBackground(String... params) {
        path = params[0];
       if (path!=null){

        return HttpUtils.getImageWithPath(path);
       }else{
           return  null;
       }

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap!=null&&path.equals(iv.getTag().toString())){
            iv.setImageBitmap(bitmap);
        }
    }
}

package com.example.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.utils.HttpUtils;

/**
 * Created by Administrator on 2016/5/6.
 */
public class ImageTask extends AsyncTask<String,Void,Bitmap> {

    private String path;

    private ImageView iv;

    public ImageTask(ImageView iv) {
        this.iv=iv;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        path = params[0];

        return HttpUtils.getImageWithPath(path);
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
        super.onPostExecute(bitmap);
    }
}

package com.example.httputils.bitmap;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by Administrator on 2016/5/30.
 */
public class MemoryCacheTool {
    private static LruCache<String,Bitmap> mLruCache ;

    static {
        if (mLruCache == null) {
            int max = 4 * 1024 * 1024;
            mLruCache = new LruCache<String,Bitmap>(max) {

                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount();
                }
            };
        }
    }

    public static void writeBitmapToCache(String url, Bitmap bitmap) {
        mLruCache.put(url,bitmap);
    }

    public static Bitmap readBitmapFromCache(String url) {
        Bitmap bitmap = mLruCache.get(url);
        return bitmap;
    }
}

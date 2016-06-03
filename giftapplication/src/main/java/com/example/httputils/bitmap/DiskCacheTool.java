package com.example.httputils.bitmap;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/5/24.
 */
public class DiskCacheTool {
    public static final int MAX_SIZE = 4 * 1024 * 1024;
    private static DiskLruCache diskCache;

    public static void init(Context context) {
        try {
            if (diskCache == null) {
                diskCache = DiskLruCache.open(getCacheDir(context), getVersionCode(context), 1, MAX_SIZE);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public  static void writeBitmapToDisk(String url,Bitmap bitmap){
        String cacheKey = getCacheKey(url);
        try{
            DiskLruCache.Editor edit = diskCache.edit(cacheKey);
            if (edit != null){
                OutputStream outputStream = edit.newOutputStream(0);
                boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                if (compress){
                    edit.commit();
                }else{
                    edit.abort();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static Bitmap readBitmapFromDisk(String url){
        String cacheKey = getCacheKey(url);
        try{
            DiskLruCache.Snapshot snapshot = diskCache.get(cacheKey);
            if(snapshot != null){
                InputStream inputStream = snapshot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return  bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }

    private static String getCacheKey(String url) {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(url.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0;i < digest.length;i++){
                stringBuffer.append(Integer.toHexString(Math.abs(digest[i])));
            }
            return stringBuffer.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return String.valueOf(url.hashCode());
    }
    private  static int getVersionCode(Context context){
        try{
            PackageInfo packageInfo  = context.getPackageManager().getPackageInfo(context.getPackageName(),0);
          return  packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //保存图片的路径
    private  static File getCacheDir(Context context){
        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED&& !Environment.isExternalStorageRemovable()){
            return  context.getExternalCacheDir();
        }
        return context.getCacheDir();
    }
}

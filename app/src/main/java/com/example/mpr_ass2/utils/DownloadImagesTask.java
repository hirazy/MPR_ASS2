package com.example.mpr_ass2.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImagesTask extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView = null;

    public DownloadImagesTask(ImageView imageView) {
        this.imageView = imageView;
    }

//    override fun onPostExecute(bitmap: Bitmap?)
//    {
//        imageView.setImageBitmap(bitmap)
//    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        try {
            InputStream stream = new URL(urls[0]).openStream();

            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

//        this.imageView = imageViews[0];
//        return download_Image((String)imageView.getTag());
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }

//    private Bitmap download_Image(String url) {
//
//        Bitmap bmp =null;
//        try{
//            URL ulr = new URL(url);
//            HttpURLConnection con = (HttpURLConnection)ulr.openConnection();
//            InputStream is = con.getInputStream();
//            bmp = BitmapFactory.decodeStream(is);
//            if (null != bmp)
//                return bmp;
//
//        }catch(Exception e){}
//        return bmp;
//    }
}

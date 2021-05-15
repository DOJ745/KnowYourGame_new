package com.faa.knowyourgame_new.retrofit.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;
    String imgName;

    public DownloadImageTask(ImageView _imageView, String _imgName) {

        this.imageView = _imageView;
        this.imgName = _imgName;
    }

    protected Bitmap doInBackground(String... urls) {

        String urlDisplay = urls[0];
        String savePath = "";
        Bitmap loadedImage = null;
        boolean success = false;

        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            loadedImage = BitmapFactory.decodeStream(in);

            File sdCardDirectory = Environment.getExternalStorageDirectory();

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                sdCardDirectory = Environment.getStorageDirectory();
            }

            File savedImage = new File(sdCardDirectory, "/images/" + imgName);
            savePath = savedImage.getPath();

            // Encode the file as a JPEG image.
            FileOutputStream outStream;

            outStream = new FileOutputStream(savedImage);
            loadedImage.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            /* 100 to keep full quality of the image */

            outStream.flush();
            outStream.close();
            success = true;
        }
        catch (IOException e) { e.printStackTrace(); }

        if (success) { Log.d("IMAGE", "Image saved to " + savePath); }
        else { Log.e("IMAGE", "Error during image saving!"); }

        return loadedImage;
    }

    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}
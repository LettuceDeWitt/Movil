package com.example.pokeapijavaapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void cargarImagen(String urlImagen, ImageView imageView) {
        imageView.setImageDrawable(null);
        imageView.setTag(urlImagen);

        executorService.execute(() -> {
            try {
                URL url = new URL(urlImagen);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(10000);
                connection.setReadTimeout(10000);
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                connection.disconnect();

                handler.post(() -> {
                    Object tag = imageView.getTag();
                    if (tag != null && tag.equals(urlImagen)) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            } catch (Exception e) {
                handler.post(() -> imageView.setImageResource(android.R.drawable.ic_menu_help));
            }
        });
    }
}

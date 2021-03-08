package com.greysonparrelli.glideimagefailure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ImageView working = findViewById(R.id.image_working);
    ImageView broken  = findViewById(R.id.image_broken);


    try {
      Glide.with(this)
           .load(readFully(getAssets().open("working.jpg")))
           .into(working);

      Glide.with(this)
           .load(readFully(getAssets().open("broken.jpg")))
           .into(broken);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  public static byte[] readFully(InputStream in) throws IOException {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    byte[] buffer              = new byte[4096];
    int read;

    while ((read = in.read(buffer)) != -1) {
      bout.write(buffer, 0, read);
    }

    in.close();

    return bout.toByteArray();
  }
}
package io.github.httpjgabrielfreitas.urireceiver;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.activity_main) RelativeLayout activityBackground;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    // Get the intent that started this activity
    Intent intent = getIntent();
    Uri data = intent.getData();

    // Figure out what to do based on the intent type
    if (intent.getType() != null && intent.getType().contains("text/plain")) {

      String color = data.getQueryParameter("color");

      Log.e("received", String.format("received: %s", color));
      activityBackground.setBackgroundColor(Color.parseColor(color));
    }

  }
}

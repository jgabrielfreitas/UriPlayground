package io.github.httpjgabrielfreitas.urireceiver;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

  @BindView(R.id.activity_main) RelativeLayout activityBackground;
  String packageId;

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
      packageId    = data.getQueryParameter("pkgId");

      Log.e("received", String.format("received: %s", color));
      activityBackground.setBackgroundColor(Color.parseColor(color));
      makeText(this, packageId, LENGTH_SHORT).show();
    }

  }

  @OnClick(R.id.returnOkButton)
  public void returnOkButtonClicked() {

    if (packageId != null) {

      Intent resultIntent = new Intent(packageId, Uri.parse(packageId));


    } else {
      makeText(this, "packageId = null !!", LENGTH_SHORT).show();
    }

  }

}

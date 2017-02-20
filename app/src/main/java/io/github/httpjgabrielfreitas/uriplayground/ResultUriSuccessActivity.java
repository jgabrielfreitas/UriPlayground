package io.github.httpjgabrielfreitas.uriplayground;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;

@InjectLayout(layout = R.layout.activity_result_uri_sucess)
public class ResultUriSuccessActivity extends AbstractActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    Uri data      = intent.getData();
  }
}

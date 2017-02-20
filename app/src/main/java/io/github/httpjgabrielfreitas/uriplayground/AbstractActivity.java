package io.github.httpjgabrielfreitas.uriplayground;

import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.ButterKnife;
import com.jgabrielfreitas.layoutid.activity.InjectLayoutBaseActivity;

/**
 * Created by JGabrielFreitas on 20/02/17.
 */

public abstract class AbstractActivity extends InjectLayoutBaseActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
  }
}

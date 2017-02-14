package io.github.httpjgabrielfreitas.uriplayground;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pavelsikun.vintagechroma.ChromaDialog;
import com.pavelsikun.vintagechroma.ChromaUtil;
import com.pavelsikun.vintagechroma.IndicatorMode;
import com.pavelsikun.vintagechroma.OnColorSelectedListener;
import com.pavelsikun.vintagechroma.colormode.ColorMode;

public class MainActivity extends AppCompatActivity {

  private static final String EXTRA_MODE = "extra_MODE";
  String selectedColor = "000000";
  private ColorMode mode;
  private int color;
  @BindView(R.id.colorView) View colorView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    mode = savedInstanceState == null
        ? ColorMode.RGB
        : ColorMode.values()[savedInstanceState.getInt(EXTRA_MODE)];
  }

  @OnClick(R.id.fab)
  public void facbClicked() {

    IndicatorMode indicatorMode = IndicatorMode.HEX;
    if(mode == ColorMode.HSV
        || mode == ColorMode.CMYK
        || mode == ColorMode.HSL) indicatorMode = IndicatorMode.DECIMAL; // cuz HEX is dumb for those

    new ChromaDialog.Builder()
        .initialColor(color)
        .colorMode(mode)
        .indicatorMode(indicatorMode) //HEX or DECIMAL;
        .onColorSelected(new OnColorSelectedListener() {
          @Override public void onColorSelected(int newColor) {
            updateTextView(newColor);
          }
        })
        .create()
        .show(getSupportFragmentManager(), "dialog");
  }

  private void updateTextView(int newColor) {
    colorView.setBackgroundColor(Color.parseColor(convertColorIntToString(newColor)));
  }

  private String convertColorIntToString(int color) {
    return ChromaUtil.getFormattedColorString(color, false);
  }
}

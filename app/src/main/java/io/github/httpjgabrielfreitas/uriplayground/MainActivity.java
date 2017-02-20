package io.github.httpjgabrielfreitas.uriplayground;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.jgabrielfreitas.layoutid.annotations.InjectLayout;
import com.pavelsikun.vintagechroma.ChromaDialog;
import com.pavelsikun.vintagechroma.ChromaUtil;
import com.pavelsikun.vintagechroma.IndicatorMode;
import com.pavelsikun.vintagechroma.OnColorSelectedListener;
import com.pavelsikun.vintagechroma.colormode.ColorMode;

import static android.content.Intent.ACTION_VIEW;
import static com.pavelsikun.vintagechroma.IndicatorMode.DECIMAL;
import static com.pavelsikun.vintagechroma.IndicatorMode.HEX;
import static com.pavelsikun.vintagechroma.colormode.ColorMode.CMYK;
import static com.pavelsikun.vintagechroma.colormode.ColorMode.HSL;
import static com.pavelsikun.vintagechroma.colormode.ColorMode.HSV;
import static com.pavelsikun.vintagechroma.colormode.ColorMode.RGB;

@InjectLayout(layout = R.layout.activity_main)
public class MainActivity extends AbstractActivity {

  String selectedColor   = "000000";
  private ColorMode mode = RGB;
  @BindView(R.id.colorView) View colorView;

  private void updateTextView(int newColor) {
    colorView.setBackgroundColor(Color.parseColor(convertColorIntToString(newColor)));
  }

  private String convertColorIntToString(int color) {
    selectedColor = ChromaUtil.getFormattedColorString(color, false);
    return selectedColor;
  }

  @OnClick(R.id.fab)
  public void fabClicked() {

    IndicatorMode indicatorMode = HEX;
    if(mode == HSV
        || mode == CMYK
        || mode == HSL) indicatorMode = DECIMAL; // cuz HEX is dumb for those

    new ChromaDialog.Builder()
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

  @OnClick(R.id.sendColorButton)
  public void sendColor() {
    Log.e("color", selectedColor);

    Uri.Builder builder = new Uri.Builder();
    builder.scheme("urireceiver").authority("path")
                                 .appendQueryParameter("color", selectedColor)
                                 .appendQueryParameter("pkgId", getApplicationInfo().packageName);

    Intent intent = new Intent(ACTION_VIEW);
    intent.setDataAndType(builder.build(), "text/plain");
    startActivity(intent);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    Uri result = data.getData();
    //Toast.makeText(this, result.getQueryParameter("result"), Toast.LENGTH_SHORT).show();
    Log.e("result", "result OK");

  }
}

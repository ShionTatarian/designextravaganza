package fi.qvik.extravaganzadesign;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fi.qvik.designextravaganza.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ImageView image1, image2, image3, image4, image5, image6, image7;
    private TextView text1, text2, text3, text4, text5, text6, text7;

    private List<ImageView> imageList = new ArrayList<>();
    private List<TextView> textList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image1 = (ImageView) findViewById(R.id.image_1);
        imageList.add(image1);
        text1 = (TextView) findViewById(R.id.text_1);
        textList.add(text1);

        image2 = (ImageView) findViewById(R.id.image_2);
        imageList.add(image2);
        text2 = (TextView) findViewById(R.id.text_2);
        textList.add(text2);

        image3 = (ImageView) findViewById(R.id.image_3);
        imageList.add(image3);
        text3 = (TextView) findViewById(R.id.text_3);
        textList.add(text3);

        image4 = (ImageView) findViewById(R.id.image_4);
        imageList.add(image4);
        text4 = (TextView) findViewById(R.id.text_4);
        textList.add(text4);

        image5 = (ImageView) findViewById(R.id.image_5);
        imageList.add(image5);
        text5 = (TextView) findViewById(R.id.text_5);
        textList.add(text5);

        image6 = (ImageView) findViewById(R.id.image_6);
        imageList.add(image6);
        text6 = (TextView) findViewById(R.id.text_6);
        textList.add(text6);

        image7 = (ImageView) findViewById(R.id.image_7);
        imageList.add(image7);
        text7 = (TextView) findViewById(R.id.text_7);
        textList.add(text7);

        updateContent();
    }

    private void updateContent() {
        for (int i = 0; i < 7; i++) {
            ImageView iv = imageList.get(i);
            TextView tv = textList.get(i);

            Log.d(TAG, "for: " + i);
            setPaletteColor(tv, iv);
        }

    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private static void setPaletteColor(TextView tv, ImageView iv) {
        Bitmap bm = drawableToBitmap(iv.getDrawable());
        final Palette p = Palette.from(bm).generate();
        int color = p.getDarkVibrantColor(Color.BLACK);

        String colorHex = String.format("#FF%06X", 0xFFFFFF & color);
        Log.d(TAG, "Color: " + colorHex);
        boolean isSuccess = !"#FF000000".equals(colorHex);
        tv.setText(String.format("HEX: %s\nisSuccess: %s", colorHex, isSuccess));
        tv.setTextColor(color);
    }

}

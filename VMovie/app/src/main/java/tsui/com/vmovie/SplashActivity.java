package tsui.com.vmovie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity implements ViewPropertyAnimatorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ImageView bg = (ImageView) findViewById(R.id.teach_splash_image);
        bg.post(new Runnable() {
            @Override
            public void run() {
                // 为了兼容5.0以下
                ViewCompat.setPivotX(bg, bg.getWidth() / 2);
                ViewCompat.setPivotY(bg, bg.getHeight() / 2);
                ViewCompat.animate(bg)
                        .scaleX(1.2f)
                        .scaleY(1.2f)
                        .setDuration(2000)
                        .setListener(SplashActivity.this)
                        .start();
            }
        });
    }

    @Override
    public void onAnimationStart(View view) {

    }

    @Override
    public void onAnimationEnd(View view) {
        SharedPreferences first = getSharedPreferences("first", MODE_PRIVATE);
        boolean flag = first.getBoolean("isFirstRun", true);
        if (flag) {
            startActivity(new Intent(this, GuideActivity.class));
            first.edit().putBoolean("isFirstRun", false).commit();
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

    @Override
    public void onAnimationCancel(View view) {

    }
}

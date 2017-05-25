package tsui.com.vmovie;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Property;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import tsui.com.vmovie.adapters.MainPagerAdapter;
import tsui.com.vmovie.fragments.MainFragment;


public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private ImageView side;
    private ViewPager pager;
    private RadioButton btn;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (RadioButton) findViewById(R.id.main_btn_1);
        side = (ImageView) findViewById(R.id.main_side);
        btn.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams params = side.getLayoutParams();
                params.width = btn.getWidth();
                side.setLayoutParams(params);
            }
        });
        pager = (ViewPager) findViewById(R.id.main_pager);
        pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(this);
        group = (RadioGroup) findViewById(R.id.main_group);
        group.setOnCheckedChangeListener(this);
    }

    @Override
    public void onFragmentInteraction(final String title, boolean isUp) {
//        btn.setText(title);
        AnimatorSet set = new AnimatorSet();
        if (isUp) {
            ObjectAnimator y = ObjectAnimator.ofFloat(btn, "translationY", 0, -btn.getHeight());
            y.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    btn.setText(title);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            set.playSequentially(
                    y,
                    ObjectAnimator.ofFloat(btn, "translationY", btn.getHeight(), 0)
            );
        } else {
            set.playSequentially(
                    ObjectAnimator.ofFloat(btn, "translationY", 0, btn.getHeight()),
                    ObjectAnimator.ofObject(btn, Property.of(TextView.class, CharSequence.class, "text"),
                            new TypeEvaluator<CharSequence>() {
                                @Override
                                public CharSequence evaluate(float fraction, CharSequence startValue, CharSequence endValue) {
                                    return fraction == 1 ? endValue : startValue;
                                }
                            }, btn.getText(), title).setDuration(0),
                    ObjectAnimator.ofFloat(btn, "translationY", -btn.getHeight(), 0)
            );
        }
        set.start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ViewCompat.setTranslationX(side, side.getWidth() * (position + positionOffset));
    }

    @Override
    public void onPageSelected(int position) {
        group.check(group.getChildAt(position).getId());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.main_btn_1:
                pager.setCurrentItem(0);
                break;
            case R.id.main_btn_2:
                pager.setCurrentItem(1);
                break;
        }
    }

}

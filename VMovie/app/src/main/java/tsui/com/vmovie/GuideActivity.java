package tsui.com.vmovie;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import tsui.com.vmovie.adapters.GuideAdapter;


public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        pager = (ViewPager) findViewById(R.id.guide_pager);
        pager.setAdapter(new GuideAdapter(this));
        pager.addOnPageChangeListener(this);
        group = (RadioGroup) findViewById(R.id.guide_group);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        group.check(group.getChildAt(position).getId());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

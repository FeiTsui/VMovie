package tsui.com.vmovie.adapters;

/**
 * Email：tsui@onetos.cc
 * Created by Tsui on 2017/5/25 00:15
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tsui.com.vmovie.MainActivity;
import tsui.com.vmovie.R;

public class GuideAdapter extends PagerAdapter implements View.OnClickListener {
    private List<View> views;
    private Context context;
    public GuideAdapter(Context context) {
        views = new ArrayList<>();
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        views.add(inflater.inflate(R.layout.view_guide_1, null));
        views.add(inflater.inflate(R.layout.view_guide_2, null));
        View view = inflater.inflate(R.layout.view_guide_3, null);
        view.setOnClickListener(this);
        views.add(view);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public void onClick(View v) {
        context.startActivity(new Intent(context, MainActivity.class));
        ((Activity) context).finish();
    }
}
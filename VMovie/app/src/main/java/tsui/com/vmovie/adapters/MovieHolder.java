package tsui.com.vmovie.adapters;

/**
 * Email：tsui@onetos.cc
 * Created by Tsui on 2017/5/25 00:16
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import tsui.com.vmovie.R;
import tsui.com.vmovie.beans.MovieList;
import tsui.com.vmovie.utIls.BindingView;
import tsui.com.vmovie.utIls.CommAdapter;


public class MovieHolder {
    @BindingView(id = R.id.movie_cover, fieldName = "image")
    public ImageView cover;
    @BindingView(id = R.id.movie_title, fieldName = "title")
    public TextView title;
    @BindingView(id = R.id.movie_duration, fieldName = "duration", binder = DurationBinder.class)
    public TextView duration;
    @BindingView(id = R.id.movie_cate, fieldName = "cates", binder = CateBinder.class)
    public TextView cate;
    @BindingView(id = R.id.movie_item, fieldName = "publish_time", binder = ItemBinder.class)
    public View item;
    public static class DurationBinder implements CommAdapter.ViewBinder<TextView, String> {
        @Override
        public void onBind(TextView view, String data) {
            long l = Long.parseLong(data);
            view.setText(String.format("%02d'%02d\"", l / 60, l % 60));
        }
    }
    public static class CateBinder implements CommAdapter.ViewBinder<TextView, List<MovieList.MovieBean.CatesBean>> {

        @Override
        public void onBind(TextView view, List<MovieList.MovieBean.CatesBean> data) {
            view.setText(data.get(0).getCatename());
        }
    }
    public static class ItemBinder implements CommAdapter.ViewBinder<View, String> {

        @Override
        public void onBind(View view, String data) {
            SimpleDateFormat format = new SimpleDateFormat("MMM.dd", Locale.ENGLISH);
            view.setContentDescription(format.format(new Date(Long.parseLong(data) * 1000)));
        }
    }
}

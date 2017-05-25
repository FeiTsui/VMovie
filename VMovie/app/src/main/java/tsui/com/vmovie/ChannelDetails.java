package tsui.com.vmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tsui.com.vmovie.beans.MovieList;
import tsui.com.vmovie.utIls.CommAdapter;
import tsui.com.vmovie.utIls.NetworkFactory;
import tsui.com.vmovie.utIls.Url;

public class ChannelDetails extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,AbsListView.OnScrollListener, View.OnClickListener{

    private CommAdapter<Object> adapter;
    private int page ;
    private ListView listView;
    private TextView textView;
    private ImageView imageView;
    private FrameLayout fl;
    private SwipeRefreshLayout swipe;
    private boolean isLoading = true;
    private String cateid,catename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_details);

        Intent intent = getIntent();
        cateid = intent.getStringExtra("cateid");
        catename = intent.getStringExtra("catename");
        //String path = "http://app.vmoiver.com/apiv3/post/getPostInCate?cateid=" + cateid + "?p=" + page ;

        listView = (ListView)findViewById(R.id.channel_details);
        textView = (TextView) findViewById(R.id.details_text);
        imageView = (ImageView) findViewById(R.id.channel_details_image);
        swipe = (SwipeRefreshLayout) findViewById(R.id.channel_details_swipe);
        fl = (FrameLayout) findViewById(R.id.channel_details_layout);
        textView.setText(catename);
        listView.setOnScrollListener(this);
        swipe.setOnRefreshListener(this);
        imageView.setOnClickListener(this);
        fl.setOnClickListener(this);
        adapter = new CommAdapter<Object>(this, new ArrayList<>());
        listView.setAdapter(adapter);
        page = 1;
        NetworkFactory.get(this);
    }

    @Url(method = "getMovieUrl")
    public void getMovieList(MovieList movielist) {
        List<Object> list = new ArrayList<>();
        for (MovieList.MovieBean bean : movielist.getData()) {
            list.add(bean);
        }
        adapter.addAll(list);
        isLoading =false;
    }

    public String getMovieUrl() {

//        return "http://app.vmoiver.com/apiv3/post/getPostInCate?cateid=" + cateid + "?p=" + page;
        return "http://app.vmoiver.com/apiv3/post/getPostInCate/cateid/"+cateid+"/p/"+page;
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        page = 1;
        NetworkFactory.get(this);
        swipe.setRefreshing(false);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!isLoading && firstVisibleItem + visibleItemCount > totalItemCount - 2) {
            page++;
            isLoading = true;
            NetworkFactory.get(this);
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}

package tsui.com.vmovie.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import tsui.com.vmovie.R;
import tsui.com.vmovie.beans.MovieList;
import tsui.com.vmovie.beans.MovieListBanner;
import tsui.com.vmovie.beans.PublishDate;
import tsui.com.vmovie.utIls.CommAdapter;
import tsui.com.vmovie.utIls.ErrorMethod;
import tsui.com.vmovie.utIls.NetworkFactory;
import tsui.com.vmovie.utIls.Url;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {

    private OnFragmentInteractionListener mListener;
    private int page = 1;
    private CommAdapter<Object> adapter;
    private SwipeRefreshLayout swipe;
    private boolean isLoading = true;
    private String date;
    private SimpleDateFormat format = new SimpleDateFormat("MMM.dd", Locale.ENGLISH);

    public MainFragment() {
        // Required empty public constructor
        date = format.format(new Date());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = (ListView) view.findViewById(R.id.movie_list);
        adapter = new CommAdapter<>(getContext(), new ArrayList<>(), 2);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
        swipe = ((SwipeRefreshLayout) view);
        swipe.setOnRefreshListener(this);
        page = 1;
        NetworkFactory.get(this);
//        new NetworkUtil<MovieList>(this).execute("http://app.vmoiver.com/apiv3/post/getPostByTab");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Url(method = "getMovieUrl")
    public void getMovieList(MovieList r) {
        Log.d("Main", "getMovieList: " + r.getData().get(0).getTitle());
        List<Object> list = new ArrayList<>();
        String date = null;
        if (adapter.getCount() > 0) {
            MovieList.MovieBean item = (MovieList.MovieBean) adapter.getItem(adapter.getCount() - 1);
            date = format.format(new Date(Long.parseLong(item.getPublish_time()) * 1000));
        } else {
            date = format.format(new Date());
        }
        for (MovieList.MovieBean bean : r.getData()) {
            String temp = this.format.format(new Date(Long.parseLong(bean.getPublish_time()) * 1000));
            if (!date.equals(temp)) {
                PublishDate publishDate = new PublishDate();
                publishDate.setDate(temp);
                list.add(publishDate);
                date = temp;
            }
            list.add(bean);
        }
        adapter.addAll(list);
        isLoading = false;
    }

    @Url("http://app.vmoiver.com/apiv3/index/getBanner")
    public void getBanner(MovieListBanner banner) {
        Log.d("Main", "getBanner: " + banner.getData().get(0).getImage());
        swipe.setRefreshing(false);
    }

    @ErrorMethod
    public void onError(Exception e) {
        e.printStackTrace();
    }

    public String getMovieUrl() {
        return String.format(Locale.getDefault(), "http://app.vmoiver.com/apiv3/post/getPostByTab?p=%d", page);
    }

    @Override
    public void onRefresh() {
        adapter.clear();
        page = 1;
        NetworkFactory.get(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View v = view.getChildAt(0);
        if (v != null) {
            String date = (String) v.getContentDescription();
            if (!Objects.equals(date, this.date)) {
                try {
                    mListener.onFragmentInteraction(date.equals(format.format(new Date())) ? "最新" : date, format.parse(date).compareTo(format.parse(this.date)) < 0);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                this.date = date;
            }
        }
        if (!isLoading && firstVisibleItem + visibleItemCount > totalItemCount - 2) {
            page++;
            isLoading = true;
            NetworkFactory.get(this, "getMovieList");
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(String title, boolean isUp);
    }
}

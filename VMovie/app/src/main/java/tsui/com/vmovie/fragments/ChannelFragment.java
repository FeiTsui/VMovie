package tsui.com.vmovie.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import tsui.com.vmovie.R;
import tsui.com.vmovie.beans.ChannelGrid;
import tsui.com.vmovie.utIls.CommAdapter;
import tsui.com.vmovie.utIls.NetworkFactory;
import tsui.com.vmovie.utIls.Url;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment {
    private GridView gridview;
    private CommAdapter<Object> adapter;
//    private String path = "http://app.vmoiver.com/apiv3/cate/getList";

    public ChannelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_channel, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridview = (GridView) view.findViewById(R.id.channel);

        adapter = new CommAdapter<>(getContext(), new ArrayList<>());
        gridview.setAdapter(adapter);
        NetworkFactory.get(this);

//        new NetworkUtil<ChannelGrid>(this).execute(path);
    }

    @Url("http://app.vmoiver.com/apiv3/cate/getList")
    public void getChannelGrid(ChannelGrid c) {
        List<Object> list = new ArrayList<>();
        for (ChannelGrid.ChannelBean bean : c.getData()) {

            list.add(bean);
        }
        adapter.addAll(list);

    }
}

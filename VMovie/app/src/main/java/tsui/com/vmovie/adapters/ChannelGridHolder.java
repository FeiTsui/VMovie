package tsui.com.vmovie.adapters;

import android.widget.ImageView;
import android.widget.TextView;

import tsui.com.vmovie.R;
import tsui.com.vmovie.utIls.BindingView;


/**
 * Email：tsui@onetos.cc
 * Created by Tsui on 2017/5/25 09:17
 */

public class ChannelGridHolder {
    @BindingView(id = R.id.item_channel_catename, fieldName = "catename")
    public TextView catename;
    @BindingView(id = R.id.item_channel_icon, fieldName = "icon")
    public ImageView icon;
}

package tsui.com.vmovie.adapters;

/**
 * Email：tsui@onetos.cc
 * Created by Tsui on 2017/5/25 00:15
 */

import android.widget.TextView;

import tsui.com.vmovie.R;
import tsui.com.vmovie.utIls.BindingView;
import tsui.com.vmovie.utIls.CommAdapter;


public class DateHolder {
    @BindingView(id = R.id.date_text, fieldName = "date", binder = ItemBinder.class)
    public TextView text;
    public static class ItemBinder implements CommAdapter.ViewBinder<TextView, String> {

        @Override
        public void onBind(TextView view, String data) {
            view.setText("-" + data + "-");
            view.setContentDescription(data);
        }
    }

}
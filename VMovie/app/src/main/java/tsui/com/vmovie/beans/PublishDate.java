package tsui.com.vmovie.beans;


import tsui.com.vmovie.R;
import tsui.com.vmovie.adapters.DateHolder;
import tsui.com.vmovie.utIls.AdapterData;

/**
 * Email：tsui@onetos.cc
 * Created by Tsui on 2017/5/25 00:17
 */



@AdapterData(layoutId = R.layout.item_date, viewHolder = DateHolder.class)
public class PublishDate {
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

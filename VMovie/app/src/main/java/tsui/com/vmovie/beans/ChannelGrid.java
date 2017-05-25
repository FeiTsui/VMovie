package tsui.com.vmovie.beans;

import java.util.List;

import tsui.com.vmovie.R;
import tsui.com.vmovie.adapters.ChannelGridHolder;
import tsui.com.vmovie.utIls.AdapterData;


/**
 * Email：tsui@onetos.cc
 * Created by Tsui on 2017/5/25 00:16
 */

public class ChannelGrid {

    /*{
  "data": [
    {
      "cate_type": "0",
      "orderid": "1",
      "cateid": "6",
      "catename": "创意",
      "alias": "Idea",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c9f3d1bc2d.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "2",
      "cateid": "11",
      "catename": "旅行",
      "alias": "Travel",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c958d6411a.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "3",
      "cateid": "13",
      "catename": "广告",
      "alias": "Ad",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6e7901621.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "4",
      "cateid": "8",
      "catename": "搞笑",
      "alias": "Fun",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570ca09bd4325.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "5",
      "cateid": "12",
      "catename": "爱情",
      "alias": "Love",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c94f8ce2e0.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "6",
      "cateid": "17",
      "catename": "剧情",
      "alias": "Story",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6e518f2b4.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "7",
      "cateid": "10",
      "catename": "运动",
      "alias": "Sports",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6e9d55dcb.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "8",
      "cateid": "16",
      "catename": "动画",
      "alias": "Animation",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c9cedd55bc.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "9",
      "cateid": "7",
      "catename": "励志",
      "alias": "Inspiration",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6e61a6eea.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "10",
      "cateid": "18",
      "catename": "音乐",
      "alias": "MV",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6f1cdd754.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "11",
      "cateid": "23",
      "catename": "科幻",
      "alias": "Fiction",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6edab6f69.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "12",
      "cateid": "43",
      "catename": "预告",
      "alias": "Trailer",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6f2ae0f5b.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "13",
      "cateid": "24",
      "catename": "纪录",
      "alias": "Record",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6ee6a51c7.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "14",
      "cateid": "44",
      "catename": "混剪",
      "alias": "Cut",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6e6d9fff0.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "15",
      "cateid": "45",
      "catename": "实验",
      "alias": "Experimental",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6f431d2d1.jpg"
    },
    {
      "cate_type": "0",
      "orderid": "16",
      "cateid": "78",
      "catename": "生活",
      "alias": "Lifeness",
      "icon": "http://cs.vmoiver.com/Uploads/Series/2016-04-12/570c6f4f9679c.jpg"
    }
  ]
}*/

    private List<ChannelBean> data;

    public List<ChannelBean> getData() {
        return data;
    }

    public void setData(List<ChannelBean> data) {
        this.data = data;
    }
    @AdapterData(layoutId = R.layout.item_channel_grid, viewHolder = ChannelGridHolder.class)
    public static class ChannelBean {

        private String cate_type;
        private String orderid;
        private String cateid;
        private String catename;
        private String alias;
        private String icon;

        public String getCate_type() {
            return cate_type;
        }

        public void setCate_type(String cate_type) {
            this.cate_type = cate_type;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getCateid() {
            return cateid;
        }

        public void setCateid(String cateid) {
            this.cateid = cateid;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }
}

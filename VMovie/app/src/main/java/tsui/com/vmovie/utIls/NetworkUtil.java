package tsui.com.vmovie.utIls;

/**
 * Email：tsui@onetos.cc
 * Created by Tsui on 2017/5/25 00:21
 */

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtil<T> extends AsyncTask<Object, Void, Object> {
    private Class<T> type;
    private Callback<T> callback;

    public NetworkUtil(Class<T> type, Callback<T> callback) {
        this.type = type;
        this.callback = callback;
    }

    public NetworkUtil(Callback<T> callback) {
        this.callback = callback;
        Type[] types = callback.getClass().getGenericInterfaces();
        //通过反射获取泛型
        for (Type type1 : types) {
            if (type1 instanceof ParameterizedType) {
                if (((ParameterizedType) type1).getRawType() == Callback.class) {
                    this.type = (Class<T>) ((ParameterizedType) type1).getActualTypeArguments()[0];
                    return;
                }
            }
        }
    }

    @Override
    protected Object doInBackground(Object... params) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL((String) params[0]).openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream is = connection.getInputStream();
                byte[] buffer = new byte[10 << 10];
                int length;
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                while ((length = is.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }
                return new Gson().fromJson(os.toString("UTF-8"), type);

            } else {
                return new RuntimeException("ResponseCode: " + code);
            }
        } catch (IOException e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if (o instanceof Exception) {
            callback.onError((Exception) o);
        } else {
            callback.onSucceed((T)o);
        }
    }

    public interface Callback<Result> {
        void onSucceed(Result r);
        void onError(Exception e);
    }
}

package tsui.com.vmovie.utIls;

/**
 * Email：tsui@onetos.cc
 * Created by Tsui on 2017/5/25 00:20
 */

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class NetworkFactory {
    public static void get(Object o, String... methodName) {
        Method[] methods = null;
        if (methodName.length == 0) {
            methods = o.getClass().getDeclaredMethods();
        } else {
            methods = new Method[methodName.length];
            Method[] temp = o.getClass().getDeclaredMethods();
            int index = 0;
            for (Method method : temp) {
                for (String s : methodName) {
                    if (Objects.equals(method.getName(), s)) {
                        methods[index++] = method;
                        break;
                    }
                }
            }
        }
        Method error = null;
        for (Method method : methods) {
            if (method.getAnnotation(ErrorMethod.class) != null) {
                error = method;
                Log.d("Factory", "get: " + error.toGenericString());
            }
        }
        for (Method method : methods) {
            Url annotation = method.getAnnotation(Url.class);
            if (annotation != null) {
                if (!TextUtils.isEmpty(annotation.value())) {
                    new NetworkUtil<>(method.getParameterTypes()[0], new CommCallback(o, method, error)).execute(annotation.value());
                } else {
                    try {
                        Method method1 = o.getClass().getDeclaredMethod(annotation.method());
                        new NetworkUtil<>(method.getParameterTypes()[0], new CommCallback(o, method, error)).execute(method1.invoke(o));

                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private static class CommCallback implements NetworkUtil.Callback {
        private Object o;
        private Method succeed;
        private Method error;

        public CommCallback(Object o, Method succeed, Method error) {
            this.o = o;
            this.succeed = succeed;
            this.error = error;
        }

        @Override
        public void onSucceed(Object r) {
            try {
                succeed.invoke(o, r);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Exception e) {
            try {
                error.invoke(o, e);
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        }
    }
}
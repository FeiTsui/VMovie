package tsui.com.vmovie.utIls;

/**
 * Email：tsui@onetos.cc
 * Created by Tsui on 2017/5/25 00:20
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindingView {
    int id();
    String fieldName();
    Class<? extends CommAdapter.ViewBinder> binder() default CommAdapter.ViewBinder.class;
}

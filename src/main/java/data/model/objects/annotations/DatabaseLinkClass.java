package data.model.objects.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseLinkClass {
    Class linkClass();
    String tableName();
}
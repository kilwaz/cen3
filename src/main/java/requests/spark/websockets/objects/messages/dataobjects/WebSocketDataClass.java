package requests.spark.websockets.objects.messages.dataobjects;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface WebSocketDataClass {
    Class value();
}

package app.slicequeue.sq_user.common.event;

import app.slicequeue.common.base.messagerelay.event.EventTypeRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserEventTypeConfig {

    @Autowired
    public void registerEventTypes(EventTypeRegistry registry) {
        registry.register(UserEventType.USER_INFO_CHANGED);
    }

}

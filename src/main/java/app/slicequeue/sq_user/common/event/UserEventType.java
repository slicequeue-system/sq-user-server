package app.slicequeue.sq_user.common.event;

import app.slicequeue.event.domain.EventDescriptor;

public enum UserEventType implements EventDescriptor {
    USER_INFO_CHANGED("sq-user-info-changed");

    private final String topic;

    UserEventType(String topic) {
        this.topic = topic;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTopic() {
        return topic;
    }
}

package app.slicequeue.sq_user.common.event;

import app.slicequeue.common.base.messagerelay.event.EventPayload;
import app.slicequeue.common.base.messagerelay.event.EventType;
import app.slicequeue.sq_user.common.event.payload.UserInfoChangedEventPayload;

public enum UserEventType implements EventType {
    USER_INFO_CHANGED("sq-user-info-changed", UserInfoChangedEventPayload.class);

    private final String topic;
    private final Class<? extends EventPayload> payloadClass;

    UserEventType(String topic, Class<? extends EventPayload> payloadClass) {
        this.topic = topic;
        this.payloadClass = payloadClass;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public Class<? extends EventPayload> getPayloadClass() {
        return payloadClass;
    }
}

package app.slicequeue.sq_user.common.outbox_relay;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageRelayConstants {

    public static final int SHARD_COUNT = 4; // 샤드 가정 임의의 값

}

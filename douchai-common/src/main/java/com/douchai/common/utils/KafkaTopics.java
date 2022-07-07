package com.douchai.common.utils;

public interface KafkaTopics {
    /**
     * 更新票房
     */
    String TOPIC_BOX_OFFICE = "topic_box_office";

    /**
     * 更新场次座位信息
     */
    String TOPIC_SESSION_SEAT = "topic_session_seat";

    /**
     * 添加评论
     */
    String TOPIC_ADD_COMMENT = "topic_add_comment";

    /**
     * 更新评论
     */
    String TOPIC_UPDATE_COMMENT = "topic_update_comment";

}

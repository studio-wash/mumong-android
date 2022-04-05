package com.studiowash.mumong.domain.entity.community

import androidx.annotation.StringRes
import com.studiowash.mumong.R

enum class CommunityTopicEntity(@StringRes val topicNameRes: Int) {
    DAILY(R.string.community_topic_daily),
    RECORDING(R.string.community_topic_recording),
    TIP(R.string.community_topic_tip),
    QUESTION(R.string.community_topic_question),
    ADVERTISE(R.string.community_topic_advertise)
}
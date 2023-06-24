package com.coludclub.atcha.alarm

import kotlinx.serialization.Serializable


@Serializable
data class SlackAlarm(
    val channelName: String,
    val message: String,
    val cronTime: String
)

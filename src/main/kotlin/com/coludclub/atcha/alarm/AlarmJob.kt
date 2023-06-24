package com.coludclub.atcha.alarm

import com.slack.api.Slack
import mu.KotlinLogging
import org.quartz.Job
import org.quartz.JobExecutionContext

class AlarmJob : Job {

    private val logger = KotlinLogging.logger { }

    override fun execute(context: JobExecutionContext) {
        runCatching {
            val alarmInfo = context.jobDetail.jobDataMap


            val slackBot = Slack.getInstance()
                .methods(alarmInfo["token"] as String)

            val channelName = alarmInfo["channelName"] as String
            val message = alarmInfo["message"] as String


            slackBot.chatPostMessage {
                it.channel("#$channelName")
                    .text(message)
            }

        }.onFailure { e ->
            logger.error { "토큰, 슬렉 채널, 메시지 확인 해!@#!@#!@#" }
            throw (e)
        }

    }
}
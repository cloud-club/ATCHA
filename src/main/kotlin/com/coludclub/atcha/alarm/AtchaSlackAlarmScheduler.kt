package com.coludclub.atcha.alarm

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.quartz.CronScheduleBuilder
import org.quartz.JobBuilder
import org.quartz.JobDataMap
import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.quartz.impl.StdSchedulerFactory
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class AtchaSlackAlarmScheduler(
    private val atchaSlackProperties: AtchaSlackProperties,
) {

    @PostConstruct
    @OptIn(ExperimentalSerializationApi::class)
    fun schedule() {

        val scheduler: Scheduler = StdSchedulerFactory.getDefaultScheduler()

        scheduler.start()


        Json.decodeFromStream<List<SlackAlarm>>(ClassPathResource("alarm.json").inputStream)
            .forEachIndexed { index, alarm ->
                val jobDataMap = JobDataMap(
                    mapOf(
                        "token" to atchaSlackProperties.token,
                        "channelName" to alarm.channelName,
                        "message" to alarm.message
                    )
                )

                val job: JobDetail = JobBuilder.newJob(AlarmJob::class.java)
                    .withIdentity("alarm_$index", "tempGroup")
                    .setJobData(jobDataMap)
                    .build()

                val trigger: Trigger = TriggerBuilder.newTrigger()
                    .withIdentity("alarm_$index", "tempGroup")
                    .withSchedule(CronScheduleBuilder.cronSchedule(alarm.cronTime))
                    .build()


                scheduler.scheduleJob(job, trigger)

            }

    }

}
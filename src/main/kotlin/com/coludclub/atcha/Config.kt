package com.coludclub.atcha

import com.slack.api.Slack
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    @Bean
    fun slack(): Slack = Slack.getInstance()
}

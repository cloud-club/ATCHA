package com.coludclub.atcha

import com.slack.api.Slack
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SlackTempController(
    private val slack: Slack,
    private val atchaSlackProperties: AtchaSlackProperties
) {

    @PostMapping("/send")
    fun sendMessage(
        @RequestParam message: String
    ) {
        val slackBot = slack.methodsAsync(atchaSlackProperties.token)

        slackBot.chatPostMessage{
            it.channel(atchaSlackProperties.channel)
                .text(message)
        }
    }

}

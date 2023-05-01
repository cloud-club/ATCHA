package com.coludclub.atcha

import com.slack.api.Slack
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SlackTempController(
    private val slack: Slack
) {

    @PostMapping("/send")
    fun sendMessage(
        @RequestParam message: String
    ) {
        val slackBot = slack.methodsAsync("xoxb-4969505888997-5185359962918-S09VH18SRsbwXstkOmTPmPGb")

        slackBot.chatPostMessage{
            it.channel("C04VA42SX08").text(message)
        }
    }

}

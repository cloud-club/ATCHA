package com.coludclub.atcha

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@Configuration
@ConfigurationProperties(prefix = "slack")
@Validated
class AtchaSlackProperties {
    @field:NotBlank
    var token: String = ""

    @field:NotBlank
    var channel: String = ""
}

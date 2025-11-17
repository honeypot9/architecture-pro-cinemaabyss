package yandex.practicum.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties(prefix = "app")
class ApplicationProperties {
    var migrationPercent: Int? = null
}
package yandex.practicum.service.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.net.URI
import java.util.concurrent.ThreadLocalRandom

@Component
class MigrationFilter : GlobalFilter, Ordered {

    private val logger = LoggerFactory.getLogger(this::class.java)

    private val config = MigrationConfig(
        migrationPercent = 50, // 50% трафика в новый сервис
        oldService = "http://localhost:8080",
        newService = "http://localhost:8081"
    )

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val path = exchange.request.uri.path

        // Только для migration путей
        if (!path.startsWith("/api/movies") && !path.startsWith("/api/users")) {
            return chain.filter(exchange)
        }

        logger.info("Processing: {} {}", exchange.request.method, path)

        val shouldRouteToNewService = shouldRouteToNewService(config.migrationPercent)
        val targetService = if (shouldRouteToNewService) config.newService else config.oldService

        // В методе filter после определения targetService:
        val modifiedRequest = exchange.request.mutate().apply {
            header("X-Migration-Service", if (shouldRouteToNewService) "new" else "old")
            header("X-Migration-Percent", config.migrationPercent.toString())
            header("X-Target-Service", targetService)
        }.build()

        logger.info("Migration: {}% -> {}", config.migrationPercent, if (shouldRouteToNewService) "NEW" else "OLD")

        //Создаем новый маршрут вместо установки только URI
        val targetUri = URI.create("$targetService$path")

        val newRoute = Route.async()
            .id("migration-route-${System.currentTimeMillis()}")
            .uri(targetUri)
            .predicate { true }
            .order(0)
            .build()

        val modifiedExchange = exchange.mutate()
            .request(modifiedRequest)
            .build()
            .apply {
                attributes[ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR] = newRoute
                attributes[ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR] = targetUri
            }

        logger.info("Routed to: {}", targetUri)
        logger.info("Migration completed")

        return chain.filter(modifiedExchange)
    }

    override fun getOrder(): Int = Ordered.HIGHEST_PRECEDENCE

    private fun shouldRouteToNewService(migrationPercent: Int): Boolean {
        if (migrationPercent >= 100) return true
        if (migrationPercent <= 0) return false
        return ThreadLocalRandom.current().nextInt(100) < migrationPercent
    }

    data class MigrationConfig(
        val migrationPercent: Int = 0,
        val oldService: String = "",
        val newService: String = ""
    )
}
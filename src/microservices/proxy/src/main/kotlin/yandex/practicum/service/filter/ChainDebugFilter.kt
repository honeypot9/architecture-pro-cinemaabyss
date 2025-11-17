package yandex.practicum.service.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class LoggingFilter : GlobalFilter {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        logger.info("📨 Incoming request: {} {}", exchange.request.method, exchange.request.uri.path)

        return chain.filter(exchange).doOnSuccess {
            logger.info("✅ Response sent: {}", exchange.response.statusCode)
        }
    }
}
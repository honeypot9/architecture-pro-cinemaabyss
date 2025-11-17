////package yandex.practicum.service.filter
//
//// Для Publisher
//
//// Для работы с байтами и строкой
//import org.slf4j.LoggerFactory
//import org.springframework.cloud.gateway.filter.GatewayFilterChain
//import org.springframework.cloud.gateway.filter.GlobalFilter
//import org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter
//import org.springframework.cloud.gateway.route.Route
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils
//import org.springframework.core.Ordered
//import org.springframework.stereotype.Component
//import org.springframework.web.server.ServerWebExchange
//import reactor.core.publisher.Mono
//import java.net.URI
//
//
//@Component
//class AfterRouteFilter : GlobalFilter, Ordered {
//
//    val logger = LoggerFactory.getLogger(this::class.java)
//
//    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
//        // Получаем все доступные атрибуты маршрутизации
//        val route = exchange.getAttribute<Route>(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR)
//        val targetUri = exchange.getAttribute<URI>(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR)
//        val originalUri = exchange.getAttribute<URI>(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR)
//
//        logger.info("=== Gateway Routing Information ===")
//
//        if (route != null) {
//            logger.info("📍 Маршрут: ${route.id}")
//            logger.info("   Конфигурационный URI: ${route.uri}")
//        }
//
//        logger.info("🎯 Конечный целевой URI: $targetUri")
//        logger.info("🔗 Оригинальный URI: $originalUri")
//
//        // Дополнительная отладочная информация
//        val routeId = exchange.getAttribute<String>(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR)
//        val routeUrl = exchange.getAttribute<URI>(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR)
//
//        logger.info("📋 Route ID: $routeId")
//        logger.info("🌐 Route URL: $routeUrl")
//
//        logger.info("===================================")
//
//        return chain.filter(exchange)
//    }
//
//    override fun getOrder(): Int = RouteToRequestUrlFilter.ROUTE_TO_URL_FILTER_ORDER.plus(1)
//
//    private fun modifyResponse(originalBody: String): String {
//        // Логика модификации ответа
//        return originalBody
//    }
//}
//package yandex.practicum.service.filter
//
//import org.slf4j.LoggerFactory
//import org.springframework.cloud.gateway.filter.GatewayFilterChain
//import org.springframework.cloud.gateway.filter.GlobalFilter
//import org.springframework.cloud.gateway.filter.RouteToRequestUrlFilter.ROUTE_TO_URL_FILTER_ORDER
//import org.springframework.cloud.gateway.route.Route
//import org.springframework.cloud.gateway.support.ServerWebExchangeUtils
//import org.springframework.core.Ordered
//import org.springframework.stereotype.Component
//import org.springframework.web.server.ServerWebExchange
//import reactor.core.publisher.Mono
//
//@Component
//class PreRouteFilter : GlobalFilter, Ordered {
//
//    private val logger = LoggerFactory.getLogger(this::class.java)
//
//    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
//        logger.info("=== 🚦 ПЕРЕД RouteToRequestUrlFilter ===")
//
//        // 1. Основная информация о запросе
//        logger.info("📨 Original Request: ${exchange.request.method} ${exchange.request.uri}")
//        logger.info("🌐 Scheme: ${exchange.request.uri.scheme}")
//        logger.info("📍 Host: ${exchange.request.uri.host}")
//        logger.info("🔢 Port: ${exchange.request.uri.port}")
//        logger.info("🛣️ Path: ${exchange.request.uri.path}")
//        logger.info("🔍 Query: ${exchange.request.uri.query}")
//
//        // 2. Информация о маршруте (если уже определен)
//        val route = exchange.getAttribute<Route>(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR)
//        if (route != null) {
//            logger.info("📍 Route ID: ${route.id}")
//            logger.info("🎯 Route URI: ${route.uri}")
// //           logger.info("📋 Predicates: ${route.predicates}")
//            logger.info("⚙️ Filters: ${route.filters}")
//            logger.info("📊 Metadata: ${route.metadata}")
//        } else {
//            logger.info("❌ Route: еще не определен")
//        }
//
//        // 3. Все атрибуты exchange
//        logger.info("📦 All attributes:")
//        exchange.attributes.forEach { (key, value) ->
//            if (key.toString().contains("gateway", ignoreCase = true) ||
//                key.toString().contains("route", ignoreCase = true)) {
//                logger.info("   $key = $value")
//            }
//        }
//
//        // 4. Заголовки запроса
//        logger.info("📋 Headers:")
//        exchange.request.headers.forEach { (key, values) ->
//            logger.info("   $key: ${values.joinToString()}")
//        }
//
//        logger.info("==============================================")
//
//        return chain.filter(exchange)
//    }
//
//    override fun getOrder(): Int = ROUTE_TO_URL_FILTER_ORDER.minus(1) // Непосредственно перед RouteToRequestUrlFilter (10000)
//}
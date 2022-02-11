package ao.mobile.market

import ao.mobile.passes.PassesService
import org.model.sm.Component
import org.model.sm.System

class MarketService(ps: PassesService, services: System)
    : Component("Marketplace", "Сервис управления пропусками")
{
    init {
        accessTo(ps, "receiveEvent")
            .byTechnology("eventBus")
            .set("Event", ps.parkingCompleteEvent)

        accessTo(services, "Интеграция с поставщиком сервиса Маркетплейса")
            .byTechnology("HTTP/REST")
    }

}
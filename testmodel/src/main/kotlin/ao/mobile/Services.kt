package ao.mobile

import org.model.core.Model
import org.model.sm.*


class SmartbuildingStructure : Model("Структурная модель Smart Building", "")
{
    val mobileApp = System("Mobile Application", "Мобильное приложение")
    val env = SmartBuildingEnvironment()
    val mbe = Backend(env)

    init {
        mobileApp.accessTo(mbe.svcMarket, "обращается")
            .byTechnology("HTTP/REST").set("contract", "BE.V2.mobile-market")
    }

}


class Backend(val env : SmartBuildingEnvironment) : System("MBE.V2") {

    val svcPasses = PassesService()
    val svcMarket = MarketService(svcPasses, env.services)

}

class SmartBuildingEnvironment : Model("Системы в окружении Smart building", "")
{
    val payments = System("Payments", "Интернет эквайринг")
    val services = System("Services", "Система учета услуг и платежей")

    init {
        services.accessTo(payments, "выставляет счета на оплату")
    }
}

class PassesService : Component("Passes", "Сервис управления пропусками")
{
    val parkingCompleteEvent: String = "ParkingCompleteEvent"
}

class MarketService(ps: PassesService, services: System)
    : Component("Marketplace", "Сервис управления пропусками")
{
    init {

        this.accessTo(ps, "receiveEvent")
            .byTechnology("eventBus")
            .set("Event", ps.parkingCompleteEvent)

        this.accessTo(services, "Интеграция с поставщиком сервиса Маркетплейса")
            .byTechnology("HTTP/REST")

    }

}
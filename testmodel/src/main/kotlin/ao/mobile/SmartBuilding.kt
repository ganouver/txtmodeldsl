package ao.mobile

import ao.mobile.market.MarketService
import ao.mobile.passes.PassesService
import org.model.core.Model
import org.model.sm.*
import org.model.views.ServiceStructureView
import org.model.views.View


class SmartbuildingStructure () : Model("Структурная модель Smart Building", "")
{
    val mobileApp = System("Mobile Application", "Мобильное приложение")
    val env = SmartBuildingEnvironment()
    val mbe = Backend(env )

    init {
        mobileApp.accessTo(mbe.svcMarket, "обращается")
            .byTechnology("HTTP/REST").set("contract", "BE.V2.mobile-market")
    }

    override fun buildViews(): List<View> {
        return listOf(
            ServiceStructureView("Smart-Building-Structure", "Структурная модель Smart Building",
                children()
                    .union(mbe.children())
                    .union(env.children())
                    .toList()
            )
        )
    }
}


class Backend(env : SmartBuildingEnvironment) : System("MBE.V2") {

    val svcPasses = PassesService()
    val svcMarket = MarketService(svcPasses, env.services)

}

class SmartBuildingEnvironment : Model("Системы в окружении Smart building", "")
{
    val payments = System("Payments", "Интернет эквайринг", extern = true)
    val services = System("Services", "Система учета услуг и платежей", extern = true)

    init {
        services.accessTo(payments, "выставляет счета на оплату")
    }
}


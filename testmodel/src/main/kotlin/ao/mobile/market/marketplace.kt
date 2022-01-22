package ao.mobile.market


import org.model.*
import org.model.core.*
import org.model.sm.Container
import org.model.sm.Role
import org.model.sm.System

class MobileAppSystem(parent : Model, ID : String) : System("Мобильное приложение")
   val mapp = Container("Mobile application", Technology = "iOS/Android")
}

class MarketModel : Model("Маркетплейс", "") {

    val customer = Role( "Покупатель/потребитель услуг")

    //systems
    val bitrix = System("bitrix24", extern = true)
    val mapps = MobileAppSystem(this, "mapp_system")

    init {
        worker
            .need("inform_residents", "донести до жителей информацию о доступных услугах/товарах", "чтобы жители могли воспользоваться услугами и оплатить их")

        resident
            .inherit(customer)

        customer
            .need("know", "узнать о предлагаемых УК услугах/товарах", "чтобы принять решение об их приобретении")
            .need("find and order", " найти и заказать нужную услугу/товар ", " чтобы воспользоваться предлагаемой услугой")
            .act(
                usecase("view_catalog", "Просмотр каталога услуг")
                    .realize("know"))
            .act()

        worker.use(bitrix, "Настраивает каталог товаров")
        resident.use(mapps)

        mapps.accessTo(bitrix, "Использует API маркетплейса")
    }

}

class SmartBuilding : Model("Умное здание", "") {
    object Services {
        val market = MarketModel()
    }

    val worker = Role("Работник УК")
    val resident = Role( "Житель ")
        .inherits(Services.market.customer)

}
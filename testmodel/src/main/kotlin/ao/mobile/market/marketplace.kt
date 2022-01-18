package ao.mobile.market


import org.model.*
import org.model.core.*
import org.model.sm.System

class MobileAppSystem(parent : Model, ID : String) : System(parent, ID, "Мобильное приложение", false,"" ) {
   val mapp = container("app","Mobile application","iOS/Android"   )

}

class MarketModel : Model("marketplace", "Маркетплейс") {

    val worker = role("worker", "Работник УК")
    val resident = role("Resident", "Житель ")
    val customer = role("Customer", "Покупатель/потребитель услуг")


    //systems
    val bitrix = systemExt("bitrix", "Bitrix24")
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


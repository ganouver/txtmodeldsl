package ao.mobile.market


import org.model.*
import org.model.core.*
import org.model.sm.Role
import org.model.sm.System

class MobileAppSystem(parent : Model, ID : String)
      : System(parent, ID, "Мобильное приложение", false,"" ) {
   val mapp = container("app","Mobile application","iOS/Android"   )

   val be   = container("be","Backend",".NET Core"   )

}

class MarketModel
      : Model("marketplace", "Маркетплейс") {

    val worker = role("worker", "Работник УК")
    val resident = role("Resident", "Житель ")
    val customer = role("Customer", "Покупатель/потребитель услуг")


    //systems
    val bitrix = systemExt("bitrix", "Bitrix24")
    val mapps = MobileAppSystem(this, "mapp_system")

    init {
        //user stories
        story(worker, "Настраивает каталог товаров", "информировать потребителей о доступных товарах")
        worker
            .need("inform_residents", "донести до жителей информацию о доступных услугах/товарах", "чтобы жители могли воспользоваться услугами и оплатить их")

        resident
            .inherit(customer)

        story(customer, "Изучает содержимое каталога товаров", "узнать о предлагаемых УК услугах/товарах и принять решение об их приобретении")
        customer
            .need("know", "")
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
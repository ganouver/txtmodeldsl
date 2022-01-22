package ao.mobile.market


import org.model.*
import org.model.core.*
import org.model.sm.*

class MobileAppSystem : System("Мобильное приложение") {
   val mapp = Container("Mobile application", Technology = "iOS/Android")
}

class MarketModel : Model("Маркетплейс", "") {

    //systems
    val bitrix = System("bitrix24", extern = true)
    val mappsys = MobileAppSystem()

    //roles
    val customer = Role( "Покупатель/потребитель услуг")
    val customer_need_know = customer.need("Узнать о предлагаемых УК услугах/товарах")
    val customer_need_find = customer.need("Найти и заказать нужную услугу/товар ",
                                       " чтобы воспользоваться предлагаемой услугой")


    val marketAdmin = Role("Администратора маркетплейса")

    val supplier = Role("Поставщик услуг")
    val supplier_need_inform = supplier.need("Донести до жителей информацию о доступных услугах/товарах",
                                         "чтобы жители могли воспользоваться услугами и оплатить их")



    val case_view_catalog = Usecase("Просмотр каталога товаров и услуг").apply {
        participant(customer)
        realize(customer_need_know)
        realize(customer_need_find)
    }

    val case_add_order = Usecase("Создание заказа по содержимому корзины").apply {

    }

    val case_setup_catalog = Usecase("Настройка каталога товаров и услуг").apply {
        participant(marketAdmin)
        realize(supplier_need_inform)
    }


    init {
        customer.use(mappsys, "Выбирает и заказывает товар")
        marketAdmin.use(bitrix, "Настраивает каталог товаров")
        mappsys.accessTo(bitrix, "Использует API маркетплейса")
    }

}

class SmartBuilding : Model("Умное здание", "") {
    object Services {
        val market = MarketModel()
    }

    val worker = Role("Работник УК").apply {
        inherits(Services.market.marketAdmin)
    }
    val resident = Role( "Житель ").apply {
        inherits(Services.market.customer)
    }

}
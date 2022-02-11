package ao.mobile.market


import ao.mobile.SmartbuildingStructure
import org.model.core.*
import org.model.sm.*
import org.model.views.ServiceStructureView
import org.model.views.UsecaseView
import org.model.views.View

class MarketUsageModel(val smartBuilding : SmartbuildingStructure) : Model("Маркетплейс", "") {

    //roles
    val customer = Role( "Покупатель/потребитель услуг")
    val customer_need_know = customer.need("Узнать о предлагаемых УК услугах/товарах")
    val customer_need_order = customer.need("Найти и заказать нужную услугу/товар ",
                                       " чтобы воспользоваться предлагаемой услугой")
    val  customer_need_pay = customer.need("оплатить сделанный заказ")
    val customer_need_chat = customer.need("обсудить детали заказа с поставщиком")


    val marketAdmin = Role("Администратор маркетплейса")

    val supplier = Role("Поставщик услуг")
    val supplier_need_inform = supplier.need("Донести до жителей информацию о доступных услугах/товарах",
                                         "чтобы жители могли воспользоваться услугами и оплатить их")
    var supplier_need_sale = supplier.need("Продать товар или услугу")

    val case_view_catalog = Usecase("Просмотр каталога товаров и услуг").apply {
        participant(customer)
        realize(customer_need_know)
        realize(customer_need_order)
    }

    val case_add_order = Usecase("Создание заказа по содержимому корзины").apply {
        participant(customer)
        realize(customer_need_order)
    }

    val case_payment = Usecase("Оплата сделанного заказа").apply {
        participant(customer)
        realize(customer_need_pay)
    }

    val case_chat = Usecase("Чат с поставщиком по заказу").apply {
        participant(customer)
        participant(supplier)
        realize(customer_need_chat)
        realize(supplier_need_sale)
    }

    val case_setup_catalog = Usecase("Настройка каталога товаров и услуг").apply {
        participant(marketAdmin)
        realize(supplier_need_inform)
    }

    val case_manage_orders = Usecase("Управление заказами").apply {
        participant(supplier)
        realize(supplier_need_sale)
        invoke(Usecase("Управление заказами").apply {
            participant(supplier)
            realize(supplier_need_sale)
        })
    }

    init {
        customer.use(smartBuilding.mobileApp, "Выбирает и заказывает товар")
        marketAdmin.use(smartBuilding.env.services, "Настраивает каталог товаров")
        supplier.use(smartBuilding.env.services, "Взаимодействует с покупателями")
    }

    override fun buildViews(): List<View> {
        return listOf(
                UsecaseView("market_cases", "Маркетплейс - сценарии использования",
                    this.childrenOf<Usecase>()),
                ServiceStructureView("market_structure", "Маркетплейс - структурная диаграмма",
                    smartBuilding.mbe.svcMarket.linkedItems(1)
                        .union(listOf(smartBuilding.env.payments, smartBuilding))
                        .union(childrenOf<Role>())
                        .distinct()
                        .toList()
                )
            )
    }
}


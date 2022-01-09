package ao.mobile.market


import org.model.accessTo
import org.model.core.Model
import org.model.core.container
import org.model.core.person
import org.model.core.systemExt
import org.model.sm.System
import org.model.use

class MobileAppSystem(parent : Model, ID : String) : System(parent, ID, "Мобильное приложение", false,"" ) {
   val mapp = container("app","Mobile application","iOS/Android"   )

}

class MarketModel : Model("marketplace", "Маркетплейс") {

    val worker = person("worker", "Работник УК")
    val resident = person("Resident", "Житель ")

    //systems
    val bitrix = systemExt("bitrix", "Bitrix24")
    val mapps = MobileAppSystem(this, "mapp_system")

    init {
        worker.use(bitrix, "Настраивает каталог товаров")
        resident.use(mapps)

        mapps.accessTo(bitrix, "Использует API маркетплейса")
    }

}
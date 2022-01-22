package org.model.sm

import org.model.core.Item
import org.model.core.LinkStereotypes

open class Role(Label: String, Description: String = "") : Item(Label, Description) {
    fun inherits(role: Role) {
        super.linkTo(role, "включает", LinkStereotypes.inheritance)
    }

    fun use(system: System, label: String) {
        linkTo(system, label, LinkStereotypes.usage)
    }

    fun need(label: String, description : String = ""): Need {
        return Need(label, description).also {
            this.linkTo(it, "", LinkStereotypes.needs)
        }
    }
}

//нужда, потребность роли
class Need(Label: String, Description: String = "") : Item(Label, Description)

//сценарий использования
class Usecase(Label: String, Description: String = "") : Item(Label, Description){
    //добавить потребность, реализуемую сценарием
    fun realize(n : Need, label : String = "") {
        this.linkTo(n, label, LinkStereotypes.realize)
    }

    //добавить участника сценария
    fun participant(p: Role, label : String = "") {
        this.linkTo(p, label, LinkStereotypes.participant)
    }

    fun invoke(usecase: Usecase) {
        linkTo(usecase, "<<invoke>>", LinkStereotypes.depends)
    }

}



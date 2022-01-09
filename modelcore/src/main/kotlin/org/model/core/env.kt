package org.model.core

import org.model.sm.Container
import org.model.sm.Person
import org.model.sm.System


fun Model.person(ID: String, Label: String, Description: String = "") : Person {
    return Person(this, ID, Label , Description)
}

fun Model.systemExt(ID: String, Label: String, Description: String = "") : System {
    return System(this, ID, Label, true, Description)
}

fun ContainerItem.system(ID: String, Label: String, Description: String = "") : System {
    return System(this, ID, Label, false, Description)
}

fun System.container(ID: String, Label: String, Technology : String, Description: String = "") : Container {
    return Container(this, ID, Label, Technology, false, Description)
}

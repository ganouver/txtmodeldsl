package org.model.core

import org.model.sm.Container
import org.model.sm.Role
import org.model.sm.System
import org.model.sm.Usecase


fun Model.role(ID: String, Label: String, Description: String = "") : Role {
    return Role(this, ID, Label , Description)
}

fun Model.usecase(ID: String, Label: String, Description: String = "") : Usecase {
    return Usecase(this, ID, Label, Description)
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


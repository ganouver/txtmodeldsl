package org.model.sm



open  class C4Item(Label: String, Description: String, val extern: Boolean) : org.model.core.Package(Label, Description) {

}

open class Component(Label: String, Description: String = "", extern: Boolean = false) : C4Item(Label, Description, extern)
open class Container(Label: String, Description: String = "", val Technology: String = "", extern: Boolean = false) : C4Item(Label, Description, extern)
open class System(Label: String, Description: String = "", extern: Boolean = false) : C4Item(Label, Description, extern)


package org.model

class Model() {
    private var _title : String = ""
    private val _elements : MutableMap<String, Item> = HashMap()
    private val _linksFrom : MutableMap<String, Link> = HashMap()
    private  val _linksTo : MutableMap<String, Link> = HashMap()

    fun items(): Collection<Item> {
        return _elements.values
    }

    fun title(t : String) { _title = t}

    fun person(ID: String, Label: String, Description: String = "") : Person
    {
        return rememberIt(
            Person(this, ID, Label, Description))
    }

    // создает новый объект "Система" в модели
    fun system(ID: String, Label: String, Description: String = ""): System {
        return rememberIt(
            System(this, ID, Label, Description))
    }

    fun System.accessTo(other: System, label : String = ""): System {
        targetModel.link(this.ID, other.ID, label, LinkStereotypes.access)
        return this
    }

    fun Person.use(target: System, label : String = "") {
        targetModel.link(this.ID, target.ID, label, LinkStereotypes.use)
    }

    private fun <T : Item> rememberIt(s: T): T {
        _elements[s.ID] = s
        return s
    }

    // создает связь между двумя элементами модели
    fun link(from: String, to: String, label : String = "", stereotype : LinkStereotypes = LinkStereotypes.default) {
        val l = Link(from, to, label, stereotype)
        _linksFrom[from] = l
        _linksTo[to] = l
    }

}
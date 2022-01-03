package org.model

class Model() {
    private var _title : String = ""
    private val _elements : MutableMap<String, Item> = HashMap<String, Item>()

    fun title(t : String) { _title = t}

    fun system(ID: String, Label: String, Description: String = ""): System {
        val s = System(ID, Label, Description)
        _elements[s.ID] = s;
        return  s;
    }
}
package org.model

import java.io.File

class View {
    var title : String = ""
    var elements : Collection<Item> = ArrayList()


    fun title(s: String) {
        title = s
    }

    fun elements(els: Collection<Item>) {
        elements = els
    }

    fun render_puml(path2file: String) {
        val myfile = File(path2file)

        myfile.printWriter().use { out ->
            out.println("@startuml")

            out.println("title $title")

            for (item in elements) {
                item.render(out)
            }

            out.println("@enduml")
        }
    }
}
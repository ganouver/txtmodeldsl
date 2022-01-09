package org.model.views

import org.model.core.Model
import java.io.File
import java.nio.file.Path
import kotlin.io.path.name

class Systems(val mm: Model) : BaseView(Path.of(mm.ID, "systems").name) {

    var RenderContainmentAsLink = false

    override fun Render() {
        val myfile = File(fileName + ".puml")

        myfile.printWriter().use { out ->
            out.println("@startuml")

            out.println("title ${mm.Label}")

            out.println("@enduml")
        }
    }

    fun ContainmentAsLink(b: Boolean) : Systems{
        RenderContainmentAsLink = b
        return this
    }
}
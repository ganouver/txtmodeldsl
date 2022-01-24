package org.model.views

import org.model.core.Item
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.PrintWriter

abstract class View(val name : String) {
    abstract fun render(out : OutputStream)
    abstract fun getFileName(): String
}

abstract class PlantUmlView( name: String, val title: String) : View(name) {
    protected open fun renderHeader(header: PrintWriter)
    {
        with(header) {
            write("title ${title.ifBlank { name }} - Usecase Diagram\n")
        }
    }

    protected abstract fun renderElements(elements: PrintWriter)
    protected abstract fun renderRelations(relations: PrintWriter)


    val identities = mutableMapOf<Item, String>()
    val counters = mutableMapOf<String, Int>()

    protected fun identityOf(item : Item, prefix : String = "") : String {
        if (!identities.containsKey(item)) {
            identities[item] = nextID(prefix)
        }
        return identities[item]!!;
    }

    private fun nextID(prefix: String): String {
        assert(prefix.isNotEmpty())
        if (!counters.containsKey(prefix))
            counters[prefix] = 0

        counters[prefix] = counters[prefix]!! + 1
        return "$prefix${counters[prefix]}"
    }

    override fun getFileName(): String {
        return "$name.puml"
    }

    override fun render(out: OutputStream) {
        val header = ByteArrayOutputStream()
        val elements = ByteArrayOutputStream()
        val relations = ByteArrayOutputStream()

        val writeStream = { s : ByteArrayOutputStream, p : (PrintWriter) -> Unit ->
                        with (PrintWriter(s, true, Charsets.UTF_8))
                            {
                                p(this)
                                write("\n")
                                flush()
                            }
                         out.write(s.toByteArray())
                        }

        out.write("@startuml\n".toByteArray())
        writeStream(header, ::renderHeader)
        writeStream(elements, ::renderElements)
        writeStream(relations, ::renderRelations)
        out.write("\n@enduml\n".toByteArray())
    }

}
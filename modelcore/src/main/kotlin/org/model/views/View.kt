package org.model.views

import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.io.PrintWriter

abstract class View(val name : String) {
    abstract fun render(out : OutputStream)
    abstract fun getFileName(): String
}

abstract class PlantUmlView(name: String) : View(name) {
    protected abstract fun renderHeader(header: PrintWriter)
    protected abstract fun renderElements(elements: PrintWriter)
    protected abstract fun renderRelations(relations: PrintWriter)

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
        writeStream(header, ::renderHeader)
        writeStream(elements, ::renderElements)
        writeStream(relations, ::renderRelations)
    }

}
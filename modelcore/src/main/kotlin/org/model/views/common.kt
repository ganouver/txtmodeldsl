package org.model.views

import org.model.core.Model
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.exists
import kotlin.io.path.isDirectory

object views {
    private val allviews : MutableList<BaseView> = ArrayList()

    internal fun rememberView(v : BaseView)
    {
        allviews.add(v)
    }

    fun buildAll() {
        println("Rendering views... ")

        allviews.forEach {

            print("${it.title()} ... ")
            it.Render()

            println("OK")
        }

    }

    fun buildAllView(model: Model) {

        var outdir = Path("out")
        if (!outdir.exists())
            outdir.createDirectory()
        assert(outdir.isDirectory())

        val views = model.buildViews()
        for (view in views) {
            val viewfile = File(outdir.toFile(), view.getFileName())
            viewfile.outputStream().use {
                view.render(it)
            }
        }
    }
}


abstract  class BaseView(protected var fileName : String)
{
    fun filename(newName : String){
        fileName = newName
    }

    fun title() : String {
        return fileName
    }

    init {
        views.rememberView(this);
    }

    abstract fun Render()
}


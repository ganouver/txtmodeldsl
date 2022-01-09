package org.model.views

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


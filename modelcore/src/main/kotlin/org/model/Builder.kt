package org.model

object Builder {
     private var model : Model? = null;

    fun begin( m : Model) : Model{
        model = m;
        return m;
    }
}

fun model(build : Model.() -> Unit) : Model {
    return  Model().apply(build);
}
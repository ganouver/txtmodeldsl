package org.model.test

import org.model.Builder
import org.model.Model
import org.model.model

fun myModel()
{
    val m = model {
        title("Модель системы U ")
        system("s1", "System1")
        system("s2", "System2")
    }
}
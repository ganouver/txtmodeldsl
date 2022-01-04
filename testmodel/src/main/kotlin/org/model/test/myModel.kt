package org.model.test

import org.model.Model
import org.model.model

fun myModel() : Model
{
    return model {
        title("Модель системы U ")

        val s2 = system("s2", "System2")
        val s1 = system("s1", "System1")
            .accessTo(s2)

        person("user", "Пользователь")
            .use(s1)
    }
}
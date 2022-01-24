import ao.mobile.market.MarketModel
import org.model.views.views

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")

    val mm = MarketModel()
    mm.validate() // валидация модели на целостность
    views.buildAllView(mm) //тут запускается генерация всех продекларированных выше представлений
/*
    val myModel = mysysmodel()

    //представления могут декларироваться как в этой функции, так и непосредственно в конструкторах классов и объектов
    Views.System
        .Usecases(myModel.useCases())
        .filename("all usecases") //можно переопределить имя файлв


    View().apply{
        title("Context diagram")


        elements(myModel.items().filter {
            (it is System) or (it is Person)
        })
    }.render_puml("context.puml")
*/
}
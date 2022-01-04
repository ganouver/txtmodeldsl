import org.model.Person
import org.model.View
import org.model.System

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")

    val myModel = org.model.test.myModel()

    View().apply{
        title("Context diagram")
        elements(myModel.items().filter {
            (it is System) or (it is Person)
        })
    }.render_puml("context.puml")
}
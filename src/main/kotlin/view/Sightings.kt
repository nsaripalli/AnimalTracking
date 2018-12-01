package view

import tornadofx.*
import java.time.LocalDate
import java.time.Period

class Sightings : View("Sightings") {
    class Person(val id: Int, val name: String, val birthday: LocalDate) {
        val age: Int get() = Period.between(birthday, LocalDate.now()).years
    }
    private val persons = listOf(
        Person(1,"Samantha Stuart", LocalDate.of(1981,12,4)),
        Person(2,"Tom Marks", LocalDate.of(2001,1,23)),
        Person(3,"Stuart Gills", LocalDate.of(1989,5,23)),
        Person(3,"Nicole Williams", LocalDate.of(1998,8,11))
    ).observable()

    override val root = hbox {
        vbox {
            hbox {
                //TODO("only allow if a scientist.")
                button("Create Sighting") {
                    action {
                        replaceWith<CreateSighting>()
                    }
                }
            }

            // select * from watch table query
            tableview(persons) {
                readonlyColumn("ID",Person::id)
                readonlyColumn("Name", Person::name)
                readonlyColumn("Birthday", Person::birthday)
                readonlyColumn("Age",Person::age)
            }
            //TODO("copy and pasted from Home.kt")
            button("Home") {
                action {
                    replaceWith<Home>()
                }
            }
        }

    }
}

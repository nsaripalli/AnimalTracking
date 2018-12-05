package view

import controller.Watch
import controller.watch1
import tornadofx.*
import java.time.LocalDate
import java.time.Period

class Watches : View("My View") {

    class Person(val id: Int, val name: String, val birthday: LocalDate) {
        val age: Int get() = Period.between(birthday, LocalDate.now()).years
    }
    private val persons = listOf(
        Person(1,"Samantha Stuart",LocalDate.of(1981,12,4)),
        Person(2,"Tom Marks",LocalDate.of(2001,1,23)),
        Person(3,"Stuart Gills",LocalDate.of(1989,5,23)),
        Person(3,"Nicole Williams",LocalDate.of(1998,8,11))
    ).observable()
    private val watches = listOf(
        watch1
    ).observable()

    override val root = hbox {
        vbox {
            hbox {
                //TODO("only allow if a scientist.")
                button("Create Watch") {
                    action {
                        // go to create watch form
                        replaceWith<CreateWatch>()
                    }
                }
            }

            // select * from watch table query
            tableview(watches) {
                readonlyColumn("ID", Watch::watchID)
                readonlyColumn("Latitude", Watch::latitude)
                readonlyColumn("Longitude", Watch::longitude)
                readonlyColumn("Radius", Watch::radius)
                readonlyColumn("Start", Watch::startDate)
                readonlyColumn("End", Watch::endDate)
                readonlyColumn("Species", Watch::species)
                readonlyColumn("Scientist", Watch::scientist)
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
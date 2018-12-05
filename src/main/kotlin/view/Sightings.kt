package view

import controller.Sighting
import controller.Watch
import controller.sighting1
import controller.watch1
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
    private val sightings = listOf(
        sighting1
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
            tableview(sightings) {
                readonlyColumn("ID", Sighting::sightingID)
                readonlyColumn("Latitude", Sighting::latitude)
                readonlyColumn("Longitude", Sighting::longitude)
                readonlyColumn("Notes", Sighting::notes)
                readonlyColumn("Species", Sighting::species)
                readonlyColumn("Observer", Sighting::observer)
                readonlyColumn("Watch", Sighting::watch)
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

package view

import controller.Observer
import controller.Sighting
import controller.sighting1
import tornadofx.*
import java.time.LocalDate
import java.time.Period

val Sighting.commonName get() = this.species.commonName
val Sighting.name get() = this.observer.name
//TODO("Tanner look at this")
val Sighting.watchName get() = this.watch?.watchID

class Sightings : View("Sightings") {
    class Person(val id: Int, val name: String, val birthday: LocalDate) {
        val age: Int get() = Period.between(birthday, LocalDate.now()).years
    }

    private val persons = listOf(
        Person(1, "Samantha Stuart", LocalDate.of(1981, 12, 4)),
        Person(2, "Tom Marks", LocalDate.of(2001, 1, 23)),
        Person(3, "Stuart Gills", LocalDate.of(1989, 5, 23)),
        Person(3, "Nicole Williams", LocalDate.of(1998, 8, 11))
    ).observable()
    private val sightings = listOf(
        sighting1
    ).observable()

    override val root = hbox {
        style {
            backgroundColor = multi(javafx.scene.paint.Paint.valueOf("#c9daf8ff"))
        }
        vbox {
            hbox {
                //TODO("only allow if a scientist.")
                button("Create Sighting") {
                    addClass(MyStyle.regularFont)
                    action {
                        replaceWith<CreateSighting>()
                    }
                }
            }

            // select * from watch table query
            tableview(sightings) {
                addClass(MyStyle.regularFont)
                readonlyColumn("ID", Sighting::sightingID)
                readonlyColumn("Latitude", Sighting::latitude)
                readonlyColumn("Longitude", Sighting::longitude)
                readonlyColumn("Notes", Sighting::notes)
                readonlyColumn("Species", Sighting::commonName)
                readonlyColumn("Observer", Sighting::name)
                readonlyColumn("Watch ID", Sighting::watchName)
            }
            //TODO("copy and pasted from Home.kt")
            button("Home") {
                addClass(MyStyle.regularFont)
                action {
                    replaceWith<Home>()
                }
            }
        }

    }
}

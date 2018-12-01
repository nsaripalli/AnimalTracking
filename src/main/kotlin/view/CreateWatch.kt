package view

import tornadofx.*

class CreateWatch : View("Create Watch Form") {
    override val root = form {
        fieldset("Log Watch") {
            field("Species Scientific Name") {
                textfield()
            }
            field("Latitude") {
                textfield()
            }
            field("Longitude") {
                textfield()
            }
            field("Radius") {
                textfield()
            }
            field("Start Date") {
                datepicker()
            }
            field("End Date") {
                datepicker()
            }
        }
        button("Commit") {
            action { println("Wrote to database!")}
        }
    }
}

package view

import tornadofx.*

class CreateSighting : View("My View") {
    override val root = form {
        fieldset("Log sighting") {
            field("Species Scientific name") {
                textfield()
            }
            field("quantity") {
                textfield()
            }
            field("latitude") {
                textfield()
            }
            field("longitude") {
                textfield()
            }
            field("notes") {
                textfield()
            }
            field("photo") {
                textfield()
            }

        }
        button("Commit") {
            action { println("Wrote to database!")}
        }
    }
}

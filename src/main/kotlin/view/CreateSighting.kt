package view

import tornadofx.*

class CreateSighting : View("My View") {
    override val root = form {
        fieldset("Personal Info") {
            field("First Name") {
                textfield()
            }
            field("Last Name") {
                textfield()
            }
            field("Birthday") {
                datepicker()
            }
        }
        fieldset("Contact") {
            field("Phone") {
                textfield()
            }
            field("Email") {
                textfield()
            }
        }
        button("Commit") {
            action { println("Wrote to database!")}
        }
    }
}

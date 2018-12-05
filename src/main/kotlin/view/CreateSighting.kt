package view

import tornadofx.*

class CreateSighting : View("My View") {
    override val root = form {
        fieldset("Log sighting") {
            addClass(MyStyle.regularFont)
            field("Species Scientific name") {
                addClass(MyStyle.regularFont)
                textfield() {
                    addClass(MyStyle.regularFont)
                }
            }
            field("quantity") {
                addClass(MyStyle.regularFont)
                textfield() {
                    addClass(MyStyle.regularFont)
                }
            }
            field("latitude") {
                addClass(MyStyle.regularFont)
                textfield() {
                    addClass(MyStyle.regularFont)
                }
            }
            field("longitude") {
                addClass(MyStyle.regularFont)
                textfield() {
                    addClass(MyStyle.regularFont)}
            }
            field("notes") {
                addClass(MyStyle.regularFont)
                textfield() {
                    addClass(MyStyle.regularFont)
                }
            }
            field("photo") {
                addClass(MyStyle.regularFont)
                textfield() {
                    addClass(MyStyle.regularFont)
                }
            }

        }
        button("Commit") {
            addClass(MyStyle.regularFont)
            action { println("Wrote to database!")}
        }
        button("Home") {
            addClass(MyStyle.regularFont)
            action {
                replaceWith<Home>()
            }
        }
    }
}

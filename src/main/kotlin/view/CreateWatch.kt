package view

import tornadofx.*

class CreateWatch : View("Create Watch Form") {
    override val root = form {
        fieldset("Log Watch") {
            addClass(MyStyle.regularFont)
            field("Species Scientific Name") {
                addClass(MyStyle.regularFont)
                textfield() {
                    addClass(MyStyle.regularFont)
                }
            }
            field("Latitude") {
                addClass(MyStyle.regularFont)
                textfield() {
                    addClass(MyStyle.regularFont)
                }
            }
            field("Longitude") {
                addClass(MyStyle.regularFont)
                textfield() {
                    addClass(MyStyle.regularFont)
                }
            }
            field("Radius") {
                addClass(MyStyle.regularFont)
                textfield() {
                    addClass(MyStyle.regularFont)
                }
            }
            field("Start Date") {
                addClass(MyStyle.regularFont)
                datepicker() {
                    addClass(MyStyle.regularFont)
                }
            }
            field("End Date") {
                addClass(MyStyle.regularFont)
                datepicker() {
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

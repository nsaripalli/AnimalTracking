package view

import tornadofx.*

class Watches : View("My View") {

    override val root = hbox {
        vbox {
            hbox {
                //TODO("only allow if a scientist.")
                button("Create Watch") {
                    action {
                        // go to create watch form
                    }
                }

                
            }
        }
    }
}
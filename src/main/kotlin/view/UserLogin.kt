package view

import javafx.geometry.Insets
import javafx.scene.layout.Priority
import javafx.scene.text.FontWeight
import tornadofx.*

class UserLogin : View() {

    // vbox = vertical, hbox = horizontal
    override val root = vbox {
        vbox {
            vboxConstraints {
                marginLeft = 20.0
            }
            hbox {
                label("User Login Page") {

                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        fontSize = 56.px
                        //TODO("change font to avenir")
                        addClass(MyStyle.regularFont)
                        // somehow increase font?
                    }

                }
                vboxConstraints {
                    marginBottom = 20.0
                    marginTop = 20.0
                }

            }
            hbox {
                // new user
                vbox {
                    hboxConstraints {
                        hGrow = Priority.ALWAYS
                    }
                    hbox {
                        label("Email") {
                            hboxConstraints { marginRight = 10.0}
                            addClass(MyStyle.regularFont)
                        }
                        textfield() {
                            addClass(MyStyle.regularFont)
                        }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }
                    hbox {
                        label("Full Name:") {
                            hboxConstraints { marginRight = 10.0}
                            addClass(MyStyle.regularFont)
                        }
                        textfield() {
                            addClass(MyStyle.regularFont)
                        }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }
                    hbox {
                        label("Optional Phone Number") {
                            hboxConstraints { marginRight = 10.0}
                            addClass(MyStyle.regularFont)
                        }
                        textfield() {
                            addClass(MyStyle.regularFont)
                        }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }
                    hbox {
                        label("Scientist?") {
                            hboxConstraints { marginRight = 10.0}
                            addClass(MyStyle.regularFont)
                        }
                        checkbox { }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }
                    button("Create user") {
                        action {
                            //TODO("validate user")
                            replaceWith<Home>()
                            println("handle creating a user")
                            // should validate this info with database, then store if okay to do.
                            vboxConstraints {
                                marginBottom = 20.0
                            }
                        }
                        addClass(MyStyle.regularFont)
                    }
                }
                // login existing user
                vbox {
                    hboxConstraints {
                        hGrow = Priority.ALWAYS
                    }
                    hbox {
                        label("Email") {
                            hboxConstraints { marginRight = 10.0}
                            addClass(MyStyle.regularFont)
                        }
                        textfield() {
                            addClass(MyStyle.regularFont)
                        }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }
                    button("Login") {
                        addClass(MyStyle.regularFont)
                        action {
                            //TODO("handle login")
                            replaceWith<Home>()
                            println("handle logging in")
                        }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }
                }
            }
        }
    }
}


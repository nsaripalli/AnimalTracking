package view

import controller.Observer
import controller.insertUser
import controller.userFromEmail
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Alert
import javafx.scene.layout.Priority
import javafx.scene.text.FontWeight
import tornadofx.*

class UserLogin : View() {
    private val loginEmail = SimpleStringProperty()

    private val fullName = SimpleStringProperty()
    private val newEmail = SimpleStringProperty()
    private val phone = SimpleStringProperty()
    private val scientist = SimpleBooleanProperty()


    override val root = vbox {
        style {
            backgroundColor = multi(javafx.scene.paint.Paint.valueOf("#c9daf8ff"))
        }
        vbox {
            vboxConstraints {
                marginLeft = 20.0
            }
            hbox {
                label("User Login Page") {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        fontSize = 56.px
                        addClass(MyStyle.regularFont)
                    }
                }
                vboxConstraints {
                    marginBottom = 20.0
                    marginTop = 20.0
                }

            }
            hbox {
                vbox {
                    hboxConstraints {
                        hGrow = Priority.ALWAYS
                    }
                    hbox {
                        label("Email") {
                            hboxConstraints { marginRight = 10.0 }
                            addClass(MyStyle.regularFont)
                        }
                        textfield {
                            bind(newEmail)
                            addClass(MyStyle.regularFont)
                        }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }
                    hbox {
                        label("Full Name:") {
                            hboxConstraints { marginRight = 10.0 }
                            addClass(MyStyle.regularFont)
                        }
                        textfield {
                            bind(fullName)
                            addClass(MyStyle.regularFont)
                        }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }

                    hbox {
                        label("Optional Phone Number") {
                            hboxConstraints { marginRight = 10.0 }
                            addClass(MyStyle.regularFont)
                        }
                        textfield {
                            bind(phone)
                            addClass(MyStyle.regularFont)
                        }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }
                    hbox {
                        label("Scientist?") {
                            hboxConstraints { marginRight = 10.0 }
                            addClass(MyStyle.regularFont)
                        }
                        checkbox {
                            bind(scientist)
                        }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }
                    button("Create user") {
                        action {
                            val user = Observer(
                                    observerID = null,
                                    name = fullName.value,
                                    email = newEmail.value,
                                    phone = phone.value,
                                    isScientist = scientist.value
                            )

                            insertUser(user)

                            replaceWith(Home(user))

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
                            hboxConstraints { marginRight = 10.0 }
                            addClass(MyStyle.regularFont)
                        }
                        textfield {
                            bind(loginEmail)
                            addClass(MyStyle.regularFont)
                        }
                        vboxConstraints {
                            marginBottom = 20.0
                        }
                    }
                    button("Login") {
                        addClass(MyStyle.regularFont)
                        action {
                            val user = userFromEmail(loginEmail.value)

                            if (user != null) {
                                replaceWith(Home(user))
                            } else {
                                alert(
                                        type = Alert.AlertType.WARNING,
                                        header = "Failed Login",
                                        content = "No user with that email was found"
                                )
                            }

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


package view

import javafx.stage.Stage
import tornadofx.*

class MainApp: App(UserLogin::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.isMaximized = true
        stage.isResizable = false
    }
}
package org.catinthedark.jvcrplotter.game.states

import org.catinthedark.jvcrplotter.game.States
import org.catinthedark.jvcrplotter.game.editor.Editor
import org.catinthedark.jvcrplotter.lib.IOC
import org.catinthedark.jvcrplotter.lib.states.IState

class StartNewGameState : IState {
    override fun onActivate() {
        val editor = Editor(
            mutableListOf(
                mutableListOf("INT", "42", ""),
                mutableListOf("MOV", "X", "0"),
                mutableListOf("MOV", "Y", "0"),
                mutableListOf("INT", "44", ""),
                mutableListOf("INT", "43", ""),
                mutableListOf("MOV", "X", "15"),
                mutableListOf("MOV", "Y", "15"),
                mutableListOf("INT", "44", "")
            )
        )
        IOC.put("editor", editor)
        IOC.put("currentTaskId", 0)
        IOC.put("instructionsCount", 0)
        IOC.put("state", States.WORKSPACE_SCREEN)
    }

    override fun onUpdate() {
    }

    override fun onExit() {
    }
}

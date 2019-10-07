package org.catinthedark.jvcrplotter.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Stage
import org.catinthedark.jvcrplotter.game.ui.HelperDialog
import org.catinthedark.jvcrplotter.lib.AfterBarrier
import org.catinthedark.jvcrplotter.lib.IOC
import org.catinthedark.jvcrplotter.lib.managed

val quickHelpText = "" +
    "   Quick manual\n" +
    "Registers: X, Y, A, B\n" +
    "INT X - control burner:\n" +
    "  X = 42 - raise head\n" +
    "  X = 43 - lower head\n" +
    "  X = 44 - move head to\n" +
    "    (X:Y) (registers)\n" +
    "MOV A B - put B in A\n" +
    "ADD A B - put A + B in A\n" +
    "JML C - jump (C + 1)\n" +
    "  lines up or down\n" +
    "CMP A B - compare A\n" +
    "  with B\n" +
    "JG/JE/JL C - jump if CMP\n" +
    "  result is <, = or > 0"


private val welcomeDialog = HelperDialog(
    640, 270, 500, 300,
    "Welcome to [RED]VychVyzhProm[],\n" +
        "comrade! This is our\n" +
        "workplace"
)

private val goalDialog = HelperDialog(
    200, 400, 480, 300,
    "Your task is to program\n" +
        "this burner to draw\n" +
        "patterns given in your\n" +
        "assignment"
)

private val clickAssignmentDialog = HelperDialog(
    600, 400, 480, 200,
    "Click here to view\n" +
        "today's assignment",
    States.TASK_SCREEN
)

private val assignmentDialog = HelperDialog(
    700, 270, 480, 300,
    "This is your assignment\n" +
        "for today. Simple, huh?\n" +
        "Press [RED]ESC[] to go back to\n" +
        "our workplace",
    States.WORKSPACE_SCREEN
)

private val clickCodeEditorDialog = HelperDialog(
    200, 400, 480, 300,
    "Click here to look at\n" +
        "the burner controller",
    States.CODE_EDITOR_SCREEN
)

private val thisIsControllerDialog = HelperDialog(
    400, 450, 490, 250,
    "This is our burner\n" +
        "controller.\n" +
        "Careful! It's expensive!"
)

private val thisIsCodeDialog = HelperDialog(
    290, 300, 530, 300,
    "Here is the program.\n" +
        "Press [RED]arrow keys[] to navigate\n" +
        "through the code"
)

private val howToNavigateCodeDialog = HelperDialog(
    290, 300, 530, 300,
    "Press [RED]enter[] to add new line,\n" +
        "[RED]del[] to remove a line\n" +
        "and [RED]backspace[] to erase a\n" +
        "symbol under the cursor"
)

private val howToInputDialog = HelperDialog(
    620, 313, 530, 300,
    "Down here are our controls\n" +
        "for entering instructions\n" +
        "and a large пуск button\n" +
        "for launching the code"
)

private val howToInput2Dialog = HelperDialog(
    290, 300, 530, 300,
    "Here we already have some\n" +
        "code. Let's see what it does.\n" +
        "Press [RED]пуск[] to start execution",
    States.PLOTTING_SCREEN
)

private val somethingWentWrongDialog = HelperDialog(
    400, 450, 490, 250,
    "Hmm.. Something is wrong.\n" +
        "See that last pixel?\n" +
        "Press [RED]прервать[] button\n" +
        "to go back to editing",
    States.CODE_EDITOR_SCREEN
)

private val howToFixDialog = HelperDialog(
    290, 350, 530, 300,
    "Can you fix this code?\n" +
        "You can always take a look\n" +
        "at the quick help pressing\n" +
        "this button"
)

private val wellDoneDialog = HelperDialog(
    600, 270, 520, 300,
    "Well done, comrade!\n" +
        "You have a new assignment\n" +
        "Check it here"
)

val codeEditorTutorial = {
    val currentTaskId: Int by IOC
    val hud: Stage by IOC
    val assetManager: AssetManager by IOC
    when (currentTaskId) {
        0 -> {
            thisIsControllerDialog.updateAndDraw(hud.batch)
            if (thisIsControllerDialog.isClosed) {
                thisIsCodeDialog.updateAndDraw(hud.batch)
                if (thisIsCodeDialog.isClosed) {
                    howToNavigateCodeDialog.updateAndDraw(hud.batch)
                    if (howToNavigateCodeDialog.isClosed) {
                        howToInputDialog.updateAndDraw(hud.batch)
                        if (howToInputDialog.isClosed) {
                            howToInput2Dialog.updateAndDraw(hud.batch)
                            if (!howToInput2Dialog.isClosed) {
                                hud.batch.managed {
                                    blink(it, assetManager.texture(Assets.Names.ARROW), 785f, 70f)
                                }
                            }
                        }
                    }
                }
            }
            if (somethingWentWrongDialog.isClosed) {
                howToFixDialog.updateAndDraw(hud.batch)
                if (!howToFixDialog.isClosed) {
                    hud.batch.managed {
                        blink(it, assetManager.texture(Assets.Names.ARROW), 560f, 75f)
                    }
                }
            }
        }
    }
}

val afterPlot = AfterBarrier(3f)

val plotterTutorial = {
    val currentTaskId: Int by IOC
    val hud: Stage by IOC
    val assetManager: AssetManager by IOC
    when (currentTaskId) {
        0 -> {
            afterPlot.invoke {
                somethingWentWrongDialog.updateAndDraw(hud.batch)
                if (!somethingWentWrongDialog.isClosed) {
                    hud.batch.managed {
                        blink(it, assetManager.texture(Assets.Names.ARROW), 785f, 70f)
                    }
                }
            }
        }
    }
}

val taskTutorial = {
    val currentTaskId: Int by IOC
    val hud: Stage by IOC
    when (currentTaskId) {
        0 -> {
            assignmentDialog.updateAndDraw(hud.batch)
        }
    }
}

val workspaceTutorial = {
    val currentTaskId: Int by IOC
    val hud: Stage by IOC
    val assetManager: AssetManager by IOC
    blinkTs += Gdx.graphics.deltaTime
    when (currentTaskId) {
        0 -> {
            welcomeDialog.updateAndDraw(hud.batch)
            if (welcomeDialog.isClosed) {
                goalDialog.updateAndDraw(hud.batch)
                if (goalDialog.isClosed) {
                    clickAssignmentDialog.updateAndDraw(hud.batch)
                    if (!clickAssignmentDialog.isClosed) {
                        hud.batch.managed {
                            blink(it, assetManager.texture(Assets.Names.ARROW), 1030f, 350f)
                        }
                    }
                }
            }
            if (assignmentDialog.isClosed) {
                clickCodeEditorDialog.updateAndDraw(hud.batch)
                if (!clickCodeEditorDialog.isClosed) {
                    hud.batch.managed {
                        blink(it, assetManager.texture(Assets.Names.ARROW), 350f, 250f)
                    }
                }
            }
        }
        1 -> {
            wellDoneDialog.updateAndDraw(hud.batch)
            if (!wellDoneDialog.isClosed) {
                hud.batch.managed {
                    blink(it, assetManager.texture(Assets.Names.ARROW), 1030f, 350f)
                }
            }
        }
    }
}

private var blinkTs = 0f

fun blink(it: Batch, texture: Texture, x: Float, y: Float) {
    blinkTs += Gdx.graphics.deltaTime
    if ((blinkTs * 2).toInt() % 2 == 0) {
        it.draw(texture, x, y)
    }
}

package org.nightfall

import com.formdev.flatlaf.FlatDarkLaf
import net.miginfocom.swing.MigLayout
import java.io.File
//import org.nightfall.mods.Mods
//import org.nightfall.worldgen.World
import javax.swing.*

private lateinit var frame: JFrame
private lateinit var nightfall: Nightfall

class UI: JPanel() {
    init {
        layout = MigLayout("fill")
        add(JPanel().apply {
            layout = MigLayout()
            add(JButton("Create world").apply {
                addActionListener {
                    // TODO: get size from user
                    val dialog = JDialog(frame)
                    dialog.layout = MigLayout()
                    //Mods.checkboxSettings.map { it.instance!! }.forEach { dialog.add(it, "wrap") }
                    dialog.add(JButton("OK").apply {
                        addActionListener {
                            dialog.isVisible = false
                            //val world = World(50, 10, 50)
                            /*val config = Lwjgl3ApplicationConfiguration()
                            config.setTitle("Nightfall")
                            config.setWindowedMode(800, 600)
                            config.useVsync(true)
                            config.setForegroundFPS(60)
                            Lwjgl3Application(GameWindow(world), config)*/
                            nightfall.isFinished = true
                        }
                    })
                    dialog.pack()
                    dialog.title = "Settings"
                    dialog.isVisible = true
                }
            }, "east")
        }, "north")
        File("world/").list()?.forEach {
            add(JButton(it).apply {
                addActionListener {
                    // TODO
                }
            }, "wrap")
        }
    }
}

fun main(args: Array<String>) {
    //Mods.initialize(args)
    FlatDarkLaf.setup()
    SwingUtilities.invokeLater {
        frame = JFrame("Nightfall")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane = UI()
        frame.setSize(800, 600)
        frame.isVisible = true
    }
}

class Nightfall {
    var isFinished = false
    init {
        nightfall = this
    }
    fun start() = main(arrayOf())
}
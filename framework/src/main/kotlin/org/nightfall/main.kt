package org.nightfall

import com.formdev.flatlaf.FlatDarkLaf
import net.miginfocom.swing.MigLayout
import org.nightfall.materials.Tile
import java.io.File
import org.nightfall.mods.Mods
import org.nightfall.worldgen.World
import javax.swing.*

class UI: JPanel() {
    init {
        layout = MigLayout("fill")
        add(JPanel().apply {
            layout = MigLayout()
            add(JButton("Create world").apply {
                addActionListener {
                    // TODO: get size from user
                    val dialog = JDialog(Nightfall.instance.frame)
                    dialog.layout = MigLayout()
                    Mods.checkboxSettings.map { it.instance!! }.forEach { dialog.add(it, "wrap") }
                    dialog.add(JButton("OK").apply {
                        addActionListener {
                            dialog.isVisible = false
                            val world = World(50, 10, 50)
                            Nightfall.instance.world = world
                            Nightfall.instance.started = true
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

class Nightfall {
    companion object {
        lateinit var instance: Nightfall
    }

    val tiles = mutableSetOf<Tile>()
    var started = false
    var finished = false
    lateinit var frame: JFrame
    lateinit var world: World
    init {
        instance = this
    }
    fun start(vararg args: String) {
        Mods.initialize(args)
        FlatDarkLaf.setup()
        SwingUtilities.invokeLater {
            frame = JFrame("Nightfall")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.contentPane = UI()
            frame.setSize(800, 600)
            frame.isVisible = true
        }
    }
    fun close() = frame.dispose()
    fun addTile(tile: Tile) = tiles.add(tile)
}
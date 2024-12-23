package org.nightfall.materials

import org.nightfall.Nightfall
import org.nightfall.worldgen.World
import org.nightfall.Point
import java.io.Serializable

open class Tile(val modelPath: String, val occludes: Boolean = true, val needsUpdating: Boolean = false) {
    init {
        Nightfall.instance.addTile(this)
    }
    open fun update(instance: TileInstance<*>, dt: Double) {}
}
class TileInstance<T: Tile>(val type: T, val world: World, val x: Int, val y: Int, val z: Int): Serializable {
    val needsUpdating = type.needsUpdating
    var occluded = false
    init {
        calculateOcclusion()
    }
    fun calculateOcclusion() {
        occluded = Point(x, y, z).orthogonals.all { world[it]?.type?.occludes ?: false }
    }
}
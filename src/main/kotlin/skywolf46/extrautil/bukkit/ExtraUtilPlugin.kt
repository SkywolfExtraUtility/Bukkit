package skywolf46.extrautil.bukkit

import org.bukkit.plugin.java.JavaPlugin
import skywolf46.extrautility.core.ExtraUtilityCore

class ExtraUtilPlugin : JavaPlugin() {
    override fun onEnable() {
        ExtraUtilityCore.init()
    }
}
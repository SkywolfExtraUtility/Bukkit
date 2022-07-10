package skywolf46.extrautil.bukkit

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import skywolf46.extrautil.bukkit.annotations.AllowScanning
import skywolf46.extrautility.core.ExtraUtilityCore
import skywolf46.extrautility.core.util.ReflectionUtil

class ExtraUtilPlugin : JavaPlugin() {
    override fun onEnable() {
        for (x in Bukkit.getPluginManager().plugins) {
            if (x.javaClass.getAnnotation(AllowScanning::class.java) != null)
                ReflectionUtil.addClassLoader(x.javaClass.classLoader)
        }
        ExtraUtilityCore.init()
    }
}
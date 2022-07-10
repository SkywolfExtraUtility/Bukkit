package skywolf46.extrautil.bukkit.util

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import java.util.*

private val nameUserMap = mutableMapOf<String, OfflinePlayer>()
private val uuidUserMap = mutableMapOf<UUID, OfflinePlayer>()

fun playerOf(uuid: UUID) =
    Bukkit.getPlayer(uuid)


fun playerOf(name: String) =
    Bukkit.getPlayerExact(name)

fun offlinePlayerOf(uuid: UUID) =
    uuidUserMap.getOrPut(uuid) { Bukkit.getOfflinePlayer(uuid) }

fun offlinePlayerOf(name: String) =
    nameUserMap.getOrPut(name.lowercase()) { Bukkit.getOfflinePlayer(name) }

package skywolf46.extrautil.bukkit.util

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

object ItemStackUtil {

    fun builder(type: Material): ItemConstructor {
        return Builder(type)
    }

    interface ItemConstructor {

        fun type(type: Material): ItemConstructor

        fun type(): Material

        fun damage(damage: Int): ItemConstructor

        fun damage(): ItemConstructor

        fun name(name: String): ItemConstructor

        fun name(): String?

        fun lore(lore: List<String>): ItemConstructor

        fun lore(): List<String>

        fun enchant(type: Enchantment, level: Int): ItemConstructor

        fun enchant(type: Enchantment): Int

        fun enchant(): Map<Enchantment, Int>

        fun hasEnchant(enchantment: Enchantment): Boolean

        fun removeEnchant(enchantment: Enchantment): ItemConstructor

        fun applyTag(name: String, data: Any): ItemConstructor

        fun construct(): ItemStack
    }

    private class Builder(private var type: Material) : ItemConstructor {
        private var name: String? = null
        private var damage = 0
        private val lore = mutableListOf<String>()
        private val enchantment = mutableMapOf<Enchantment, Int>()

        constructor(item: ItemStack) : this(item.type) {
            damage = item.durability.toInt()
            item.itemMeta?.let {
                name = it.displayName
                lore.addAll(it.lore ?: mutableListOf())
                enchantment.putAll(it.enchants)
            }
        }

        override fun damage(damage: Int): ItemConstructor {
            this.damage = damage
            return this
        }

        override fun damage(): ItemConstructor {
            return this.damage()
        }

        override fun type(type: Material): ItemConstructor {
            this.type = type
            return this
        }

        override fun type(): Material {
            return type
        }

        override fun name(name: String): ItemConstructor {
            this.name = name
            return this
        }

        override fun name(): String? {
            return name
        }

        override fun lore(lore: List<String>): ItemConstructor {
            this.lore.clear()
            this.lore.addAll(lore)
            return this
        }

        override fun lore(): List<String> {
            return this.lore
        }

        override fun enchant(type: Enchantment, level: Int): ItemConstructor {
            this.enchantment[type] = level
            return this
        }

        override fun enchant(type: Enchantment): Int {
            return enchantment.getOrElse(type) { 0 }
        }

        override fun enchant(): Map<Enchantment, Int> {
            return enchantment
        }

        override fun hasEnchant(enchantment: Enchantment): Boolean {
            return this.enchantment.containsKey(enchantment)
        }

        override fun removeEnchant(enchantment: Enchantment): ItemConstructor {
            this.enchantment -= enchantment
            return this
        }

        override fun applyTag(name: String, data: Any): ItemConstructor {
            TODO("Not yet implemented")
        }

        override fun construct(): ItemStack {
            val item = ItemStack(type)
            item.itemMeta?.let {
                it.setDisplayName(name)
                it.lore = lore
                enchantment.forEach { (k, v) ->
                    it.addEnchant(k, v, true)
                }
            }
            return item
        }
    }

    private class Wrapper {

    }
}


fun ItemStack.itemMeta(unit: ItemMeta.() -> Unit) {
    itemMeta = itemMeta.apply { this?.let { unit(it) } }
}

@Suppress("UNCHECKED_CAST")
fun <T : ItemMeta> ItemStack.castMeta(unit: T.() -> Unit) {
    itemMeta {
        unit(this as T)
    }
}
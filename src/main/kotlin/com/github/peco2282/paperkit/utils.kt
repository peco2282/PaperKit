@file:JvmName("Utils")

package com.github.peco2282.paperkit

import com.github.peco2282.paperkit.internals.CommandCreator
import com.github.peco2282.paperkit.internals.NBTCreator
import com.github.peco2282.paperkit.packet.PacketWrapper
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.jetbrains.annotations.ApiStatus

// command
@ApiStatus.Experimental
fun command(creator: CommandCreator.() -> Unit) {
}
// command

// nbt
fun nbt(
  plugin: Plugin,
  item: ItemStack,
  creator: NBTCreator.() -> Unit
) {
  val container = item.itemMeta?.persistentDataContainer ?: return
  creator(NBTCreator(plugin, container))
}

// fun ItemStack.nbt(creator: NBTCreator.() -> Unit) = nbt(this, creator)
// nbt

// packet
fun Player.sendPacket(packet: PacketWrapper) = packet.send(this)
// packet

// tasks
fun runTask(
  plugin: Plugin,
  delay: Long = 0,
  block: () -> Unit
) = Bukkit.getScheduler().runTaskLater(
  plugin,
  block,
  delay * 20L
)

fun runTask(
  plugin: Plugin,
  delay: Long = 0,
  block: Runnable
) = Bukkit.getScheduler().runTaskLater(
  plugin,
  block,
  delay * 20L
)

fun runAsyncTask(
  plugin: Plugin,
  delay: Long = 0,
  block: () -> Unit
) = Bukkit.getScheduler().runTaskLaterAsynchronously(
  plugin,
  block,
  delay * 20L
)

fun runAsyncTask(
  plugin: Plugin,
  delay: Long = 0,
  block: Runnable
) = Bukkit.getScheduler().runTaskLaterAsynchronously(
  plugin,
  block,
  delay * 20L
)

fun runTimer(
  plugin: Plugin,
  delay: Long = 0,
  period: Long,
  block: () -> Unit
) = Bukkit.getScheduler().runTaskTimer(
  plugin,
  block,
  delay * 20L,
  period * 20L
)

fun runTimer(
  plugin: Plugin,
  delay: Long = 0,
  period: Long,
  block: Runnable
) = Bukkit.getScheduler().runTaskTimer(
  plugin,
  block,
  delay * 20L,
  period * 20L
)

fun runAsyncTimer(
  plugin: Plugin,
  delay: Long = 0,
  period: Long,
  block: () -> Unit
) = Bukkit.getScheduler().runTaskTimerAsynchronously(
  plugin,
  block,
  delay * 20L,
  period * 20L
)

fun runAsyncTimer(
  plugin: Plugin,
  delay: Long = 0,
  period: Long,
  block: Runnable
) = Bukkit.getScheduler().runTaskTimerAsynchronously(
  plugin,
  block,
  delay * 20L,
  period * 20L
)

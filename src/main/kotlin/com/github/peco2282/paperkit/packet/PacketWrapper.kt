package com.github.peco2282.paperkit.packet

import net.minecraft.network.PacketListener
import net.minecraft.network.protocol.Packet
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

/**
 * Defines scope for packet operations with player sending capability.
 * @author peco2282
 */
interface PacketScope {
  /**
   * Sends this packet to the specified player.
   *
   * @param player The player to send the packet to
   */
  fun send(player: Player)
}

/**
 * Wrapper interface for Minecraft network packets providing conversion and validation capabilities.
 */
interface PacketWrapper : PacketScope {
  /**
   * Converts this wrapper to a Minecraft packet.
   *
   * @return The converted Minecraft packet
   */
  fun convert(): Packet<out PacketListener>

  /**
   * Validates if this packet is ready to be sent.
   *
   * @return true if the packet is valid, false otherwise
   */
  fun validate(): Boolean

  /**
   * Sends this packet to the specified player if validation passes.
   *
   * @param player The player to send the packet to
   */
  override fun send(player: Player) {
    if (validate()) send(player, convert())
  }

  companion object {
    /**
     * Sends a Minecraft packet to a player.
     *
     * @param player The player to send the packet to
     * @param packet The packet to send
     */
    @JvmStatic
    fun <T : PacketListener> send(
      player: Player,
      packet: Packet<T>
    ) {
      if (player is CraftPlayer) {
        player.handle.connection.send(packet)
      }
    }
  }
}

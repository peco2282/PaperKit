package com.github.peco2282.paperkit.packet

import com.github.peco2282.paperkit.internals.blockPos
import net.minecraft.network.PacketListener
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket
import org.bukkit.block.BlockState
import org.bukkit.craftbukkit.block.CraftBlockState
import org.bukkit.util.Vector

internal object ClientboundBlockUpdatePacketDefault : IBlockUpdatePacket, PacketWrapper {
  override var position: Vector? = null

  override var block: BlockState? = null

  override fun convert(): Packet<out PacketListener> =
    ClientboundBlockUpdatePacket(
      position!!.blockPos(),
      (block as CraftBlockState).handle
    )

  override fun validate(): Boolean = position != null && block != null && block is CraftBlockState
}

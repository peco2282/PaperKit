package com.github.peco2282.paperkit.packet

import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket
import org.bukkit.block.Block
import org.bukkit.block.BlockState
import org.bukkit.util.Vector

interface BlockPacket : PacketScope

interface IBlockUpdatePacket : BlockPacket {
  var position: Vector?
  var block: BlockState?
}

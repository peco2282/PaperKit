@file:JvmName("PacketUtils")
@file:ApiStatus.Experimental

package com.github.peco2282.paperkit

import com.github.peco2282.paperkit.packet.ClientboundAbsoluteTeleportEntityPacketDefault
import com.github.peco2282.paperkit.packet.ClientboundAnimatePacketDefault
import com.github.peco2282.paperkit.packet.ClientboundBlockDestructionPacketDefault
import com.github.peco2282.paperkit.packet.ClientboundRelativeTeleportEntityPacketDefault
import com.github.peco2282.paperkit.packet.IAnimatePacket
import com.github.peco2282.paperkit.packet.IBlockDestructionPacket
import com.github.peco2282.paperkit.packet.ITeleportEntityPacket
import org.jetbrains.annotations.ApiStatus

fun destroy(destroyer: IBlockDestructionPacket.() -> Unit): IBlockDestructionPacket {
  val packet = ClientboundBlockDestructionPacketDefault
  packet.destroyer()
  return packet
}

fun destroy(destryer: PacketConsumers.IBlockDestructionPacketConsumer): IBlockDestructionPacket {
  val packet = ClientboundBlockDestructionPacketDefault
  destryer.accept(packet)
  return packet
}

fun teleportAbsolutionPositon(teleporter: ITeleportEntityPacket.() -> Unit): ITeleportEntityPacket {
  val packet = ClientboundAbsoluteTeleportEntityPacketDefault
  packet.teleporter()
  return packet
}

fun teleportAbsolutionPositon(teleporter: PacketConsumers.ITeleportEntityPacketConsumer): ITeleportEntityPacket {
  val packet = ClientboundAbsoluteTeleportEntityPacketDefault
  teleporter.accept(packet)
  return packet
}

fun teleportRelativePositon(teleporter: ITeleportEntityPacket.() -> Unit): ITeleportEntityPacket {
  val packet = ClientboundRelativeTeleportEntityPacketDefault
  packet.teleporter()
  return packet
}

fun teleportRelativePositon(teleporter: PacketConsumers.ITeleportEntityPacketConsumer): ITeleportEntityPacket {
  val packet = ClientboundRelativeTeleportEntityPacketDefault
  teleporter.accept(packet)
  return packet
}

fun animation(animator: IAnimatePacket.() -> Unit): IAnimatePacket {
  val packet = ClientboundAnimatePacketDefault(null)
  packet.animator()
  return packet
}

fun animation(animator: PacketConsumers.IAnimatePacketConsumer): IAnimatePacket {
  val packet = ClientboundAnimatePacketDefault(null)
  animator.accept(packet)
  return packet
}

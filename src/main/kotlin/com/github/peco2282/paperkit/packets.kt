@file:JvmName("PacketUtils")
@file:ApiStatus.Experimental

package com.github.peco2282.paperkit

import com.github.peco2282.paperkit.internals.Debugged
import com.github.peco2282.paperkit.packet.ClientboundAbsoluteTeleportEntityPacketDefault
import com.github.peco2282.paperkit.packet.ClientboundAnimatePacketDefault
import com.github.peco2282.paperkit.packet.ClientboundBlockDestructionPacketDefault
import com.github.peco2282.paperkit.packet.ClientboundBlockUpdatePacketDefault
import com.github.peco2282.paperkit.packet.ClientboundRelativeTeleportEntityPacketDefault
import com.github.peco2282.paperkit.packet.ClientboundUpdateAdvancementsPacketDefault
import com.github.peco2282.paperkit.packet.IAnimatePacket
import com.github.peco2282.paperkit.packet.IBlockDestructionPacket
import com.github.peco2282.paperkit.packet.IBlockUpdatePacket
import com.github.peco2282.paperkit.packet.ITeleportEntityPacket
import com.github.peco2282.paperkit.packet.IUpdateAdvancementsPacket
import org.jetbrains.annotations.ApiStatus

/**
 * Creates a block destruction packet using the provided configuration lambda.
 *
 * @param destroyer Lambda to configure the block destruction packet
 * @return Configured [IBlockDestructionPacket] instance
 */
fun destroy(destroyer: IBlockDestructionPacket.() -> Unit): IBlockDestructionPacket {
  val packet = ClientboundBlockDestructionPacketDefault
  packet.destroyer()
  return packet
}

/**
 * Creates a block destruction packet using the provided consumer.
 *
 * @param destryer Consumer to configure the block destruction packet
 * @return Configured [IBlockDestructionPacket] instance
 */
fun destroy(destryer: PacketConsumers.IBlockDestructionPacketConsumer): IBlockDestructionPacket {
  val packet = ClientboundBlockDestructionPacketDefault
  destryer.accept(packet)
  return packet
}

/**
 * Creates an absolute position teleport packet using the provided configuration lambda.
 *
 * @param teleporter Lambda to configure the teleport packet
 * @return Configured [ITeleportEntityPacket] instance
 */
fun teleportAbsolutionPositon(teleporter: ITeleportEntityPacket.() -> Unit): ITeleportEntityPacket {
  val packet = ClientboundAbsoluteTeleportEntityPacketDefault
  packet.teleporter()
  return packet
}

/**
 * Creates an absolute position teleport packet using the provided consumer.
 *
 * @param teleporter Consumer to configure the teleport packet
 * @return Configured [ITeleportEntityPacket] instance
 */
fun teleportAbsolutionPositon(teleporter: PacketConsumers.ITeleportEntityPacketConsumer): ITeleportEntityPacket {
  val packet = ClientboundAbsoluteTeleportEntityPacketDefault
  teleporter.accept(packet)
  return packet
}

/**
 * Creates a relative position teleport packet using the provided configuration lambda.
 *
 * @param teleporter Lambda to configure the teleport packet
 * @return Configured [ITeleportEntityPacket] instance
 */
fun teleportRelativePositon(teleporter: ITeleportEntityPacket.() -> Unit): ITeleportEntityPacket {
  val packet = ClientboundRelativeTeleportEntityPacketDefault
  packet.teleporter()
  return packet
}

/**
 * Creates a relative position teleport packet using the provided consumer.
 *
 * @param teleporter Consumer to configure the teleport packet
 * @return Configured [ITeleportEntityPacket] instance
 */
fun teleportRelativePositon(teleporter: PacketConsumers.ITeleportEntityPacketConsumer): ITeleportEntityPacket {
  val packet = ClientboundRelativeTeleportEntityPacketDefault
  teleporter.accept(packet)
  return packet
}

/**
 * Creates an animation packet using the provided configuration lambda.
 *
 * @param animator Lambda to configure the animation packet
 * @return Configured [IAnimatePacket] instance
 */
@Debugged
fun animation(animator: IAnimatePacket.() -> Unit): IAnimatePacket {
  val packet = ClientboundAnimatePacketDefault(null)
  packet.animator()
  return packet
}

/**
 * Creates an animation packet using the provided consumer.
 *
 * @param animator Consumer to configure the animation packet
 * @return Configured [IAnimatePacket] instance
 */
@Debugged
fun animation(animator: PacketConsumers.IAnimatePacketConsumer): IAnimatePacket {
  val packet = ClientboundAnimatePacketDefault(null)
  animator.accept(packet)
  return packet
}

/**
 * Creates an advancement update packet using the provided configuration lambda.
 *
 * @param advancement Lambda to configure the advancement packet
 * @return Configured [IUpdateAdvancementsPacket] instance
 */
fun advancement(advancement: IUpdateAdvancementsPacket.() -> Unit): IUpdateAdvancementsPacket {
  val packet = ClientboundUpdateAdvancementsPacketDefault
  packet.advancement()
  return packet
}

/**
 * Creates an advancement update packet using the provided consumer.
 *
 * @param advancement Consumer to configure the advancement packet
 * @return Configured [IUpdateAdvancementsPacket] instance
 */
fun advancement(advancement: PacketConsumers.IUpdateAdvancementsPacketConsumer): IUpdateAdvancementsPacket {
  val packet = ClientboundUpdateAdvancementsPacketDefault
  advancement.accept(packet)
  return packet
}

/**
 * Creates a block update packet using the provided configuration lambda.
 *
 * @param updater Lambda to configure the block update packet
 * @return Configured [IBlockUpdatePacket] instance
 */
fun updateBlock(updater: IBlockUpdatePacket.() -> Unit): IBlockUpdatePacket {
  val packet = ClientboundBlockUpdatePacketDefault
  packet.updater()
  return packet
}

/**
 * Creates a block update packet using the provided consumer.
 *
 * @param updater Consumer to configure the block update packet
 * @return Configured [IBlockUpdatePacket] instance
 */
fun updateBlock(updater: PacketConsumers.IBlockUpdatePacketConsumer): IBlockUpdatePacket {
  val packet = ClientboundBlockUpdatePacketDefault
  updater.accept(packet)
  return packet
}

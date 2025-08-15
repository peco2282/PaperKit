package com.github.peco2282.paperkit;

import com.github.peco2282.paperkit.packet.IAnimatePacket;
import com.github.peco2282.paperkit.packet.IBlockDestructionPacket;
import com.github.peco2282.paperkit.packet.ITeleportEntityPacket;

import java.util.function.Consumer;

/**
 * Contains functional interfaces for consuming different types of packets. Provides type-safe
 * consumers for handling block destruction and entity teleportation packets.
 *
 * @author peco2282
 */
public interface PacketConsumers {
  /** Consumer interface for handling block destruction packets. */
  interface IBlockDestructionPacketConsumer extends Consumer<IBlockDestructionPacket> {}

  /** Consumer interface for handling entity teleportation packets. */
  interface ITeleportEntityPacketConsumer extends Consumer<ITeleportEntityPacket> {}

  interface IAnimatePacketConsumer extends Consumer<IAnimatePacket> {}
}

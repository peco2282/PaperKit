package com.github.peco2282.paperkit;

import com.github.peco2282.paperkit.packet.*;

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

  /** Consumer interface for handling entity animation packets. */
  interface IAnimatePacketConsumer extends Consumer<IAnimatePacket> {}

  /** Consumer interface for handling advancement update packets. */
  interface IUpdateAdvancementsPacketConsumer extends Consumer<IUpdateAdvancementsPacket> {}

  /** Consumer interface for handling block update packets. */
  interface IBlockUpdatePacketConsumer extends Consumer<IBlockUpdatePacket> {}
}

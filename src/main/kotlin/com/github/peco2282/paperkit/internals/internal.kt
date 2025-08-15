package com.github.peco2282.paperkit.internals

import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.phys.Vec3
import org.bukkit.NamespacedKey
import org.bukkit.util.Vector

internal fun Vector.blockPos() = BlockPos(blockX, blockY, blockZ)

internal fun Vector.vec3() = Vec3(x, y, z)

internal fun <T, U : T> T.cast(u: Class<U>): U = u.cast(this)

internal fun NamespacedKey.location() = ResourceLocation.fromNamespaceAndPath(namespace, key)

@Suppress("UNCHECKED_CAST")
internal fun <C, T> getReflectField(
  clazz: Class<C>,
  fieldName: String,
  instance: C
): T = clazz.getDeclaredField(fieldName).apply { isAccessible = true }.get(instance) as T

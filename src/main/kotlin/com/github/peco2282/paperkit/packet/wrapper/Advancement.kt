package com.github.peco2282.paperkit.packet.wrapper

import net.kyori.adventure.text.Component
import net.minecraft.advancements.AdvancementRequirements
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.Criterion
import net.minecraft.advancements.DisplayInfo
import org.bukkit.NamespacedKey
import java.util.Optional

interface AdvancementHolderWrapper {
  var key: NamespacedKey
  var advancement: AdvancementWrapper
}

interface AdvancementWrapper {
  var parent: Optional<NamespacedKey>
  var display: Optional<DisplayInfo>
  var rewards: AdvancementRewards
  var criteria: Map<String, Criterion<*>>
  var requirements: AdvancementRequirements
  var sendsTelemetryEvent: Boolean
  var name: Optional<Component>
}

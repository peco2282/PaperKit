package com.github.peco2282.paperkit.internals

import com.github.peco2282.paperkit.Functionals
import com.github.peco2282.paperkit.component
import com.github.peco2282.paperkit.internals.Value
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.Style
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Color

@DslMarker
annotation class ComponentDsl

@ComponentDsl
class ComponentCreator(
  component: Component
) {
  private val self = Value(component)

  fun color(color: TextColor) =
    apply {
      style {
        color(color)
      }
    }

  fun color(color: Color) =
    apply {
      style {
        color(TextColor.color(color.asRGB()))
      }
    }

  fun color(rgb: Int) =
    apply {
      style {
        color(TextColor.color(rgb))
      }
    }

  fun colorIfAbsent(color: TextColor) =
    apply {
      style {
        colorIfAbsent(color)
      }
    }

  fun colorIfAbsent(color: Color) =
    apply {
      style {
        colorIfAbsent(TextColor.color(color.asRGB()))
      }
    }

  fun colorIfAbsent(rgb: Int) =
    apply {
      style {
        colorIfAbsent(TextColor.color(rgb))
      }
    }

  fun decorate(vararg decoration: TextDecoration) =
    apply {
      style {
        decorations(setOf(*decoration), true)
      }
    }

  fun undecorate(vararg decoration: TextDecoration) =
    apply {
      style {
        decorations(setOf(*decoration), false)
      }
    }

  fun bold(flag: Boolean = true) =
    apply {
      style {
        decoration(TextDecoration.BOLD, flag)
      }
    }

  fun italic(flag: Boolean = true) =
    apply {
      style {
        decoration(TextDecoration.ITALIC, flag)
      }
    }

  fun underline(flag: Boolean = true) =
    apply {
      style {
        decoration(TextDecoration.UNDERLINED, flag)
      }
    }

  fun strikethrough(flag: Boolean = true) =
    apply {
      style {
        decoration(TextDecoration.STRIKETHROUGH, flag)
      }
    }

  fun obfuscated(flag: Boolean = true) =
    apply {
      style {
        decoration(TextDecoration.OBFUSCATED, flag)
      }
    }

  fun style(creator: Style.Builder.() -> Unit) =
    apply {
      self.consume { it.style(creator) }
    }

  fun build() = self.value
}

@ComponentDsl
class ComponentJoiner {
  private val list = mutableListOf<ComponentCreator>()

  internal fun append(component: ComponentCreator) =
    let {
      list.add(component)
      component
    }

  fun append(
    component: Component,
    creator: ComponentCreator.() -> Unit = {}
  ) = let {
    val creator = ComponentCreator(component)
    creator.creator()
    append(creator)
  }

  fun append(
    text: String,
    creator: ComponentCreator.() -> Unit = {}
  ) = append(text.component(), creator)

  fun append(
    component: Component,
    creator: Functionals.IComponentCreator
  ) = append(component, creator::accept)

  fun append(
    text: String,
    creator: Functionals.IComponentCreator
  ): ComponentJoiner {
    append(text, creator::accept)
    return this
  }

  fun space() = append(Component.space())

  fun newline() = append(Component.newline())

  fun build(): Component {
    if (list.isEmpty()) return Component.empty()
    return Component.empty().append(*(list.map { it.build() }).toTypedArray())
  }
}

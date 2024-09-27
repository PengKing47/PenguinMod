package com.penguinmod.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static RegistryEntry<StatusEffect> PENGUINS_GRACE;

    private static RegistryEntry<StatusEffect> register(String name, StatusEffect statusEffect) {
      return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of("penguinmod", name), statusEffect);
    }

    public static void registerEffects() {
      PENGUINS_GRACE = register("penguins_grace", new PenguinEffect(StatusEffectCategory.BENEFICIAL, 0x00008B));
    }
}

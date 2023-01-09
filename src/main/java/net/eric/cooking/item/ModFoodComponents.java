package net.eric.cooking.item;

import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent STEAK_AND_POTATOES =
            new FoodComponent.Builder().hunger(12).saturationModifier(0.9f).meat().build();

    public static final FoodComponent SURF_AND_TURF =
            new FoodComponent.Builder().hunger(14).saturationModifier(1.0f).meat().build();
}

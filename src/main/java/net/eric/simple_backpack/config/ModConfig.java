package net.eric.simple_backpack.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "simple_backpack")
public class ModConfig extends PartitioningSerializer.GlobalData
{
    public General GENERAL = new General();

    @Config(name = "general")
    public static class General implements ConfigData
    {
        @Comment("***********************"
                +"\nGeneral Settings"
                +"\n***********************")

        public boolean enableBackpack = true;
        public boolean enableVoidpack = true;
        public boolean enableEnderpack = true;
        public boolean enableSimpleBundle = true;
        public boolean enableVoidBundle = true;
    }
}

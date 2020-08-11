package laz.plasmine.datagen.lang;

import static laz.plasmine.registry.PMRegistry.BIOMELIST;
import static laz.plasmine.registry.PMRegistry.ITEMLIST;
import static laz.plasmine.registry.PMRegistry.SIMPLE;
import static laz.plasmine.registry.PMRegistry.TILES;

import org.apache.commons.lang3.text.WordUtils;

import laz.plasmine.Plasmine;
import laz.plasmine.registry.BlockRegistryObjectGroup;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;

public class PMLang extends LanguageProvider {

    public PMLang(DataGenerator gen) {
        super(gen, Plasmine.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (BlockRegistryObjectGroup simple : SIMPLE) {
            if (simple != null) {
                this.addItem(simple::getItem, WordUtils.capitalizeFully(simple.getName().replace("_", " ")));
            }
        }
        for (BlockRegistryObjectGroup tiles : TILES) {
            if (tiles != null) {
                this.addItem(tiles::getItem, WordUtils.capitalizeFully(tiles.getName().replace("_", " ")));
            }
        }
        for (RegistryObject<Item> item : ITEMLIST) {
            if (item != null) {
                this.addItem(item, WordUtils.capitalizeFully(item.getId().getPath().replace("_", " ")));
            }
        }
        for (RegistryObject<Biome> biome : BIOMELIST) {
            if (biome != null) {
                this.addBiome(biome, WordUtils.capitalizeFully(biome.getId().getPath().replace("_", " ")));
            }
        }
    }
}

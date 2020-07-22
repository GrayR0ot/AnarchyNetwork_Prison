package eu.grayroot.anarchyprison.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
    private ItemStack is;
    public ItemBuilder(Material m) {
        this(m, 1);
    }
    public ItemBuilder(ItemStack is) {
        this.is = is;
    }
    public ItemBuilder(Material m, int amount) {
        is = new ItemStack(m, amount);
    }
    public ItemBuilder(Material m, int amount, short meta){
        is = new ItemStack(m, amount, meta);
    }
    public ItemBuilder clone() {
        return new ItemBuilder(is);
    }
    public ItemBuilder setDurability(short dur) {
        is.setDurability(dur);
        return this;
    }
    public ItemBuilder setName(String name) {
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        return this;
    }
    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        is.addUnsafeEnchantment(ench, level);
        return this;
    }
    public ItemBuilder setHidenEnchantment() {
    	ItemMeta im = is.getItemMeta();
    	im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	is.setItemMeta(im);
    	return this;
    }
    public ItemBuilder setHidenAttributes() {
    	ItemMeta im = is.getItemMeta();
    	im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	is.setItemMeta(im);
    	return this;
    }
    public ItemBuilder setHidenItemFlags() {
    	ItemMeta im = is.getItemMeta();
    	im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    	im.addItemFlags(ItemFlag.HIDE_DESTROYS);
    	im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	im.addItemFlags(ItemFlag.HIDE_PLACED_ON);
    	im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
    	im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
    	is.setItemMeta(im);
    	return this;
    }
    public ItemBuilder removeEnchantment(Enchantment ench) {
        is.removeEnchantment(ench);
        return this;
    }
    public ItemBuilder setSkullOwner(String owner) {
        try {
            SkullMeta im = (SkullMeta) is.getItemMeta();
            im.setOwner(owner);
            is.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }
    public ItemBuilder addEnchant(Enchantment ench, int level) {
        ItemMeta im = is.getItemMeta();
        im.addEnchant(ench, level, true);
        is.setItemMeta(im);
        return this;
    }
    public ItemBuilder setUnbreakable() {
        ItemMeta im = is.getItemMeta();
        im.spigot().setUnbreakable(true);
        is.setItemMeta(im);
        return this;
    }
    public ItemBuilder setHidenUnbreakable() {
    	ItemMeta im = is.getItemMeta();
    	im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
    	is.setItemMeta(im);
    	return this;
    }
    public ItemBuilder setLore(String... lore) {
        ItemMeta im = is.getItemMeta();
        im.setLore(Arrays.asList(lore));
        is.setItemMeta(im);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setWoolColor(DyeColor color) {
        if (!is.getType().equals(Material.WOOL))
            return this;
        this.is.setDurability(color.getData());
        return this;
    }
    public ItemBuilder setLeatherArmorColor(Color color) {
        try {
            LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
            im.setColor(color);
            is.setItemMeta(im);
        } catch (ClassCastException expected) {
        }
        return this;
    }
    public ItemStack toItemStack() {
        return is;
    }
	public ItemBuilder setSpawnerMeta(EntityType entitytype) {
		try {
			BlockStateMeta bsm = (BlockStateMeta) is.getItemMeta();
			CreatureSpawner cs = (CreatureSpawner) bsm.getBlockState();
			cs.setSpawnedType(entitytype);
			bsm.setBlockState(cs);
			is.setItemMeta(bsm);
		} catch(ClassCastException expected) {
		}
		return this;
	}
	public ItemBuilder setSkull(String url) {
        SkullMeta im = (SkullMeta) is.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try
        {
            profileField = im.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(im, profile);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        is.setItemMeta(im);
        return this;
    }
	public ItemBuilder setFireworkColor(Color color) {
		FireworkEffectMeta few = (FireworkEffectMeta) is.getItemMeta();
		FireworkEffect fe = FireworkEffect.builder().withColor(color).build();
		few.setEffect(fe);
		is.setItemMeta(few);
		return this;
	}
}

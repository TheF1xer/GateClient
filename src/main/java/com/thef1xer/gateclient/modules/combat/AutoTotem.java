package com.thef1xer.gateclient.modules.combat;

import com.thef1xer.gateclient.modules.Module;
import com.thef1xer.gateclient.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoTotem extends Module {
    public static final AutoTotem INSTANCE = new AutoTotem();

    public AutoTotem() {
        super("Auto Totem", "autototem", Module.ModuleCategory.COMBAT);
    }

    @Override
    public void onEnabled() {
        super.onEnabled();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void onDisabled() {
        super.onDisabled();
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiContainer) {
            return;
        }

        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null) {
            if (Minecraft.getMinecraft().player.getHeldItemOffhand().getItem() != Item.getItemById(449)) {
                for (int slot = 0; slot < Minecraft.getMinecraft().player.inventoryContainer.inventorySlots.size(); slot++) {
                    ItemStack itemStack = Minecraft.getMinecraft().player.inventoryContainer.inventorySlots.get(slot).getStack();
                    if (itemStack.getItem() == Item.getItemById(449)) {
                        PlayerUtil.swapInventoryItems(slot, 45);
                        break;
                    }
                }
            }
        }
    }
}

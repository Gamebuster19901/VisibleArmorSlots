package sidben.visiblearmorslots.handler.action;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import sidben.visiblearmorslots.util.LogHelper;


public class SlotActionResolver_Debug extends SlotActionResolver
{

    private boolean _requiresServer = true;



    @Override
    public void handleClientSide(Slot targetSlot, PlayerEntity player)
    {

        try {
            LogHelper.debug("*** %s", player.container);
            LogHelper.debug("*** %s", player.inventory);
            LogHelper.debug("*** %s", player.openContainer);
            LogHelper.debug("*** %s", player.openContainer.getClass().getName().contains("ContainerCreative"));

            final boolean unnecessarilyLongVariableToDetermineIfThePlayerIsOnCreativeModeWithPersonalContainerThatDontRequireServerSideHandling = player.openContainer.getClass().getName()
                    .contains("ContainerCreative");
            this._requiresServer = !unnecessarilyLongVariableToDetermineIfThePlayerIsOnCreativeModeWithPersonalContainerThatDontRequireServerSideHandling;


            final int slotIndex = 40;
            final Slot offHandSlot = player.container.getSlot(45);

            LogHelper.debug("*** (c1) Slot index %d, number %d, has %s", offHandSlot.getSlotIndex(), offHandSlot.slotNumber, offHandSlot.getStack());
            LogHelper.debug("*** (c1) Inventory index %d, has %s", slotIndex, player.inventory.getStackInSlot(slotIndex));
            LogHelper.debug("*** (c1) Player mouse has %s", player.inventory.getItemStack());

            // Required when opening containers (server-side), to reflect client-side the real content of the slot.
            // In case of conflict, the server wins, UNLESS the player press the inventory key ('E'). In that case, the
            // client will override the server.
            if (targetSlot.slotNumber > 6) {

                // Places coal on the player mouse
                final ItemStack coals = new ItemStack(Items.COAL, 7);
                player.inventory.setItemStack(coals);

                // Places diamonds on the player off-hand slot
                final ItemStack diamonds = new ItemStack(Items.DIAMOND, 3);
                offHandSlot.putStack(diamonds);

                if (unnecessarilyLongVariableToDetermineIfThePlayerIsOnCreativeModeWithPersonalContainerThatDontRequireServerSideHandling) {
                    player.container.detectAndSendChanges();
                }
            }

            LogHelper.debug("*** (c2) Slot index %d, number %d, has %s", offHandSlot.getSlotIndex(), offHandSlot.slotNumber, offHandSlot.getStack());
            LogHelper.debug("*** (c2) Inventory index %d, has %s", slotIndex, player.inventory.getStackInSlot(slotIndex));
            LogHelper.debug("*** (c2) Player mouse has %s", player.inventory.getItemStack());


        } catch (final Exception e) {
            System.out.println(e);

        }

    }



    @Override
    public void handleServerSide(Slot targetSlot, PlayerEntity player)
    {

        try {
            LogHelper.debug("*** %s", player.container);
            LogHelper.debug("*** %s", player.inventory);
            LogHelper.debug("*** %s", player.openContainer);



            final int slotIndex = 40;
            final Slot offHandSlot = player.container.getSlot(45);
            LogHelper.debug("*** (s1) Slot index %d, number %d, has %s", offHandSlot.getSlotIndex(), offHandSlot.slotNumber, offHandSlot.getStack());
            LogHelper.debug("*** (s1) Inventory index %d, has %s", slotIndex, player.inventory.getStackInSlot(slotIndex));
            LogHelper.debug("*** (s1) Player mouse has %s", player.inventory.getItemStack());

            if (targetSlot.slotNumber > 6) {

                // Places coal on the player mouse
                final ItemStack coals = new ItemStack(Items.COAL, 7);
                player.inventory.setItemStack(coals);

                // Places diamonds on the player off-hand slot
                final ItemStack diamonds = new ItemStack(Items.DIAMOND, 3);
                offHandSlot.putStack(diamonds);

            }

            LogHelper.debug("*** (s2) Slot index %d, number %d, has %s", offHandSlot.getSlotIndex(), offHandSlot.slotNumber, offHandSlot.getStack());
            LogHelper.debug("*** (s2) Inventory index %d, has %s", slotIndex, player.inventory.getStackInSlot(slotIndex));
            LogHelper.debug("*** (s2) Player mouse has %s", player.inventory.getItemStack());


        } catch (final Exception e) {
            System.out.println(e);

        }

    }



    @Override
    public boolean requiresServerSideHandling()
    {
        return _requiresServer;
    }


    @Override
    protected boolean isSatisfiedByInternal(SlotActionType action)
    {
        // return true;
        return false;
    }

}

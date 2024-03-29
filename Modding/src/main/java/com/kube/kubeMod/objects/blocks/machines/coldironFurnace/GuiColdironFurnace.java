package com.kube.kubeMod.objects.blocks.machines.coldironFurnace;

import com.kube.kubeMod.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiColdironFurnace extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/coldiron_furnace.png");
	private final InventoryPlayer playerInventory;
	private final TileEntityColdironFurnace tileEntity;

	public GuiColdironFurnace(InventoryPlayer player, TileEntityColdironFurnace tileEntity)
	{
		super(new ContainerColdironFurnace(player, tileEntity));
		this.playerInventory = player;
		this.tileEntity = tileEntity;
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		String tileName = this.tileEntity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
		this.fontRenderer.drawString("T�" + TileEntityColdironFurnace.getRealTemperature(tileEntity.getField(4)), 49, 19, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		int k = this.getTemperatureScaled(52);
		this.drawTexturedModalRect(i + 42, j + 16 + 53 - k, 176, 69 - k, 4, k);

		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(i + 79, j + 34, 176, 0, l + 1, 16);
	}

	private int getTemperatureScaled(int pixels)
	{
		int i = TileEntityColdironFurnace.getRealTemperature(tileEntity.getField(4));
		if (i == 0)
			return 0;
		return pixels * i / tileEntity.getRealMaxTemperature();
	}

	private int getCookProgressScaled(int pixels)
	{
		int i = this.tileEntity.getField(2);
		int j = this.tileEntity.getField(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}
}
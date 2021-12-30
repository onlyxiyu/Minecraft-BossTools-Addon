package boss_tools_giselle_addon.common.compat.jer;

import java.util.List;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;

import jeresources.reference.Resources;
import jeresources.util.Font;
import jeresources.util.RenderHelper;
import jeresources.util.TranslationHelper;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.category.extensions.IRecipeCategoryExtension;
import net.minecraft.item.ItemStack;
import net.mrscauthd.boss_tools.entity.alien.AlienEntity;

public class AlienWrapper implements IRecipeCategoryExtension
{
	private final AlienEntry entry;
	private IFocus<ItemStack> focus;

	public AlienWrapper(AlienEntry entry)
	{
		this.entry = entry;
	}

	@Override
	public void setIngredients(@Nonnull IIngredients ingredients)
	{
		ingredients.setInputs(VanillaTypes.ITEM, entry.getInputs());
		ingredients.setOutputs(VanillaTypes.ITEM, entry.getOutputs());
	}

	public AlienTradeList getTrades(int level)
	{
		return entry.getAlienTrade(level);
	}

	public int getMaxLevel()
	{
		return entry.getMaxLevel();
	}

	public List<Integer> getPossibleLevels(IFocus<ItemStack> focus)
	{
		return entry.getPossibleLevels(focus);
	}

	public void setFocus(IFocus<ItemStack> focus)
	{
		this.focus = focus;
	}

	@Override
	public void drawInfo(int recipeWidth, int recipeHeight, MatrixStack matrixStack, double mouseX, double mouseY)
	{
		RenderHelper.scissor(matrixStack, 7, 43, 59, 79);
		AlienEntity alien = entry.getAlienEntity();
		RenderHelper.renderEntity(matrixStack, 37, 118, 36.0F, 38 - mouseX, 80 - mouseY, alien);
		RenderHelper.stopScissor();

		int y = AlienCategory.Y_ITEM_DISTANCE * (6 - getPossibleLevels(focus).size()) / 2;
		int i;

		for (i = 0; i < getPossibleLevels(focus).size(); i++)
		{
			RenderHelper.drawTexture(matrixStack, 130, y + i * AlienCategory.Y_ITEM_DISTANCE, 0, 120, 20, 20, Resources.Gui.Jei.VILLAGER.getResource());
		}

		i = 0;

		for (int level : getPossibleLevels(focus))
		{
			Font.normal.print(matrixStack, "lv. " + (level + 1), 72, y + i++ * AlienCategory.Y_ITEM_DISTANCE + 6);
		}

		Font.normal.print(matrixStack, TranslationHelper.translateAndFormat(entry.getDisplayName()), 10, 25);
	}

}

package beyond_earth_giselle_addon.common.compat.jei;

import java.util.List;

import javax.annotation.Nullable;

import beyond_earth_giselle_addon.client.gui.ItemStackToItemStackScreen;
import beyond_earth_giselle_addon.common.inventory.ItemStackToItemStackContainerMenu;
import mezz.jei.api.gui.handlers.IGuiContainerHandler;
import mezz.jei.api.helpers.IStackHelper;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.transfer.BasicRecipeTransferHandler;
import net.minecraft.world.item.ItemStack;

public interface IIS2ISRegistration<S extends ItemStackToItemStackScreen<? extends C>, C extends ItemStackToItemStackContainerMenu<C, ?>>
{
	public IGuiContainerHandler<S> createContainerHandler();

	public List<ItemStack> getItemstacks();

	public List<RecipeType<?>> getRecipeTypes();

	public Class<S> getScreenClass();

	public Class<C> getContainerClass();

	public default void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		registration.addGuiContainerHandler(this.getScreenClass(), this.createContainerHandler());
	}

	public default void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
	{
		for (ItemStack itemStack : this.getItemstacks())
		{
			for (RecipeType<?> recipeType : this.getRecipeTypes())
			{
				registration.addRecipeCatalyst(itemStack, recipeType.getUid());
			}

		}

	}

	public default void addRecipeTransferHandler(IRecipeTransferRegistration registration)
	{
		Class<C> containerClass = this.getContainerClass();

		for (RecipeType<?> recipeType : this.getRecipeTypes())
		{
			this.addRecipeTransferHandler(registration, containerClass, recipeType);
		}

	}

	public default <R> void addRecipeTransferHandler(IRecipeTransferRegistration registration, Class<C> containerClass, RecipeType<R> recipeType)
	{
		IRecipeTransferHandler<C, R> handler = this.ceateRecipeTransferHandler(registration, containerClass, recipeType);

		if (handler != null)
		{
			registration.addRecipeTransferHandler(handler, recipeType.getUid());
		}

	}

	@Nullable
	public default <R> IRecipeTransferHandler<C, R> ceateRecipeTransferHandler(IRecipeTransferRegistration registration, Class<C> containerClass, RecipeType<R> recipeType)
	{
		IStackHelper stackHelper = registration.getJeiHelpers().getStackHelper();
		IRecipeTransferHandlerHelper transferHelper = registration.getTransferHelper();
		IS2ISRecipeTransferInfo<C, R> info = this.ceateRecipeTransferInfo(registration, containerClass, recipeType);

		if (info != null)
		{
			return new BasicRecipeTransferHandler<C, R>(stackHelper, transferHelper, info);
		}
		else
		{
			return null;
		}

	}

	@Nullable
	public default <R> IS2ISRecipeTransferInfo<C, R> ceateRecipeTransferInfo(IRecipeTransferRegistration registration, Class<C> containerClass, RecipeType<R> recipeType)
	{
		return new IS2ISRecipeTransferInfo<>(containerClass, recipeType);
	}

}

package jeresources.jei.enchantment;

import jeresources.jei.JEIConfig;
import jeresources.reference.Resources;
import jeresources.utils.TranslationHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;

public class EnchantmentCategory implements IRecipeCategory
{
    private static final int ITEM_X = 12;
    private static final int ITEM_Y = 11;

    @Nonnull
    @Override
    public String getUid()
    {
        return JEIConfig.ENCHANTMENT;
    }

    @Nonnull
    @Override
    public String getTitle()
    {
        return TranslationHelper.translateToLocal("jer.enchantments.title");
    }

    @Nonnull
    @Override
    public IDrawable getBackground()
    {
        return Resources.Gui.Jei.ENCHANTMENT;
    }

    @Override
    public void drawExtras(Minecraft minecraft)
    {

    }

    @Override
    public void drawAnimations(Minecraft minecraft)
    {

    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper)
    {
        recipeLayout.getItemStacks().init(0, true, ITEM_X, ITEM_Y);

        if (recipeWrapper instanceof EnchantmentWrapper)
        {
            EnchantmentWrapper enchantmentWrapper = (EnchantmentWrapper)recipeWrapper;
            recipeLayout.getItemStacks().setFromRecipe(0, enchantmentWrapper.getInputs());
        }
    }
}

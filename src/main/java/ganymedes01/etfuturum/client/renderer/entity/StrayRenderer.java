package ganymedes01.etfuturum.client.renderer.entity;

import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderLiving;

public class StrayRenderer extends RenderBiped
{
    public static final ResourceLocation texture;
    private static final ResourceLocation textureOverlay;
    protected ModelBiped field_82437_k; //modelArmourChestplate
    protected ModelBiped field_82435_l; //modelArmor
    
    public StrayRenderer() {
        super(new ModelSkeleton(), 0.4f);
    }
    
    protected ResourceLocation getEntityTexture(final Entity p_110775_1_) {
        return StrayRenderer.texture;
    }
    
    static {
        texture = new ResourceLocation("textures/entity/skeleton/stray.png"); //stray, stray_merge
        textureOverlay = new ResourceLocation("textures/entity/skeleton/stray_overlay.png");
    }
}

// CREDITS: Legacy4J 1.7.5

package xyz.violaflower.legacy_tweaks.mixin.client.tweak.eye_candy.models.legacy_witch_hat;

import net.minecraft.client.model.WitchModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.violaflower.legacy_tweaks.tweaks.Tweaks;

@Mixin(WitchModel.class)
public class WitchModelMixin {

    @Redirect(method = "createBodyLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/builders/PartDefinition;addOrReplaceChild(Ljava/lang/String;Lnet/minecraft/client/model/geom/builders/CubeListBuilder;Lnet/minecraft/client/model/geom/PartPose;)Lnet/minecraft/client/model/geom/builders/PartDefinition;", ordinal = 0))
    private static PartDefinition createBodyLayerForWitch(PartDefinition instance, String partDefinition, CubeListBuilder partDefinition2, PartPose string) {
        if (Tweaks.EYE_CANDY.models.legacyWitchHat.isEnabled())
            return instance.getChild("head");
        return instance.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.ZERO);
    }

    @ModifyArg(method = "createBodyLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/builders/PartDefinition;addOrReplaceChild(Ljava/lang/String;Lnet/minecraft/client/model/geom/builders/CubeListBuilder;Lnet/minecraft/client/model/geom/PartPose;)Lnet/minecraft/client/model/geom/builders/PartDefinition;", ordinal = 1))
    private static String createBodyLayerForWitch(String string) {
        if (Tweaks.EYE_CANDY.models.legacyWitchHat.isEnabled())
            return "hat1";
        return "hat";
    }
}

package brick_001.mambomodel.client.render.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import brick_001.mambomodel.client.MambomodelClient;
import brick_001.mambomodel.client.model.mambo_character;
import brick_001.mambomodel.client.render.entity.state.MamboCharacterEntityRenderState;
import brick_001.mambomodel.entity.MamboCharacterEntity;

public class MamboCharacterEntityRenderer extends MobEntityRenderer<MamboCharacterEntity, MamboCharacterEntityRenderState, mambo_character> {

    private static final Identifier TEXTURE = Identifier.of("mambomodel", "textures/entity/mambo_character.png");

    public MamboCharacterEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new mambo_character(context.getPart(MambomodelClient.MY_MOB_LAYER)), 0.5f);
    }

    @Override
    public MamboCharacterEntityRenderState createRenderState() {
        return new MamboCharacterEntityRenderState();
    }

    @Override
    public void updateRenderState(MamboCharacterEntity entity, MamboCharacterEntityRenderState state, float partialTicks) {
        super.updateRenderState(entity, state, partialTicks);

        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.walkAnimationState.copyFrom(entity.walkAnimationState);
        state.eatAnimationState.copyFrom(entity.eatAnimationState);
        state.hurtAnimationState.copyFrom(entity.hurtAnimationState);
    }

    @Override
    public Identifier getTexture(MamboCharacterEntityRenderState state) {
        return TEXTURE;
    }
}
package brick_001.mambomodel.client;

import brick_001.mambomodel.client.model.mambo_character;
import brick_001.mambomodel.client.render.entity.MamboCharacterEntityRenderer;
import brick_001.mambomodel.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class MambomodelClient implements ClientModInitializer {

    public static final EntityModelLayer MY_MOB_LAYER = new EntityModelLayer(Identifier.of("mambomodel", "mambo_character"), "main");

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(MY_MOB_LAYER, mambo_character::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MY_MOB, MamboCharacterEntityRenderer::new);
    }
}

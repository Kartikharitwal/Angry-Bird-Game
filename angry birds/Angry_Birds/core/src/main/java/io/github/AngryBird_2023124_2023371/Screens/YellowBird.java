package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


public class YellowBird extends BaseBird {
    private static final float SPEED_MULTIPLIER = 2.0f;
    private boolean hasUsedAbility = false;

    public YellowBird(TextureAtlas atlas, Vector2 position, World world) {
        super(atlas.findRegion("chuck"), position, world);
    }

    public void activateSpeedBoost() {
        if (!hasUsedAbility && getState() == BirdState.LAUNCHED && getBody() != null) {
            Vector2 velocity = getBody().getLinearVelocity();
            getBody().setLinearVelocity(velocity.x * SPEED_MULTIPLIER, velocity.y * SPEED_MULTIPLIER);
            hasUsedAbility = true;
        }
    }

    public boolean hasUsedAbility() {
        return hasUsedAbility;
    }

}

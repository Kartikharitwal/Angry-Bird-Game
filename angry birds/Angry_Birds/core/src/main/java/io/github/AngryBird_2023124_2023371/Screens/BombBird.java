package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class BombBird extends BaseBird {
    private static final float BOMB_BIRD_SCALE = 0.05f;
    private static final float BOMB_BIRD_RADIUS_MODIFIER = 0.5f;
    private static final float EXPLOSION_SCALE = 0.4f;
    private static final float EXPLOSION_DURATION = 0.02f;
    private boolean isExploding = false;
    private float explosionTimer = 0;
    private float originalRadius;

    public BombBird(TextureAtlas atlas, Vector2 position, World world) {
        super(atlas.findRegion("BombBird"), position, world);
        setScale(BOMB_BIRD_SCALE);
    }

    private void scalePhysicsBody(float scale) {
        if (body == null) return;
        if (body.getFixtureList().size > 0) {
            body.destroyFixture(body.getFixtureList().get(0));
        }

        CircleShape shape = new CircleShape();
        float scaledRadius = (originalRadius * scale) / PPM;
        shape.setRadius(scaledRadius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.2f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    @Override
    protected void createPhysicsBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(
            (position.x + getWidth() / 2) / PPM,
            (position.y + getHeight() / 2) / PPM
        );

        body = world.createBody(bodyDef);

        originalRadius = (Math.min(getWidth(), getHeight()) * PHYSICS_RADIUS_SCALE) / 4 * BOMB_BIRD_RADIUS_MODIFIER;

        CircleShape shape = new CircleShape();
        shape.setRadius(originalRadius / PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.2f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void explode() {
        if (!isExploding && getState() == BirdState.LAUNCHED) {
            isExploding = true;
            setScale(EXPLOSION_SCALE);
            scalePhysicsBody(EXPLOSION_SCALE / BOMB_BIRD_SCALE);
        }
    }

    public boolean updateExplosion(float delta) {
        if (isExploding) {
            explosionTimer += delta;
            if (explosionTimer >= EXPLOSION_DURATION) {
                return true;
            }
        }
        return false;
    }
}

package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.physics.box2d.*;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public abstract class BaseBlock extends Actor implements Serializable {
    private static final long serialVersionUID = 1L;
    protected transient TextureRegion blockTexture;
    protected transient Body body;
    protected Vector2 position;
    protected float health;
    protected transient World world;
    private static final float BLOCK_SCALE = 0.1f;

    public Vector2 getPosition() {
        return position;
    }

    public TextureRegion getBlockTexture() {
        return blockTexture;
    }

    public void setBlockTexture(TextureRegion blockTexture) {
        this.blockTexture = blockTexture;
    }

    public Body getBody() {
        return body;
    }

    public World getWorld(){
        return world;
    }
    public float getHealth() {
        return health;
    }
    public void setHealth(float health) {
        this.health = health;
    }

    public BaseBlock(TextureRegion texture, Vector2 position, World world) {
        Gdx.app.log("BaseBlock", "Position in BaseBlock constructor: " + position);  // Log position
        this.world = world;
        this.health = 100;
        this.blockTexture = texture;
        this.position = position;

        if (this.blockTexture == null) {
            System.out.println("Error: blockTexture is null. Using a fallback texture.");
        }
        setScale(BLOCK_SCALE);
        setSize(texture.getRegionWidth(), texture.getRegionHeight());
        createPhysicsBody(world);
    }
    public void initializeTransients(TextureRegion texture, World world) {
        this.blockTexture = texture;
        this.world = world;
        createPhysicsBody(world);
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    public void createPhysicsBody(World world) {
        Gdx.app.log("BaseBlock", "Creating physics body with dimensions: " +
                    "width=" + (getWidth() * getScaleX()) +
                    ", height=" + (getHeight() * getScaleY()));

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(
            (position.x + (getWidth() * getScaleX() / 2)) / GamePhysicsConstants.PPM,
            (position.y + (getHeight() * getScaleY() / 2)) / GamePhysicsConstants.PPM
        );

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(
            (getWidth() * getScaleX()) / 2 / GamePhysicsConstants.PPM,
            (getHeight() * getScaleY()) / 2 / GamePhysicsConstants.PPM
        );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 5.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        body.setTransform(position.x / GamePhysicsConstants.PPM, position.y / GamePhysicsConstants.PPM, 0);
    }
    public void reduceHealth(float damage) {
        this.health -= damage;
        if (this.health <= 0) {
            world.destroyBody(body);
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (blockTexture != null && body != null) {
            Vector2 pos = body.getPosition();
            float width = getWidth() * getScaleX();
            float height = getHeight() * getScaleY();
            float x = (pos.x * GamePhysicsConstants.PPM) - width/2;
            float y = (pos.y * GamePhysicsConstants.PPM) - height/2;

            batch.draw(
                blockTexture,
                x, y,
                width/2, height/2,
                width, height,
                1, 1,
                (float)Math.toDegrees(body.getAngle())
            );
        }
    }

    public boolean isDestroyed(){
        return health<=0;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Vector2 bodyPosition = body.getPosition();
        setPosition(bodyPosition.x * GamePhysicsConstants.PPM, bodyPosition.y * GamePhysicsConstants.PPM);
    }



}

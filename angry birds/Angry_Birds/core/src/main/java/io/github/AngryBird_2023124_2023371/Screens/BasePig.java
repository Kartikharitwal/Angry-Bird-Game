package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import java.io.Serializable;

public abstract class BasePig extends Actor implements Pig, Serializable {

    private static final String TAG = "BasePig";
    protected static TextureAtlas atlas = new TextureAtlas("character.atlas");
    protected Vector2 position;
    protected TextureRegion pigTexture;
    protected Body body;
    protected World world;
    private Label healthLabel;
    private float health = 50.0f;

    private BitmapFont font;
    private boolean markedForDestruction = false;

    public BasePig(TextureRegion pigTexture, Vector2 position, World world) {
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }

        this.pigTexture = pigTexture;
        this.position = position;
        this.world = world;

        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(0.5f);

        setSize(pigTexture.getRegionWidth(), pigTexture.getRegionHeight());

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        healthLabel = new Label("100", skin);
        healthLabel.setFontScale(0.8f);
        healthLabel.setColor(Color.RED);
        updateHealthLabel();

        definePigBody(world);
    }

    public void setHealth(float health) {
      this.health = health;
    }


    public Body getBody() {

        return body;

    }

    public void initializeTexture(TextureAtlas atlas) {
        this.atlas = atlas;
        this.pigTexture = atlas.findRegion("pig");
    }

    private void updateHealthLabel() {
        healthLabel.setText(String.format("%.0f", health));
        healthLabel.setPosition(
            getX() + getWidth()/2 - healthLabel.getWidth()/2,
            getY() + getHeight() + 20
        );
    }

    public Label getHealthLabel() {
        return healthLabel;
    }

    public void setBody(Body body) {

        this.body = body;

    }

    protected void definePigBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.angularDamping = 0.9f;

        bodyDef.position.set(
            position.x / GamePhysicsConstants.PPM,
            position.y / GamePhysicsConstants.PPM
        );

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        float width = getWidth() * getScaleX() * 0.05f;
        float height = getHeight() * getScaleY() * 0.05f;

        shape.setAsBox(
            width / 2 / GamePhysicsConstants.PPM,
            height / 2 / GamePhysicsConstants.PPM
        );

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.8f;
        fixtureDef.friction = 0.9f;
        fixtureDef.restitution = 0.3f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        shape.dispose();
    }

    @Override
    public void getHit() {
        reduceHealth(25);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (pigTexture != null && body != null) {
            Vector2 pos = body.getPosition();
            float x = pos.x * GamePhysicsConstants.PPM - (getWidth() * getScaleX() / 2);
            float y = pos.y * GamePhysicsConstants.PPM - (getHeight() * getScaleY() / 2);
            batch.draw(
                pigTexture,
                x, y,
                getWidth() * getScaleX() / 2,
                getHeight() * getScaleY() / 2,
                getWidth() * getScaleX(),
                getHeight() * getScaleY(),
                1, 1,
                body.getAngle() * MathUtils.radiansToDegrees
            );

            font.draw(
                batch,
                "HP: " + health,
                x + (getWidth() * getScaleX() / 2) - 15,
                y + getHeight() * getScaleY() + 20
            );
        }
    }

    public void reduceHealth(float damage) {
        float oldHealth = this.health;
        this.health -= damage;
        if (health < 0) health = 0;

        Gdx.app.log(TAG, String.format("Pig health reduced from %.1f to %.1f (damage: %.1f)",
            oldHealth, health, damage));

        if (health <= 0) {
            Gdx.app.log(TAG, "Pig marked for destruction!");
            markForDestruction();
        }
        updateHealthLabel();
    }

    public void markForDestruction() {
        markedForDestruction = true;
    }

    public boolean isMarkedForDestruction() {
        return markedForDestruction;
    }

    public float getHealth() {


        return this.health;

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updateHealthLabel();
    }

    public void dispose() {
        font.dispose();
    }
}

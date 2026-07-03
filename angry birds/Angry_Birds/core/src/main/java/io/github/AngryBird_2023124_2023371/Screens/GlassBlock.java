package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class GlassBlock extends BaseBlock {


    public GlassBlock(Vector2 position, World world) {
        super(new TextureRegion(new Texture(Gdx.files.internal("blockglass1.png"))), position, world);
        Gdx.app.log("GlassBlock", "Position: " + position);
    }
}

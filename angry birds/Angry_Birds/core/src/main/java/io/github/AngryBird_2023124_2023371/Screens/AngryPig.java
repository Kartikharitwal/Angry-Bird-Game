package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class AngryPig extends BasePig{

    public AngryPig(World world, TextureAtlas atlas, Vector2 position) {
        super(atlas.findRegion("helmetpig"), position,world);
    }
}

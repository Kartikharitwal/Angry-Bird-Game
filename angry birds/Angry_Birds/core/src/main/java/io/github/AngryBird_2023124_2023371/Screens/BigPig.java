package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class BigPig extends BasePig{


    public BigPig(TextureAtlas atlas, Vector2 pos, World world){
        super(atlas.findRegion("fatpig"),pos,world);
    }
}

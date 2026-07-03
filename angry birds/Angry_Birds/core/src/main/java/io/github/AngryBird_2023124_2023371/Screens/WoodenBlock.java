package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class WoodenBlock extends BaseBlock{

   public WoodenBlock(Vector2 position, World world) {

      super(new TextureRegion(new Texture(Gdx.files.internal("blockwood_1.png"))),position, world);


  }


   public WoodenBlock(World world, TextureAtlas atlas, Vector2 position) {
        super(new TextureRegion(new Texture(Gdx.files.internal("blockwood_1.png"))), position, world);
        this.setScale(2.5f);
   }

}

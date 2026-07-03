package io.github.AngryBird_2023124_2023371;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.AngryBird_2023124_2023371.Screens.PlayScreen;

import java.io.Serializable;

public class AngryBirds extends Game implements Serializable {
    public static final int V_WIDTH = 960;
    public static final int V_HEIGHT = 540;
    public static final int PPM = 32;
    public static final int PPM1 = 1;
    private SpriteBatch batch;



    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void create () {
        batch = new SpriteBatch();
        PlayScreen p1 = new PlayScreen(this);
        setScreen(p1);
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
        this.getBatch().dispose();
    }
}

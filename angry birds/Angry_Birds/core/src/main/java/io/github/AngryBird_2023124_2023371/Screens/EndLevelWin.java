package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.AngryBird_2023124_2023371.AngryBirds;

public class EndLevelWin implements Screen {
    private Stage stage;
    private AngryBirds game;
    private Texture backgroundTexture;
    private TextButton mainMenuButton;
    private PlayScreen playScreen;

    public EndLevelWin(final AngryBirds game) {
        this.game = game;
        this.playScreen = new PlayScreen(game);
        stage = new Stage(new StretchViewport(800, 400));
        Gdx.input.setInputProcessor(stage);
        backgroundTexture = new Texture(Gdx.files.internal("WinningScreen.jpeg"));
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        mainMenuButton = new TextButton("Home", skin);
        mainMenuButton.setSize(200, 50);
        mainMenuButton.setPosition(300, 75);
        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new mainMenuScreen(game, playScreen));
            }
        });
        stage.addActor(mainMenuButton);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.getBatch().begin();
        if (backgroundTexture != null) {
            stage.getBatch().draw(backgroundTexture, 0, 0, 800, 400);
        }
        stage.getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        if (backgroundTexture != null) backgroundTexture.dispose();
    }
}

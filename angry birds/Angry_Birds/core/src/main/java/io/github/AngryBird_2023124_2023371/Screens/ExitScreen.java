package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.AngryBird_2023124_2023371.AngryBirds;

import java.io.Serializable;

public class ExitScreen implements Screen, Serializable {
    private final AngryBirds game;
    private Stage stage;
    private OrthographicCamera camera;
    private Skin skin;
    private TextButton confirmButton;
    private TextButton cancelButton;
    private Label messageLabel;
    private com.badlogic.gdx.scenes.scene2d.ui.Image birdImg;
    private Texture BirdPic;

    public ExitScreen(AngryBirds game) {
        this.game = game;
        camera = new OrthographicCamera();
        stage = new Stage(new StretchViewport(800, 600, camera));
        Gdx.input.setInputProcessor(stage);
        BirdPic = new Texture("cryingBackground.jpg");
        birdImg = new com.badlogic.gdx.scenes.scene2d.ui.Image(BirdPic);
        birdImg.setHeight(stage.getHeight());
        birdImg.setWidth(stage.getWidth());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        messageLabel = new Label("Are you sure you want to exit?", skin);
        messageLabel.setColor(Color.RED);
        confirmButton = new TextButton("Yes", skin);
        cancelButton = new TextButton("No", skin);
        confirmButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new mainMenuScreen(game, new PlayScreen(game)));
            }
        });
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(messageLabel).padBottom(20).row();
        table.add(confirmButton).padRight(20);
        table.add(cancelButton);
        stage.addActor(birdImg);
        stage.addActor(table);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
        skin.dispose();
    }
}

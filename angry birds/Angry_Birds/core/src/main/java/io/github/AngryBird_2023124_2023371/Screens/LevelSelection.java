package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.AngryBird_2023124_2023371.AngryBirds;

public class LevelSelection implements Screen {
    private Stage stage;
    private Texture background;
    private Image level1Button;
    private Image level2Button;
    private Image level3Button;
    private Image title;
    private SpriteBatch batch;
    private ImageButton backbtn;
    private PlayScreen playScreen;

    public LevelSelection(AngryBirds game) {
        stage = new Stage(new StretchViewport(800, 400));
        Gdx.input.setInputProcessor(stage);

        this.playScreen = new PlayScreen(game);


        background = new Texture(Gdx.files.internal("back 2.jpg"));


        Texture titleTexture = new Texture(Gdx.files.internal("11q.png"));
        Texture level1Texture = new Texture(Gdx.files.internal("level1.png"));
        Texture level2Texture = new Texture(Gdx.files.internal("level2.png"));
        Texture level3Texture = new Texture(Gdx.files.internal("level3.png"));

        Texture pressed_up = new Texture(Gdx.files.internal("backbtn.png"));
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(pressed_up);
        ImageButton playButton = new ImageButton(style);


        backbtn = playButton;
        title = new Image(titleTexture);
        level1Button = new Image(level1Texture);
        level2Button = new Image(level2Texture);
        level3Button = new Image(level3Texture);


        float iconWidth = 175;
        float iconHeight = 175;
        float titleWidth = 450;
        float titleHeight = 90;


        title.setSize(titleWidth, titleHeight);
        title.setPosition((800 - titleWidth) / 2, 300);


        backbtn.setSize(60, 60);
        backbtn.setPosition(20, 320);
        level1Button.setSize(iconWidth, iconHeight);
        level2Button.setSize(iconWidth, iconHeight);
        level3Button.setSize(iconWidth, iconHeight);


        float spacing = 35;
        level1Button.setPosition(100, 100);
        level2Button.setPosition(level1Button.getX() + iconWidth + spacing, 100);
        level3Button.setPosition(level2Button.getX() + iconWidth + spacing, 100);


        stage.addActor(title);
        stage.addActor(backbtn);
        stage.addActor(level1Button);
        stage.addActor(level2Button);
        stage.addActor(level3Button);

        backbtn.addListener(new ClickListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                backbtn.setScale(1.1f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                backbtn.setScale(1f);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new mainMenuScreen(game,playScreen));
            }
        });


        level1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new Level1(game));
            }
        });
         level2Button.addListener(new ClickListener() {
             @Override
             public void clicked(InputEvent event, float x, float y) {

                 game.setScreen(new Level2(game));
             }
         });
         level3Button.addListener(new ClickListener() {
             @Override
             public void clicked(InputEvent event, float x, float y) {
                 game.setScreen(new Level3(game)); // Transition to Level 3
             }
         });

        level2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level2(game)); // Transition to Level2
            }
        });

        level3Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Level3(game)); // Transition to Level3
            }
        });


        level1Button.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                level1Button.setScale(1.1f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                level1Button.setScale(1f);
            }
        });

        level2Button.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                level2Button.setScale(1.1f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                level2Button.setScale(1f);
            }
        });

        level3Button.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                level3Button.setScale(1.1f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                level3Button.setScale(1f);
            }
            });


    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch = (SpriteBatch) stage.getBatch();
        batch.begin();
        batch.draw(background, 0, 0, 800, 400);
        batch.end();


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
        background.dispose();
    }
}

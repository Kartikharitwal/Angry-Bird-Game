package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.AngryBird_2023124_2023371.AngryBirds;

public class ResumeScreen implements Screen {
    private AngryBirds game;
    private Texture BgroundText;
    private Texture Bground;
    private Stage stage;
    private SpriteBatch batch;

    private Image savedGame1, savedGame2, savedGame3, savedGame4;

    public ResumeScreen(AngryBirds game) {
        this.game = game;
        this.BgroundText = new Texture("choose.png");
        this.Bground = new Texture("resumebg.jpg");
        this.batch = new SpriteBatch();
        this.stage = new Stage(new StretchViewport(AngryBirds.V_WIDTH, AngryBirds.V_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        float buttonWidth = 200f;
        float buttonHeight = 200f;
        float spacing = 10f;

        float startX = (AngryBirds.V_WIDTH - (2 * buttonWidth + spacing)) / 2;
        float startY = 210f;

        savedGame1 = createSavedGameImage("level1.png", 1, startX, startY, buttonWidth, buttonHeight);
        savedGame2 = createSavedGameImage("level2.png", 2, startX + buttonWidth + spacing, startY, buttonWidth, buttonHeight);
        savedGame3 = createSavedGameImage("level3.png", 3, startX, startY - buttonHeight - spacing, buttonWidth, buttonHeight);
        savedGame4 = createSavedGameImage("gig.png", 1, startX + buttonWidth + spacing, startY - buttonHeight - spacing, buttonWidth, buttonHeight);

        stage.addActor(savedGame1);
        stage.addActor(savedGame2);
        stage.addActor(savedGame3);
        stage.addActor(savedGame4);

        addBackButton();
    }

    private Image createSavedGameImage(String texturePath, int levelNumber, float x, float y, float width, float height) {
        Image image = new Image(new TextureRegionDrawable(new Texture(texturePath)));
        image.setPosition(x, y);
        image.setSize(width, height);

        image.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String saveSlot = "save_slot_" + levelNumber + ".json";
                try {
                    if (Gdx.files.local(saveSlot).exists()) {
                        Screen loadedLevel = null;
                        switch(levelNumber) {
                            case 1:
                                loadedLevel = Level1.loadGameFromJson(game, saveSlot);
                                break;
                            case 2:
                                loadedLevel = Level2.loadGameFromJson(game, saveSlot);
                                break;
                            case 3:
                                loadedLevel = Level3.loadGameFromJson(game, saveSlot);
                                break;
                        }

                        if (loadedLevel != null) {
                            Gdx.app.log("ResumeScreen", "Loading level " + levelNumber);
                            game.setScreen(loadedLevel);
                        } else {
                            Gdx.app.error("ResumeScreen", "Failed to load level " + levelNumber);
                        }
                    } else {
                        Gdx.app.log("ResumeScreen", "No save file found for level " + levelNumber);
                    }
                } catch (Exception e) {
                    Gdx.app.error("ResumeScreen", "Error loading level " + levelNumber, e);
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                image.setScale(1.1f);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                image.setScale(1.0f);
            }
        });

        return image;
    }

    private void addBackButton() {
        try {
            Image backButton = new Image(new Texture(Gdx.files.internal("backbtn.png")));
            backButton.setSize(60, 60);
            backButton.setPosition(20, Gdx.graphics.getHeight() - 80);

            backButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    try {
                        game.setScreen(new mainMenuScreen(game, null));
                        dispose();
                    } catch (Exception e) {
                        Gdx.app.error("ResumeScreen", "Error transitioning to main menu", e);
                    }
                }
            });

            stage.addActor(backButton);
        } catch (Exception e) {
            Gdx.app.error("ResumeScreen", "Error creating back button", e);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(Bground, 0, 0, AngryBirds.V_WIDTH, AngryBirds.V_HEIGHT);
        batch.draw(BgroundText, 100f, 380f, 450, 90);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
        stage.dispose();
        Bground.dispose();
        BgroundText.dispose();
    }
}

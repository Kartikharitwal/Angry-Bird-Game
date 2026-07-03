package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.AngryBird_2023124_2023371.AngryBirds;

public class PauseMenuScreen implements Screen {
    private Stage stage;
    private AngryBirds game;
    private Texture pauseMenuBackground;
    private ImageButton resumeButton;
    private ImageButton restartButton;
    private ImageButton mainMenuButton;

    private Screen currentLevel;
    private int levelNumber;

    public PauseMenuScreen(final AngryBirds game, final Screen currentLevel) {
        this.game = game;
        this.currentLevel = currentLevel;

        if (currentLevel instanceof Level1) {
            this.levelNumber = 1;
        } else if (currentLevel instanceof Level2) {
            this.levelNumber = 2;
        } else if (currentLevel instanceof Level3) {
            this.levelNumber = 3;
        }

        stage = new Stage(new StretchViewport(800, 480));
        Gdx.input.setInputProcessor(stage);

        pauseMenuBackground = new Texture(Gdx.files.internal("pausemenu_background.png"));
        Texture resumeTexture = new Texture(Gdx.files.internal("resume_btn.png"));
        Texture restartTexture = new Texture(Gdx.files.internal("restart_btn.png"));
        Texture menuTexture = new Texture(Gdx.files.internal("mainmenu_btn.png"));

        ImageButton.ImageButtonStyle resumeStyle = new ImageButton.ImageButtonStyle();
        resumeStyle.up = new TextureRegionDrawable(resumeTexture);

        ImageButton.ImageButtonStyle restartStyle = new ImageButton.ImageButtonStyle();
        restartStyle.up = new TextureRegionDrawable(restartTexture);

        ImageButton.ImageButtonStyle menuStyle = new ImageButton.ImageButtonStyle();
        menuStyle.up = new TextureRegionDrawable(menuTexture);

        resumeButton = new ImageButton(resumeStyle);
        restartButton = new ImageButton(restartStyle);
        mainMenuButton = new ImageButton(menuStyle);

        float buttonWidth = 120;
        float buttonHeight = 60;
        float spacing = 40;

        float totalWidth = (buttonWidth * 3) + (spacing * 2);
        float startX = (800 - totalWidth) / 2;
        float centerY = 480 / 2;

        resumeButton.setBounds(startX, centerY - buttonHeight/2, buttonWidth, buttonHeight);
        restartButton.setBounds(startX + buttonWidth + spacing, centerY - buttonHeight/2, buttonWidth, buttonHeight);
        mainMenuButton.setBounds(startX + (buttonWidth + spacing) * 2, centerY - buttonHeight/2, buttonWidth, buttonHeight);

        setupButtons();
    }

    private void setupButtons() {
        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen loadedLevel = null;
                switch (levelNumber) {
                    case 1:
                        loadedLevel = Level1.loadGameFromJson(game, SaveSlot.SLOT_1);
                        break;
                    case 2:
                        loadedLevel = Level2.loadGameFromJson(game, SaveSlot.SLOT_2);
                        break;
                    case 3:
                        loadedLevel = Level3.loadGameFromJson(game,  SaveSlot.SLOT_3);
                        break;
                }

                if (loadedLevel != null) {
                    game.setScreen(loadedLevel);
                }
            }
        });

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Screen newLevel = null;
                switch (levelNumber) {
                    case 1:
                        newLevel = new Level1(game);
                        break;
                    case 2:
                        newLevel = new Level2(game);
                        break;
                    case 3:
                        newLevel = new Level3(game);
                        break;
                }
                if (newLevel != null) {
                    game.setScreen(newLevel);
                }
            }
        });

        mainMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new mainMenuScreen(game, null));
            }
        });

        stage.addActor(resumeButton);
        stage.addActor(restartButton);
        stage.addActor(mainMenuButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void show() {}
    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void dispose() {
        stage.dispose();
        if (pauseMenuBackground != null) pauseMenuBackground.dispose();
    }
}


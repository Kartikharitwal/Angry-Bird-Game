package io.github.AngryBird_2023275_2023371.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.github.AngryBird_2023275_2023371.AngryBirds;
import io.github.AngryBird_2023275_2023371.Screens.mainMenuScreen;
import io.github.AngryBird_2023275_2023371.Screens.Level1;

public class SaveLoadMenu implements Screen {
    private static final String TAG = "SaveLoadMenu";
    private final Stage stage;
    private final Skin skin;
    private final AngryBirds game;
    private final Screen currentLevel;
    private final int levelNumber;

    public SaveLoadMenu(AngryBirds game, Screen currentLevel) {
        this.game = game;
        this.currentLevel = currentLevel;
        this.stage = new Stage(new FitViewport(800, 480));
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        if (currentLevel instanceof Level1) {
            this.levelNumber = 1;
        } else if (currentLevel instanceof Level2) {
            this.levelNumber = 2;
        } else if (currentLevel instanceof Level3) {
            this.levelNumber = 3;
        } else {
            this.levelNumber = 0;
        }

        createUI();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        try {
            if (stage != null) {
                stage.dispose();
            }
            Gdx.app.log(TAG, "SaveLoadMenu disposed successfully");
        } catch (Exception e) {
            Gdx.app.error(TAG, "Error disposing SaveLoadMenu", e);
        }
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);

        Label titleLabel = new Label("Save Level " + levelNumber, skin);
        titleLabel.setFontScale(1.5f);
        TextButton saveSlot1 = new TextButton("Save Level " + levelNumber + " to Slot 1", skin);
        TextButton saveSlot2 = new TextButton("Save Level " + levelNumber + " to Slot 2", skin);
        TextButton saveSlot3 = new TextButton("Save Level " + levelNumber + " to Slot 3", skin);
        TextButton saveSlot4 = new TextButton("Save Level " + levelNumber + " to Slot 4", skin);
        TextButton backButton = new TextButton("Back", skin);

        saveSlot1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveLevel(SaveSlot.SLOT_1);
            }
        });

        saveSlot2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveLevel(SaveSlot.SLOT_2);
            }
        });

        saveSlot3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveLevel(SaveSlot.SLOT_3);
            }
        });

        saveSlot4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveLevel(SaveSlot.SLOT_4);
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                returnToLevel();
            }
        });

        table.add(titleLabel).padBottom(40).row();
        table.add(saveSlot1).width(250).height(50).padBottom(20).row();
        table.add(saveSlot2).width(250).height(50).padBottom(20).row();
        table.add(saveSlot3).width(250).height(50).padBottom(20).row();
        table.add(saveSlot4).width(250).height(50).padBottom(20).row();
        table.add(backButton).width(250).height(50);

        stage.addActor(table);
    }

    private void saveLevel(String saveSlot) {
        try {
            switch (levelNumber) {
                case 1:
                    ((Level1)currentLevel).saveGameToJson(saveSlot);
                    break;
                case 2:
                    ((Level2)currentLevel).saveGameToJson(saveSlot);
                    break;
                case 3:
                    ((Level3)currentLevel).saveGameToJson(saveSlot);
                    break;
            }
            returnToLevel();
        } catch (Exception e) {
            Gdx.app.error(TAG, "Error saving level " + levelNumber, e);
        }
    }

    private void returnToLevel() {
        game.setScreen(currentLevel);
        switch (levelNumber) {
            case 1:
                Gdx.input.setInputProcessor(((Level1)currentLevel).getStage());
                break;
            case 2:
                Gdx.input.setInputProcessor(((Level2)currentLevel).getStage());
                break;
            case 3:
                Gdx.input.setInputProcessor(((Level3)currentLevel).getStage());
                break;
        }
        dispose();
    }
}


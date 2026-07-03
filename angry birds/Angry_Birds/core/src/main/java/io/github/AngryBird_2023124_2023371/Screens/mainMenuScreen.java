package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.AngryBird_2023124_2023371.AngryBirds;

import java.io.Serializable;


public class mainMenuScreen implements Screen, Serializable {

    public static final Color YELLOW = new Color(0xffff00ff);
    public static final Color SKY = new Color(0x87ceebff);
    private AngryBirds game;
    private float elapsed;
    private Stage stage;
    private Table table;
    private com.badlogic.gdx.scenes.scene2d.ui.Image birdImg;
    private com.badlogic.gdx.scenes.scene2d.ui.Image flock;
    private OrthographicCamera cam;
    private Label title;
    private StretchViewport gameViewPort;
    private ImageButton newGameBtn;

    private Texture BirdPic;
    private Texture additional;
    private ImageButton ResumeGameBtn;
    private ImageButton ExitGameBtn;

    private BitmapFont black;
    private BitmapFont white;
    private TextureAtlas atlas;
    private Viewport viewport;
    private PlayScreen p1;

    public AngryBirds getGame() {
        return game;
    }

    public void setGame(AngryBirds game) {
        this.game = game;
    }

    public float getElapsed() {
        return elapsed;
    }

    public void setElapsed(float elapsed) {
        this.elapsed = elapsed;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Image getTankImg() {
        return birdImg;
    }

    public void setTankImg(Image tankImg) {
        this.birdImg = tankImg;
    }

    public OrthographicCamera getCam() {
        return cam;
    }

    public void setCam(OrthographicCamera cam) {
        this.cam = cam;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public StretchViewport getGameViewPort() {
        return gameViewPort;
    }

    public void setGameViewPort(StretchViewport gameViewPort) {
        this.gameViewPort = gameViewPort;
    }

    public ImageButton getNewGameBtn() {
        return newGameBtn;
    }

    public void setNewGameBtn(ImageButton newGameBtn) {
        this.newGameBtn = newGameBtn;
    }

    public Texture getTankPic() {
        return BirdPic;
    }

    public void setTankPic(Texture tankPic) {
        BirdPic = tankPic;
    }

    public ImageButton getResumeGameBtn() {
        return ResumeGameBtn;
    }

    public void setResumeGameBtn(ImageButton resumeGameBtn) {
        ResumeGameBtn = resumeGameBtn;
    }

    public ImageButton getExitGameBtn() {
        return ExitGameBtn;
    }

    public void setExitGameBtn(ImageButton exitGameBtn) {
        ExitGameBtn = exitGameBtn;
    }

    public BitmapFont getBlack() {
        return black;
    }

    public void setBlack(BitmapFont black) {
        this.black = black;
    }

    public BitmapFont getWhite() {
        return white;
    }

    public void setWhite(BitmapFont white) {
        this.white = white;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void setAtlas(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public PlayScreen getP1() {
        return p1;
    }

    public void setP1(PlayScreen p1) {
        this.p1 = p1;
    }

    public mainMenuScreen(final AngryBirds game, final PlayScreen p1) {
        this.game = game;
        this.p1 = p1;
        this.cam = new OrthographicCamera();
        viewport = new StretchViewport(AngryBirds.V_WIDTH, AngryBirds.V_HEIGHT, cam);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        cam.setToOrtho(false, AngryBirds.V_WIDTH, AngryBirds.V_HEIGHT);
        gameViewPort = new StretchViewport(AngryBirds.V_WIDTH, AngryBirds.V_HEIGHT, cam);

        black = new BitmapFont(Gdx.files.internal("font/black.fnt"));
        white = new BitmapFont(Gdx.files.internal("font/gradient.fnt"));

        additional = new Texture("redvspig.png");
        flock = new com.badlogic.gdx.scenes.scene2d.ui.Image(additional);
        flock.setHeight(cam.viewportHeight / 2.0f);
        flock.setWidth(cam.viewportWidth / 2.0f);
        flock.setPosition(40, 130);

        BirdPic = new Texture("mainbg_1.jpg");
        birdImg = new com.badlogic.gdx.scenes.scene2d.ui.Image(BirdPic);
        birdImg.setHeight(cam.viewportHeight);
        birdImg.setWidth(cam.viewportWidth);


        Texture pressed_up = new Texture(Gdx.files.internal("newgamebtn.png"));
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = new TextureRegionDrawable(pressed_up);
        ImageButton playButton = new ImageButton(style);

        Texture resume_btn_style = new Texture(Gdx.files.internal("FinalResumebtn.png"));
        ImageButton.ImageButtonStyle style_2 = new ImageButton.ImageButtonStyle();
        style_2.up = new TextureRegionDrawable(resume_btn_style);
        ImageButton resumeBtn = new ImageButton(style_2);

        Texture exit_btn = new Texture(Gdx.files.internal("exitgamebtn.png"));
        ImageButton.ImageButtonStyle style_3 = new ImageButton.ImageButtonStyle();
        style_3.up = new TextureRegionDrawable(exit_btn);
        ImageButton Exitbtn = new ImageButton(style_3);


        newGameBtn = playButton;
        ResumeGameBtn = resumeBtn;
        ExitGameBtn = Exitbtn;


        table = new Table();
        table.padTop(5);
        table.setSize(AngryBirds.V_WIDTH / 2, AngryBirds.V_HEIGHT);
        table.add(newGameBtn).height(150).width(300).spaceTop(30).expandX().row();
        table.add(ResumeGameBtn).height(150).width(400).spaceTop(30).expandX().row();
        table.add(ExitGameBtn).height(150).width(300).spaceTop(30).expandX().row();
        table.setPosition(AngryBirds.V_WIDTH / 2, 0);


        stage.addActor(birdImg);
        stage.addActor(flock);
        stage.addActor(table);



        newGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                newGameBtn.addAction(Actions.sequence(
                    Actions.scaleTo(0.9f, 0.9f, 0.1f, Interpolation.smooth),
                    Actions.run(() -> {

                        game.setScreen(new LevelSelection(game));
                        dispose();
                    }),
                    Actions.scaleTo(1f, 1f, 0.1f, Interpolation.smooth)
                ));
            }
        });

        ResumeGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ResumeGameBtn.addAction(Actions.sequence(
                    Actions.scaleTo(0.9f, 0.9f, 0.1f, Interpolation.smooth),
                    Actions.run(() -> {
                        ResumeScreen r1 = new ResumeScreen(game);
                        game.setScreen(r1);
                        dispose();
                    }),
                    Actions.scaleTo(1f, 1f, 0.1f, Interpolation.smooth)
                ));
            }
        });

        ExitGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ExitGameBtn.addAction(Actions.sequence(
                    Actions.scaleTo(0.9f, 0.9f, 0.1f, Interpolation.smooth),
                    Actions.run(() -> {

                        ExitScreen e1 = new ExitScreen(p1.getGame());
                        game.setScreen(e1);
                        dispose();
                    }),
                    Actions.scaleTo(1f, 1f, 0.1f, Interpolation.smooth)
                ));
            }
        });
    }




    private Texture createColorTexture(Color color) {
        Pixmap pixmap = new Pixmap(200, 80, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }





    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        elapsed += Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(SKY);
        stage.act(Gdx.graphics.getDeltaTime());
        game.getBatch().setProjectionMatrix(cam.combined);
        stage.draw();


        game.getBatch().begin();
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        gameViewPort.update(width,height);
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
        stage.dispose();
    }
}

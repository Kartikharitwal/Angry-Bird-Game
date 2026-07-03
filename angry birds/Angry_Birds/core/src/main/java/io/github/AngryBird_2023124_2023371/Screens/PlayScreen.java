package io.github.AngryBird_2023124_2023371.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.AngryBird_2023124_2023371.AngryBirds;

import java.io.Serializable;

public class PlayScreen implements Screen , Serializable {

    private AngryBirds game;
    private Texture texture;

    private int i;
    private float j;

    private Animation<TextureRegion> animation;
    private float elapsed;

    private OrthographicCamera GameCam;
    private Viewport GamePort;

    private Texture LoadingBar;

    private BitmapFont font;

    private Texture LoadingProgress1;
    private Texture LoadingProgress2;
    private Texture LoadingProgress3;
    private Texture LoadingProgress4;


    public float getJ() {
        return j;
    }

    public void setJ(float j) {
        this.j = j;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public float getElapsed() {
        return elapsed;
    }

    public void setElapsed(float elapsed) {
        this.elapsed = elapsed;
    }

    public Texture getLoadingProgress2() {
        return LoadingProgress2;
    }

    public void setLoadingProgress2(Texture loadingProgress2) {
        LoadingProgress2 = loadingProgress2;
    }

    public Texture getLoadingProgress3() {
        return LoadingProgress3;
    }

    public void setLoadingProgress3(Texture loadingProgress3) {
        LoadingProgress3 = loadingProgress3;
    }

    public Texture getLoadingProgress4() {
        return LoadingProgress4;
    }

    public void setLoadingProgress4(Texture loadingProgress4) {
        LoadingProgress4 = loadingProgress4;
    }

    public Texture getLoadingProgress1() {
        return LoadingProgress1;
    }

    public void setLoadingProgress1(Texture loadingProgress1) {
        LoadingProgress1 = loadingProgress1;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }


    public Texture getLoadingBar() {
        return LoadingBar;
    }

    public void setLoadingBar(Texture loadingBar) {
        LoadingBar = loadingBar;
    }


    public AngryBirds getGame() {
        return game;
    }

    public void setGame(AngryBirds game) {
        this.game = game;
    }

    public OrthographicCamera getGameCam() {
        return GameCam;
    }

    public void setGameCam(OrthographicCamera gameCam) {
        GameCam = gameCam;
    }

    public Viewport getGamePort() {
        return GamePort;
    }

    public void setGamePort(Viewport gamePort) {
        GamePort = gamePort;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public PlayScreen(AngryBirds game){
        i = 0;
        j = 0;
        this.game = game;
        this.texture = new Texture("AngryBirdsHomePage3.jpg");
        this.LoadingBar = new Texture("barbg.png");
        this.LoadingProgress1 = new Texture("load1.png");
        this.LoadingProgress2 = new Texture("load2.png");
        this.LoadingProgress3 = new Texture("load3.png");
        this.LoadingProgress4 = new Texture("load4.png");
        this.font = new BitmapFont();
        this.GameCam = new OrthographicCamera();
        this.GameCam.setToOrtho(false, AngryBirds.V_WIDTH, AngryBirds.V_HEIGHT);
        this.GamePort = new StretchViewport(AngryBirds.V_WIDTH, AngryBirds.V_HEIGHT,this.GameCam);
        this.getFont().getData().scale(1.25f);
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("angry_bird_loading.gif").read());
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.getBatch().setProjectionMatrix(this.getGameCam().combined);
        game.getBatch().begin();
        game.getBatch().draw(texture,0,0,getGameCam().viewportWidth,getGameCam().viewportHeight);
        game.getBatch().draw(LoadingBar,480-LoadingBar.getWidth()/2,-185);
        if(i >= 0 && i < 60){
            game.getBatch().draw(LoadingBar,480-LoadingBar.getWidth()/2,-185);
            this.getFont().draw(game.getBatch(), "Loading 0%", 300, 95);
        }

        else  if(i >= 60 && i < 120){
            game.getBatch().draw(LoadingProgress1,480-LoadingProgress1.getWidth()/2,-185);
            this.getFont().draw(game.getBatch(), "Loading 25%", 280, 95);
        }

        else  if(i >= 120 && i < 180){
            game.getBatch().draw(LoadingProgress2,480-LoadingProgress2.getWidth()/2,-185);
            this.getFont().draw(game.getBatch(), "Loading 50%", 280, 95);
        }

        else  if(i >= 180 && i < 310){
            game.getBatch().draw(LoadingProgress3,480-LoadingProgress3.getWidth()/2,-185);
            this.getFont().draw(game.getBatch(), "Loading 75%", 280, 95);
        }

        else  if(i >= 310){
            game.getBatch().draw(LoadingProgress4,480-LoadingProgress4.getWidth()/2,-185);
            this.getFont().draw(game.getBatch(), "Loading 100%", 280, 95);
        }
        if(j < 365){
            game.getBatch().draw(animation.getKeyFrame(elapsed), 220.0f + j, 112.5f,150,75);
            j++;
        }
        if(j == 360){
            j = 0;
        }
        i++;

        if(i > 320 ){
            this.getFont().draw(game.getBatch(), "Click to  Continue", 350, 195+100);
            if(Gdx.input.isTouched()){
                mainMenuScreen m1 = new mainMenuScreen(getGame(),this);
                getGame().setScreen(m1);
            }
        }
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        GamePort.update(width,height);
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

    }
}

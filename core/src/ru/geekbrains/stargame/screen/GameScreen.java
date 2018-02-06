package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import ru.geekbrains.stargame.Background;
import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;
import ru.geekbrains.stargame.star.Star;

public class GameScreen extends Base2DScreen {

    private Texture backgroundTexture;
    private Background background;

    private TextureAtlas atlas;
    private Array<Star> stars = new Array<Star>();

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("bg.png");
        background = new Background(new TextureRegion(backgroundTexture));

        atlas = new TextureAtlas("menuAtlas.tpack");
        for(int i = 0; i < 100; ++i){
            stars.add( new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.5f, -0.1f), 0.01f));
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for(Star star: stars){
            star.update(delta);
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for(Star star: stars){
            star.draw(batch);
        }
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for(Star star: stars){
            star.resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        atlas.dispose();
        super.dispose();
    }
}

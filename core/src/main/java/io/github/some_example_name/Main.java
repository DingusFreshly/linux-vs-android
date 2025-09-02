package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2; // ve
import com.badlogic.gdx.graphics.Color;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    private Vector2 playerPos;
    private Vector2 playerVel;
    private final float SPEED = 0.2f; // change ts so it doesnt be goofy i guess
    private final float DRAG = 0.95f;

    private int SCREEN_WIDTH ; //holy its taking eons
    private int SCREEN_HEIGHT; // scre it im doing it mangually

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        playerPos = new Vector2(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f);
        playerVel = Vector2.Zero;

        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();
    }

    @Override
    public void render() { // main loop is ts function, no call backs :(
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // two updates to clear

        handleInput();
        movePlayer();
        wrapPlayer();
        drawShapes();//k i think this is why its not running
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.W)) playerVel.y += SPEED;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.S)) playerVel.y -= SPEED;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A)) playerVel.x -= SPEED;
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D)) playerVel.x += SPEED;
    }
    
    private void movePlayer() { // actually apply vel to pos and add drag
        playerPos = playerPos.add(playerVel);
        playerVel.scl(DRAG);
    }
    
    //where is screen size uhh
    private void wrapPlayer() {
        //particle accelarator
        if (playerPos.x > SCREEN_WIDTH) playerPos.x = 0;
        
        if (playerPos.x < 0) playerPos.x = SCREEN_WIDTH;
        if (playerPos.y > SCREEN_HEIGHT) playerPos.y = 0;
        
        if (playerPos.y < 0) playerPos.y = SCREEN_HEIGHT;
    }
    
    private void drawShapes() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);//dam zuckerburg
        shapeRenderer.setColor(new Color(0, 255, 0, 0)); // uhh its taking 
        shapeRenderer.circle(playerPos.x, playerPos.y, 10);
       
        shapeRenderer.end();
    }

    @Override // time to understand ts javaslop
    public void dispose() {
        shapeRenderer.dispose();
    }
} // check discoed
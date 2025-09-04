package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2; // ve
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.graphics.Color;


public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    // private Vector2 playerPos; // todo: get rid of this crap
    // private Vector2 playerVel;
    private final float RADIUS = 10f;
    private final float SPEED = 0.2f; // change ts so it doesnt be goofy i guess
    private final float DRAG = 0.95f;

    private int SCREEN_WIDTH ; //holy its taking eons
    private int SCREEN_HEIGHT; // scre it im doing it mangually
    private Vector2 GRAVITY;
    public World world;
    private Player player;
    
    @Override
    public void create() {
        //constant intit
        GRAVITY = new Vector2(0, -0.1f);
        shapeRenderer = new ShapeRenderer();

        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();

        // playerPos = new Vector2((float)SCREEN_WIDTH / 2f, (float)SCREEN_HEIGHT / 2f);
        // playerVel = Vector2.Zero;

        world = new World(GRAVITY, true); // world instantiate before player ‼️
        player = new Player(world, new Vector2(5, 10));//holy crap its so tiny

    }

    @Override
    public void render() { // main loop is ts function, no call backs :(
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // two updates to clear

        world.step(1/60f, 6, 2); 
        
        player.update();
        // handleInput();
        // movePlayer();
        // wrapPlayer();
        drawPlayers();
    }

    // private void handleInput() {
    //     if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.W)) playerVel.y += SPEED;
    //     if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.S)) playerVel.y -= SPEED;
    //     if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A)) playerVel.x -= SPEED;
    //     if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D)) playerVel.x += SPEED;
    // }
    
    // private void movePlayer() { // actually apply vel to pos and add drag
    //     playerVel.scl(DRAG);
    //     playerPos = playerPos.add(playerVel);
    // }
    
    // //where is screen size uhh
    // private void wrapPlayer() {
    //     //particle accelarator
    //     if (playerPos.x > SCREEN_WIDTH) playerPos.x = 0;
    //     if (playerPos.x < 0) playerPos.x = SCREEN_WIDTH;

    //     // if (playerPos.y > SCREEN_HEIGHT) playerPos.y = 0;
    //     // if (playerPos.y < 0) playerPos.y = SCREEN_HEIGHT;
    //     if (playerPos.y < RADIUS) playerPos.y = RADIUS;
    // }
    
    private void drawPlayers() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);//dam zuckerburg
        shapeRenderer.setColor(new Color(0, 0, 255, 0));

        player.draw(shapeRenderer);

        shapeRenderer.end();
    }

    @Override // time to understand ts javaslop
    public void dispose() {
        shapeRenderer.dispose();
    }
} // check discoed
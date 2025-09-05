package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2; // ve
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.graphics.Color;

import java.util.List;
import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;

    // private Vector2 playerPos; // todo: get rid of this crap
    // private Vector2 playerVel;
    // private final float RADIUS = 10f;
    private final float SPEED = 0.2f; // change ts so it doesnt be goofy i guess
    // private final float DRAG = 0.95f;
    private List<Body> Bodies = new ArrayList<>();

    public int SCREEN_WIDTH ; //holy its taking eons
    public int SCREEN_HEIGHT; // scre it im doing it mangually
    private Vector2 GRAVITY;
    public World world;
    private Player player;
    
    public Body floorBody;

    public void createScene() {
        BodyDef floorBodyDef = new BodyDef();
        floorBodyDef.type = BodyType.StaticBody;
        floorBodyDef.position.set(0,0);

        floorBody = world.createBody(floorBodyDef);

        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(50f, 0.5f);
        
        // Fixture
        floorBody.createFixture(floorShape, 0.0f);

        floorShape.dispose();

    }

    @Override
    public void create() {
        //constant intit
        GRAVITY = new Vector2(0, -30.8f);
        shapeRenderer = new ShapeRenderer();

        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();

        world = new World(GRAVITY, true); 
        player = new Player(world, new Vector2(5, 10));//actually we do

    }

    @Override
    public void render() { // main loop is ts function, no call backs :(
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // two updates to clear

        world.step(1/60f, 6, 2); 
        
        handleInput();
        // movePlayer();
        // wrapPlayer();
        drawPlayers();
    }

     private void handleInput() {
         if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D)) player.jump(1);
         
         if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A)) player.jump(-1);

         if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.W)) player.jump(0);
    }
    
    // private void movePlayer() { // actually apply vel to pos and add drag
    //     playerVel.scl(DRAG);
    //     playerPos = playerPos.add(playerVel);
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
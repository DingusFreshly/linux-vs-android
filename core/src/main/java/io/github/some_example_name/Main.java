package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
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
    private List<Body> Bodies = new ArrayList<>();//use;ess

    public int SCREEN_WIDTH ;
    public int SCREEN_HEIGHT;
    private Vector2 GRAVITY;
    public World world;
    private Player player;
    
    public Body floorBody;
    public final Vector2 FLOOR_SIZE = new Vector2(100.f, 10.f);

    @Override
    public void create() {
        //constant intit
        GRAVITY = new Vector2(0, -30.8f);
        shapeRenderer = new ShapeRenderer();

        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();

        world = new World(GRAVITY, true); 
        player = new Player(world, new Vector2(5, 10));
        
        createScene();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1/60f, 6, 2); 
        
        handleInput();
        drawPlayers();
        drawScene();
    }

     private void handleInput() {
         if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.D)) player.jump(1);
         
         if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.A)) player.jump(-1);

         if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.W)) player.jump(0);
    }

    public void createScene() {
        BodyDef floorBodyDef = new BodyDef();
        floorBodyDef.type = BodyType.StaticBody;
        floorBodyDef.position.set(0,0);

        floorBody = world.createBody(floorBodyDef);

        PolygonShape floorShape = new PolygonShape();
        floorShape.setAsBox(FLOOR_SIZE.x, FLOOR_SIZE.y);
        
        floorBody.createFixture(floorShape, 0.0f);

        floorShape.dispose();

    }
    public void drawScene() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BROWN);

        shapeRenderer.rect(
            0,
            0,
            FLOOR_SIZE.x,
            FLOOR_SIZE.y
        );

        shapeRenderer.end();
    }
    
    private void drawPlayers() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 255, 0));

        player.draw(shapeRenderer);

        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
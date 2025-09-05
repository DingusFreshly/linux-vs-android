package io.github.some_example_name;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2; // ve
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.Gdx;




public class Player {
    
    private World world;
    public Vector2 velocity;
    private Color color;
    public  PolygonShape box;
    public Body body;
    
    public static final float PIXELS_PER_METER = 50f;
    public static final float PLAYER_SIZE = 1;
    public static final float DRAW_SIZE = PLAYER_SIZE / PIXELS_PER_METER;
    public static final Vector2 jumpVelocity = new Vector2(4,10);

    public static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();

    public Player(World world, Vector2 startPos) {
        this.world = world; 
        this.velocity = new Vector2(0, 0);
        this.color = Color.PURPLE;

        createBody(startPos);
        // DOES THIS WORK
    }

    private void createBody(Vector2 startPos ) {
        //idk what im doing lol
        
        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyType.DynamicBody;
        playerBodyDef.position.set(startPos.x,startPos.y); 
        // how to set position of player

        body = world.createBody(playerBodyDef);

        //create collider set the collider
        box = new PolygonShape();
        box.setAsBox(DRAW_SIZE, DRAW_SIZE); // rahhhhh DID YOU PUSH TO GIT

        //ts is for physical properties
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.2f;
        
        body.createFixture(fixtureDef);
        box.dispose();
    }

    public void draw(ShapeRenderer renderer) {
        Vector2 pos = body.getPosition();
        
        renderer.setColor(color);
        renderer.rect(
            pos.x - PLAYER_SIZE / 2,
            pos.y - PLAYER_SIZE  / 2,
            PLAYER_SIZE * PIXELS_PER_METER, 
            PLAYER_SIZE * PIXELS_PER_METER
            
            ); //maybe turn this to an image later
    }

    public void jump(int direction) {

        if (this.body.getPosition().y < SCREEN_HEIGHT / 6 ){ // yo what does dividing by 6 have to do wit anything

            this.body.applyForceToCenter(new Vector2(jumpVelocity.x * direction,jumpVelocity.y ), false);
        }

    }

    public void update() {

    }//
    
}

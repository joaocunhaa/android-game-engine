package com.innoveworkshop.gametest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.innoveworkshop.gametest.engine.BounceRectangle;
import com.innoveworkshop.gametest.engine.Circle;
import com.innoveworkshop.gametest.engine.EndGameCircle;
import com.innoveworkshop.gametest.engine.GameObject;
import com.innoveworkshop.gametest.engine.GameSurface;
import com.innoveworkshop.gametest.engine.HoleCircle;
import com.innoveworkshop.gametest.engine.Rectangle;
import com.innoveworkshop.gametest.engine.Vector;

public class MainActivity extends AppCompatActivity {
    protected GameSurface gameSurface;
    protected Game game;

    boolean movingUp = false;
    boolean movingDown = false;
    boolean movingRight = false;
    boolean movingLeft = false;
    int playerSpeed = 20;
    boolean restart = false;
    boolean endGame = false;

    public enum CollisionSide {
        TOP, BOTTOM, LEFT, RIGHT, NONE
    }

    public enum CollisionSideBounce {
        TOP, BOTTOM, LEFT, RIGHT, NONE
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameSurface = (GameSurface) findViewById(R.id.gameSurface);
        game = new Game();
        gameSurface.setRootGameObject(game);

        gameSurface.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                Log.d("SWIPE UP", "swipe up");
                movingUp = true;
                movingDown = false;
                movingRight = false;
                movingLeft = false;
            }
            public void onSwipeRight() {
                Log.d("SWIPE RIGHT", "swipe right");
                movingUp = false;
                movingDown = false;
                movingRight = true;
                movingLeft = false;
            }
            public void onSwipeLeft() {
                Log.d("SWIPE LEFT", "swipe left");
                movingUp = false;
                movingDown = false;
                movingRight = false;
                movingLeft = true;
            }
            public void onSwipeBottom() {
                Log.d("SWIPE BOTTOM", "swipe bottom");
                movingUp = false;
                movingDown = true;
                movingRight = false;
                movingLeft = false;
            }
        });
    }

    class Game extends GameObject {
        public Circle circle;
        public EndGameCircle endGameCircle;
        public Rectangle[] rectangles = new Rectangle[31];
        public BounceRectangle[] bounceRectangles = new BounceRectangle[9];
        public HoleCircle[] holeCircles = new HoleCircle[5];

        @Override
        public void onStart(GameSurface surface) {
            super.onStart(surface);

            // Player Circle
            if (circle == null) {
                circle = new Circle(85, surface.getHeight() - 85, 50, Color.GREEN);
                surface.addGameObject(circle);
            }

            // End Game Circle
            if (endGameCircle == null) {
                endGameCircle = new EndGameCircle(2200, 150, 50, Color.MAGENTA);
                surface.addGameObject(endGameCircle);
            }

            // Corner Walls
            if (rectangles[0] == null) {
                rectangles[0] = new Rectangle(new Vector(0, surface.getHeight()),
                        surface.getWidth() * 2, 75, Color.rgb(102, 51, 0));
            }

            if (rectangles[1] == null) {
                rectangles[1] = new Rectangle(new Vector(0, 0),
                        surface.getWidth() * 2, 75, Color.rgb(102, 51, 0));
            }

            if (rectangles[2] == null) {
                rectangles[2] = new Rectangle(new Vector(0, 0),
                        75, surface.getHeight() * 2, Color.rgb(102, 51, 0));
            }

            if (rectangles[3] == null) {
                rectangles[3] = new Rectangle(new Vector(surface.getWidth(), 0),
                        75, surface.getHeight() * 2, Color.rgb(102, 51, 0));
            }

            // Walls
            if (rectangles[4] == null) {
                rectangles[4] = new Rectangle(new Vector(0, surface.getHeight() - 150),
                        425, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[5] == null) {
                rectangles[5] = new Rectangle(new Vector(500, surface.getHeight() - 100),
                        37, 150, Color.rgb(102, 51, 0));
            }

            if (rectangles[6] == null) {
                rectangles[6] = new Rectangle(new Vector(360, surface.getHeight() - 380),
                        37, 500, Color.rgb(102, 51, 0));
            }

            if (rectangles[7] == null) {
                rectangles[7] = new Rectangle(new Vector(250, surface.getHeight() - 280),
                        200, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[8] == null) {
                rectangles[8] = new Rectangle(new Vector(0, surface.getHeight() - 410),
                        425, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[9] == null) {
                rectangles[9] = new Rectangle(new Vector(250, surface.getHeight() - 540),
                        200, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[10] == null) {
                rectangles[10] = new Rectangle(new Vector(450, surface.getHeight() - 540),
                        200, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[11] == null) {
                rectangles[11] = new Rectangle(new Vector(169, surface.getHeight() - 650),
                        37, 250, Color.rgb(102, 51, 0));
            }

            if (rectangles[12] == null) {
                rectangles[12] = new Rectangle(new Vector(750, surface.getHeight() - 150),
                        190, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[13] == null) {
                rectangles[13] = new Rectangle(new Vector(360, 150),
                        37, 150, Color.rgb(102, 51, 0));
            }

            if (rectangles[14] == null) {
                rectangles[14] = new Rectangle(new Vector(1155, surface.getHeight() / 2 + 100),
                        1325, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[15] == null) {
                rectangles[15] = new Rectangle(new Vector(650, surface.getHeight() - 195),
                        37, 130, Color.rgb(102, 51, 0));
            }

            if (rectangles[16] == null) {
                rectangles[16] = new Rectangle(new Vector(1600, surface.getHeight() / 2),
                        37, 170, Color.rgb(102, 51, 0));
            }

            if (rectangles[17] == null) {
                rectangles[17] = new Rectangle(new Vector(900, surface.getHeight() / 2 - 100),
                        700, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[18] == null) {
                rectangles[18] = new Rectangle(new Vector(1000, surface.getHeight() / 2 - 190),
                        37, 180, Color.rgb(102, 51, 0));
            }

            if (rectangles[19] == null) {
                rectangles[19] = new Rectangle(new Vector(750, surface.getHeight() / 2 - 190),
                        37, 180, Color.rgb(102, 51, 0));
            }

            if (rectangles[20] == null) {
                rectangles[20] = new Rectangle(new Vector(820, surface.getHeight() - 270),
                        37, 240, Color.rgb(102, 51, 0));
            }

            if (rectangles[21] == null) {
                rectangles[21] = new Rectangle(new Vector(1300, surface.getHeight() - 175),
                        600, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[22] == null) {
                rectangles[22] = new Rectangle(new Vector(1200, surface.getHeight() - 100),
                        37, 150, Color.rgb(102, 51, 0));
            }

            if (rectangles[23] == null) {
                rectangles[23] = new Rectangle(new Vector(1500, 150),
                        37, 150, Color.rgb(102, 51, 0));
            }

            if (rectangles[24] == null) {
                rectangles[24] = new Rectangle(new Vector(1525, surface.getHeight() / 2 - 100),
                        187, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[25] == null) {
                rectangles[25] = new Rectangle(new Vector(1798, surface.getHeight() / 2 + 202),
                        37, 240, Color.rgb(102, 51, 0));
            }

            if (rectangles[26] == null) {
                rectangles[26] = new Rectangle(new Vector(2000, surface.getHeight() / 2),
                        37, 240, Color.rgb(102, 51, 0));
            }

            if (rectangles[27] == null) {
                rectangles[27] = new Rectangle(new Vector(2100, 350),
                        600, 37, Color.rgb(102, 51, 0));
            }

            if (rectangles[28] == null) {
                rectangles[28] = new Rectangle(new Vector(2175, 800),
                        37, 400, Color.rgb(102, 51, 0));
            }

            if (rectangles[29] == null) {
                rectangles[29] = new Rectangle(new Vector(1900, 260),
                        37, 150, Color.rgb(102, 51, 0));
            }

            if (rectangles[30] == null) {
                rectangles[30] = new Rectangle(new Vector(2100, 100),
                        37, 150, Color.rgb(102, 51, 0));
            }

            // Bounce Walls
            if (bounceRectangles[0] == null) {
                bounceRectangles[0] = new BounceRectangle(new Vector(0, 56), surface.getWidth() * 2, 37, Color.BLUE);
            }

            if (bounceRectangles[1] == null) {
                bounceRectangles[1] = new BounceRectangle(new Vector(360, 400), 370, 37, Color.BLUE);
            }

            if (bounceRectangles[2] == null) {
                bounceRectangles[2] = new BounceRectangle(new Vector(930, surface.getHeight() - 360), 200, 37, Color.BLUE);
            }

            if (bounceRectangles[3] == null) {
                bounceRectangles[3] = new BounceRectangle(new Vector(1490, 150), 37, 150, Color.BLUE);
            }

            if (bounceRectangles[4] == null) {
                bounceRectangles[4] = new BounceRectangle(new Vector(1700, surface.getHeight() - 30), 200, 37, Color.BLUE);
            }

            if (bounceRectangles[5] == null) {
                bounceRectangles[5] = new BounceRectangle(new Vector(1700, surface.getHeight() - 360), 200, 37, Color.BLUE);
            }

            if (bounceRectangles[6] == null) {
                bounceRectangles[6] = new BounceRectangle(new Vector(2195, 370), 360, 37, Color.BLUE);
            }

            if (bounceRectangles[7] == null) {
                bounceRectangles[7] = new BounceRectangle(new Vector(2010, 473), 37, 250, Color.BLUE);
            }

            if (bounceRectangles[8] == null) {
                bounceRectangles[8] = new BounceRectangle(new Vector(2365, 473), 37, 250, Color.BLUE);
            }

            // Hole Circles
            if (holeCircles[0] == null) {
                holeCircles[0] = new HoleCircle(1500, surface.getHeight() / 2, 50, Color.BLACK);
            }

            if (holeCircles[1] == null) {
                holeCircles[1] = new HoleCircle(1100, 300, 50, Color.BLACK);
            }

            if (holeCircles[2] == null) {
                holeCircles[2] = new HoleCircle(1100, surface.getHeight() - 95, 50, Color.BLACK);
            }

            if (holeCircles[3] == null) {
                holeCircles[3] = new HoleCircle(1600, 150, 50, Color.BLACK);
            }

            if (holeCircles[4] == null) {
                holeCircles[4] = new HoleCircle(2275, surface.getHeight() - 95, 50, Color.BLACK);
            }

            // For loop to draw each bounce rectangle
            for (int i = 0; i < bounceRectangles.length; i++) {
                surface.addGameObject(bounceRectangles[i]);
            }

            // For loop to draw each rectangle
            for (int i = 0; i < rectangles.length; i++) {
                surface.addGameObject(rectangles[i]);
            }

            // For loop to draw each hole circle
            for (int i = 0; i < holeCircles.length; i++) {
                surface.addGameObject(holeCircles[i]);
            }
        }

        @Override
        public void onFixedUpdate() {
            super.onFixedUpdate();

            // For loop to detect collisions for each rectangle
            for (int i = 0; i < rectangles.length; i++) {
                CollisionSide temp = GetCollisionSide(circle.position.x, circle.position.y, circle.radius,
                        rectangles[i].position.x, rectangles[i].position.y, rectangles[i].width, rectangles[i].height);

                // Switch function to disable player movement in the direction of the collision
                switch(temp) {
                    case TOP:
                        movingDown = false;
                        break;
                    case BOTTOM:
                        movingUp = false;
                        break;
                    case RIGHT:
                        movingLeft = false;
                        break;
                    case LEFT:
                        movingRight = false;
                        break;
                }
            }

            // For loop to detect collisions for each bounce rectangle
            for (int i = 0; i < bounceRectangles.length; i++) {
                CollisionSideBounce temp = GetCollisionSideBounce(circle.position.x, circle.position.y, circle.radius,
                        bounceRectangles[i].position.x, bounceRectangles[i].position.y, bounceRectangles[i].width, bounceRectangles[i].height);

                // Switch function to disable player movement in the direction of the collision
                switch(temp) {
                    case TOP:
                        movingUp = true;
                        movingDown = false;
                        movingRight = false;
                        movingLeft = false;
                        break;
                    case BOTTOM:
                        movingUp = false;
                        movingDown = true;
                        movingRight = false;
                        movingLeft = false;
                        break;
                    case RIGHT:
                        movingUp = false;
                        movingDown = false;
                        movingRight = true;
                        movingLeft = false;
                        break;
                    case LEFT:
                        movingUp = false;
                        movingDown = false;
                        movingRight = false;
                        movingLeft = true;
                        break;
                }
            }

            // For loop to detect collisions for each hole circle
            for (int i = 0; i < holeCircles.length; i++) {
                HoleCircleCollision(circle.position.x, circle.position.y, circle.radius, holeCircles[i].position.x, holeCircles[i].position.y);
            }

            // Detect collisions between the player and the end game circle
            EndGameCircleCollision(circle.position.x, circle.position.y, circle.radius, endGameCircle.position.x, endGameCircle.position.y);

            // If statements to enable collisions with outside walls to stop the player
            if (!circle.hitRoof()) {
            } else { movingUp = false; }

            if (!circle.isFloored()) {
            } else { movingDown = false; }

            if (!circle.hitRightWall()) {
            } else { movingRight = false; }

            if (!circle.hitLeftWall()) {
            } else { movingLeft = false; }

            // Setting the positions for each player direction
            if (movingUp) { circle.setPosition(circle.position.x, circle.position.y - playerSpeed); }

            if (movingDown) { circle.setPosition(circle.position.x, circle.position.y + playerSpeed); }

            if (movingRight) { circle.setPosition(circle.position.x + playerSpeed, circle.position.y); }

            if (movingLeft) { circle.setPosition(circle.position.x - playerSpeed, circle.position.y); }

            // Game Restart
            if (restart) {
                Restart();
            }

            // End Game
            if (endGame) {
                EndGame();
            }
        }

        // Rectangles Collisions Function
        private CollisionSide GetCollisionSide(float circleX, float circleY, float circleRadius, float rectX, float rectY, float rectWidth, float rectHeight)
        {
            // Calculate the closest point
            float closestX = Clamp(circleX, rectX - rectWidth / 2, rectX + rectWidth / 2);
            float closestY = Clamp(circleY, rectY - rectHeight / 2, rectY + rectHeight / 2);

            // Calculate the distance between the closest point and the ball position
            float distanceX = circleX - closestX;
            float distanceY = circleY - closestY;
            float distance = (float) Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

            // If the distance is smaller than the circle radius, the ball collided
            if (distance <= circleRadius) {
                float overlapX = circleRadius - Math.abs(distanceX);
                float overlapY = circleRadius - Math.abs(distanceY);

                // If the y overlap is smaller than x, the ball collided in the y axis
                if (overlapX > overlapY) {
                    return (distanceY < 0) ? CollisionSide.TOP : CollisionSide.BOTTOM;
                }
                // If is not, the ball collided in the x axis
                else {
                    return (distanceX < 0) ? CollisionSide.LEFT : CollisionSide.RIGHT;
                }
            }
            return CollisionSide.NONE;
        }

        // Bounce Rectangles Collisions Function
        private CollisionSideBounce GetCollisionSideBounce(float circleX, float circleY, float circleRadius, float rectX, float rectY, float rectWidth, float rectHeight)
        {
            // Calculate the closest point
            float closestX = Clamp(circleX, rectX - rectWidth / 2, rectX + rectWidth / 2);
            float closestY = Clamp(circleY, rectY - rectHeight / 2, rectY + rectHeight / 2);

            // Calculate the distance between the closest point and the ball position
            float distanceX = circleX - closestX;
            float distanceY = circleY - closestY;
            float distance = (float) Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

            // If the distance is smaller than the circle radius, the ball collided
            if (distance <= circleRadius) {
                float overlapX = circleRadius - Math.abs(distanceX);
                float overlapY = circleRadius - Math.abs(distanceY);

                // If the y overlap is smaller than x, the ball collided in the y axis
                if (overlapX > overlapY) {
                    return (distanceY < 0) ? CollisionSideBounce.TOP : CollisionSideBounce.BOTTOM;
                }
                // If is not, the ball collided in the x axis
                else {
                    return (distanceX < 0) ? CollisionSideBounce.LEFT : CollisionSideBounce.RIGHT;
                }
            }
            return CollisionSideBounce.NONE;
        }

        // Hole Circles Collisions Function
        private void HoleCircleCollision(float circleX, float circleY, float circleRadius, float holeCircleX, float holeCircleY)
        {
            // Calculate the distance between the closest point and the ball position
            float distanceX = circleX - holeCircleX;
            float distanceY = circleY - holeCircleY;
            float distance = (float) Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

            // If the distance is smaller than the circle radius, the ball collided
            if (distance <= circleRadius) {
                restart = true;
            }
        }

        // End Game Circle Collisions Function
        private void EndGameCircleCollision(float circleX, float circleY, float circleRadius, float holeCircleX, float holeCircleY)
        {
            // Calculate the distance between the closest point and the ball position
            float distanceX = circleX - holeCircleX;
            float distanceY = circleY - holeCircleY;
            float distance = (float) Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

            // If the distance is smaller than the circle radius, the ball collided
            if (distance <= circleRadius) {
                endGame = true;
            }
        }

        private float Clamp(float value, float min, float max) {
            return Math.max(min, Math.min(max, value));
        }

        public void Restart() {
            Log.d("RESTART", "restarting game");
            circle.position.x = -100;
            circle.position.y = -100;
            restart = false;
            circle.destroy();
            finish();
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }

        public void EndGame() {
            Log.d("END GAME", "end game circle reached");
            circle.position.x = -100;
            circle.position.y = -100;
            endGame = false;
            circle.destroy();
            finish();
            Intent intent = new Intent(MainActivity.this, EndGameActivity.class);
            startActivity(intent);
        }
    }
}
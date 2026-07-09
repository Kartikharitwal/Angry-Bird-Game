package io.github.AngryBird_2023275_2023371.Screens;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import io.github.AngryBird_2023275_2023371.Screens.BaseBird;
import io.github.AngryBird_2023275_2023371.Screens.BaseBlock;
import io.github.AngryBird_2023275_2023371.Screens.BasePig;
import io.github.AngryBird_2023275_2023371.Screens.RedBird;
import io.github.AngryBird_2023275_2023371.Screens.YellowBird;
import io.github.AngryBird_2023275_2023371.Screens.BombBird;
import com.badlogic.gdx.Gdx;

public class MyContactListener implements ContactListener {
    private static final String TAG = "ContactListener";
    private static final float DAMAGE_THRESHOLD = 1.0f;
    private static final float BASE_DAMAGE = 15.0f;
    private static final float BIRD_MULTIPLIER = 2.0f;
    private static final float FALL_DAMAGE = 5.0f;
    private static final float FALL_VELOCITY_THRESHOLD = 8.0f;
    private static final float GROUND_Y_THRESHOLD = 1.5f;
    private static final float BIRD_DAMAGE_MULTIPLIER = 2.0f;
    private static final float BIRD_DAMAGE_THRESHOLD = 0.5f;

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Object userDataA = fixtureA.getUserData();
        Object userDataB = fixtureB.getUserData();

        Gdx.app.log(TAG, "BEGIN CONTACT between: " + userDataA + " and " + userDataB);
        if (userDataA instanceof BaseBird && userDataB instanceof BasePig) {
            Gdx.app.log(TAG, "Bird-Pig collision detected (A-B)");
            handleBirdPigCollision((BaseBird)userDataA, (BasePig)userDataB);
        }
        else if (userDataA instanceof BasePig && userDataB instanceof BaseBird) {
            Gdx.app.log(TAG, "Bird-Pig collision detected (B-A)");
            handleBirdPigCollision((BaseBird)userDataB, (BasePig)userDataA);
        }
        if (userDataA instanceof BasePig && userDataB instanceof BaseBlock) {
            Gdx.app.log(TAG, "Block-Pig collision detected (A-B)");
            handleBlockPigCollision((BasePig)userDataA, (BaseBlock)userDataB, contact);
        }
        else if (userDataA instanceof BaseBlock && userDataB instanceof BasePig) {
            Gdx.app.log(TAG, "Block-Pig collision detected (B-A)");
            handleBlockPigCollision((BasePig)userDataB, (BaseBlock)userDataA, contact);
        }

        handleCollision(fixtureA, fixtureB);
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        Object userDataA = contact.getFixtureA().getUserData();
        Object userDataB = contact.getFixtureB().getUserData();
        if (userDataA instanceof BasePig) {
            checkFallDamage((BasePig)userDataA, impulse);
        }
        if (userDataB instanceof BasePig) {
            checkFallDamage((BasePig)userDataB, impulse);
        }
    }

    private void checkFallDamage(BasePig pig, ContactImpulse impulse) {
        Vector2 velocity = pig.getBody().getLinearVelocity();
        float verticalVelocity = Math.abs(velocity.y);
        float pigHeight = pig.getBody().getPosition().y;
        float maxImpulse = 0;
        for (float imp : impulse.getNormalImpulses()) {
            maxImpulse = Math.max(maxImpulse, imp);
        }
        float impactDamage = 0;
        float fallDamage = 0;
        if (maxImpulse > DAMAGE_THRESHOLD) {
            impactDamage = FALL_DAMAGE * (maxImpulse / DAMAGE_THRESHOLD);
        }
        if (verticalVelocity > FALL_VELOCITY_THRESHOLD && pigHeight < GROUND_Y_THRESHOLD) {
            fallDamage = verticalVelocity * 5.0f;
        }

        float totalDamage = Math.max(impactDamage, fallDamage);

        if (totalDamage > 0) {
            Gdx.app.log("Pig fall damage detected!",
                "Impact force: " + maxImpulse +
                " Fall velocity: " + verticalVelocity +
                " Height: " + pigHeight);
            Gdx.app.log("Damage", "Impact: " + impactDamage + " Fall: " + fallDamage);
            Gdx.app.log("Pig Health", "Before: " + pig.getHealth());

            pig.reduceHealth(totalDamage);

            Gdx.app.log("Pig Health", "After: " + pig.getHealth());
        }
    }

    private void handleCollision(Fixture fixtureA, Fixture fixtureB) {
        if (isBlock(fixtureA) && isBird(fixtureB)) {
            handleBlockBirdCollision((BaseBlock)fixtureA.getUserData(), (BaseBird)fixtureB.getUserData());
        } else if (isBlock(fixtureB) && isBird(fixtureA)) {
            handleBlockBirdCollision((BaseBlock)fixtureB.getUserData(), (BaseBird)fixtureA.getUserData());
        }
        if (isBlock(fixtureA) && isBlock(fixtureB)) {
            handleBlockBlockCollision((BaseBlock)fixtureA.getUserData(), (BaseBlock)fixtureB.getUserData());
        }
        if (isPig(fixtureA) && isBlock(fixtureB)) {
            checkPigSupport((BasePig)fixtureA.getUserData(), (BaseBlock)fixtureB.getUserData());
        } else if (isPig(fixtureB) && isBlock(fixtureA)) {
            checkPigSupport((BasePig)fixtureB.getUserData(), (BaseBlock)fixtureA.getUserData());
        }
    }

    private void handleBlockBirdCollision(BaseBlock block, BaseBird bird) {
        Vector2 birdVelocity = bird.getBody().getLinearVelocity();
        float impactForce = birdVelocity.len();

        if (impactForce > DAMAGE_THRESHOLD) {
            float damage = impactForce * BASE_DAMAGE;
            block.reduceHealth(damage);
            checkBlockDestruction(block);
        }
    }

    private void handleBlockBlockCollision(BaseBlock block1, BaseBlock block2) {
        if (block1.isDestroyed() || block2.isDestroyed()) {
            float transferDamage = 5.0f;
            block1.reduceHealth(transferDamage);
            block2.reduceHealth(transferDamage);

            checkBlockDestruction(block1);
            checkBlockDestruction(block2);
        }
    }

    private void checkPigSupport(BasePig pig, BaseBlock block) {
        if (block.isDestroyed()) {
            Vector2 pigPos = pig.getBody().getPosition();
            Vector2 blockPos = block.getBody().getPosition();
            if (blockPos.y < pigPos.y && Math.abs(blockPos.x - pigPos.x) < 1.0f) {
                pig.reduceHealth(5.0f);
            }
        }
    }

    private void checkBlockDestruction(BaseBlock block) {
        if (block.isDestroyed()) {
            block.getWorld().destroyBody(block.getBody());
            block.remove();

        }
    }
    private void applyDamage(Fixture fixtureA, Fixture fixtureB, float damage) {
        if (isBlock(fixtureA)) {
            ((BaseBlock)fixtureA.getUserData()).reduceHealth(damage);
        }
        if (isBlock(fixtureB)) {
            ((BaseBlock)fixtureB.getUserData()).reduceHealth(damage);
        }
    }

    private boolean isBlock(Fixture fixture) {
        return fixture.getUserData() instanceof BaseBlock;
    }

    private boolean isBird(Fixture fixture) {
        return fixture.getUserData() instanceof BaseBird;
    }

    private boolean isPig(Fixture fixture) {
        return fixture.getUserData() instanceof BasePig;
    }

    private void handleBlockPigCollision(BasePig pig, BaseBlock block, Contact contact) {
        Vector2 velocity = block.getBody().getLinearVelocity();
        float impactForce = velocity.len();
        float baseDamage = 5.0f;
        float damage = baseDamage + (impactForce * 2.0f);

        Gdx.app.log("Block-Pig Collision", "Impact force: " + impactForce);
        Gdx.app.log("Block-Pig Collision", "Applying damage: " + damage);
        pig.reduceHealth(damage);
    }

    private void handleBirdPigCollision(BaseBird bird, BasePig pig) {
        Vector2 birdVelocity = bird.getBody().getLinearVelocity();
        float impactSpeed = birdVelocity.len();

        Gdx.app.log(TAG, "Processing Bird-Pig collision:");
        Gdx.app.log(TAG, "Bird type: " + bird.getClass().getSimpleName());
        Gdx.app.log(TAG, "Bird velocity: " + impactSpeed);
        Gdx.app.log(TAG, "Pig health before: " + pig.getHealth());

        if (impactSpeed > BIRD_DAMAGE_THRESHOLD) {
            float damage = calculateDamage(impactSpeed, bird);

            Gdx.app.log(TAG, "Calculated damage: " + damage);
            pig.reduceHealth(damage);
            Gdx.app.log(TAG, "Pig health after: " + pig.getHealth());
        }
    }

    private float calculateDamage(float speed, BaseBird bird) {
        float damage = Math.max(10f, speed * 5f);
        if (bird instanceof BombBird) {
            damage *= 5.0f;
            Gdx.app.log(TAG, "BombBird multiplier (5.0x) applied to damage: " + damage);
        } else if (bird instanceof YellowBird) {
            damage *= 2.0f;
            Gdx.app.log(TAG, "YellowBird multiplier applied");
        } else if (bird instanceof RedBird) {
            damage *= 1.5f;
            Gdx.app.log(TAG, "RedBird multiplier applied");
        }

        return damage;
    }
}

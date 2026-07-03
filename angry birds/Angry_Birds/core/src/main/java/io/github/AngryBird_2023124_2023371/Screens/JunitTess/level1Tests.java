package io.github.AngryBird_2023124_2023371.Screens.JunitTess;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.physics.box2d.World;
import io.github.AngryBird_2023124_2023371.AngryBirds;
import io.github.AngryBird_2023124_2023371.Screens.Level1;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class level1Tests {

    private static HeadlessApplication application;
    private static World world;
    private static AngryBirds game;
    private static Level1 level1;

    @BeforeClass
    public static void init() {
        // Initialize a headless application for testing
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(new ApplicationAdapter() {}, config);


        Gdx.gl20 = Gdx.gl;

        // Initialize game and Level1
        game = new AngryBirds();
        level1 = new Level1(game);
    }

    @Test
    public void testLevelInitialization() {
        assertNotNull("Level1 should be initialized", level1);
        assertNotNull("World should be initialized", level1.getWorld());
        assertNotNull("Stage should be initialized", level1.getStage());
    }

    @Test
    public void testBirdsInitialization() {
        assertNotNull("Birds list should not be null", level1.getBirds());
        assertEquals("Should have 3 birds initialized", 3, level1.getBirds().size());
    }

    @Test
    public void testPigsInitialization() {
        assertNotNull("Pigs list should not be null", level1.getPigs());
        assertEquals("Should have 3 pigs initialized", 3, level1.getPigs().size());
    }

    @Test
    public void testBlocksInitialization() {
        assertNotNull("Blocks list should not be null", level1.getBlocks());
        assertTrue("Should have at least one block initialized", level1.getBlocks().size() > 0);
    }

    @Test
    public void testLaunchCurrentBird() {
        int initialBirdIndex = level1.getCurrentBirdIndex();
        level1.launchCurrentBird();
        assertTrue("Bird should be in flight after launch", level1.isBirdInFlight);
        assertEquals("Current bird index should increment", initialBirdIndex + 1, level1.getCurrentBirdIndex());
    }

    @Test
    public void testSaveAndLoadGame() {
        level1.saveGameToJson("test_save");
        Level1 loadedLevel = Level1.loadGameFromJson(game, "test_save");
        assertNotNull("Loaded level should not be null", loadedLevel);
        assertEquals("Loaded level should have same number of birds", level1.getBirds().size(), loadedLevel.getBirds().size());
        assertEquals("Loaded level should have same number of pigs", level1.getPigs().size(), loadedLevel.getPigs().size());
    }

    @AfterClass
    public static void cleanup() {
        if (application != null) {
            application.exit();
            application = null;
        }
    }
}

package io.github.AngryBird_2023124_2023371.Screens.JunitTess;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.*;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import io.github.AngryBird_2023124_2023371.Screens.*;

public class GameObjectTests {
    private static HeadlessApplication application;
    private World world;
    private TextureAtlas mockAtlas;
    private BaseBird bird;
    private BasePig pig;

    @BeforeClass
    public static void initializeGdx() {
        application = new HeadlessApplication(new ApplicationAdapter() {
            @Override
            public void create() {
                // Initialize Gdx context
            }
        });
    }

    @Before
    public void setUp() {
        try {
            world = new World(new Vector2(0, -9.8f), true);

            // Create mock TextureAtlas
            mockAtlas = mock(TextureAtlas.class);
            TextureAtlas.AtlasRegion mockRegion = mock(TextureAtlas.AtlasRegion.class);
            when(mockAtlas.findRegion(anyString())).thenReturn(mockRegion);

        } catch (Exception e) {
            fail("Test setup failed: " + e.getMessage());
        }
    }

    @Test
    public void testBirdCreation() {
        assertNotNull("World should be initialized", world);
        try {
            bird = new RedBird(mockAtlas, new Vector2(100, 100), world);
            assertNotNull("Bird should be created", bird);
        } catch (Exception e) {
            fail("Bird creation failed: " + e.getMessage());
        }
    }

    @Test
    public void testPigCreation() {
        assertNotNull("World should be initialized", world);
        try {
            pig = new MechanicPig(mockAtlas, new Vector2(100, 100), world);
            assertNotNull("Pig should be created", pig);
        } catch (Exception e) {
            fail("Pig creation failed: " + e.getMessage());
        }
    }

    @Test
    public void testBirdPosition() {
        bird = new RedBird(mockAtlas, new Vector2(100, 100), world);
        Vector2 position = new Vector2(bird.getX(),bird.getY());
        assertEquals("Bird X position should be 100", 100f, position.x, 0.01f);
        assertEquals("Bird Y position should be 100", 100f, position.y, 0.01f);
    }

    @After
    public void tearDown() {
        if (world != null) {
            world.dispose();
        }
    }

    @AfterClass
    public static void cleanup() {
        if (application != null) {
            application.exit();
        }
    }
}

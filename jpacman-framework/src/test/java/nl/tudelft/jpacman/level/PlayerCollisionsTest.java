package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class PlayerCollisionsTest extends AbstractPlayerCollisionTest {

    @Override
    protected CollisionMap getCollisionMap() {
        return new PlayerCollisions();
    }
}

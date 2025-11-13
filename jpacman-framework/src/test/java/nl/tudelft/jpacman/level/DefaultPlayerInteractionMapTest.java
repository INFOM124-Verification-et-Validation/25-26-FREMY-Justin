package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class DefaultPlayerInteractionMapTest extends AbstractPlayerCollisionTest{
    @Override
    protected CollisionMap getCollisionMap() {
        return new DefaultPlayerInteractionMap();
    }
}

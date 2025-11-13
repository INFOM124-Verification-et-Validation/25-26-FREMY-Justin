package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

/**
 * Suite de tests abstraite pour toutes les implémentations de CollisionMap
 * gérant les collisions des joueurs.
 * Les classes concrètes doivent fournir l'implémentation de CollisionMap à tester.
 */
public abstract class AbstractPlayerCollisionTest {

    protected Ghost ghost;
    protected Pellet pellet;
    protected Player player;
    protected CollisionMap collisionMap; // L'objet à tester

    /**
     * Méthode abstraite pour obtenir l'instance spécifique de CollisionMap.
     * Doit être implémentée par les sous-classes.
     */
    protected abstract CollisionMap getCollisionMap();

    @BeforeEach
    public void setUp() {
        player = mock(Player.class);
        ghost = mock(Ghost.class);
        pellet = mock(Pellet.class);
        when(pellet.getValue()).thenReturn(10);
        collisionMap = getCollisionMap();
    }

    @Test
    public void testPlayerCollidingPellet() {
        collisionMap.collide(player, pellet);
        verify(player).addPoints(pellet.getValue());
        verify(pellet).leaveSquare();
    }

    @Test
    public void testPlayerCollidingGhost() {
        collisionMap.collide(player, ghost);
        verify(player).setAlive(false);
        verifyZeroInteractions(ghost);
    }

    @Test
    public void testGhostCollidingPellet() {
        collisionMap.collide(ghost, pellet);
        verifyZeroInteractions(pellet);
        verifyZeroInteractions(ghost);
    }

    @Test
    public void testGhostCollidingPlayer() {
        collisionMap.collide(ghost, player);
        verify(player).setAlive(false);
        verifyZeroInteractions(ghost);
    }
}

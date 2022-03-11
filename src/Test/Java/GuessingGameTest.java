package Java;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

class GuessingGameTest {

    GuessingGame game;
    Player player;
    Computer npc;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
        player = new Player();
        npc = new Computer();
        player.setName("Spencer");
        player.setGuess(10);
        npc.setNumber(15);
    }

    @Test
    void compare() {
        assertEquals("TOO_LOW", game.compare(player.getGuess(), npc.getNumber()),
                "Unexpected value returned.");
    }

    @Test
    void result() {
        assertEquals("Your guess is too low.", game.result(player.getGuess(), npc.getNumber()), "Unexpected value returned.");
    }

    @AfterEach
    void tearDown() {
    }
}
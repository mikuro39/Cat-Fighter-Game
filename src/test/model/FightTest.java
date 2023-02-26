package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//TODO: finish tests
public class FightTest {
    private Fight fight;
    private CatCollection cc;
    private Bot bot;

    @BeforeEach
    void runBefore() {
        cc = new CatCollection();
        bot = new Bot(1, 1);
        fight = new Fight(cc, bot);
    }

    //EFFECTS: proceeds one round of fight
    @Test
    void testProceedByOneRound() {

    }
}

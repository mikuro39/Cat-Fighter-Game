package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//test class for the Fight class
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

    @Test
    void testProceedByOneRound() {
        cc.addCatWithRarity("cat 1", 1);
        String result = fight.proceedByOneRound();
        assertEquals(cc.catListSize(), 0);
        assertEquals(bot.getHP(), 0);
        assertEquals(result, "Your cat cat 1 died! You won!");
        assertEquals(fight.showDeadCats().size(), 1);
        assertEquals(fight.showDeadCats().get(0), "cat 1");
        fight.repairCats();
        assertEquals(cc.catListSize(), 1);
        assertTrue(fight.checkGameOver());
        cc = new CatCollection();
        bot = new Bot(1, 1);
        fight = new Fight(cc, bot);
        cc.addCatWithRarity("cat 5", 5);
        result = fight.proceedByOneRound();
        assertEquals(cc.findCat(0).getHP(), 4);
        assertEquals(bot.getHP(), -4);
        assertEquals(result, "You won!");
        assertTrue(fight.checkGameOver());
        cc = new CatCollection();
        bot = new Bot(10, 10);
        fight = new Fight(cc, bot);
        cc.addCatWithRarity("cat 1", 1);
        result = fight.proceedByOneRound();
        assertEquals(cc.catListSize(), 0);
        assertEquals(bot.getHP(), 9);
        assertEquals(result, "You lost!");
        assertEquals(fight.showDeadCats().size(), 1);
        assertEquals(fight.showDeadCats().get(0), "cat 1");
        assertTrue(fight.checkGameOver());
        cc = new CatCollection();
        bot = new Bot(10, 10);
        fight = new Fight(cc, bot);
        cc.addCatWithRarity("cat 1", 1);
        cc.addCatWithRarity("cat 5", 5);
        result = fight.proceedByOneRound();
        assertEquals(cc.catListSize(), 1);
        assertEquals(cc.findCat(0).getHP(), 5);
        assertEquals(bot.getHP(), 9);
        assertEquals(result, "Your cat cat 1 died!");
        assertEquals(fight.showDeadCats().size(), 1);
        assertEquals(fight.showDeadCats().get(0), "cat 1");
        fight.repairCats();
        assertEquals(cc.catListSize(), 2);
        assertFalse(fight.checkGameOver());
        cc = new CatCollection();
        bot = new Bot(1, 10);
        fight = new Fight(cc, bot);
        cc.addCatWithRarity("cat 1", 5);
        result = fight.proceedByOneRound();
        assertEquals(cc.findCat(0).getHP(), 4);
        assertEquals(bot.getHP(), 5);
        assertEquals(result, "The bot has been knocked down to 5! \n Your cat is at 4!");
        assertFalse(fight.checkGameOver());
    }

    @Test
    void upgradeCat() {
        cc.addCatWithRarity("cat 1", 1);
        UpgradeItem upgradeItem = new UpgradeItem(1, 1);
        fight.upgradeCat(upgradeItem);
        assertEquals(fight.getFirstCat().getHP(), 2);
        assertEquals(fight.getFirstCat().getPower(), 2);
        UpgradeItem upgradeItem2 = new UpgradeItem(10, 10);
        fight.upgradeCat(upgradeItem2);
        assertEquals(fight.getFirstCat().getHP(), 12);
        assertEquals(fight.getFirstCat().getPower(), 12);
    }

    @Test
    void getFirstCat() {
        Cat cat = new Cat("test", 1);
        cc.addCatDebug(cat);
        assertEquals(fight.getFirstCat(), cat);
        Cat cat2 = new Cat("test2", 2);
        cc.addCatDebug(cat2);
        assertEquals(fight.getFirstCat(), cat);
        cc.removeCat(0);
        assertEquals(fight.getFirstCat(), cat2);
    }

}

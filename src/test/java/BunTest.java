import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    String name;
    float price;

    public BunTest(String name, float price){
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object [][] buns() {
        return new Object[][]{
                {"Rye bun", 10},
                {"potato bun", 15.5F},
                {"Gluten-free bun", 25},
                {" ", -10},
                {"", 0}
        };
    }

    @Test
    public void getBunNameTest() {
        Bun bun = new Bun(name, price);
        String actualNameResult = bun.getName();
        assertEquals(name, actualNameResult);
    }

    @Test
    public void getBunPriceTest() {
        Bun bun = new Bun(name, price);
        float actualPriceResult = bun.getPrice();
        assertEquals(price, actualPriceResult, 0.0);
    }
}

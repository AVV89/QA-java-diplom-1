
import org.junit.Test;
import praktikum.Bun;
import praktikum.Database;
import praktikum.Ingredient;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class DatabaseTest {
    Database database = new Database();

    @Test
    public void availableBunsMockTest(){
        List<Bun> buns = database.availableBuns();
        assertNotNull(buns);
    }

    @Test
    public void availableIngredientsMockTest(){
        List<Ingredient> ingredients = database.availableIngredients();
        assertNotNull(ingredients);
    }

}

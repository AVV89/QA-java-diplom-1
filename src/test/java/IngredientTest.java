import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class IngredientTest {

    String name;
    float price;
    String mockTypeName;

    @Mock
    IngredientType mockType;

    public IngredientTest(String name, float price, String mockTypeName){
        this.name = name;
        this.price = price;
        this.mockTypeName= mockTypeName;
    }

    @Parameterized.Parameters
    public static Object [][] ingredients() {
        return new Object[][]{
                {"hot sauce", 100, "SAUCE"},
                {"sour cream", 0, "SAUCE"},
                {"chili sauce", -1, "SAUCE"},
                {" ", 200 , "SAUCE"},
                {"", 0 , "SAUCE"},
                {"cutlet", 100, "FILLING"},
                {"sausage",0,"FILLING"},
                {"dinosaur", -1, "FILLING"},
        };
    }

    @Before
    public void setUpMock(){
        MockitoAnnotations.initMocks(this);
        when(mockType.toString()).thenReturn(mockTypeName);
    }


    @Test
    public void getIngredientTypeTest() {
        Ingredient ingredient = new Ingredient(mockType, name, price);
        String actualNameType = ingredient.getType().toString();
        assertEquals(mockTypeName, actualNameType);
    }

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(mockType, name, price);
        float actualPrice = ingredient.getPrice();
        assertEquals(price, actualPrice, 0.0);
    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(mockType, name, price);
        String actualName = ingredient.getName();
        assertEquals(name, actualName);
    }
}
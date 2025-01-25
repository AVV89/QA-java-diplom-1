import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {
    Bun mockBun;
    Ingredient mockIngredient1, mockIngredient2, mockIngredient3;

    String bunName;
    float bunPrice;

    String IngredientName1;
    float IngredientPrice1;
    String IngredientTypeName1;

    String IngredientName2;
    float IngredientPrice2;
    String IngredientTypeName2;

    String IngredientName3;
    float IngredientPrice3;
    String IngredientTypeName3;

    float totalPrice;

    @Mock
    IngredientType mockIngredientType1, mockIngredientType2, mockIngredientType3;

    public BurgerTest(String bunName, float bunPrice, String ingredientName1, float ingredientPrice1, String ingredientTypeName1, String ingredientName2, float ingredientPrice2, String ingredientTypeName2, String ingredientName3, float ingredientPrice3, String ingredientTypeName3, float totalPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.IngredientName1 = ingredientName1;
        this.IngredientPrice1 = ingredientPrice1;
        this.IngredientTypeName1 = ingredientTypeName1;
        this.IngredientName2 = ingredientName2;
        this.IngredientPrice2 = ingredientPrice2;
        this.IngredientTypeName2 = ingredientTypeName2;
        this.IngredientName3 = ingredientName3;
        this.IngredientPrice3 = ingredientPrice3;
        this.IngredientTypeName3 = ingredientTypeName3;
        this.totalPrice = totalPrice;
    }

    @Parameterized.Parameters
    public static Object [][] ingredients() {
        return new Object[][]{
                {"Rye bun", 100, "dinosaur", 200, "FILLING", "sausage", 300, "FILLING", "chili sauce", 300, "SAUCE", 1000},
                {"Potato bun", 100, "pterodactyls", 1200, "FILLING", "sausage", 0, "FILLING", "tomato sauce", -100, "SAUCE", 1300},
        };
    }


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockBun = mock(Bun.class);
        when(mockBun.getName()).thenReturn(bunName); //перебрать все булки через параметризацию
        when(mockBun.getPrice()).thenReturn(bunPrice);

        mockIngredient1 = mock(Ingredient.class);
        when(mockIngredient1.getName()).thenReturn(IngredientName1);
        when(mockIngredient1.getPrice()).thenReturn(IngredientPrice1);
        when(mockIngredient1.getType()).thenReturn(mockIngredientType1);
        when(mockIngredientType1.toString()).thenReturn(IngredientTypeName1);

        mockIngredient2 = mock(Ingredient.class);
        when(mockIngredient2.getName()).thenReturn(IngredientName2);
        when(mockIngredient2.getPrice()).thenReturn(IngredientPrice2);
        when(mockIngredient2.getType()).thenReturn(mockIngredientType2);
        when(mockIngredientType2.toString()).thenReturn(IngredientTypeName2);

        mockIngredient3 = mock(Ingredient.class);
        when(mockIngredient3.getName()).thenReturn(IngredientName3);
        when(mockIngredient3.getPrice()).thenReturn(IngredientPrice3);
        when(mockIngredient3.getType()).thenReturn(mockIngredientType3);
        when(mockIngredientType3.toString()).thenReturn(IngredientTypeName3);

    }

    @Test
    public void setBunsMockTest(){
        String actualNameResult;
        float actualPriceResult;

        Burger burger = new Burger();
        burger.setBuns(mockBun);

        actualNameResult = mockBun.getName();
        actualPriceResult = mockBun.getPrice();

        assertEquals(bunName, actualNameResult);
        assertEquals(bunPrice, actualPriceResult, 0.0);
    }

    @Test
    public void addIngredientMockTest(){

        String actualNameResult;
        float actualPriceResult;
        String actualTypeResult;

        Burger burger = new Burger();
        burger.addIngredient(mockIngredient1);

        Ingredient lastIngredient = burger.ingredients.get(burger.ingredients.size()-1);

        actualNameResult = lastIngredient.getName();
        actualPriceResult = lastIngredient.getPrice();
        actualTypeResult = lastIngredient.getType().toString();

        assertEquals(IngredientName1, actualNameResult);
        assertEquals(IngredientPrice1, actualPriceResult, 0.0);
        assertEquals(IngredientTypeName1, actualTypeResult);
    }

    @Test
    public void removeIngredientMockTest(){

        String actualNameResult;
        float actualPriceResult;
        String actualTypeResult;

        Burger burger = new Burger();
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        int index = 0;

        Ingredient deleteIngredient = burger.ingredients.get(index);

        actualNameResult = deleteIngredient.getName();
        actualPriceResult = deleteIngredient.getPrice();
        actualTypeResult = deleteIngredient.getType().toString();

        assertEquals(IngredientName1, actualNameResult);
        assertEquals(IngredientPrice1, actualPriceResult, 0.0);
        assertEquals(IngredientTypeName1, actualTypeResult);

        burger.removeIngredient(index);
        assertFalse(burger.ingredients.contains(mockIngredient1));
    }

    @Test
    public void moveIngredientMockTest() {
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(1, 2);
        assertEquals(List.of(mockIngredient1,mockIngredient3,mockIngredient2), burger.ingredients);
    }

    @Test
    public void moveIngredientToLastMockTest() {
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 2);
        assertEquals(List.of(mockIngredient2,mockIngredient3,mockIngredient1), burger.ingredients);
      }

    @Test
    public void moveIngredientToFirstMockTest() {
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(1, 0);
        assertEquals(List.of(mockIngredient2,mockIngredient1,mockIngredient3), burger.ingredients);
    }


    @Test
    public void getPriceMockTest()
    {
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        assertEquals(totalPrice, burger.getPrice(),0.0);
    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                mockBun.getName(),
                mockIngredient1.getType().toString().toLowerCase(), mockIngredient1.getName(),
                mockIngredient2.getType().toString().toLowerCase(), mockIngredient2.getName(),
                mockIngredient3.getType().toString().toLowerCase(), mockIngredient3.getName(),
                mockBun.getName(),
                burger.getPrice()
        );

        assertEquals(expectedReceipt, burger.getReceipt());
    }

}

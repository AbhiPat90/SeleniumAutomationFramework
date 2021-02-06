import com.BaseTest;
import com.operations.AddToCartFlowOperations;
import org.testng.Assert;
import org.testng.annotations.*;

public class CartMessageTest extends BaseTest{

    AddToCartFlowOperations flowAddToCart = new AddToCartFlowOperations();

    @BeforeMethod
    public void setUp(){
        //Default browser has been set as Chrome
        browserSetup("");
        getTestUrl();
    }

    @Test
    public void verify_the_message_that_cart_is_empty() {
        logger = extent.createTest("Demo Test");
        driver.get(site_URL);
        int requiredQuantity = 4;
        Assert.assertTrue(flowAddToCart.clickOnMoreOption(driver),
                "User is not navigated to Product page URL");
        Assert.assertTrue(flowAddToCart.increaseQuantityOfTheProduct(driver,requiredQuantity),
                "Increase quantity of the product pricing isn't matching");
        Assert.assertTrue(flowAddToCart.proceedToCheckoutAndValidateQuantityAndPricing(driver,requiredQuantity),
                "Product Price as per quantities are not matched");
        Assert.assertTrue(flowAddToCart.evaluateEmptyCartMessage(driver),"Shopping cart is not empty");
        System.out.println("Test Verification is complete");
    }
}

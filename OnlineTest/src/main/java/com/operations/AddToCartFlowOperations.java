package com.operations;

import com.components.HomePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;


public class AddToCartFlowOperations{
    private static final Logger LOG = LoggerFactory.getLogger(AddToCartFlowOperations.class);

    final String PRODUCT_PAGE_URL = "http://automationpractice.com/index.php?id_product=1&controller=product";
    final String ORDER_PAGE_URL = "http://automationpractice.com/index.php?controller=order";

    float initialPrice;
    float increasedQuantityPrice;

    public boolean clickOnMoreOption(WebDriver driver) {
        HomePage homePage = new HomePage(driver);
        homePage.waitForElement(homePage.getHomeSlider(), "Waiting for home slider to load", 3);
        if (homePage.getHomeSlider().isDisplayed()) {
            homePage.getLinkT_Shirts().click();
            homePage.mouseHoverToElement(homePage.getTShirtProducts().get(0));
            homePage.getLinkMoreOption().click();
            try {
                homePage.getScreenShot("Product Page");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return PRODUCT_PAGE_URL.equalsIgnoreCase(driver.getCurrentUrl())?true:false;
    }

    public boolean increaseQuantityOfTheProduct(WebDriver driver, int requiredQuantity){
        HomePage homePage = new HomePage(driver);
        float productPrice = Float.parseFloat(homePage.getProductPrice());
        int defaultQuantity = Integer.parseInt(homePage.getProductQuantity());
        initialPrice = calculatePrice(productPrice,defaultQuantity);
        for (int i = 0; i < requiredQuantity-1; i++) {
            homePage.clickToIncreaseQuantity();
        }
        int increasedQuantity = Integer.parseInt(homePage.getProductQuantity());
        increasedQuantityPrice = calculatePrice(productPrice, increasedQuantity);
        LOG.info("Default Quantity: "+defaultQuantity+"--> Unit Price: "+initialPrice);
        LOG.info("Increased Quantity: "+increasedQuantity+"--> Increased Price: "+increasedQuantityPrice);
        return (defaultQuantity != increasedQuantity)?true:false;
    }

    float calculatePrice(float unitPrice, int quantity){
        return unitPrice*quantity;
    }

    public boolean proceedToCheckoutAndValidateQuantityAndPricing(WebDriver driver, int quantity){
        HomePage homePage = new HomePage(driver);
        homePage.clickAddToCartButton();
        homePage.waitForElement(homePage.getPopupProductAddedToCart(), "Waiting for Product Add To Cart Popup to appear", 5);
        homePage.mouseHoverToElement(homePage.getPopupProductAddedToCart());
        Assert.assertTrue(homePage.getSuccessfullyProductAddedText().contains("Product successfully added to your shopping cart"));
        Assert.assertTrue(homePage.getProductQuantityAddedToCartFromMessage()==quantity);
        homePage.clickPopupBtnProceedToCheckout();
        LOG.info("Total Pricing: "+homePage.getTotalPricingIncludingShipping());
        return (homePage.getTotalProductPrice() == increasedQuantityPrice)?true:false;
    }

    public boolean evaluateEmptyCartMessage(WebDriver driver){
        HomePage homePage = new HomePage(driver);
        homePage.clickProceedToCheckoutButton();
        homePage.waitForElement(homePage.getBtnSignIn(), "Waiting for Sign In button to appear", 3);
        homePage.mouseHoverToElement(homePage.getViewMyShoppingCart());
        homePage.clickCloseIconToMakeCartEmpty();
        homePage.clickViewMyShoppingCart();
        return "Your shopping cart is empty.".equalsIgnoreCase(homePage.getEmptyCartMessage())?true:false;
    }
}


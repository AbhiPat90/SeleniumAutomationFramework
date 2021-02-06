package com.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "div#homepage-slider")
    private WebElement homeSlider;

    @FindBy(css = "div#block_top_menu>ul>li>a[title='T-shirts']")
    private WebElement linkTshirts;

    @FindBy(css = "div#center_column ul[class^='product_list'] li") //[class^='ajax_block_product']
    private List<WebElement> firstTShirtProduct;

    @FindBy(css = "div.button-container a[title='View']")
    private WebElement linkMoreOption;

    @FindBy(css = "span#our_price_display")
    private WebElement productPrice;

    @FindBy(css="input#quantity_wanted")
    private WebElement productQuantity;

    @FindBy(css="p#quantity_wanted_p a i[class='icon-minus']")
    private WebElement btnMinusQuantity;

    @FindBy(css="p#quantity_wanted_p a i[class='icon-plus']")
    private WebElement btnPlusQuantity;

    @FindBy(css="p#add_to_cart button[name='Submit']")
    private WebElement btnAddToCart;

    @FindBy(css="div#layer_cart div[class='clearfix']")
    private WebElement popupProductAddedToCart;  // use to move to popup

    @FindBy(css="div#layer_cart div[class^='layer_cart_product'] h2")
    private WebElement txtProductAddedSuccessfully;

    @FindBy(css = "div#layer_cart span.ajax_cart_quantity")
    private WebElement txtQuantityAppearedAtPopup;

    @FindBy(css = "div#layer_cart a[title='Proceed to checkout']")
    private WebElement popupBtnProceedToCheckout;

    @FindBy(css = "table#cart_summary td#total_product")
    private WebElement totalProductPrice;

    @FindBy(css = "table#cart_summary td#total_price_container")
    private WebElement totalPricing;

    @FindBy(css = "p.cart_navigation.clearfix a[title='Proceed to checkout']")
    private WebElement btnProceedToCheckout;

    @FindBy(css="button#SubmitLogin")
    private WebElement btnSignIn; // use to wait till the page loads

    @FindBy(css="a[title='View my shopping cart']")
    private WebElement viewMyShoppingCart;  // mouse hover

    @FindBy (css = "span.remove_link a.ajax_cart_block_remove_link")
    private WebElement closeIconToRemoveFromCart;

    @FindBy(css="p.alert.alert-warning")
    private WebElement cartEmptyMessage;

    public WebElement getHomeSlider(){
        return homeSlider;
    }

    public WebElement getLinkT_Shirts(){
        return linkTshirts;
    }

    public List<WebElement> getTShirtProducts(){
        return firstTShirtProduct;
    }

    public WebElement getLinkMoreOption(){
        return linkMoreOption;
    }

    public String getProductPrice(){
        return productPrice.getText().replace("$","");
    }

    public String getProductQuantity(){
        return productQuantity.getAttribute("value");
    }

    public void clickToDecreaseQuantity(){
        btnMinusQuantity.click();
    }

    public void clickToIncreaseQuantity(){
        btnPlusQuantity.click();
    }

    public void clickAddToCartButton(){
        btnAddToCart.click();
    }

    public WebElement getPopupProductAddedToCart(){
        return popupProductAddedToCart;
    }

    public String getSuccessfullyProductAddedText(){
        return txtProductAddedSuccessfully.getText();
    }

    public int getProductQuantityAddedToCartFromMessage(){
        return Integer.parseInt(txtQuantityAppearedAtPopup.getText());
    }

    public void clickPopupBtnProceedToCheckout(){
        popupBtnProceedToCheckout.click();
    }

    public float getTotalProductPrice(){
        return Float.parseFloat(totalProductPrice.getText().replace("$",""));
    }

    public float getTotalPricingIncludingShipping(){
        return Float.parseFloat(totalPricing.getText().replace("$",""));
    }

    public void clickProceedToCheckoutButton(){
        btnProceedToCheckout.click();
    }

    public WebElement getBtnSignIn(){
        return btnSignIn;
    }

    public WebElement getViewMyShoppingCart(){
        return viewMyShoppingCart;
    }

    public void clickViewMyShoppingCart(){
        viewMyShoppingCart.click();
    }

    public void clickCloseIconToMakeCartEmpty(){
        closeIconToRemoveFromCart.click();
    }

    public String getEmptyCartMessage(){
        return cartEmptyMessage.getText();
    }

}

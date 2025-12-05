package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // Wait for Logout to Complete
            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any
     * errors
     */
    public Boolean searchForProduct(String product) {
        try {
            // Clear the contents of the search box and Enter the product name in the search box
            WebElement searchBox = driver.findElement(By.xpath("MuiInputBase-input"));
            searchBox.clear();
            searchBox.sendKeys(product);


            Thread.sleep(3000);
            // Wait for search results to appear
        //     WebDriverWait wait = new WebDriverWait(driver, 10);
            
        //   wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='root']/div/div/div[3]/div/div[2]/div/div/div[1]")));

            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<>();
        try {
            // Find all web elements corresponding to the card content section of each of
            // search results
            searchResults = driver.findElements(By.xpath("//div[contains(@class, 'MuiCard-root')]"));

            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;
        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // Check the presence of "No products found" text in the web page
            WebElement noResultsMessage = driver.findElement(By.className("css-ubsgp5"));
            status = noResultsMessage.isDisplayed();

            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            List<WebElement> products = driver.findElements(By.xpath("//div[contains( @class, 'MuiPaper-root MuiPaper-elevation')]//p"));
            for (WebElement product : products) {
                System.out.println(product.getText());
                if (product.getText().equalsIgnoreCase(productName)) {
                    WebElement addToCartButton = product.findElement(By.xpath("//div[contains( @class, 'spacing card-actions')]"));
                    addToCartButton.click();
                    return true;
                }
            }
            System.out.println("Product not found: " + productName);
            return false;
        } catch (Exception e) {
            System.out.println("Exception in addProductToCart: " + e.getMessage());
            return false;
        }
    }
    

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        try {
            WebElement checkoutButton = driver.findElement(By.xpath("//button[text()='Checkout']"));
            checkoutButton.click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.urlContains("checkout"));
            return true;
        } catch (Exception e) {
            System.out.println("Exception in clickCheckout: " + e.getMessage());
            return false;
        }
    }
    

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {
            WebElement cartParent = driver.findElement(By.className("cart"));
            List<WebElement> cartContents = cartParent.findElements(By.className("css-zgtx0t"));

            int currentQty;
            for (WebElement item : cartContents) {
                if (productName.contains(
                        item.findElement(By.xpath("//*[@class='MuiBox-root css-1gjj37g']/div[1]")).getText())) {
                    currentQty = Integer.valueOf(item.findElement(By.className("css-olyig7")).getText());

                    while (currentQty != quantity) {
                        if (currentQty < quantity) {
                            item.findElements(By.tagName("button")).get(1).click();

                        } else {
                            item.findElements(By.tagName("button")).get(0).click();
                        }

                        synchronized (driver) {
                            driver.wait(2000);
                        }

                        currentQty = Integer
                                .valueOf(item.findElement(By.xpath("//div[@data-testid=\"item-qty\"]")).getText());
                    }

                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println(("exception occurred when updating cart"));
            return false;
        }
    }
    
    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            WebElement cartParent = driver.findElement(By.className("cart"));
            List<WebElement> cartContents = cartParent.findElements(By.className("css-zgtx0t"));

            ArrayList<String> actualCartContents = new ArrayList<String>() {};
            for (WebElement cartItem : cartContents) {
                actualCartContents.add(
                        cartItem.findElement(By.className("css-1gjj37g")).getText().split("\n")[0]);
            }

            for (String expected : expectedCartContents) {
                if (!actualCartContents.contains(expected)) {
                    return false;
                }
            }

            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}

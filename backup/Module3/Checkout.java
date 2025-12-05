package QKART_SANITY_LOGIN.Module1;

import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addressString) {
        try {
            WebElement addNewAddressButton = driver.findElement(By.xpath("//button[@id='add-new-btn']"));
            addNewAddressButton.click();
    
            WebElement addressTextBox = driver.findElement(By.xpath("//textarea[@placeholder='Enter your complete address']"));
            addressTextBox.sendKeys(addressString);
    
                WebElement addButton = driver.findElement(By.xpath("//button[normalize-space(text())='Add']"));
            addButton.click();
    
            Thread.sleep(2000); // Wait for address to be added
            return true;
        } catch (Exception e) {
            System.out.println("Exception in addNewAddress: " + e.getMessage());
            return false;
        }
    }
    

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) throws InterruptedException {
        try {
            List<WebElement> addresses = driver.findElements(By.xpath("//div[contains(@class, 'address-item')]"));
            for (WebElement address : addresses) {
                Thread.sleep(3000);
                String addressText = address.findElement(By.xpath(".//p")).getText();
                if (addressText.equalsIgnoreCase(addressToSelect)) {
                    address.findElement(By.xpath(".//input[@name='address' and @type='radio']")).click();
                    return true;
                }
            }
            System.out.println("Address not found: " + addressToSelect);
            return false;
        } catch (Exception e) {
            System.out.println("Exception in selectAddress: " + e.getMessage());
            return false;
        }
    }
    

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            WebElement placeOrderButton = driver.findElement(By.xpath("//button[text()='PLACE ORDER']"));
            placeOrderButton.click();
    
            // WebDriverWait wait = new WebDriverWait(driver, 10);
            // wait.until(ExpectedConditions.urlContains("order-success"));
            return true;
        } catch (Exception e) {
            System.out.println("Exception in placeOrder: " + e.getMessage());
            return false;
        }
    }
    

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6

            WebElement ele = driver.findElement(By.id("notistack-snackbar"));

            if(ele.getText().contains("You do not have enough balance in your wallet for this purchase")){
                return true;
            }

            
            return false;
        } catch (Exception e) {
            System.out.println("Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }

    public Boolean verifyCartContents(String string, int i) {
        return null;
    }
}

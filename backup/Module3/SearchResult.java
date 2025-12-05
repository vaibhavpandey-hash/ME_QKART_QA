package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {
            WebElement sizeChartLink = parentElement.findElement(By.xpath(".//button[text()='Size chart']"));
            sizeChartLink.click();
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            WebElement sizeChartLink = parentElement.findElement(By.xpath(".//button[text()='Size chart']"));
            if (sizeChartLink.isDisplayed()){

            if(sizeChartLink.getText().equalsIgnoreCase("SIZE CHART"))

            
               status = true;
            }
            return status;
        } catch (Exception e) {
            System.out.println("Size chart does not exist: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        Boolean status = true;
        try {
            // Locate the size chart table
            WebElement sizeChartTable = driver.findElement(By.xpath("//div[contains(@role,'dialog')]//table"));
            Thread.sleep(3000);
            // Validate headers
            List<WebElement> tableHeaders = sizeChartTable.findElements(By.xpath(".//thead/tr/th"));
            if (tableHeaders.size() != expectedTableHeaders.size()) {
                System.out.println("Mismatch in number of headers.");
                return false;
            }
            for (int i = 0; i < expectedTableHeaders.size(); i++) {
                String actualHeader = tableHeaders.get(i).getText().trim();
                String expectedHeader = expectedTableHeaders.get(i).trim();
                if (!actualHeader.contains(expectedHeader)) {
                    System.out.println("Header mismatch: Expected = " + expectedHeader + ", Actual = " + actualHeader);
                    status = false;
                }
            }

            // Validate body
            List<WebElement> tableRows = sizeChartTable.findElements(By.xpath(".//tbody/tr"));
            if (tableRows.size() != expectedTableBody.size()) {
                System.out.println("Mismatch in number of rows.");
                return false;
            }
            for (int i = 0; i < expectedTableBody.size(); i++) {
                List<WebElement> rowCells = tableRows.get(i).findElements(By.tagName("td"));
                if (rowCells.size() != expectedTableBody.get(i).size()) {
                    System.out.println("Mismatch in number of columns in row " + (i + 1));
                    return false;
                }
                for (int j = 0; j < expectedTableBody.get(i).size(); j++) {
                    String actualCell = rowCells.get(j).getText().trim();
                    String expectedCell = expectedTableBody.get(i).get(j).trim();
                    if (!actualCell.contains(expectedCell)) {
                        System.out.println("Cell mismatch at row " + (i + 1) + ", column " + (j + 1) +
                                ": Expected = " + expectedCell + ", Actual = " + actualCell);
                        status = false;
                    }
                }
            }

            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        try {
            WebElement sizeDropdown = parentElement.findElement(By.xpath(".//select"));
            return sizeDropdown.isDisplayed();
        } catch (Exception e) {
            System.out.println("Size dropdown does not exist: " + e.getMessage());
            return false;
        }
    }
}

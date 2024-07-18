import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModalPage {

    WebDriver driver;

    public ModalPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@id='example-modal-sizes-title-lg']")
    private WebElement titleDiv;

    @FindBy(xpath = "//table//tbody//td[text()='Student Name']/following-sibling::td")
    private WebElement nameValue;

    @FindBy(xpath = "//table//tbody//td[text()='Student Email']/following-sibling::td")
    private WebElement emailValue;

    @FindBy(xpath = "//table//tbody//td[text()='Gender']/following-sibling::td")
    private WebElement genderValue;

    @FindBy(xpath = "//table//tbody//td[text()='Mobile']/following-sibling::td")
    private WebElement mobileValue;

    @FindBy(xpath = "//table//tbody//td[text()='Date of Birth']/following-sibling::td")
    private WebElement dateBirthValue;

    @FindBy(xpath = "//table//tbody//td[text()='Subjects']/following-sibling::td")
    private WebElement subjectsValue;

    @FindBy(xpath = "//table//tbody//td[text()='Hobbies']/following-sibling::td")
    private WebElement hobbiesValue;

    @FindBy(xpath = "//table//tbody//td[text()='Picture']/following-sibling::td")
    private WebElement pictureValue;

    @FindBy(xpath = "//table//tbody//td[text()='Address']/following-sibling::td")
    private WebElement addressValue;

    @FindBy(xpath = "//table//tbody//td[text()='State and City']/following-sibling::td")
    private WebElement stateCityValue;

    @FindBy(xpath = "//button[@id='closeLargeModal']")
    private WebElement closeButton;


    public String getTitleDiv() {
        return titleDiv.getText();
    }

    public String getNameValueText() {
        return nameValue.getText();
    }

    public String getEmailValueText() {
        return emailValue.getText();
    }

    public String getGenderValueText() {
        return genderValue.getText();
    }

    public String getMobileValueText() {
        return mobileValue.getText();
    }

    public String getDateBirthValueText() {
        return dateBirthValue.getText();
    }

    public String getSubjectsValueText() {
        return subjectsValue.getText();
    }

    public String getHobbiesValueText() {
        return hobbiesValue.getText();
    }

    public String getPictureValueText() {
        return pictureValue.getText();
    }

    public String getAddressValueText() {
        return addressValue.getText();
    }

    public String getStateCityValueText() {
        return stateCityValue.getText();
    }

    public void clickCloseButton(){
        closeButton.click();
    }

}

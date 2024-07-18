import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PracticeFormPage {

    WebDriver driver;
    String birthdayMonthString;

    public PracticeFormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    //todo сделать разные локаторы
    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id ="lastName")
    private WebElement lastNameField;

    @FindBy(xpath="//form[@id='userForm']//input[@id='userEmail']")
    private WebElement emailField;

    @FindBy(css = "input#userNumber")
    private WebElement mobileField;

    @FindBy(xpath = "//form[@id='userForm']//input[@id='dateOfBirthInput']")
    private WebElement dateBirthField;

    @FindBy(css = "div#dateOfBirth select.react-datepicker__year-select option")
    private List<WebElement> yearsOption;

    @FindBy(xpath = "//form[@id='userForm']//div[@id='dateOfBirth']//select[@class='react-datepicker__month-select']//option")
    private List<WebElement> monthsOption;

    @FindBy(xpath ="//form[@id='userForm']//input[@id='subjectsInput']")
    private WebElement subjectsField;

    @FindBy(xpath = "//form[@id='userForm']//div[@id='subjectsWrapper']/descendant::div[contains(@id,'option')]")
    private List<WebElement> subjectsOption;

    @FindBy(xpath = "//form[@id='userForm']//div[@id='hobbiesWrapper']//descendant::input[@type='checkbox']/parent::div")
    private List<WebElement> hobbiesCheckBox;

    @FindBy(xpath = "//form[@id='userForm']//input[@type='file']")
    private WebElement pictureFile;

    @FindBy(xpath = "//form[@id='userForm']//textarea[@id='currentAddress']")
    private WebElement currentAddressField;

    @FindBy(xpath = "//form[@id='userForm']//div[@id='state']")
    private WebElement stateField;

    @FindBy(xpath = "//form[@id='userForm']//div[@id='state']//descendant::div[contains(@id,'option')]")
    private List<WebElement> stateOption;

    @FindBy(xpath = "//form[@id='userForm']//div[@id='city']")
    private WebElement cityField;

    @FindBy(xpath = "//form[@id='userForm']//div[@id='city']//descendant::div[contains(@id,'option')]")
    private List<WebElement> cityOption;

    @FindBy(xpath="//form[@id='userForm']//button[text()='Submit']")
    private WebElement submitButton;




    public void inputFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void inputEmail(String email) {
        emailField.sendKeys(email);
    }

    public void setGenderRadioButton(String gender) {
        WebElement genderElement = driver.findElement(By.xpath("//form[@id='userForm']//input[@name='gender' and @value='"+gender+"']//parent::div"));
        if (!genderElement.isSelected()) genderElement.click();
    }

    public void inputMobile(String number) {
        mobileField.sendKeys(number);
    }

    public void activateDateBirth(){
        dateBirthField.click();
    }

    public int getCountYearsOption() {
        return yearsOption.size();
    }
    public int getMinValueYearsOption() {
        return Integer.parseInt(yearsOption.get(0).getAttribute("value"));
    }
    public void setYearsOption(int i) {
        yearsOption.get(i-Integer.parseInt(yearsOption.get(0).getAttribute("value"))).click();
    }

    public void setMonthsOption(int i) {
        monthsOption.get(i-1).click();
        birthdayMonthString = monthsOption.get(i-1).getAttribute("text");
    }

    //todo подумать как переделать на опциональное
    public void setDayElement(int i){
        WebElement dayElement = driver.findElement(By.xpath("//form[@id='userForm']//div[@id='dateOfBirth']//div[@class='react-datepicker__week']//div[text()='"+i+"'][contains(@aria-label,'"+birthdayMonthString+"')]"));
        dayElement.click();
    }


    public void inputSubjects(String subject){
        subjectsField.clear();
        subjectsField.sendKeys(subject);
    }

    public int getSubjectsOptionSize(){
        return subjectsOption.size();
    }

    public void setSubjectsOption(int i){
        subjectsOption.get(i).click();
    }

    public String getSubjectsOptionText(int i){
        return subjectsOption.get(i).getText();
    }


    public int getHobbiesCheckBoxSize(){
        return hobbiesCheckBox.size();
    }

    public void setHobbyCheckBox(int i){
        WebElement hobbyInput = hobbiesCheckBox.get(i);
        if (!hobbyInput.isSelected()) hobbyInput.click();
    }

    public String getHobbyCheckBoxText(int i){
        WebElement hobbyLabel = hobbiesCheckBox.get(i).findElement(By.xpath("./child::label"));
        return hobbyLabel.getText();
    }

    public void setPictureFile(String path) {
        pictureFile.sendKeys(path);
    }

    public void inputCurrentAddress(String address) {
        currentAddressField.sendKeys(address);
    }


    public void activateStateField() {
        stateField.click();
    }

    public List<WebElement> getStateOption() {
        return stateOption;
    }

    public void setStateField(int i) {
        stateOption.get(i).click();
    }


    public void activateCityField() {
        cityField.click();
    }

    public List<WebElement> getCityOption() {
        return cityOption;
    }

    public void setCityField(int i) {
        cityOption.get(i).click();
    }


    public ModalPage clickSubmitButton(){
        submitButton.click();
        return new ModalPage(driver);
    }





}

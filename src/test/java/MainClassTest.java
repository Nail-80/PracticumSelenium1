import org.junit.*;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.Month;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainClassTest {

    private static WebDriver driver;
    private static PracticeFormPage practiceFormPage;
    private static ModalPage modalPage;

    private static String firstName="Alexey";
    private static String lastName="Petrov";
    private static String email;
    private static String gender="Male";
    private static StringBuilder mobile;      // random
    private static int birthdayYear;          // random
    private static int birthdayMonth;         // random
    private static int birthdayDay;           // random
    private static String subjects;           // random
    private static int maxSubjectsCount=5;
    private static String hobbies;            // random
    private static String pathPictureFile="C:\\Users\\Default\\Pictures\\gift.png";
    private static String currentAddress="26 Floor Raj Kapoor Bilding,\nMumbai Road";
    private static String stateString;        // random
    private static String cityString;         // random


    @BeforeClass
    public static void setUp() {
        //System.setProperty("webdriver.xxx.driver","xxx");
        ChromeOptions opt = new ChromeOptions();
        opt.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/automation-practice-form/");
        practiceFormPage = new PracticeFormPage(driver);

        Random random = new Random();

        practiceFormPage.inputFirstName(firstName);

        practiceFormPage.inputLastName(lastName);

        email = firstName.substring(0,1).toLowerCase(Locale.ROOT)+lastName.toLowerCase(Locale.ROOT)+"@example.com";
        practiceFormPage.inputEmail(email);

        practiceFormPage.setGenderRadioButton(gender);

        mobile = new StringBuilder();
        while (mobile.length() < 10) {
            mobile.append((int) (Math.random() * 10));
        }
        practiceFormPage.inputMobile(mobile.toString());

        practiceFormPage.activateDateBirth();
        birthdayYear = random.nextInt(practiceFormPage.getCountYearsOption()) + practiceFormPage.getMinValueYearsOption();
        practiceFormPage.setYearsOption(birthdayYear);
        birthdayMonth = random.nextInt(12)+1;
        practiceFormPage.setMonthsOption(birthdayMonth);
        birthdayDay = random.nextInt( Month.of(birthdayMonth).maxLength() ) + 1;
        practiceFormPage.setDayElement(birthdayDay);

        String letters = "qwertyuiopasdfghjklzxcvbnm";
        int curSubjectsCount=0;
        while (curSubjectsCount<maxSubjectsCount) {
            String letter = String.valueOf(letters.charAt(random.nextInt(letters.length())));
            System.out.println(letter);
            practiceFormPage.inputSubjects(letter);
            int subjectsOptionSize = practiceFormPage.getSubjectsOptionSize();
            if(subjectsOptionSize>0) {
                int choiceSubjects = random.nextInt(subjectsOptionSize);
                subjects = (subjects!=null?subjects+", ":"") + practiceFormPage.getSubjectsOptionText(choiceSubjects);
                practiceFormPage.setSubjectsOption(choiceSubjects);
                curSubjectsCount++;
            }
        }

        int hobbiesCount = practiceFormPage.getHobbiesCheckBoxSize();
        int choiceHobby = random.nextInt(hobbiesCount);
        practiceFormPage.setHobbyCheckBox(choiceHobby);
        hobbies = practiceFormPage.getHobbyCheckBoxText(choiceHobby);

        practiceFormPage.setPictureFile(pathPictureFile);

        practiceFormPage.inputCurrentAddress(currentAddress);

        practiceFormPage.activateStateField();
        List<WebElement> stateOption = practiceFormPage.getStateOption();
        int stateCode = random.nextInt(stateOption.size());
        stateString = stateOption.get(stateCode).getText();
        practiceFormPage.setStateField(stateCode);

        practiceFormPage.activateCityField();
        List<WebElement> cityOption = practiceFormPage.getCityOption();
        int cityCode = random.nextInt(cityOption.size());
        cityString = cityOption.get(cityCode).getText();
        practiceFormPage.setCityField(cityCode);

        modalPage = practiceFormPage.clickSubmitButton();
    }

    @AfterClass
    public static void tearDown(){
        modalPage.clickCloseButton();
        driver.quit();
        //driver.close();
    }

    @Test
    public void testTitle(){
        String title="Thanks for submitting the form";
        Assert.assertEquals(title,modalPage.getTitleDiv());
    }

    @Test
    public void testName(){
        Assert.assertEquals(firstName+" "+lastName,modalPage.getNameValueText());
    }

    @Test
    public void testEmail(){
        Assert.assertEquals(email,modalPage.getEmailValueText());
    }

    @Test
    public void testGender(){
        Assert.assertEquals(gender,modalPage.getGenderValueText());
    }

    @Test
    public void testMobile(){
        Assert.assertEquals(mobile.toString(),modalPage.getMobileValueText());
    }

    @Test
    public void testDateBirth(){
        String month =  (Month.of(birthdayMonth).toString().toUpperCase()).substring(0,1) + (Month.of(birthdayMonth).toString().toLowerCase()).substring(1);
        String dateBirth =  (birthdayDay<10?"0":"") + birthdayDay + " " + month + ","+birthdayYear;
        Assert.assertEquals(dateBirth,modalPage.getDateBirthValueText());
    }

    @Test
    public void testSubjects(){
        Assert.assertEquals(subjects,modalPage.getSubjectsValueText());
    }

    @Test
    public void testHobbies(){
        Assert.assertEquals(hobbies,modalPage.getHobbiesValueText());
    }

    @Test
    public void testPicture(){
        String filename = pathPictureFile.substring(pathPictureFile.lastIndexOf("\\")+1);
        Assert.assertEquals(filename,modalPage.getPictureValueText());
    }

    @Test
    public void testAddress(){
        Assert.assertEquals(currentAddress.replaceAll("\\n"," "),modalPage.getAddressValueText());
    }

    @Test
    public void testStateCity(){
        Assert.assertEquals(stateString+" "+cityString,modalPage.getStateCityValueText());
    }


}

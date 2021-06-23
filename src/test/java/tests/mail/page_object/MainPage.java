package tests.mail.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.mail.business_object.User;

public class MainPage extends AbstractPage{
    User user = new User();
    private static final By LOGIN_ENTER_FIELD_LOCATOR = By.xpath("//input[@placeholder='Имя ящика']");
    private static final By BUTTON_GO_NEXT_TO_ENTER_PASSWORD_LOCATOR = By.xpath("//button[@data-testid='enter-password']");
    private static final By PASSWORD_ENTER_FIELD_LOCATOR = By.xpath("//input[@placeholder='Пароль']");
    private static final By LOGIN_ENTER_LOCATOR = By.xpath("//button[@data-testid='login-to-mail']");
    public MainPage(WebDriver driver){
        super(driver);
    }
    public MainPage open() {
        driver.get("https://mail.ru/");
        return this;
    }
    public MainPage enterLogin(){
        driver.findElement(LOGIN_ENTER_FIELD_LOCATOR).sendKeys(user.getLogin());
        return this;
    }
    public MainPage moveToPasswordEnter(){
        driver.findElement(BUTTON_GO_NEXT_TO_ENTER_PASSWORD_LOCATOR).click();
        return this;
    }
    public MainPage enterPassword(){
        driver.findElement(PASSWORD_ENTER_FIELD_LOCATOR).sendKeys(user.getPassword());
        return this;
    }
    public InboxPage clickLoginButton(){
        driver.findElement(LOGIN_ENTER_LOCATOR).click();
        return new InboxPage(driver);
    }

}

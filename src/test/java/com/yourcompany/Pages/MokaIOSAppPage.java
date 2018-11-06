package com.yourcompany.Pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;

public class MokaIOSAppPage {

  @FindBy(id = "goToSignIn")
  private WebElement goToSignInButton;

  @FindBy(id = "email")
  private WebElement emailSignIn;

  @FindBy(id = "password")
  private WebElement passwordSignIn;

  @FindBy(id = "signInButton")
  private WebElement signInButton;

  @FindBy(id = "tableSubtitle")
  private WebElement libraryTitle;

  public WebDriver driver;

  public MokaIOSAppPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public void clickSignIn() {
    this.goToSignInButton.click();
  }

  public void inputEmailOnSignIn(String email) {
    this.emailSignIn.click();
    this.emailSignIn.sendKeys(email);
  }

  public void inputPasswordOnSignIn(String password) {
    this.passwordSignIn.click();
    this.passwordSignIn.sendKeys(password);
  }

  public void clickSignInButton() {
    this.signInButton.click();
  }

  public String getLibraryTitleText() {
    return this.libraryTitle.getText();
  }

  public boolean isOnMokaLibraryPage() {
    try {
      getLibraryTitleText();
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}

package com.mytesting.pages;

import com.mytesting.actions.IActionBot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CampusPage extends BasePage implements IValidatable {

  @FindBy(how = How.ID, using = "levelTest-cta")
  private WebElement levelContinueButton;

  @FindBy(how = How.CSS, using = ".ui-dialog-titlebar-close")
  private WebElement closeStartCourseDialog;

  @FindBy(how = How.CSS, using = ".ui-icon-closethick")
  private WebElement closeSendPersonalInformationDialog;

  @FindBy(how = How.ID, using = "my_account")
  private WebElement myAccountMenu;

  @FindBy(how = How.ID, using = "aLinkHeadLogout")
  private WebElement logoutButton;

  @FindBy(how = How.ID, using = "popup-closeAction")
  private WebElement doNotInviteFriendsNowButton;

  @FindBy(how = How.ID, using = "idUlHome")
  private WebElement homeButton;

  @FindBy(how = How.CSS, using = ".comenzarcurso")
  private WebElement startCourseButton;

  public CampusPage(WebDriver driver) {
    super(driver);
  }

  public void clickLevelContinueButton() {

    actionBot.waitElementDisplayedAndClick(levelContinueButton);
  }

  public void clickCloseStartCourseDialog() {

    actionBot.waitElementDisplayedAndClick(closeStartCourseDialog);
  }

  public void clickCloseSendPersonalInformationDialog() {

    actionBot.waitElementDisplayedAndClick(closeSendPersonalInformationDialog);
  }

  public void clickNotNowInviteFriendsDialog() {

    actionBot.waitElementDisplayedAndClick(doNotInviteFriendsNowButton);
  }

  public void clickMyAccountMenu() {

    actionBot.waitElementDisplayedAndClick(myAccountMenu);
  }

  public void clickLogoutButton() {

    actionBot.waitElementDisplayedAndClick(logoutButton);
  }

  public void clickHomeButton() {

    actionBot.waitElementDisplayedAndClick(homeButton);
  }

  public void clickStartCourse() {

    actionBot.waitElementDisplayedAndClick(startCourseButton);
  }

  public boolean validatePage() {

    if (actionBot.isElementVisible(myAccountMenu) &&
        actionBot.isElementVisible(homeButton))

      return true;

    return false;
  }

}
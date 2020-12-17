package appmanager;

import model.ContactData;
import model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirst_name());
        type(By.name("middlename"), contactData.getMiddle_name());
        type(By.name("lastname"), contactData.getLast_name());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWork());


        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }


        if (isElementPresent(By.name("new_group"))) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }
    }

    public void submitContactCreation(String submit) {
        click(By.name(submit));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void initModifyContact(int id) {
        click(By.xpath("(//img[@alt='Edit'])['" + id + "']"));
    }

    public void  initContactModificationById(int id){
        //ищем ссылку с href edit.php?id
        click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
        //альтернативная запись через String.format
        //click(By.xpath(String.format("//a[@href='edit.php?id='%s']", id)));
    }


    public void returnToHomePage(){
        click(By.linkText("home"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void submitContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo()
                .alert()
                .accept();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("(//img[@alt='Edit'])[1]"));
    }

    public void create(ContactData contact, boolean creation) {
        fillContactForm(contact, true);
        submitContactCreation("submit");
        contactCache = null;
        //submitContactCreation("submit");
    }

    public void modify(ContactData contact){
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact){
        selectContactById(contact.getId());
        submitContactDeletion();
        contactCache = null;
        returnToHomePage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }


    public Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){
            List<WebElement> cells = element.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            contactCache.add(new ContactData().withId(id).withFirst_name(firstName).withLast_name(lastName));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact){
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("firstname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirst_name(firstname).withLast_name(lastname).withHome(home).withWork(work).withMobile(mobile);
    }



}
package appmanager;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.openqa.selenium.By.name;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public Contacts contactCache = null;

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(name("firstname"), contactData.getFirst_name());
        type(name("middlename"), contactData.getMiddle_name());
        type(name("lastname"), contactData.getLast_name());
        type(name("nickname"), contactData.getNickname());
      //  attach(By.name("photo"), contactData.getPhoto());
       // type(By.name("title"), contactData.getTitle());
      //  type(By.name("company"), contactData.getCompany());
      //  type(By.name("address"), contactData.getAddress());
        type(name("home"), contactData.getHome());
        type(name("mobile"), contactData.getMobile());
        type(name("work"), contactData.getWork());
     //   type(By.name("email"), contactData.getEmail());
     //   type(By.name("email2"), contactData.getEmail2());
    //    type(By.name("email3"), contactData.getEmail3());


        if (creation) {
          if (contactData.getGroups().size() > 0 ){
              Assert.assertTrue(contactData.getGroups().size() == 1);
              new Select(wd.findElement(name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
          }
        }else {
            Assert.assertFalse(isElementPresent(name("new_group")));
        }
    }

    public void submitContactCreation(String submit) {
        click(name(submit));
    }

    public void submitContactCreation() {
        click(name("submit"));
    }



    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']"))
                .click();
    }

    public void initModifyContact(int id) {
        click(By.xpath("(//img[@alt='Edit'])['" + id + "']"));
    }

    public void initContactModificationById(int id) {
        //ищем ссылку с href edit.php?id
        click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
        //альтернативная запись через String.format
        //click(By.xpath(String.format("//a[@href='edit.php?id='%s']", id)));
    }


    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void submitContactModification() {
        click(name("update"));
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
        initNewContact();
        fillContactForm(contact, true);
        submitContactCreation("submit");
        //   contactCache = null;

    }

    private void initNewContact() {
        addNewContact();
    }

    private void addNewContact() {
        click(By.linkText("add new"));
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        submitContactDeletion();
        contactCache = null;
        returnToHomePage();
    }

    public int count() {
        return wd.findElements(name("selected[]"))
                .size();
    }

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> rows = wd.findElements(name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(cells.get(0)
                    .findElement(By.tagName("input"))
                    .getAttribute("value"));
            String firstName = cells.get(2)
                    .getText();
            String lastName = cells.get(1)
                    .getText();
            String allPhones = cells.get(5)
                    .getText();
            String allEmails = cells.get(4)
                    .getText();
            String address = cells.get(3)
                    .getText();
            contacts.add(new ContactData().withId(id)
                    .withFirst_name(firstName)
                    .withLast_name(lastName)
                    .withAddress(address)
                    .withAllEmails(allEmails)
                    .withAllPhones(allPhones));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(name("firstname"))
                .getAttribute("value");
        String lastname = wd.findElement(name("firstname"))
                .getAttribute("value");
        String home = wd.findElement(name("home"))
                .getAttribute("value");
        String work = wd.findElement(name("work"))
                .getAttribute("value");
        String mobile = wd.findElement(name("mobile"))
                .getAttribute("value");
        String email = wd.findElement(name("email"))
                .getAttribute("value");
        String email2 = wd.findElement(name("email2"))
                .getAttribute("value");
        String email3 = wd.findElement(name("email3"))
                .getAttribute("value");
        String address = wd.findElement(name("address"))
                .getAttribute("value");
        wd.navigate()
                .back();
        return new ContactData().withId(contact.getId())
                .withFirst_name(firstname)
                .withLast_name(lastname)
                .withAddress(address)
                .withHome(home)
                .withMobile(mobile)
                .withWork(work)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3);
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        selelectDispayedGroup(group.getName());
        selectContactById(contact.getId());
        removeFromGroup(group.getName());
    }

    private void removeFromGroup(String name) {
        click(name("remove"));
    }

    private void selelectDispayedGroup(String name) {
        new Select(wd.findElement(name("group"))).selectByVisibleText(name);
    }

    public void selectDisplayGroup(String name) {
        new Select(wd.findElement(name("group"))).selectByVisibleText(name);
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        new Select(wd.findElement(name("to_group"))).selectByVisibleText(group.getName());
        click(name("add"));
    }

    //Contacts with Cashe
   /* public Contacts all() {
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
            String[] phones = cells.get(5).getText().split("\n");
            contactCache.add(new ContactData().withId(id).withFirst_name(firstName).withLast_name(lastName).withHome(phones[0]).withMobile(phones[1]).withWork(phones[2]));
        }
        return new Contacts(contactCache);
    }*/


}
package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-d", description = "Data Format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        //Библиотека JCommander
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch  (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> groups = generateContacts(count);
        if (format.equals("csv")){
            saveAsCsv(groups, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(groups, new File(file));
        } else if (format.equals("json")){
            saveAsJson(groups, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
       try( Writer writer = new FileWriter(file)) {
           writer.write(json);
       }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);//берет конфиги из анотаций в классе или можно xstream.alias("group", GroupData.class);
        //передаем объект который надо сереализовать т.е. из объектного представления в строчку в формате xml
        String xml = xstream.toXML(contacts);
       try ( Writer writer = new FileWriter(file)){
           writer.write(xml);
       }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        //открываем фаил на запись
        try( Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                //запись в фаил
                writer.write(String.format("%s;%s;%s\n", contact.getFirst_name(), contact.getLast_name(), contact.getGroup()));
            }
        }
    }

    private  List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirst_name(String.format("FirstName %s", i))
                    .withLast_name(String.format("LastName %s", i))
            .withGroup(String.format("test1")));
        }
        return contacts;
    }
}

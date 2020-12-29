package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter (names = "-f", description = "Target File")
    public String file;

    @Parameter (names = "-c", description = "Contact Count")
    public int count;

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
        List<ContactData> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private void save(List<ContactData> contacts, File file) throws IOException {

        //открываем фаил на запись
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            //запись в фаил
            writer.write(String.format("%s;%s;%s\n", contact.getFirst_name(), contact.getLast_name()));
        }
        //надо обязательно закрыть фаил
        writer.close();
    }

    private  List<ContactData> generateContacts(int count) {
        List<ContactData> groups = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            groups.add(new ContactData().withFirst_name(String.format("test %s", i))
                    .withLast_name(String.format("test %s", i)));
        }
        return groups;
    }
}

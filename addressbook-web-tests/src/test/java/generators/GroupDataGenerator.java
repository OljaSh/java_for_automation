package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    //Библиотека JCommander
    @Parameter(names = "-c", description ="Group Count")
    public int count;

    //Библиотека JCommander
    @Parameter(names = "-f", description ="Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
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
        List<GroupData> groups = generateGroups(count);
        save(groups, new File(file));
    }

    private void save(List<GroupData> groups, File file) throws IOException {

        //открываем фаил на запись
        Writer writer = new FileWriter(file);
        for (GroupData group : groups){
            //запись в фаил
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
             }
        //надо обязательно закрыть фаил
        writer.close();
    }

    private  List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++){
            groups.add(new GroupData().withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i))
                    .withFooter(String.format("footer %s", i)));
        }
        return groups;
    }
}
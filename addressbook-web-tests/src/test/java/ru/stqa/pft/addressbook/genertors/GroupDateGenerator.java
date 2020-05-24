package ru.stqa.pft.addressbook.genertors;

import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDateGenerator {

     public static void main (String[] args) throws IOException {

        //Convert in number
        int count = Integer.parseInt(args[0]);

        //Convert String to directory
        File file = new File (args[1]);


        List<GroupData> groups = generateGroups (count);
        save(groups, file);
    }


    private static void save(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group: groups){
            writer.write(String.format("%s;%s:%s\n", group.getName(),group.getHeader(),group.getFooter()));

        }
        writer.close();

    }



    private static List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i=0; i < count; i++){
            groups.add(new GroupData().withName(String.format("test %s",i))
                    .withHeader(String.format("header %s",i)).withFooter(String.format("footer %s",i)));

        }
        return groups;

    }
}

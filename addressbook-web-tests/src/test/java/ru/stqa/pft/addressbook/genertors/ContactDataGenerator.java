package ru.stqa.pft.addressbook.genertors;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Data count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main (String[] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        } catch(ParameterException ex)
        {
            jCommander.usage();
            return;
        }
        generator.run();
    }


    private void run() throws IOException {
        List<ContactData> contacts = generateContacts (count);
        if (format.equals("xml")){
            saveAsXml(contacts, new File (file));}
        else if (format.equals("json")){
            saveAsJson(contacts, new File (file));}
        else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson (contacts);
        try(Writer writer = new FileWriter(file)) {
        writer.write(json);}

    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException  {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try(Writer writer = new FileWriter(file)){
        writer.write(xml);}

    }


    private List<ContactData> generateContacts (int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i < count; i++){
            contacts.add(new ContactData().withFirstname(String.format("firstname %s",i))
                    .withMiddlename(String.format("middletname %s",i))
                    .withLastname(String.format("lastname %s",i))
                    .withNickname(String.format("nickname %s",i))
                    .withMobile(String.format("8931776888%s",i))
                    .withHomephone(String.format("8931776889%s",i))
                    .withWork(String.format("8931776890%s",i))
                    .withAddress(String.format("Address%s",i))
                    .withEmail(String.format("email1_%s@mail.ru",i))
                    .withEmail2(String.format("email2_%s@mail.ru",i))
                    .withEmail3(String.format("email3_%s@mail.ru",i))
                    .withCompany(String.format("company %s",i)));

        }
        return contacts;

    }

}

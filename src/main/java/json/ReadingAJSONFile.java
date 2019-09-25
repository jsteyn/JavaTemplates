package json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * First download the GSON library or add it to the Maven configuration file
 */
public class ReadingAJSONFile {

    /**
     * filename containing json
     */
    static String filename = "data/sample_jsondata.json";
    /**
     * Create a configurable gson object
     */
    static public GsonBuilder builder = new GsonBuilder();
    static public Gson gson;

    /**
     * We need a class structure that reflects the json structure
     */
    static public JSONFileOfPersons jsonFileOfPersons = new JSONFileOfPersons();

    static public void main(String[] args) {

        switch (args[0]) {
            case "1":
                createJSONfile_hardcoded();
                break;
            case "2":
                readJSONformFile();
                break;
            case "3":
                writeJSONToFile();
                break;
        }


        // Write json to file from populated data structure
    }

    static private void createJSONfile_hardcoded() {
        gson = builder.setPrettyPrinting().create(); // Add pretty printing for easy reading
        // Add some people
        jsonFileOfPersons.add(new Person(jsonFileOfPersons.getPersons().size() + 1, "Jannetta Sophia", "Steyn"));
        jsonFileOfPersons.add(new Person(jsonFileOfPersons.getPersons().size() + 1, "Stuart Manfred", "Lewis"));
        jsonFileOfPersons.add(new Person(jsonFileOfPersons.getPersons().size() + 1, "Leonetta Wendy", "Lewis"));
        jsonFileOfPersons.add(new Person(jsonFileOfPersons.getPersons().size() + 1, "Leonetta", "Henning"));
        try {
            gson.toJson(jsonFileOfPersons, new FileWriter(new File(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("People added: " );

        jsonFileOfPersons.getPersons().forEach((person) -> {System.out.println(person.getId() + "\t" + person.getFirstnames() + " /" + person.getSurname() + "/");});
        // Pretty print data
        System.out.println("JSON format: " );
        // Extract json from populated data structure
        System.out.println(gson.toJson(jsonFileOfPersons));

    }

    static private void readJSONformFile() {
        // Read json from a file into data structure
        try {
            JSONFileOfPersons jsonFileOfPersons2 = gson.fromJson(new FileReader(filename), JSONFileOfPersons.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static private void writeJSONToFile() {
        try {
            FileWriter fw = new FileWriter(new File(filename));
            gson.toJson(jsonFileOfPersons, fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package Controller;

import DAO.MockDataBase;
import spark.Request;
import spark.Response;

public class PersonController {
    static MockDataBase mockdb = MockDataBase.getInstance();
    public static String getPerson(Request request, Response response) {
        int personID = Integer.parseInt(request.queryParams("personID"));
        return "Person " + mockdb.getPerson(personID);
    }

    public static String getAllPeople(Request request, Response response) {
        return mockdb.getAllPeople().replaceAll("\n", "<br/>");
    }

    public static String addPerson(Request request, Response response) {
        String ret;
        if (mockdb.addPerson(request.queryParams("firstname"), request.queryParams("lastname")))
            ret = request.queryParams("firstname") + "," + request.queryParams("lastname") + " added";
        else ret = "Adding failed";
        return ret;
    }
}

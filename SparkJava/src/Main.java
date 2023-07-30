import Controller.PersonController;
import DAO.MockDataBase;
import static spark.Spark.*;
public class Main {

    MockDataBase mockdb = MockDataBase.getInstance();
    /**
     * To pass information back and forth between a browser and a webserver one has to use
     * either a GET or a POST.
     * This post describes the difference quite clearly:
     * https://www.baeldung.com/cs/http-get-vs-post#:~:text=GET%20requests%20are%20intended%20to,may%20modify%20the%20server's%20state.
     * I always remember the difference as GET using the URL to pass parameters while
     * POST sends the information in the body of the document
     * @param args
     */
    public static void main(String[] args) {
        port(4567); //default if not specified
        staticFiles.header("Access-Control-Allow-Origin", "*");
        staticFiles.location("/public");

        System.out.println("You should be able to access this service on http://0.0.0.0:4567");

        // just some information that can be retrieved
        before((req, res) -> {
            System.out.println("Query string: " + req.queryString());
            System.out.println("Headers: " + req.headers());
            System.out.println("Protocol: " + req.protocol());
            System.out.println("Method: " + req.raw().getMethod());
            System.out.println("Matched path: " + req.matchedPath());
            System.out.println("Path info: " + req.pathInfo());
        });

        // USING GET
        // a simple home page
        get("/", (req, res) ->
                "Hello World: <a href=\"http://0.0.0.0:4567/hello\">Hello World</a><br/>" +
                        "getPerson: <a href=\"http://0.0.0.0:4567/getPerson?personID=2\">2</a><br/>" +
                        "<a href=\"http://0.0.0.0:4567/html/index.html\">addPerson</a><br/>" +
                        "<a href=\"http://0.0.0.0:4567/getAllPeople\">getAllPeople</a><br/>");

        // direct response to a get
        get("/hello", (req, res) -> "Hello World");

        // direct response to a get with a variable: http://0.0.0.0:4567/hello/jannetta
        get("/hello/:name", (req, res) -> "Hello " + req.params(":name"));

        // call method to handle get request
        // this lamda is effectively the same as
        // get("/getPerson", (req, res) -> PersonController.getPerson(req, res));
        // Get a person using the personID
        // http://0.0.0.0:4567/getPerson?personID=2
        get("/getPerson", PersonController::getPerson);

        // Get all people in the database
        // http://0.0.0.0:4567/getAllPeople
        get("/getAllPeople", PersonController::getAllPeople);
        get("/getsomepeople", PersonController::getAllPeople);

        // USING POST
        // Start by going to http://0.0.0.0:4567/html/index.html
        // fill in the form and click Submit
        // Submit will call addPerson
        post("/addPerson",  PersonController::addPerson);

    }
}
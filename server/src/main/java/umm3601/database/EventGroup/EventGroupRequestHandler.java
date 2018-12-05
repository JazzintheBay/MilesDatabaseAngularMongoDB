package umm3601.database.EventGroup;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import spark.Request;
import spark.Response;

public class EventGroupRequestHandler {
    private final EventGroupController eventGroupController;
    public EventGroupRequestHandler(EventGroupController eventGroupController){
        this.eventGroupController = eventGroupController;
    }
    /**Method called from Server when the 'api/items/:id' endpoint is received.
     * Get a JSON response with a list of all the users in the database.
     *
     * @param req the HTTP request
     * @param res the HTTP response
     * @return one user in JSON formatted string and if it fails it will return text with a different HTTP status code
     */

    // gets one item using its ObjectId--didn't use, just for potential future functionality
    public String getEventGroupJSON(Request req, Response res){
        res.type("application/json");
        String id = req.params("id");
        String item;
        try {
            item = eventGroupController.getEventGroupID(id);
        } catch (IllegalArgumentException e) {
            // This is thrown if the ID doesn't have the appropriate
            // form for a Mongo Object ID.
            // https://docs.mongodb.com/manual/reference/method/ObjectId/
            res.status(400);
            res.body("The requested item id " + id + " wasn't a legal Mongo Object ID.\n" +
                    "See 'https://docs.mongodb.com/manual/reference/method/ObjectId/' for more info.");
            return "";
        }
        if (item != null) {
            return item;
        } else {
            res.status(404);
            res.body("The requested item with id " + id + " was not found");
            return "";
        }
    }



    /**Method called from Server when the 'api/eventGroups' endpoint is received.
     * This handles the request received and the response
     * that will be sent back.
     *@param req the HTTP request
     * @param res the HTTP response
     * @return an array of users in JSON formatted String
     */

    // Gets the eventGroups from the DB given the query parameters
    public String getEventGroups(Request req, Response res)
    {
        res.type("application/json");
        return eventGroupController.getEventGroups(req.queryMap().toMap());
    }

    /**Method called from Server when the 'api/eventGroups/new'endpoint is received.
     * Gets specified user info from request and calls addNewUser helper method
     * to append that info to a document
     *
     * @param req the HTTP request
     * @param res the HTTP response
     * @return a boolean as whether the eventGroup was added successfully or not
     */
    public String addNewEventGroup(Request req, Response res)
    {

        res.type("application/json");
        Object o = JSON.parse(req.body());
        try {
            // if the object that is the JSON representation of the request body's class is the class BasicDBObject
            // then try to add the item with itemController's addNewItem method
            if(o.getClass().equals(BasicDBObject.class)) {
                try {
                    BasicDBObject dbO = (BasicDBObject) o;

                    String userID = dbO.getString("userID");
                    String purpose = dbO.getString("purpose");
                    String category = dbO.getString("category");
                    String musicGroupName = dbO.getString("musicGroupName");
                    Boolean status = dbO.getBoolean("status");
                    String frequency = dbO.getString("frequency");
                    String start = dbO.getString("start");
                    String end = dbO.getString("end");
                    String next = dbO.getString("next");

                    System.err.println("Adding new eventGroup for user "+ userID + " [purpose=" + purpose + ", category=" +
                            category + " musicGroupName=" + musicGroupName + " status=" + status + ", frequency= " + frequency +
                            ", start=" + start + ", end=" + end + ", next=" + next + ']');
                    return eventGroupController.addNewEventGroup(userID, purpose, category, musicGroupName, status, frequency, start, end, next).toString();
                } catch (NullPointerException e) {
                    System.err.println("A value was malformed or omitted, new item request failed.");
                    return null;
                }

            }
            else
            {
                System.err.println("Expected BasicDBObject, received " + o.getClass());
                return null;
            }
        }
        catch(RuntimeException ree)
        {
            ree.printStackTrace();
            return null;
        }
    }

    public String editEventGroup(Request req, Response res)
    {

        res.type("application/json");
        Object o = JSON.parse(req.body());
        try {
            // if the object that is the JSON representation of the request body's class is the class BasicDBObject
            // then try to add the item with itemController's completeEventGroup method
            if(o.getClass().equals(BasicDBObject.class)) {
                try {
                    BasicDBObject dbO = (BasicDBObject) o;

                    String id = dbO.getString("_id");
                    String purpose = dbO.getString("purpose");
                    String category = dbO.getString("category");
                    String musicGroupName = dbO.getString("musicGroupName");
                    Boolean status = dbO.getBoolean("status");
                    String frequency = dbO.getString("frequency");
                    String start = dbO.getString("start");
                    String end = dbO.getString("end");
                    String next = dbO.getString("next");

                    return eventGroupController.editEventGroup(id, purpose, category, musicGroupName, status, frequency, start, end, next).toString();
                } catch (NullPointerException e) {
                    System.err.println("A value was malformed or omitted, new item request failed.");
                    return null;
                }

            }
            else
            {
                System.err.println("Expected BasicDBObject, received " + o.getClass());
                return null;
            }
        }
        catch(RuntimeException ree)
        {
            ree.printStackTrace();
            return null;
        }
    }

    public String deleteEventGroup(Request req, Response res){

        System.out.println("Deleting eventGroup with ID: " + req.params(":id"));

        res.type("application/json");

        try {
            String id = req.params(":id");
            eventGroupController.deleteEventGroup(id);
            return req.params(":id");
        }
        catch(RuntimeException ree)
        {
            ree.printStackTrace();
            return null;
        }
    }}


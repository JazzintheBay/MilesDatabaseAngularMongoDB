//package umm3601.database.EventGroup;
//
//import com.google.gson.Gson;
//import com.mongodb.MongoException;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.util.JSON;
//import org.bson.Document;
//import org.bson.types.ObjectId;
//
//import java.util.Iterator;
//import java.util.Map;
//
//import static com.mongodb.client.model.Filters.eq;
//
//
//// Controller that manages information about the EventGroups as listed by schema
//public class EventGroupController {
//
//    private final Gson gson;
//    private MongoDatabase database;
//    // eventGroupCollection is the collection that the events data is in.
//    private final MongoCollection<Document> eventGroupCollection;
//
//    // Construct controller for the eventGroup objects.
//    public EventGroupController(MongoDatabase database) {
//        gson = new Gson();
//        this.database = database;
//        eventGroupCollection = database.getCollection("eventGroup");
//    }
//
//    // get an event by its EventGroup ObjectId, for filtering in the backend
//    public String getEventGroupID(String id) {
//        FindIterable<Document> jsonItems
//                = eventGroupCollection
//                .find(eq("_id", new ObjectId(id)));
//
//        Iterator<Document> iterator = jsonItems.iterator();
//        if (iterator.hasNext()) {
//            Document eventGroup = iterator.next();
//            return eventGroup.toJson();
//        } else {
//            // We didn't find the desired item
//            return null;
//        }
//    }
//
//    // Helper method which iterates through the collection, receiving all
//    // documents if no query parameter is specified. If the eventGroup parameter is
//    // specified, then the collection is filtered so only documents of that
//    // specified eventGroup are found.
//
//    public String getEventGroups(Map<String, String[]> queryParams) {
//
//        Document filterDoc = new Document();
//
//        //
////        For future integration with userID
//
////        //Filter by userID
////        //If there is no userID provided, return an empty result
////        if (queryParams.containsKey("userID")) {
////            String targetContent = (queryParams.get("userID")[0]);
////
////            Document contentRegQuery = new Document();
////            contentRegQuery.append("$regex", targetContent);
////            contentRegQuery.append("$options", "i");
////            filterDoc = filterDoc.append("userID", contentRegQuery);
////        } else {
////            System.out.println("It had no userID");
////            return JSON.serialize("[ ]");
////        }
//
//        // "eventGroup" will be a key to a string object, where the object is
//        // what we get when people enter their eventGroups as a text body.
//        // "eventGroup" is the purpose of the eventGroup
//
//        /////////////////////////////////////////////////////////////////////////////////////////
//
////        if (queryParams.containsKey("purpose")) {
////            String targetContent = (queryParams.get("purpose")[0]);
////            Document contentRegQuery = new Document();
////            contentRegQuery.append("$regex", targetContent);
////            contentRegQuery.append("$options", "i");
////            filterDoc = filterDoc.append("purpose", contentRegQuery);
////        }
////
////        // category is the category of the eventGroup, also a String
////        if (queryParams.containsKey("category")) {
////            String targetContent = (queryParams.get("category")[0]);
////            Document contentRegQuery = new Document();
////            contentRegQuery.append("$regex", targetContent);
////            contentRegQuery.append("$options", "i");
////            filterDoc = filterDoc.append("category", contentRegQuery);
////        }
////
////        // name is the title of the eventGroup
////        if (queryParams.containsKey("name")) {
////            String targetContent = (queryParams.get("name")[0]);
////            Document contentRegQuery = new Document();
////            contentRegQuery.append("$regex", targetContent);
////            contentRegQuery.append("$options", "i");
////            filterDoc = filterDoc.append("name", contentRegQuery);
////        }
////
////        if (queryParams.containsKey("status")) {
////            boolean targetStatus = Boolean.parseBoolean(queryParams.get("status")[0]);
////            filterDoc = filterDoc.append("status", targetStatus);
////        }
////
////        // FindIterable comes from mongo, Document comes from Gson
////        FindIterable<Document> matchingEventGroups = eventGroupCollection.find(filterDoc);
////
////        return JSON.serialize(matchingEventGroups);
////    }
////
////
////    public String addNewEventGroup(String userID, String purpose, String category, String name,
////                             Boolean status, String frequency, String start, String end, String next) {
////
////        // makes the search Document key-pairs
////        Document newEventGroup = new Document();
////        newEventGroup.append("userID", userID);
////        newEventGroup.append("purpose", purpose);
////        newEventGroup.append("category", category);
////        newEventGroup.append("name", name);
////        newEventGroup.append("status", status);
////        newEventGroup.append("frequency", frequency);
////        newEventGroup.append("start", start);
////        newEventGroup.append("end", end);
////        newEventGroup.append("next", next);
////
////        try {
////            eventGroupCollection.insertOne(newEventGroup);
////            ObjectId id = newEventGroup.getObjectId("_id");
////
////            System.err.println("Successfully added new eventGroup " + userID + " [_id=" + id + ", purpose=" + purpose +
////                    ", category=" + category + ", name=" + name + ", frequency= "+ frequency +  ", start=" + start +
////                    ", end=" + end + ", next=" + next +']');
////            return JSON.serialize(id);
////        } catch(MongoException me) {
////            me.printStackTrace();
////            return null;        if (queryParams.containsKey("purpose")) {
//            String targetContent = (queryParams.get("purpose")[0]);
//            Document contentRegQuery = new Document();
//            contentRegQuery.append("$regex", targetContent);
//            contentRegQuery.append("$options", "i");
//            filterDoc = filterDoc.append("purpose", contentRegQuery);
//        }
//
//        // category is the category of the eventGroup, also a String
//        if (queryParams.containsKey("category")) {
//            String targetContent = (queryParams.get("category")[0]);
//            Document contentRegQuery = new Document();
//            contentRegQuery.append("$regex", targetContent);
//            contentRegQuery.append("$options", "i");
//            filterDoc = filterDoc.append("category", contentRegQuery);
//        }
//
//        // name is the title of the eventGroup
//        if (queryParams.containsKey("name")) {
//            String targetContent = (queryParams.get("name")[0]);
//            Document contentRegQuery = new Document();
//            contentRegQuery.append("$regex", targetContent);
//            contentRegQuery.append("$options", "i");
//            filterDoc = filterDoc.append("name", contentRegQuery);
//        }
//
//        if (queryParams.containsKey("status")) {
//            boolean targetStatus = Boolean.parseBoolean(queryParams.get("status")[0]);
//            filterDoc = filterDoc.append("status", targetStatus);
//        }
//
//        // FindIterable comes from mongo, Document comes from Gson
//        FindIterable<Document> matchingEventGroups = eventGroupCollection.find(filterDoc);
//
//        return JSON.serialize(matchingEventGroups);
//    }
//
//
//    public String addNewEventGroup(String userID, String purpose, String category, String name,
//                             Boolean status, String frequency, String start, String end, String next) {
//
//        // makes the search Document key-pairs
//        Document newEventGroup = new Document();
//        newEventGroup.append("userID", userID);
//        newEventGroup.append("purpose", purpose);
//        newEventGroup.append("category", category);
//        newEventGroup.append("name", name);
//        newEventGroup.append("status", status);
//        newEventGroup.append("frequency", frequency);
//        newEventGroup.append("start", start);
//        newEventGroup.append("end", end);
//        newEventGroup.append("next", next);
//
//        try {
//            eventGroupCollection.insertOne(newEventGroup);
//            ObjectId id = newEventGroup.getObjectId("_id");
//
//            System.err.println("Successfully added new eventGroup " + userID + " [_id=" + id + ", purpose=" + purpose +
//                    ", category=" + category + ", name=" + name + ", frequency= "+ frequency +  ", start=" + start +
//                    ", end=" + end + ", next=" + next +']');
//            return JSON.serialize(id);
//        } catch(MongoException me) {
//            me.printStackTrace();
//            return null;
//        }
//    }
//
//    public String editGoal(String id, String purpose, String category,
//                           String name, Boolean status, String frequency, String start,
//                           String end, String next){
//        Document newGoal = new Document();
//        newGoal.append("purpose", purpose);
//        newGoal.append("category", category);
//        newGoal.append("name", name);
//        newGoal.append("status", status);
//        newGoal.append("frequency", frequency);
//        newGoal.append("start", start);
//        newGoal.append("end", end);
//        newGoal.append("next", next);
//        Document setQuery = new Document();
//        setQuery.append("$set", newGoal);
//        Document searchQuery = new Document().append("_id", new ObjectId(id));
//
//        try {
//            goalCollection.updateOne(searchQuery, setQuery);
//            ObjectId theID = searchQuery.getObjectId("_id");
//            return JSON.serialize(theID);
//        } catch(MongoException me) {
//            me.printStackTrace();
//            return null;
//        }
//    }
//
//    public void deleteGoal(String id){
//        Document searchQuery = new Document().append("_id", new ObjectId(id));
//        System.out.println("Goal id: " + id);
//        try {
//            goalCollection.deleteOne(searchQuery);
//            ObjectId theID = searchQuery.getObjectId("_id");
//            System.out.println("Succesfully deleted goal with ID: " + theID);
//
//        } catch(MongoException me) {
//            me.printStackTrace();
//            System.out.println("error");
//        }
//    }
//
//}
////        }
////    }
////
////    public String editGoal(String id, String purpose, String category,
////                           String name, Boolean status, String frequency, String start,
////                           String end, String next){
////        Document newGoal = new Document();
////        newGoal.append("purpose", purpose);
////        newGoal.append("category", category);
////        newGoal.append("name", name);
////        newGoal.append("status", status);
////        newGoal.append("frequency", frequency);
////        newGoal.append("start", start);
////        newGoal.append("end", end);
////        newGoal.append("next", next);
////        Document setQuery = new Document();
////        setQuery.append("$set", newGoal);
////        Document searchQuery = new Document().append("_id", new ObjectId(id));
////
////        try {
////            goalCollection.updateOne(searchQuery, setQuery);
////            ObjectId theID = searchQuery.getObjectId("_id");
////            return JSON.serialize(theID);
////        } catch(MongoException me) {
////            me.printStackTrace();
////            return null;
////        }
////    }
////
////    public void deleteGoal(String id){
////        Document searchQuery = new Document().append("_id", new ObjectId(id));
////        System.out.println("Goal id: " + id);
////        try {
////            goalCollection.deleteOne(searchQuery);
////            ObjectId theID = searchQuery.getObjectId("_id");
////            System.out.println("Succesfully deleted goal with ID: " + theID);
////
////        } catch(MongoException me) {
////            me.printStackTrace();
////            System.out.println("error");
////        }
////    }
////
////}

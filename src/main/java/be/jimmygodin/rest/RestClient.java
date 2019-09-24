package be.jimmygodin.rest;

import be.jimmygodin.model.Message;
import be.jimmygodin.model.MessageList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;


public class RestClient {
    private static final String REST_URI_GET_MESSAGE_BY_ID = "http://localhost:8080/GuestBook/rest/messages/{id}";
    private static final String REST_URI_GET_ALL_MESSAGES = "http://localhost:8080/GuestBook/rest/messages";
    private static final String REST_URI_POST_NEW_MESSAGE = "http://localhost:8080/GuestBook/rest/messages";
    private static final String REST_URI_POST_NEW_MESSAGE_PARAM ="http://localhost:8080/GuestBook/rest/messages/{name}/{message}";
    private static final String REST_URI_PUT_MESSAGE_WITH_ID = "http://localhost:8080/GuestBook/rest/messages/{id}";

    private Client client;

    public RestClient (Client client) {
        this.client = client;
    }

    public Message getJsonMessage(int id) {
       return client
                .target(REST_URI_GET_MESSAGE_BY_ID)
                .resolveTemplate("id", id)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Message.class);
    }

    public MessageList getJsonAllMessages() {
        return client
                .target(REST_URI_GET_ALL_MESSAGES)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(MessageList.class);
    }

    public URI getUriOfCreatedMessage(Message message) {
        if(message == null) {
            throw new IllegalStateException("Message cannot be null, please provide one!");
        }
        return client
                .target(REST_URI_POST_NEW_MESSAGE)
                .request()
                .post(Entity.entity(message, MediaType.APPLICATION_JSON))
                .getLocation();
    }

    public URI getUriOfCreatedMessage(String author, String message) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(author) || StringUtils.isBlank(message)) {
            throw new IllegalStateException("Author and Message cannot be null!");
        }
        Map<String, Object> pathParams = new HashMap<>();
        pathParams.put("name", author);
        pathParams.put("message", message);
        return client
                .target(REST_URI_POST_NEW_MESSAGE_PARAM)
                .resolveTemplates(pathParams)
                .request()
                .post(Entity.json(""))
                .getLocation();
    }

    public Response updateMessage(Message message) {
        Entity<Message> updateEntity = Entity.json((message));
        return client.target(REST_URI_PUT_MESSAGE_WITH_ID)
                .resolveTemplate("id", message.getId())
                .request()
                .put(updateEntity);
    }

}

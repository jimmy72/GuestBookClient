package be.jimmygodin;

import be.jimmygodin.rest.RestClient;
import be.jimmygodin.model.Message;

import javax.ws.rs.client.ClientBuilder;
import java.io.UnsupportedEncodingException;

/**
 * GuestBook Client App
 *
 */
public class ClientApp
{
    public static void main( String[] args ) throws UnsupportedEncodingException {

        //create Rest client
        RestClient client = new RestClient(ClientBuilder.newClient());
        Message message;

        // 1) Create new message with message in body and show URI (the suggested way)
//        message = new Message();
//        message.setName("Jimmy");
//        message.setMessage("A message from Jimmy");
//        URI uri = client.getUriOfCreatedMessage(message);
//        System.out.println(uri);

        // 2) Create new message with path params and and show URI
//        URI uri = client.getUriOfCreatedMessage("Jimmy", "A message from Jimmy");
//        System.out.println(uri);

        // 3) Find message by id and print returned message
//        message = client.getJsonMessage(3);
//        System.out.println(message);


        // 4) Print out all messages
//        client.getJsonAllMessages().getMessageList().forEach(System.out::println);

        // 5) change message with id 1
//        message = new Message();
//        message.setId(1);
//        message.setName("Jimmy");
//        message.setMessage("An updated message from Jimmy");
//        Response response = client.updateMessage(message);
//        System.out.println(response.getStatus());
    }
}

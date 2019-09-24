package be.jimmygodin;

import static org.junit.Assert.assertEquals;

import be.jimmygodin.rest.RestClient;
import be.jimmygodin.model.Message;
import org.junit.Test;
import org.junit.Before;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

/**
 * Unit test for GuestBook Client App.
 */
public class ClientAppTest {
    public static final int HTTP_NO_CONTENT_CODE = 204;
    private RestClient client;

    @Before
    public void before() {
        client = new RestClient(ClientBuilder.newClient());
    }

    @Test
    public void ifMessageUpdatedOrCreated_whenCorrectJsonRequest_thenResponseCodeCreated()
    {
        Message message = new Message();
        message.setId(1);
        message.setName("Jimmy");
        message.setMessage("An updated message from Jimmy");

        Response response = client.updateMessage(message);

        assertEquals(HTTP_NO_CONTENT_CODE, response.getStatus());
    }
}

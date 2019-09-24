package be.jimmygodin.model;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.StringJoiner;


@XmlRootElement
public class Message implements Serializable, Comparable<Message> {

    private long id;
    private String name;
    private String message;

    public Message() {
    }

    public Message(String name, String message) {
        this.setName(name);
        this.setMessage(message);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Please provide a valid name!");
        }
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if(StringUtils.isBlank(message)) {
            throw new IllegalArgumentException("Please provide a valid message!");
        }
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Message)) {
            return false;
        }
        Message m = (Message) o;
        return this.id == m.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",
                Message.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("text='" + message + "'").toString();
    }

    @Override
    public int compareTo(Message o) {
        if(this.equals(o)) {
            return 0;
        }else {
            return this.getName().equalsIgnoreCase(o.getName())
                    ? -1
                    : this.getName().toLowerCase().compareTo(o.getName().toLowerCase());
        }
    }
}

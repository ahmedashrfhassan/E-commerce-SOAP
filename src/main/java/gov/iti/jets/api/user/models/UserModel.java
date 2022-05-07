package gov.iti.jets.api.user.models;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "user")
@Getter @Setter
public class UserModel {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    public UserModel() {
    }


    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

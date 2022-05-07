package gov.iti.jets.api.order.models;

import gov.iti.jets.api.user.models.UserModel;
import gov.iti.jets.persistance.entity.LineItem;
import gov.iti.jets.persistance.entity.User;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@XmlRootElement
@Getter @Setter
public class OrderModel implements Serializable {

    private int id;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<LineItemModel> lineItems;

    private UserModel maker;



}

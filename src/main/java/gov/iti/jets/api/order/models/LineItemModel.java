package gov.iti.jets.api.order.models;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@XmlRootElement
@Getter @Setter
public class LineItemModel {
    private int id;

    private String productName;

    private BigDecimal productPrice;

    private int quantity;
}

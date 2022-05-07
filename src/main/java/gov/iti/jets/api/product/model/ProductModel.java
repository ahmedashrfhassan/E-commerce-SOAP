package gov.iti.jets.api.product.model;

import gov.iti.jets.api.category.model.CategoryModel;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@XmlRootElement
public class ProductModel {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<CategoryModel> categories;
    private Integer quantity;


}

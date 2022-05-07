package gov.iti.jets.api.category.model;


import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@XmlRootElement
public class CategoryModel {
    private Integer id;
    private String name;
}

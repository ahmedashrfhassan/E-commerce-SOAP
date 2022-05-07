package gov.iti.jets.service;

import gov.iti.jets.api.category.mapper.CategoryMapper;
import gov.iti.jets.api.category.model.CategoryModel;
import gov.iti.jets.api.exceptions.MyNotFoundException;
import gov.iti.jets.persistance.entity.Category;
import gov.iti.jets.persistance.repo.CategoryRepository;
import gov.iti.jets.persistance.repo.impl.CategoryRepositoryImpl;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

//@WebService
public class CategoryService {
    private CategoryRepository categoryRepository = CategoryRepositoryImpl.getInstance();

    public CategoryModel findById(int id) {

        return CategoryMapper.INSTANCE.entityToModel(getCategory(id));
    }
    private Category getCategory(int id){
        Category category=categoryRepository.findById(id);
        if (category == null) {
            throw new MyNotFoundException("category not found");
        }
        return category;
    }

    public List<CategoryModel> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryMapper.INSTANCE.entitiesToModels(categories);
    }
//    @WebMethod(exclude = true)
    public boolean save(CategoryModel categoryModel) {
        Category category=CategoryMapper.INSTANCE.modelToEntity(categoryModel);
        category.setId(0);
        category=categoryRepository.save(category);
        return true;
    }

    public boolean delete(int id) {
        Category category=getCategory(id);
        return categoryRepository.delete(category);
    }
//    @WebMethod(exclude = true)
    public boolean update() {
        return false;
    }
}

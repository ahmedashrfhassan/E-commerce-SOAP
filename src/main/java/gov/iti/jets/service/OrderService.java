package gov.iti.jets.service;

import gov.iti.jets.api.exceptions.IllegalInputException;
import gov.iti.jets.api.exceptions.MyNotFoundException;
import gov.iti.jets.api.order.mappers.OrderMapper;
import gov.iti.jets.api.order.models.OrderModel;
import gov.iti.jets.api.order.models.CartItemModel;
import gov.iti.jets.persistance.entity.LineItem;
import gov.iti.jets.persistance.entity.Order;
import gov.iti.jets.persistance.entity.Product;
import gov.iti.jets.persistance.entity.User;
import gov.iti.jets.persistance.repo.OrderRepository;
import gov.iti.jets.persistance.repo.ProductRepository;
import gov.iti.jets.persistance.repo.UserRepo;
import gov.iti.jets.persistance.repo.impl.OrderRepositoryImpl;
import gov.iti.jets.persistance.repo.impl.ProductRepositoryImpl;
import gov.iti.jets.persistance.repo.impl.UserRepoImpl;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService
public class OrderService {
    UserRepo userRepo = UserRepoImpl.getInstance();
    ProductRepository productRepository = ProductRepositoryImpl.getInstance();
    OrderRepository orderRepository = new OrderRepositoryImpl();

     
    public boolean createOrder(List<CartItemModel> cartItemModels, int userId) {
        checkInputsValidity(cartItemModels, userId);
        User user = userRepo.findById(userId);
        if (user == null) throw new MyNotFoundException("No users found with this Id");
        List<LineItem> lineItems = createLineItems(cartItemModels);
        Order order = new Order(lineItems,user);
        lineItems.forEach(lineItem -> lineItem.setOrder(order));
        orderRepository.save(order);
        return true;
    }

     
    public List<OrderModel> viewUserOrders(int id) {
        if (id <= 0) throw new IllegalInputException("zero and Negative numbers are not accepted for user id");
        User user = userRepo.findById(id);
        if (user == null) throw new MyNotFoundException("No users found with this Id");
        List<Order> orders = orderRepository.findUserOrders(id);
        List<OrderModel>orderModels= OrderMapper.INSTANCE.fromOrdersToOrderModels(orders);
        return orderModels;
    }

     
    public List<OrderModel> getAllOrders() {
        List<Order>orders = orderRepository.findAll();

        List<OrderModel> orderModels = OrderMapper.INSTANCE.fromOrdersToOrderModels(orders);
        return orderModels;
    }

    private List<LineItem> createLineItems(List<CartItemModel> cartItemModels) {
        List<LineItem> lineItems = new ArrayList<>();
        for(CartItemModel cartItemModel: cartItemModels){
            Product product = productRepository.findById(cartItemModel.getProductId());
            if(product!=null){
                LineItem lineItem = new LineItem(product.getName(), product.getPrice(), cartItemModel.getProductQuantity());
                lineItems.add(lineItem);
            }
        }
        if(lineItems.size()==0) throw new MyNotFoundException("No products matched the Id/Ids you entered");
        return lineItems;
    }

    private void checkInputsValidity(List<CartItemModel> cartItemModel, int userId) {
        if (userId <= 0) throw new IllegalInputException("zero and Negative numbers are not accepted for user id");
        if(cartItemModel.size()>0) cartItemModel = cartItemModel.stream()
                                                    .filter(cartItemModel1 -> cartItemModel1.getProductId()>0)
                                                    .filter(cartItemModel1 -> cartItemModel1.getProductQuantity()>0)
                                                    .collect(Collectors.toList());
        if(cartItemModel.size()<=0) throw new IllegalInputException("check the products Ids and quantities you entered");
    }
}

package service;

import dao.AddressDAO;
import dao.CityDAO;
import dao.CustomerDAO;
import dao.StoreDAO;
import dto.CustomerCreateDTO;
import entity.Address;
import entity.City;
import entity.Customer;
import entity.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class CustomerService {
    private final SessionFactory sessionFactory;

    private final CustomerDAO customerDAO;
    private final StoreDAO storeDAO;
    private final AddressDAO addressDAO;
    private final CityDAO cityDAO;

    public CustomerService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        customerDAO = new CustomerDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
    }

    public Customer createCustomer(CustomerCreateDTO customerCreateDTO, int storeId) {

        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Store store = storeDAO.getById(storeId);
            Address address;
            Optional<Address> addressByName = addressDAO.getAddressByName(customerCreateDTO.getAddress());
            if (!addressByName.isPresent()) {
                Optional<City> cityByName = cityDAO.getCityByName(customerCreateDTO.getCity());
                if (cityByName.isPresent()) {
                    address = Address.builder()
                            .address(customerCreateDTO.getAddress())
                            .address2(customerCreateDTO.getAddress2())
                            .district(customerCreateDTO.getDistrict())
                            .city(cityByName.get())
                            .postalCode(customerCreateDTO.getPostalCode())
                            .phone(customerCreateDTO.getPhone())
                            .build();
                } else {
                    throw new NullPointerException("Address is invalid");
                }
            } else
                address = addressByName.get();

            Customer customer = Customer.builder()
                    .store(store)
                    .firstName(customerCreateDTO.getFirstName())
                    .lastName(customerCreateDTO.getLastName())
                    .email(customerCreateDTO.getEmail())
                    .address(address)
                    .isActive(true)
                    .build();
            session.persist(customer);
            transaction.commit();
            return customer;
        }
    }
}

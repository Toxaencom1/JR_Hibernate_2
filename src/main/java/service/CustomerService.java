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

public class CustomerService {
    private SessionFactory sessionFactory;

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

    public Customer createCustomer(CustomerCreateDTO customerCreateDTO, Store store) {

        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Address address;
            City city;
            address = addressDAO.getAddressByName(customerCreateDTO.getAddress());
            city = cityDAO.getCityByName(customerCreateDTO.getCity());
            if (address == null || city == null) {
                address = Address.builder()
                        .address(customerCreateDTO.getAddress())
                        .address2(customerCreateDTO.getAddress2())
                        .district(customerCreateDTO.getDistrict())
                        .city(city)
                        .postalCode(customerCreateDTO.getPostalCode())
                        .phone(customerCreateDTO.getPhone())
                        .build();
                session.persist(address);
            }
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

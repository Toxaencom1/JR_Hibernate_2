package service;

import dao.*;
import dto.CustomerCreateDTO;
import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.Optional;

public class CustomerService {
    private final SessionFactory sessionFactory;

    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    public CustomerService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
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

    public Rental filmReturning(int rentalId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Rental rental = rentalDAO.getById(rentalId);
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.update(rental);
            transaction.commit();
            return rental;
        }
    }
}

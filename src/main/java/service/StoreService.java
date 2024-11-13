package service;

import dao.*;
import dto.CustomerCreateDTO;
import dto.FilmCreateDTO;
import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoreService {
    private final SessionFactory sessionFactory;

    private final AddressDAO addressDAO;
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
    private final ActorDAO actorDAO;
    private final CategoryDAO categoryDAO;

    public StoreService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        addressDAO = new AddressDAO(sessionFactory);

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

        actorDAO = new ActorDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
    }

    public Customer createCustomer(CustomerCreateDTO customerCreateDTO, int storeId) {

        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Store store = storeDAO.findById(storeId);
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

            Rental rental = rentalDAO.findById(rentalId);
            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.update(rental);
            transaction.commit();
            return rental;
        }
    }

    public Rental rentTheMovie(int customerId, int filmId, int staffId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Customer customer = customerDAO.findById(customerId);

            Inventory inventory = inventoryDAO.pickAvailableInventoryMovie(filmId);

            Staff staff = staffDAO.findById(staffId);

            if (customer != null && inventory != null && staff != null) {
                Rental rental = Rental.builder()
                        .rentalDate(LocalDateTime.now())
                        .inventory(inventory)
                        .customer(customer)
                        .staff(staff)
                        .build();
                Payment payment = Payment.builder()
                        .customer(customer)
                        .staff(staff)
                        .rental(rental)
                        .amount(BigDecimal.valueOf(0.99))
                        .paymentDate(LocalDateTime.now())
                        .build();
                paymentDAO.save(payment);
                transaction.commit();
                return rental;
            } else
                throw new NullPointerException((inventory == null) ? "Film not available." : "Customer or Inventory or Staff is null");
        }
    }

    public List<Inventory> addNewFilm(int count, FilmCreateDTO filmDTO, List<Actor> actorList, List<Category> categories, int storeId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Language language = languageDAO.findById(filmDTO.getLanguageId());
            Language originalLanguage = languageDAO.findById(filmDTO.getOriginalLanguageId());
            Store store = storeDAO.findById(storeId);

            Film film = Film.builder()
                    .title(filmDTO.getTitle())
                    .description(filmDTO.getDescription())
                    .releaseYear(filmDTO.getReleaseYear())
                    .language(language)
                    .originalLanguage(originalLanguage)
                    .rentalDuration(filmDTO.getRentalDuration())
                    .rentalRate(filmDTO.getRentalRate())
                    .length(filmDTO.getLength())
                    .replacementCost(filmDTO.getReplacementCost())
                    .rating(filmDTO.getRating())
                    .specialFeatures(filmDTO.getSpecialFeatures())
                    .actors(actorList)
                    .categories(categories)
                    .build();

            List<Inventory> inventoryList = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Inventory inventory = Inventory.builder()
                        .film(film)
                        .store(store)
                        .build();
                inventoryList.add(inventory);
            }
            List<Inventory> savedList = inventoryDAO.save(inventoryList);
            transaction.commit();
            return savedList;
        }
    }

    public Actor findActorByNameAndSurname(String name, String surname) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Actor actor = actorDAO.findActorByNameAndSurname(name, surname);
            transaction.commit();
            return actor;
        }
    }

    public Category findCategoryByName(String categoryName) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Category category = categoryDAO.findByName(categoryName);
            transaction.commit();
            return category;
        }
    }
}

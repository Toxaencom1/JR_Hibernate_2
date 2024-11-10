import config.DataBaseConfig;
import dao.*;
import org.hibernate.SessionFactory;


public class Main {
    SessionFactory sessionFactory;
    DataBaseConfig config;

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

    public Main(){
        config = new DataBaseConfig();
        sessionFactory = config.buildSessionFactory();

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

    public static void main(String[] args) {
        Main app = new Main();
//        City city = app.cityDAO.getById(343);
//        Store store = app.storeDAO.getById(1);
//        Address address = Address.builder()
//                .address("Chehova")
//                .city(city)
//                .district("Center")
//                .postalCode("12345")
//                .phone("+77071561900")
//                .build();
//        app.addressDAO.save(address);
//        Customer customer = Customer.builder()
//                .store(store)
//                .firstName("John")
//                .lastName("Doe")
//                .email("john@doe.com")
//                .address(address)
//                .isActive(true)
//                .build();
//        Customer customer1 = app.customerDAO.save(customer);
//        Customer customer1 = app.customerDAO.getById(600);
//        System.out.println(customer1);
    }
}

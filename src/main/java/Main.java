import config.DataBaseConfig;
import org.hibernate.SessionFactory;
import service.StoreService;


public class Main {
    DataBaseConfig config;
    SessionFactory sessionFactory;

    private final StoreService storeService;

    public Main() {
        this.config = new DataBaseConfig();
        this.sessionFactory = config.buildSessionFactory();
        storeService = new StoreService(sessionFactory);
    }

    public static void main(String[] args) {
        Main app = new Main();

//        CustomerCreateDTO customerDTO = CustomerCreateDTO.builder()
//                .firstName("John")
//                .lastName("Doe")
//                .email("John@mail.ru")
//                .address("Behtereva 15")
//                .address2("flat 2")
//                .phone("+77771234567")
//                .district("Central")
//                .city("Moscow")
//                .postalCode("12345")
//                .build();
//        Customer customer = app.storeService.createCustomer(customerDTO, 1);
//        System.out.println(customer);

//        Rental returned = app.storeService.filmReturning(16061);
//        System.out.println(returned.getReturnDate());

//        Rental rental = app.storeService.rentTheMovie(606,1001,2);
//        System.out.println(rental.getRentalId());
//        System.out.println(rental.getReturnDate());


//        Actor actor1 = app.storeService.findActorByNameAndSurname("Matt", "Smith");
//        Actor actor2 = app.storeService.findActorByNameAndSurname("Jenna", "Coleman");
//        List<Actor> actorList = List.of(actor1, actor2);
//
//
//        Category category1 = app.storeService.findCategoryByName("Action");
//        Category category2 = app.storeService.findCategoryByName("Sci-Fi");
//        Category category3 = app.storeService.findCategoryByName("Drama");
//        List<Category> categoryList = List.of(category1, category2, category3);
//
//
//        FilmCreateDTO filmDTO = FilmCreateDTO.builder()
//                .title("Doctor Who: The Snowmen")
//                .description("The Doctor, who has lost faith in himself, meets Clara in Victorian London.")
//                .releaseYear(Year.of(2012))
//                .languageId(7)
//                .originalLanguageId(1)
//                .rentalDuration((byte) 3)
//                .rentalRate(BigDecimal.valueOf(2.99))
//                .length((short) 60)
//                .replacementCost(BigDecimal.valueOf(15.99))
//                .rating(Rating.PG)
//                .specialFeatures("Trailers,Behind the Scenes")
//                .build();
//        List<Inventory> inventoryList = app.storeService.addNewFilm(3, filmDTO, actorList, categoryList, 1);
//        System.out.println(inventoryList.size());
    }
}

package dto;

import enums.Rating;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Year;

@Data
@Builder
public class FilmCreateDTO {

    private String title;

    private String description;

    private Year releaseYear;

    private Integer languageId;

    private Integer originalLanguageId;

    private Byte rentalDuration;

    private BigDecimal rentalRate;

    private Short length;

    private BigDecimal replacementCost;

    private Rating rating;

    private String specialFeatures;

}

package enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute != null ? attribute.getValue() : null;  // Используем getValue() для записи строки
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        for (Rating value : Rating.values()) {
            if (value.getValue().equals(dbData)) {
                return value;
            }
        }
        return null;
    }
}

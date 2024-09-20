package app.goog1e.chyunyea.enumtype.converter;

import app.goog1e.chyunyea.enumtype.CalendarTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * 數據庫枚舉轉換器(日曆類型)
 */
@Converter
public class CalendarTypeEnumConverter implements AttributeConverter<CalendarTypeEnum, String> {

	@Override
	public String convertToDatabaseColumn(CalendarTypeEnum attribute) {
		return null == attribute ? null : attribute.name();
	}

	@Override
	public CalendarTypeEnum convertToEntityAttribute(String dbData) {
		return null == dbData || dbData.isBlank() ? null : CalendarTypeEnum.valueOf(dbData);
	}
}

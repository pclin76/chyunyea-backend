package app.goog1e.chyunyea.enumtype.converter;

import app.goog1e.chyunyea.enumtype.ShiftChangeTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * 數據庫枚舉轉換器(換值類型)
 */
@Converter
public class ShiftChangeTypeEnumConverter implements AttributeConverter<ShiftChangeTypeEnum, String> {

	@Override
	public String convertToDatabaseColumn(ShiftChangeTypeEnum attribute) {
		return null == attribute ? null : attribute.name();
	}

	@Override
	public ShiftChangeTypeEnum convertToEntityAttribute(String dbData) {
		return null == dbData || dbData.isBlank() ? null : ShiftChangeTypeEnum.valueOf(dbData);
	}
}

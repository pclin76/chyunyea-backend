package app.goog1e.chyunyea.enumtype;

import app.goog1e.chyunyea.enumtype.serializer.CalendarTypeEnumSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

/**
 * 枚舉(日曆類型)
 */
@Getter
@JsonSerialize(using = CalendarTypeEnumSerializer.class)
public enum CalendarTypeEnum {

	/**
	 * 上課
	 */
	SCHOOL("上課"),

	/**
	 * 假日
	 */
	HOLIDAY("假日"),

	/**
	 * 停課
	 */
	SUSPENDED("停課"),

	/**
	 * 補課
	 */
	MAKEUP("上課");

	/**
	 * 中文
	 */
	private final String chinese;

	/**
	 * @param chinese 中文
	 */
	CalendarTypeEnum(String chinese) {
		this.chinese = chinese;
	}
}

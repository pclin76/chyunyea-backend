package app.goog1e.chyunyea.enumtype;

import app.goog1e.chyunyea.enumtype.serializer.ShiftChangeTypeEnumSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

/**
 * 枚舉(換值類型)
 */
@Getter
@JsonSerialize(using = ShiftChangeTypeEnumSerializer.class)
public enum ShiftChangeTypeEnum {

	/**
	 * 主動
	 */
	ACTIVE("主動"),

	/**
	 * 補課
	 */
	PASSIVE("被動");

	/**
	 * 中文
	 */
	private final String chinese;

	/**
	 * @param chinese 中文
	 */
	ShiftChangeTypeEnum(String chinese) {
		this.chinese = chinese;
	}
}

package app.goog1e.chyunyea.enumtype.serializer;

import app.goog1e.chyunyea.enumtype.CalendarTypeEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.Serial;

/**
 * 枚舉序列器(休假類型)
 */
public class CalendarTypeEnumSerializer extends StdSerializer<CalendarTypeEnum> {

	@Serial
	private static final long serialVersionUID = 3851160379626107231L;

	/**
	 * 默認構造函式
	 */
	public CalendarTypeEnumSerializer() {
		this(null);
	}

	/**
	 * @param type 枚舉
	 */
	public CalendarTypeEnumSerializer(Class<CalendarTypeEnum> type) {
		super(type);
	}

	/**
	 * @param typeEnum 枚舉
	 * @param jsonGenerator 杰森產生器
	 * @param serializerProvider 序列化提供者
	 * @throws IOException 輸入輸出操作發生失敗或中斷
	 */
	@Override
	public void serialize(CalendarTypeEnum typeEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeStringField(
			"value",
			typeEnum.name()
		);

		jsonGenerator.writeStringField(
			"chinese",
			typeEnum.getChinese()
		);

		jsonGenerator.writeEndObject();
	}
}

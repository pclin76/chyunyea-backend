package app.goog1e.chyunyea.enumtype.serializer;

import app.goog1e.chyunyea.enumtype.ShiftChangeTypeEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.Serial;

/**
 * 枚舉序列器(換值類型)
 */
public class ShiftChangeTypeEnumSerializer extends StdSerializer<ShiftChangeTypeEnum> {

	@Serial
	private static final long serialVersionUID = 7170359836993369725L;

	/**
	 * 默認構造函式
	 */
	public ShiftChangeTypeEnumSerializer() {
		this(null);
	}

	/**
	 * @param type 枚舉
	 */
	public ShiftChangeTypeEnumSerializer(Class<ShiftChangeTypeEnum> type) {
		super(type);
	}

	/**
	 * @param typeEnum 枚舉
	 * @param jsonGenerator 杰森產生器
	 * @param serializerProvider 序列化提供者
	 * @throws IOException 輸入輸出操作發生失敗或中斷
	 */
	@Override
	public void serialize(ShiftChangeTypeEnum typeEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
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

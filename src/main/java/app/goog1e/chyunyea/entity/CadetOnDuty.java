package app.goog1e.chyunyea.entity;

import app.goog1e.chyunyea.enumtype.ShiftChangeTypeEnum;
import app.goog1e.chyunyea.enumtype.converter.ShiftChangeTypeEnumConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 實體類(值日生)
 */
@Data
@Entity
@NoArgsConstructor
@Table(
	schema = "public",
	name = "zhi_ri_sheng",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = {
			"xing_shi_li",
			"xue_yuan"
		})
	}
)
public class CadetOnDuty implements Serializable {

	@Serial
	private static final long serialVersionUID = 7395151810258096336L;

	/**
	 * 主鍵(雪花)
	 */
	@Basic(optional = false)
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long id;

	/**
	 * 樂觀鎖
	 */
	//@Column(name = "le_guan_suo")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Version
	private Long optimisticLocking;

	/**
	 * 換值類型
	 */
	@Column(name = "huan_zhi_lei_xing")
	@Convert(converter = ShiftChangeTypeEnumConverter.class)
	@Enumerated(EnumType.STRING)
	private ShiftChangeTypeEnum shiftChangeType;

	/**
	 * 行事曆
	 */
	@JoinColumn(
		name = "xing_shi_li",
		nullable = false,
		referencedColumnName = "id"
	)
	@ManyToOne(optional = false)
	@JsonBackReference
	private CourseCalendar courseCalendar;

	/**
	 * 學員
	 */
	@JoinColumn(
		name = "xue_yuan",
		nullable = false,
		referencedColumnName = "id"
	)
	@ManyToOne(optional = false)
	@JsonBackReference
	private Cadet cadet;

	/**
	 * @param courseCalendar 行事曆
	 * @param cadet 學員
	 */
	public CadetOnDuty(CourseCalendar courseCalendar, Cadet cadet) {
		this.courseCalendar = courseCalendar;
		this.cadet = cadet;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CadetOnDuty other)) {
			return false;
		}
		return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
	}

	@Override
	public String toString() {
		try {
			return new JsonMapper().writeValueAsString(this);
		} catch (JsonProcessingException ignore) {
			return null == id ? "null" : id.toString();
		}
	}
}

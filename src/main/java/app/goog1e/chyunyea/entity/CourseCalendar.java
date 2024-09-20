package app.goog1e.chyunyea.entity;

import app.goog1e.chyunyea.enumtype.CalendarTypeEnum;
import app.goog1e.chyunyea.enumtype.converter.CalendarTypeEnumConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * 實體類(行事曆)
 */
@Data
@Entity
@NoArgsConstructor
@Table(
	schema = "public",
	name = "xing_shi_li"
)
public class CourseCalendar implements Serializable {

	@Serial
	private static final long serialVersionUID = 1637643643717864652L;

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
	 * 日曆類型
	 */
	@Basic(optional = false)
	@Column(
		name = "ri_li_lei_xing",
		nullable = false
	)
	@Convert(converter = CalendarTypeEnumConverter.class)
	@Enumerated(EnumType.STRING)
	@NotNull
	private CalendarTypeEnum calendarType;

	/**
	 * 事件
	 */
	@Basic(optional = false)
	@Column(
		name = "shi_jian",
		nullable = false
	)
	@NotNull
	private String event;

	/**
	 * 春節
	 */
	@Basic(optional = false)
	@Column(
		name = "chun_jie",
		nullable = false
	)
	@NotNull
	private boolean springFestival = false;

	/**
	 * 開始
	 */
	@Basic(optional = false)
	@Column(
		name = "kai_shi",
		nullable = false
	)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date since;

	/**
	 * 結束
	 */
	@Basic(optional = false)
	@Column(
		name = "jie_shu",
		nullable = false
	)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date until;

	/**
	 * 值日生們
	 */
	@OneToMany(
		cascade = CascadeType.ALL,
		mappedBy = "courseCalendar"
	)
	@JsonManagedReference
	private Collection<CadetOnDuty> cadetsOnDuty;

	/**
	 * @param calendarType 日曆類型
	 * @param event 事件
	 * @param springFestival 春節
	 * @param since 開始
	 * @param until 結束
	 */
	public CourseCalendar(CalendarTypeEnum calendarType, String event, boolean springFestival, Date since, Date until) {
		this.calendarType = calendarType;
		this.event = event;
		this.springFestival = springFestival;
		this.since = since;
		this.until = until;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof CourseCalendar other)) {
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

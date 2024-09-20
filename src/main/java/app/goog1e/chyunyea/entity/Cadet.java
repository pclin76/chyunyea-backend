package app.goog1e.chyunyea.entity;

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
 * 實體類(學員)
 */
@Data
@Entity
@NoArgsConstructor
@Table(
	schema = "public",
	name = "xue_yuan",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = {"line_id"}),
		@UniqueConstraint(columnNames = {"zuo_hao"})
	}
)
public class Cadet implements Serializable {

	@Serial
	private static final long serialVersionUID = 123930565776740895L;

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
	 * 時戳
	 */
	@Basic(optional = false)
	@Column(
		name = "shi_chuo",
		nullable = false
	)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date occurredAt = new Date(
		System.currentTimeMillis()
	);

	/**
	 * LINE 用戶 ID
	 */
	@Basic(optional = false)
	@Column(
		length = 33,
		name = "line_id",
		nullable = false
	)
	@NotNull
	private String lineId;

	/**
	 * 座號
	 */
	@Basic(optional = false)
	@Column(
		name = "zuo_hao",
		nullable = false
	)
	@NotNull
	private short seatNumber;

	/**
	 * 離訓日
	 */
	@Column(name = "li_xun")
	@Temporal(TemporalType.DATE)
	private Date droppedOutOn;

	/**
	 * 值日們
	 */
	@OneToMany(
		cascade = CascadeType.ALL,
		mappedBy = "cadet"
	)
	@JsonManagedReference
	private Collection<CadetOnDuty> cadetsOnDuty;

	/**
	 * @param lineId LINE 用戶 ID
	 */
	public Cadet(String lineId) {
		this.lineId = lineId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Cadet other)) {
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

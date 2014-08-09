package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.collections.CollectionUtils;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
public class Message extends Model {

	@Id
	public Long id;

	@Required
	public String name;

	@MinLength(10)
	@MaxLength(200)
	public String message;

	@CreatedTimestamp
	public Date postDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Member member;

	@Override
	public String toString() {
		return "Message [id=" + id + ", name=" + name + ", message=" + message
				+ ", postDate=" + postDate + ", member=" + member + "]";
	}

	public static Finder<Long, Message> find = new Finder<Long, Message>(
			Long.class, Message.class);

	public static Message findByName(String name) {
		List<Message> datas = find.where().eq("name", name).findList();
		if (CollectionUtils.isNotEmpty(datas)) {
			return datas.get(0);
		} else {
			return null;
		}
	}
}

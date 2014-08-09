package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.collections.CollectionUtils;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Member extends Model {

	@Id
	public Long id;

	@Required
	public String name;

	@Email
	public String mail;

	public String tel;

	@OneToMany(cascade = CascadeType.ALL)
	public List<Message> messages = new ArrayList<Message>();
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", mail=" + mail
				+ ", tel=" + tel + ", messages=" + messages + "]";
	}

	public static Finder<Long, Member> find = new Finder<Long, Member>(
			Long.class, Member.class);

	public static Member findByName(String name) {
		List<Member> datas = find.where().eq("name", name).findList();
		if (CollectionUtils.isNotEmpty(datas)) {
			return datas.get(0);
		} else {
			return null;
		}
	}
}

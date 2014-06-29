package sample.action;

import java.util.List;

import javax.annotation.Resource;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

import sample.entity.User;
import sample.form.UserForm;
import sample.service.UserService;

public class UserAction {

	public List<User> userItems;

	@ActionForm
	@Resource
	protected UserForm userForm;

	@Resource
	protected UserService userService;

	@Execute(validator = false)
	public String index() {
		userItems = userService.findAll();

		return "list.jsp";
	}

	@Execute(validator = false, urlPattern = "show/{id}")
	public String show() {
		User entity = userService.findById(Integer.valueOf(userForm.id));
		Beans.copy(entity, userForm).dateConverter("yyyy-MM-dd").execute();
		return "show.jsp";
	}

	@Execute(validator = false, urlPattern = "edit/{id}")
	public String edit() {
		User entity = userService.findById(Integer.valueOf(userForm.id));
		Beans.copy(entity, userForm).dateConverter("yyyy-MM-dd").execute();
		return "edit.jsp";
	}

	@Execute(validator = false)
	public String create() {
		return "create.jsp";
	}

	@Execute(validator = false, urlPattern = "delete/{id}", redirect = true)
	public String delete() {
		User entity = Beans.createAndCopy(User.class, userForm)
				.dateConverter("yyyy-MM-dd").execute();
		userService.delete(entity);
		return "/user/";
	}

	@Execute(input = "create.jsp", redirect = true)
	public String insert() {
		User entity = Beans.createAndCopy(User.class, userForm)
				.dateConverter("yyyy-MM-dd").execute();
		userService.insert(entity);
		return "/user/";
	}

	@Execute(input = "edit.jsp", redirect = true)
	public String update() {
		User entity = Beans.createAndCopy(User.class, userForm)
				.dateConverter("yyyy-MM-dd").execute();
		userService.update(entity);
		return "/user/";
	}
}
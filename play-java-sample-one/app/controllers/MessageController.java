package controllers;

import java.util.List;

import models.Message;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.message.add;
import views.html.message.delete;
import views.html.message.edit;
import views.html.message.find;
import views.html.message.item;

public class MessageController extends Controller {
	
	public static class FindForm {
		@Required
		public String condition;
	}

	public static Result index() {
		return redirect("/");
	}


	public static Result add() {
		Form<Message> f = new Form<Message>(Message.class);
		return ok(add.render("Submit Form", f));
	}

	public static Result create() {
		Form<Message> f = new Form<Message>(Message.class).bindFromRequest();
		if (!f.hasErrors()) {
			Message data = f.get();
			data.save();
			return redirect("/");
		} else {
			return badRequest(add.render("ERROR", f));
		}
	}

	public static Result setItem() {
		Form<Message> f = new Form<Message>(Message.class);
		return ok(item.render("Input Edit Id", f));
	}

	public static Result edit() {
		Form<Message> f = new Form<Message>(Message.class).bindFromRequest();
		if (!f.hasErrors()) {
			Message data = Message.find.byId(f.get().id);
			if (data != null) {
				f = new Form<Message>(Message.class).fill(data);
				return ok(edit.render("Id = " + data.id, f));
			} else {
				return ok(item.render(
						"Error:data is not found. Id = " + f.get().id, f));
			}
		} else {
			return badRequest(item.render("Error", f));
		}
	}

	public static Result update() {
		Form<Message> f = new Form<Message>(Message.class).bindFromRequest();
		if (!f.hasErrors()) {
			f.get().update();
			return redirect("/");
		} else {
			return badRequest(edit.render("Error", f));
		}
	}

	public static Result delete() {
		Form<Message> f = new Form<Message>(Message.class);
		return ok(delete.render("Input Delete Id", f));
	}

	public static Result remove() {
		Form<Message> f = new Form<Message>(Message.class).bindFromRequest();
		if (!f.hasErrors()) {
			Message data = Message.find.byId(f.get().id);
			if (data != null) {
				data.delete();
				return redirect("/");
			} else {
				return ok(delete.render(
						"Error:data is not found. Id = " + f.get().id, f));
			}
		} else {
			return ok(delete.render("Error", f));

		}
	}

	public static Result find() {
		Form<FindForm> f = new Form<FindForm>(FindForm.class).bindFromRequest();
		List<Message> datas = null;
		if (!f.hasErrors()) {
			datas = Message.find.where()
					.like("name", "%" + f.get().condition + "%")
					.orderBy("name").findPagingList(10).getPage(0).getList();
		}
		return ok(find.render("Search Data", f, datas));
	}
}

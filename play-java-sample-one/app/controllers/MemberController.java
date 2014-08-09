package controllers;

import java.util.List;

import models.Member;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.member.add;
import views.html.member.delete;
import views.html.member.edit;
import views.html.member.item;
import views.html.member.find;

public class MemberController extends Controller {

	public static class FindForm {
		@Required
		public String condition;
	}

	public static Result index() {
		return redirect("/");
	}

	public static Result add() {
		Form<Member> f = new Form<Member>(Member.class);
		return ok(add.render("Submit Form", f));
	}

	public static Result create() {
		Form<Member> f = new Form<Member>(Member.class).bindFromRequest();
		if (!f.hasErrors()) {
			Member data = f.get();
			data.save();
			return redirect("/");
		} else {
			return badRequest(add.render("ERROR", f));
		}
	}

	public static Result setItem() {
		Form<Member> f = new Form<Member>(Member.class);
		return ok(item.render("Input Edit Id", f));
	}

	public static Result edit() {
		Form<Member> f = new Form<Member>(Member.class).bindFromRequest();
		if (!f.hasErrors()) {
			Member data = Member.find.byId(f.get().id);
			if (data != null) {
				f = new Form<Member>(Member.class).fill(data);
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
		Form<Member> f = new Form<Member>(Member.class).bindFromRequest();
		if (!f.hasErrors()) {
			f.get().update();
			return redirect("/");
		} else {
			return badRequest(edit.render("Error", f));
		}
	}

	public static Result delete() {
		Form<Member> f = new Form<Member>(Member.class);
		return ok(delete.render("Input Delete Id", f));
	}

	public static Result remove() {
		Form<Member> f = new Form<Member>(Member.class).bindFromRequest();
		if (!f.hasErrors()) {
			Member data = Member.find.byId(f.get().id);
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
		List<Member> datas = null;
		if (!f.hasErrors()) {
			datas = Member.find.where()
					.like("name", "%" + f.get().condition + "%")
					.orderBy("name").findPagingList(10).getPage(0).getList();
		}
		return ok(find.render("Search Data", f, datas));
	}
}

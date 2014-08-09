package controllers;

import models.Member;
import models.Message;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {


	public static Result index() {
		return ok(index.render("data", Message.find.all(), Member.find.all()));
	}
}

package requests.pages;

import core.Request;
import requests.annotations.JSP;
import requests.annotations.RequestName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestName("processes")
@JSP("processes.jsp")
public class Processes extends Request {

    public Processes() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}

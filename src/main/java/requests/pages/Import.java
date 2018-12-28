package requests.pages;

import core.Request;
import org.apache.log4j.Logger;
import requests.annotations.JSP;
import requests.annotations.RequestName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestName("import")
@JSP("import.jsp")
public class Import extends Request {
    private static Logger log = Logger.getLogger(Import.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
//        log.info("Getting the import");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
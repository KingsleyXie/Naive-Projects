import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import Model.Bean;

public class Controller extends HttpServlet {
	protected void doPost(
		HttpServletRequest req,
		HttpServletResponse res
	) throws ServletException, IOException {
		if (req.getParameter("operation").equals("insert")) {
			req.setCharacterEncoding("UTF-8");
			res.setCharacterEncoding("UTF-8");

			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document config = db.parse(new File(
					req.getServletContext().getRealPath("/WEB-INF/config.xml")
				));

				Bean bean = new Bean();
				String result = bean.insert(
					config.getElementsByTagName("database").item(0).getTextContent(),
					config.getElementsByTagName("username").item(0).getTextContent(),
					config.getElementsByTagName("password").item(0).getTextContent(),

					new String(
						req.getParameter("nickname").getBytes("ISO-8859-1"), "UTF-8"
					),
					new String(
						req.getParameter("message").getBytes("ISO-8859-1"), "UTF-8"
					)
				);

				if (result.equals("OK")) {
					res.sendRedirect("./result.jsp");
				} else {
					req.setAttribute("fail", result);
					getServletConfig().getServletContext()
					.getRequestDispatcher("/result.jsp").forward(req, res);
				}
			} catch (Exception e) {
				req.setAttribute("fail", e.toString());
				getServletConfig().getServletContext()
				.getRequestDispatcher("/result.jsp").forward(req, res);
			}
		}
	}

	protected void doGet(
		HttpServletRequest req,
		HttpServletResponse res
	) throws ServletException, IOException {
		if (req.getParameter("operation").equals("list")) {
			req.setCharacterEncoding("UTF-8");
			res.setCharacterEncoding("UTF-8");

			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document config = db.parse(new File(
					req.getServletContext().getRealPath("/WEB-INF/config.xml")
				));
				Bean bean = new Bean();
				String result = bean.getAllMsg(
					config.getElementsByTagName("database").item(0).getTextContent(),
					config.getElementsByTagName("username").item(0).getTextContent(),
					config.getElementsByTagName("password").item(0).getTextContent()
				);

				if (result.equals("OK")) {
					req.setAttribute("messages", bean.messages);
					getServletConfig().getServletContext()
					.getRequestDispatcher("/messages.jsp").forward(req, res);
				} else {
					req.setAttribute("fail", result);
					getServletConfig().getServletContext()
					.getRequestDispatcher("/result.jsp").forward(req, res);
				}
			} catch (Exception e) {
				req.setAttribute("fail", e.toString());
				getServletConfig().getServletContext()
				.getRequestDispatcher("/result.jsp").forward(req, res);
			}
		}
	}
}

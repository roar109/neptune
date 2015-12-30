package org.rage.neptune.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rage.neptune.delegate.OwnerDataDelegate;
import org.rage.neptune.dto.OwnerDTO;
import org.rage.neptune.utils.DataValidateHelper;

@WebServlet("/auth")
public class AuthenticationServlet extends HttpServlet {

	private static final long serialVersionUID = 2974218995208229517L;

	@Inject
	private OwnerDataDelegate ownerDataDelegate;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String inputEmail = request.getParameter("inputEmail");

		if (validateParameterEmail(inputEmail)) {
			final OwnerDTO owner = ownerDataDelegate.getOwnerByEmail(inputEmail);

			if (owner != null) {
				request.getSession().setAttribute("email", owner.getEmail());
				request.getSession().setAttribute("ownerid", owner.getId());
				request.getSession().setAttribute("firstname", owner.getFirstName());
				request.getSession().setAttribute("lastname", owner.getLastName());
				setRedirectToHome(response);
				System.out.println("User found");
			} else {
				System.out.println("User not found");
				setRedirectToLogin(response);
			}

		} else {
			System.out.println("User not found");
			setRedirectToLogin(response);
		}
	}

	private boolean validateParameterEmail(String email) throws IOException {
		try {
			DataValidateHelper.validateInputData(email);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	private void setRedirectToLogin(HttpServletResponse response) throws IOException {
		response.sendRedirect("index.jsp");
	}

	private void setRedirectToHome(HttpServletResponse response) throws IOException {
		response.sendRedirect("home.jsp");
	}
}

package com.remal.fips;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FIPS validator servlet.
 *
 * <p>Copyright 2021 Arnold Somogyi</p>
 *
 * @author arnold.somogyi@gmail.com
 */
@WebServlet(value = "/")
public class FipsValidator extends HttpServlet {

    /**
     * Called by the server (via the service method) to allow a servlet to handle a GET request.
     *
     * @param request object that contains the request the client has made of the servlet
     * @param response object that contains the response the servlet sends to the client
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        List<String> providers = getSecurityProviders();
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head><title>FIPS Checker</title></head");
            out.println("<body>");
            out.print("<h1>FIPS Checker</h1>");
            out.print(generateProviderReport(providers));

            if (isJsafeJceInstalled(providers) && isRsaJsseInstalled(providers)) {
                out.print("<p style='color:green; font-weight: bold;'>FIPS 140-2 Mode is enabled</p>");
            } else {
                out.print("<p style='color:red; font-weight: bold;'>FIPS 140-2 Mode is disabled</p>");
            }

            out.print("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check whether the JSA JCE security provider is loaded or not.
     *
     * @param providers the list of the available security providers
     * @return true if JSA JCE security provider is loaded
     */
    private boolean isJsafeJceInstalled(List<String> providers) {
        for (String provider : providers) {
            if (provider.toLowerCase().contains("jsafejce")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the RSA JSSE security provider is loaded or not.
     *
     * @param providers the list of the available security providers
     * @return true if RAS JSSE security provider is loaded
     */
    private boolean isRsaJsseInstalled(List<String> providers) {
        for (String provider : providers) {
            if (provider.toLowerCase().contains("rsajsse")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generates a HTML formatted security provider list.
     *
     * @param providers the list of the available security providers
     * @return the HTML formatted security provider list
     */
    private String generateProviderReport(List<String> providers) {
        StringBuilder report = new StringBuilder();
        report.append("<h4>Supported Java Security Providers:</h4>");
        report.append("<ul>");
        for (String provider : providers) {
            report.append("<li>").append(provider).append("</li>");
        }
        report.append("</ul>");

        return report.toString();
    }

    private List<String> getSecurityProviders() {
        List<String> providerNames = new ArrayList<>();
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            providerNames.add(provider.getName());
        }

        return providerNames;
    }
}

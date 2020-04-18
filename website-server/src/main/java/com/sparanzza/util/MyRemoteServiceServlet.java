package com.sparanzza.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.google.gwt.user.server.rpc.SerializationPolicyLoader;

public class MyRemoteServiceServlet extends RemoteServiceServlet
{
    @Override
    protected SerializationPolicy doGetSerializationPolicy(HttpServletRequest request, String moduleBaseURL, String strongName)
    {
        return MyRemoteServiceServlet.loadSerializationPolicy(this, request, moduleBaseURL, strongName);
    }


    /**
     * Used by HybridServiceServlet.
     */
    static SerializationPolicy loadSerializationPolicy(HttpServlet servlet,
                                                       HttpServletRequest request, String moduleBaseURL, String strongName) {
        // The serialization policy path depends only by contraxt path
        String contextPath = request.getContextPath();

        SerializationPolicy serializationPolicy = null;


        String contextRelativePath = contextPath + "/";



        String serializationPolicyFilePath = SerializationPolicyLoader.getSerializationPolicyFileName(contextRelativePath
                + strongName);

        // Open the RPC resource file and read its contents.
        InputStream is = servlet.getServletContext().getResourceAsStream(
                serializationPolicyFilePath);
        try {
            if (is != null) {
                try {
                    serializationPolicy = SerializationPolicyLoader.loadFromStream(is,
                            null);
                } catch (ParseException e) {
                    servlet.log("ERROR: Failed to parse the policy file '"
                            + serializationPolicyFilePath + "'", e);
                } catch (IOException e) {
                    servlet.log("ERROR: Could not read the policy file '"
                            + serializationPolicyFilePath + "'", e);
                }
            } else {
                String message = "ERROR: The serialization policy file '"
                        + serializationPolicyFilePath
                        + "' was not found; did you forget to include it in this deployment?";
                servlet.log(message);
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // Ignore this error
                }
            }
        }

        return serializationPolicy;
    }
}
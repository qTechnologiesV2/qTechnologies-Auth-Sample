package dev.mdma.simpleauth;

import dev.mdma.simpleauth.utils.response.Response;

import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        String currentVersion = "1.0"; // Current application version for updating checking.
        String USER_ID = "2"; // This will be replaced on download with the downloading user's id
        String RESOURCE_ID = "5"; // This will be replaced on download with the resource id

        // Check license and fetch latest version string
        Response.getPostResponse("https://verify.mdma.dev/customer.php",
            String.format("userId=%s&resourceId=%s", USER_ID, RESOURCE_ID), (responseContainer) -> {
                // If response is null, server down?
                if (Objects.isNull(responseContainer)) {
                    showErrorMessage("Could not contact authentication server. Please try again.");
                    return;
                }

                // Compare currentVersion string with latest version string
                if (!currentVersion.equals(responseContainer.getVersion())) {
                    showInfoMessage(String.format("A new update is available. (%s)",
                        responseContainer.getVersion()));
                }

                String message = "Success";

                switch (responseContainer.getStatus()) {
                    case SUCCESS: {
                        //If request is successful and user is authenticated, handle your stuff
                        doStuff();
                        return;
                    }

                    case BANNED: {
                        message = "Please contact Support for further assistance.";
                        break;
                    }

                    case SERVER_ERROR: {
                        message = "We've had a server error! Please contact Support.";
                        break;
                    }

                    case INVALID_USERNAME: {
                        message = "Invalid username. Please contact Support.";
                        break;
                    }

                    case MISSING_LICENSE: {
                        message = "License missing or expired. Please contact Support.";
                        break;
                    }

                    case INCOMPLETE_REQUEST: {
                        message = "Incomplete request. Please contact Support.";
                        break;
                    }
                }

                showErrorMessage(message);
                System.exit(-1);
            });
    }

    private static void doStuff() {

    }

    private static void showErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    private static void showInfoMessage(String message) {
        System.out.println("[INFO] " + message);
    }
}

package org.recap.ils;

import com.ceridwen.circulation.SIP.exceptions.*;
import com.ceridwen.circulation.SIP.messages.*;
import com.ceridwen.circulation.SIP.transport.SocketConnection;
import com.ceridwen.circulation.SIP.types.enumerations.ProtocolVersion;
import com.ceridwen.circulation.SIP.types.flagfields.SupportedMessages;

/**
 * Created by saravanakumarp on 22/9/16.
 */
public abstract class ESIPConnector {

    public CheckOutResponse checkoutItem(String itemIdentifier, String patronIdentifier) {
        SocketConnection connection = getSocketConnection();
        if (connection == null) return null;

        /**
         * It is necessary to send a SC Status with protocol version 2.0 else
         * server will assume 1.0)
         */
        ACSStatus acsStatus = getAcsStatus(connection);
        if (acsStatus == null) {
            System.err.println("Error - Status Request did not return valid response from server.");
            return null;
        }

        if (!acsStatus.getSupportedMessages().isSet(SupportedMessages.LOGIN)) {
            System.out.println("Login service not supported");
            return null;
        }

        LoginResponse loginResponse = getLoginResponse(connection);
        if (!(loginResponse != null && loginResponse.isOk())) {
            System.out.println("Unable to login");
            return null;
        }

        if (!acsStatus.getSupportedMessages().isSet(SupportedMessages.CHECK_OUT)) {
            System.out.println("Check out not supported");
            return null;
        }

        CheckOut checkOut = new CheckOut();
        checkOut.setItemIdentifier(itemIdentifier);
        checkOut.setPatronIdentifier(patronIdentifier);

        CheckOutResponse checkOutResponse = (CheckOutResponse) getResponse(checkOut, connection);
        connection.disconnect();
        return checkOutResponse;
    }

    public ItemInformationResponse lookupItem(String itemIdentifier) {
        SocketConnection connection = getSocketConnection();
        if (connection == null) return null;

        ACSStatus acsStatus = getAcsStatus(connection);
        if (acsStatus == null) {
            System.err.println("Error - Status Request did not return valid response from server.");
            return null;
        }

        if (!acsStatus.getSupportedMessages().isSet(SupportedMessages.ITEM_INFORMATION)) {
            System.out.println("Item Information service not supported");
            return null;
        }

        ItemInformation itemInformation = new ItemInformation();
        itemInformation.setItemIdentifier(itemIdentifier);

        ItemInformationResponse itemInformationResponse = (ItemInformationResponse) getResponse(itemInformation, connection);
        connection.disconnect();
        return itemInformationResponse;
    }


    public PatronInformationResponse lookupUser(String patronIdentifier) {
        SocketConnection connection = getSocketConnection();
        if (connection == null) return null;

        ACSStatus acsStatus = getAcsStatus(connection);
        if (acsStatus == null) {
            System.err.println("Error - Status Request did not return valid response from server.");
            return null;
        }

        if (!acsStatus.getSupportedMessages().isSet(SupportedMessages.LOGIN)) {
            System.out.println("Login service not supported");
            return null;
        }

        LoginResponse loginResponse = getLoginResponse(connection);
        if (!(loginResponse != null && loginResponse.isOk())) {
            System.out.println("Unable to login");
            return null;
        }

        if (!acsStatus.getSupportedMessages().isSet(SupportedMessages.PATRON_INFORMATION)) {
            System.out.println("Patron Information service not supported");
            return null;
        }

        PatronInformation patronInformation = new PatronInformation();
        patronInformation.setPatronIdentifier(patronIdentifier);

        PatronInformationResponse patronInformationResponse = (PatronInformationResponse) getResponse(patronInformation, connection);
        connection.disconnect();
        return patronInformationResponse;
    }

    private ACSStatus getAcsStatus(SocketConnection connection) {
        SCStatus scStatus = new SCStatus();
        scStatus.setProtocolVersion(ProtocolVersion.VERSION_2_00);

        return (ACSStatus) getResponse(scStatus, connection);
    }

    private LoginResponse getLoginResponse(SocketConnection connection) {
        Login login = new Login();
        login.setLoginUserId(getOperatorUserId());
        login.setLoginPassword(getOperatorPassword());
        login.setLocationCode(getOperatorLocation());

        return (LoginResponse) getResponse(login, connection);
    }

    private Message getResponse(Message request, SocketConnection connection) {
        Message response;
        try {
            response = connection.send(request);
        } catch (RetriesExceeded e) {
            e.printStackTrace();
            return null;
        } catch (ConnectionFailure e) {
            e.printStackTrace();
            return null;
        } catch (MessageNotUnderstood e) {
            e.printStackTrace();
            return null;
        } catch (ChecksumError e) {
            e.printStackTrace();
            return null;
        } catch (SequenceError e) {
            e.printStackTrace();
            return null;
        } catch (MandatoryFieldOmitted e) {
            e.printStackTrace();
            return null;
        } catch (InvalidFieldLength e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }

    private SocketConnection getSocketConnection() {
        SocketConnection connection = new SocketConnection();

        connection.setHost(getHost());
        connection.setPort(7031);
        connection.setConnectionTimeout(30000);
        connection.setIdleTimeout(30000);
        connection.setRetryAttempts(2);
        connection.setRetryWait(500);

        try {
            connection.connect();
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
        return connection;
    }

    public abstract String getHost();

    public abstract String getOperatorUserId();

    public abstract String getOperatorPassword();

    public abstract String getOperatorLocation();

}


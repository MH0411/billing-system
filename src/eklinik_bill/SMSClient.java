package eklinik_bill;

import hu.ozeki.OzSMSMessage;
import hu.ozeki.OzSmsClient;
import java.io.IOException;
import java.util.Date;

public class SMSClient extends OzSmsClient {
    
    public SMSClient(String host, int port) throws IOException, InterruptedException {
        super(host, port);
    }

    @Override
    public void doOnMessageReceived(OzSMSMessage osmsm) {
        Date now = new Date();
        System.out.println(now.toString() + " Message received. Sender "
                + "address: " + osmsm.sender + " Message text: " + osmsm.messageData);
    }

    @Override
    public void doOnMessageDeliveredToHandset(OzSMSMessage osmsm) {
        Date now = new Date();
        System.out.println(now.toString() + " Message delivered to handset. ID: " + osmsm.messageId);
    }

    @Override
    public void doOnMessageDeliveredToNetwork(OzSMSMessage osmsm) {
       Date now = new Date();
    System.out.println(now.toString() + " Message delivered to network. ID: " + osmsm.messageId);
    }

    @Override
    public void doOnMessageAcceptedForDelivery(OzSMSMessage osmsm) {
        Date now = new Date();
        System.out.println(now.toString() + " Message accepted for delivery. ID: " + osmsm.messageId);
    }

    @Override
    public void doOnMessageDeliveryError(OzSMSMessage osmsm) {
        Date now = new Date();
        System.out.println(now.toString() + " Message could not be delivered. ID: " + osmsm.messageId +
         " Error message: " + osmsm.errorMessage + "\r\n");
    }

    @Override
    public void doOnClientConnectionError(int errorCode, String errorMessage) {
        Date now = new Date();
        System.out.println(now.toString() + " Message code: " + errorCode + ", Message: " + errorMessage);
    }
}

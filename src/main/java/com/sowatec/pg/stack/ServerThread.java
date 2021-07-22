package main.java.com.sowatec.pg.stack;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.util.StreamReaderDelegate;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.StringReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThread extends Thread {
    Socket client;

    public ServerThread(Socket socket) {
        client = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(client.getInputStream());
            String message = input.readUTF();
            String type = message.substring(message.indexOf("<type>"), message.indexOf("</type>")).replace("<type>", "");
            JAXBContext context;
            Unmarshaller unmarshaller;
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Receiving data: " + message);
            switch (type) {
                case "UserDBO":
                    context = JAXBContext.newInstance(UserDBO.class);
                    unmarshaller = context.createUnmarshaller();
                    UserDBO userDBO = (UserDBO) unmarshaller.unmarshal(new StringReader(message));
                    switch (userDBO.action){
                        case INSERT:
                            boolean success = DatabaseExecutor.registerUser(userDBO);
                            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                            dos.writeUTF("success: " + success);
                            Logger.getLogger(getClass().getName()).log(Level.INFO, "writing data: " + success);
                            dos.close();
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + userDBO.action);
                    }
                    break;

                default:
                    throw new IllegalArgumentException(type + " is not a supported type");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

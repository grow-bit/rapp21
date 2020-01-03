package org.growingabit.rapp21;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;

public class RApP21Mail {

    public RApP21Mail() {
        
    }

    private void send(String subject, String text) {
        // [START simple_example]
    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    try {
      Message msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress("rapp21@growbit-0.appspotmail.com", "RApP21 GAE"));
      msg.addRecipient(Message.RecipientType.TO,
                       new InternetAddress("rap2019@growbit.xyz", "RApP21 GrowBit"));
      msg.setSubject(subject);
      msg.setText(text);
      Transport.send(msg);
    } catch (AddressException e) {
      // ...
    } catch (MessagingException e) {
      // ...
    } catch (UnsupportedEncodingException e) {
      // ...
    }
    // [END simple_example]
        
    }

    public void newSignUp(String email) {
        this.send("RApP GAE | newSignUp", String.format("Nuovo utente %s, dovra' completare la registrazione.", email));
    }

    public void pendingSignUp(String firstName, String lastName, String school, String email) {
        this.send("RApP GAE | pendingSignUp", String.format("%s %s della scuola %s ha completato la registrazione con email %s. Ricordarsi di verificare e confermare registrazione.", firstName, lastName, school, email));
    }
}
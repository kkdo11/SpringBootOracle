package kopo.poly.service.impl;

import kopo.poly.dto.MailDTO;
import kopo.poly.service.IMailService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Service("MailService")
public class MailService implements IMailService {

    final String host = "smtp.naver.com";
    final String user = "dream7418@naver.com";
    final String password = "kkdowon0612";

    @Override
    public int doSendMail(MailDTO pDTO) {
        log.info(this.getClass().getName() + ".doSendMail start");

        int res = 1;

        if (pDTO == null) {
            pDTO = new MailDTO();
        }

        String toMail = CmmUtil.nvl(pDTO.getToMail());

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

            message.setSubject(CmmUtil.nvl(pDTO.getTitle()));

            message.setText(CmmUtil.nvl(pDTO.getContents()));

            Transport.send(message);
        } catch (MessagingException e) {
            res = 0;
            log.info("[ERROR] " + this.getClass().getName() + " .doSendMail : " + e);
        } catch (Exception e) {
            res = 0;
            log.info("[ERROR] " + this.getClass().getName() + " .doSendMail : " + e);
        }
        log.info(this.getClass().getName() + ".doSendMail End");
        return res;
    }
}

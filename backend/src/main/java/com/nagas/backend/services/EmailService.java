package com.nagas.backend.services;

import com.nagas.backend.entity.EmailTemplate;
import com.nagas.backend.model.EmailRequest;
import java.util.Arrays;
import java.util.Map;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@Service
public class EmailService {

    private SpringTemplateEngine templateEngine;
    private JavaMailSender mailSender;

    public String sendMail(EmailTemplate emailTemplate, Map<String, Object> valueMap) {
        log.info("Entering the sendMail method:" + emailTemplate.getSubject());

        try {
            Context context = new Context();
            context.setVariables(valueMap);
            String htmlBody = this.templateEngine.process(emailTemplate.getBody(), context);
            EmailRequest request = new EmailRequest();
            request.setBody(htmlBody);
            request.setSubject(emailTemplate.getSubject());
            request.setTo(Arrays.asList(emailTemplate.getTo().split(",")));
            if (emailTemplate.getCc() != null) {
                request.setCc(Arrays.asList(emailTemplate.getCc().split(",")));
            }

            if (emailTemplate.getBcc() != null) {
                request.setBcc(Arrays.asList(emailTemplate.getBcc().split(",")));
            }

            MimeMessage message = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            InternetAddress[] toMails = new InternetAddress[request.getTo().size()];

            for(int i = 0; i < request.getTo().size(); ++i) {
                toMails[i] = new InternetAddress((String)request.getTo().get(i));
            }

            int i;
            InternetAddress[] bccMails;
            if (!CollectionUtils.isEmpty(request.getCc())) {
                bccMails = new InternetAddress[request.getCc().size()];

                for(i = 0; i < request.getCc().size(); ++i) {
                    bccMails[i] = new InternetAddress((String)request.getCc().get(i));
                }

                helper.setCc(bccMails);
            }

            if (!CollectionUtils.isEmpty(request.getBcc())) {
                bccMails = new InternetAddress[request.getBcc().size()];

                for(i = 0; i < request.getBcc().size(); ++i) {
                    bccMails[i] = new InternetAddress((String)request.getBcc().get(i));
                }

                helper.setBcc(bccMails);
            }

            helper.setFrom("noreplytest44@gmail.com", "Naga Lakshmi Education Academy");
            helper.setTo(toMails);
            helper.setSubject(request.getSubject());
            helper.setText(request.getBody(), true);
            log.info("Sending email: {} with html body: {}", request);
            this.mailSender.send(message);
        } catch (Exception var11) {
            log.info("Error while sending mail", var11);
        }

        return "Mail sent successfully";
    }

    public EmailService(final SpringTemplateEngine templateEngine, final JavaMailSender mailSender) {
        this.templateEngine = templateEngine;
        this.mailSender = mailSender;
    }
}


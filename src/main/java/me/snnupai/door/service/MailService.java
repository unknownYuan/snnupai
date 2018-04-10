package me.snnupai.door.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MailService {


    @Autowired
    private JavaMailSender javaMailSender;

    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer configurer;

    /**
     * @param params       发送邮件的主题对象 object
     * @param title        邮件标题
     * @param templateName 模板名称
     */

    public void sendMessageMail(Object params, String title, String templateName, String toMailbox) {

        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            String from = "1593028064@qq.com";
            helper.setFrom(from);
            helper.setTo(InternetAddress.parse(toMailbox));//发送给谁
            helper.setSubject("【" + title + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "】");//邮件标题

            Map<String, Object> model = new HashMap<>();
            model.put("params", params);
            try {
                Template template = configurer.getConfiguration().getTemplate(templateName);
                try {
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

                    helper.setText(text, true);
                    javaMailSender.send(mimeMessage);
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        mailSender = new JavaMailSenderImpl();
//        mailSender.setPort(465);
//        //mailSender.setDefaultEncoding("utf-8");
//        //mailSender.setProtocol("smtp");
//        mailSender.setHost("smtp.qq.com");
//        mailSender.setUsername("1593028064@qq.com");
//        mailSender.setPassword("chghlpsvfcvqidbg");
//
//        // chghlpsvfcvqidbg
//        Properties javaMailProperties = new Properties();
////        javaMailProperties.put("mail.smtp.ssl.enable", true);
//        javaMailProperties.put("mail.smtp.auth", true);
//        //javaMailProperties.put("mail.smtp.timeout", 5000);
//        javaMailProperties.put("mail.smtp.starttls.enable", true);
//        javaMailProperties.put("mail.smtp.starttls.required", true);
//        mailSender.setJavaMailProperties(javaMailProperties);
//    }
}

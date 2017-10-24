package main.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import unsw.curation.api.domain.ExtractNamedEntity;
import unsw.curation.api.extractnamedentity.ExtractEntitySentence;
import unsw.curation.api.tokenization.ExtractionKeywordImpl;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class curation_model {
    private Set<String> bully;
    private ExtractionKeywordImpl ek;
    private String contextPath;
    private ExtractEntitySentence ess;


    public curation_model(String ContextRealPath) {
        this.contextPath = ContextRealPath;
        this.bully = setBully();
        this.ek = new ExtractionKeywordImpl();
        this.ess = new ExtractEntitySentence();
    }

    private Set<String> setBully() {
        File file = new File(contextPath+"bully.txt");
        BufferedReader reader = null;
        String tempString = "";
        try {
            reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            while (line != null) {
                tempString += line;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        tempString = tempString.replaceAll("\n", "");
        tempString = tempString.replaceAll("\\s", "");
        String[] wordList = tempString.split(",");
        Set<String> result = new HashSet<>();

        for (String word: wordList
             ) {
            result.add(word.toLowerCase());
        }
        return result;
    }

    public Set<String> extractKeywords(String message) throws Exception {
        Set<String> result  = new HashSet<>();
        String words = ek.ExtractSentenceKeyword(message, new File(contextPath+"words.txt"));
        String[] wordList = words.split(",");
        result.addAll(Arrays.asList(wordList));
        return result;
    }

    public Set<String> checkBully(Integer userID, String message) throws Exception {
        Set<String> result  = new HashSet<>();
        Set<String> keyword = extractKeywords(message);
        for (String word:keyword
             ) {
            if (bully.contains(word.toLowerCase())){
                result.add(word);
            }
        }
        if (result.size()>0){
            // send Email
            sendBullyEmail("unswbookemailserver@gmail.com", result, userID);
            //add log
            admin_model am = new admin_model();
            am.log_bully(userID, result);
        }
        return result;
    }

    public Set<String> extractNamedEntities(String message) throws URISyntaxException {
        Set<String> result  = new HashSet<>();
        List<ExtractNamedEntity> extractNamedEntities = ess.ExtractNamedEntitySentence(message);
        for (ExtractNamedEntity ene: extractNamedEntities
             ) {
            result.add(ene.getWord());
        }
        return result;
    }

    public Set<String> extractLocation(String message) throws URISyntaxException {
        Set<String> result  = new HashSet<>();
        List<String> extractNamedEntities = ess.ExtractLocation(message);
        result.addAll(extractNamedEntities);
        return result;
    }

    public Set<String> extractPerson(String message) throws URISyntaxException {
        Set<String> result  = new HashSet<>();
        List<String> extractNamedEntities = ess.ExtractPerson(message);
        result.addAll(extractNamedEntities);
        return result;
    }

    public Set<String> extractOrganization(String message) throws URISyntaxException {
        Set<String> result  = new HashSet<>();
        List<String> extractNamedEntities = ess.ExtractOrganization(message);
        result.addAll(extractNamedEntities);
        return result;
    }



    private void sendBullyEmail(String email, Set<String> bully, Integer userID) {

        String bullyString = "";
        for (String word:bully
                ) {
            bullyString += word+", ";
        }
        bullyString = bullyString.substring(0, bullyString.length()-2);

        String target_mail_add = email;
        final String from = "unswbookemailserver@gmail.com";
        final String password = "root123@";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("unswbookemailserver@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(target_mail_add));
            message.setSubject("Testing Subject");
            message.setText("Dear Administrator: \nUser: "+userID+" has posted a message with the following bully words:\n"+bullyString);
            Transport.send(message);

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}

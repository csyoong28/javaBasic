package com.example.javabasic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

@Slf4j
public class C1JOptionPane {

    public static void main(String[] args) {
        log.info("test111");

        C1JOptionPane c1JOptionPane = new C1JOptionPane();
        c1JOptionPane.yourMethod();
        c1JOptionPane.t1ShowJOptionPaneUsage();
    }

    // to show JOptionPane that can accept input, and response message
    public void t1ShowJOptionPaneUsage() {
        String userName = JOptionPane.showInputDialog("username:");
        String password = JOptionPane.showInputDialog("password:");
        if (userName.equals("aaa") && password.equals("aaa")){
            JOptionPane.showMessageDialog(null, "you are in");
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(C1JOptionPane.class);

    public void yourMethod() {
        try {
            // Your code that may throw an exception
            throw new RuntimeException("Example Exception");
        } catch (Exception e) {
            // Log the exception stack trace
            logger.error("Exception occurred", e);
        }
    }




}

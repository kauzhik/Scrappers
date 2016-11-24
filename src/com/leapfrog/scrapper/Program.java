/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.scrapper;

import com.leapfrog.scrapper.util.Grabber;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kaushik
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String link = "http://www.jobsnepal.com/simple-job-search";
        
        Scanner input = new Scanner(System.in);
        
        while(true){
            System.out.println("Enter you search keyword: [OR exit]");
            String userInput = input.next();
            if(userInput != "exit"){
                String content = Grabber.post(link, "Keywords="+userInput);
            
                String jobRegex = "<a class=\\\"job-item\\\"(.*?)href=\\\"(.*?)\\\" >(.*?)</a>";
                Pattern pattern = Pattern.compile(jobRegex);
                Matcher matcher = pattern.matcher(content);

                while(matcher.find()){
                    String jobTitle = matcher.group(3).trim();
                    System.out.println("Job Title: " + jobTitle);
    //                System.out.println("Link: " + matcher.group(2));
                    String jLink = matcher.group(2);
                    System.out.println("Crawling for " + jobTitle + "'s salary.. from " + jLink);
                    System.out.println("=========================\r\n");
                    String jobContent = Grabber.get(jLink);

                    String jobSalaryRegex = "<label>Salary:</label>          <span>(.*?)         </span>";
                    Pattern jobSalaryPattern = Pattern.compile(jobSalaryRegex);
                    Matcher jobSalaryMatcher = jobSalaryPattern.matcher(jobContent);

                    while(jobSalaryMatcher.find()){
                        System.out.println("Salary: " + jobSalaryMatcher.group(1));                    
                    }

                    System.out.println("=========================\r\n\r\n");



                }
            }else if(userInput == "exit"){
                System.exit(0);
            }
            
        }
       
    }
    
}

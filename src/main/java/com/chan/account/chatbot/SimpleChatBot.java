package com.chan.account.chatbot;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleChatBot {
    private static Map<String,String> responses=new HashMap<>();
    static Scanner scanner=new Scanner(System.in);

    public static void questions(){
        responses.put("create","Mr Kong chan crate me to help you know more about the system!");
        responses.put("hello", "Hello! How can I help you today?");
        responses.put("hi", "Hi there! What can I do for you?");
        responses.put("name", "I'm just a simple chatbot, but you can call me Chatbot.");
        responses.put("how are you", "I'm just a program, but I'm functioning perfectly! How about you?");
        responses.put("personal account management system",
                "It's a tool to help users track income, expenses, and manage their financial accounts effectively.");
        responses.put("multiple accounts",
                "Yes, you can manage multiple accounts, each with its own balance and transaction history.");
        responses.put("record expenses",
                "Expenses are recorded by selecting an account, specifying the amount, and adding details like category and date.");
        responses.put("tell me",
                "\nWhat System can do \n" +
                        "Insert a new account\n" +
                        "Find an existing account\n" +
                        "Delete an account" +
                        "Add an expense");
    }

    public static void chatBotResponse(){
        System.out.println("Hello! I'm your chatbot. Ask me anything or type 'exit' to end the chat.");
        while (true){
            System.out.print("You: ");
            String userInput=scanner.nextLine().trim().toLowerCase();
            if(userInput.equalsIgnoreCase("exit")){
                System.out.println("Chatbot: Goodbye! Have a great day!");
                break;
            }
            boolean foundRespone=false;
//            loop through to find the find in Hashmap
            for (Map.Entry<String,String> entry: responses.entrySet()){
                if(userInput.contains(entry.getKey())){
                    System.out.println("Chatbot: "+entry.getValue());
                    foundRespone=true;
                    break;
                }
            }

            if(!foundRespone){
                    System.out.println("Chatbot: I'm sorry, I didn't understand that. Can you rephrase?");
            }


        }

    }




}

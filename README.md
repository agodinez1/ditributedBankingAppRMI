# ditributedBankingAppRMI

To run:
1. navigate to ..\..DistributedBankingApp\src on the command line
2. javac *.java (compile all java files to class files)
3. navigate to ..\..DistributedBankingApp\bin on the command line
4. java -Djava.security.policy=security.policy Bank 7777
   - We set the security policy to grant permissions to allow the application to run the remote server
   - 7777 is just any port number
5. It should say 

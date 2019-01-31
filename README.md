# ditributedBankingAppRMI

# To run/test:
##### make sure you change the file path in security.policy
1. navigate to ..\..DistributedBankingApp\src on the command line
2. ```javac *.java```  
   - compile all java files to class files
3. navigate to ..\..DistributedBankingApp\bin on the command line
4. ```java -Djava.security.policy=security.policy Bank 7777```
   - We set the security policy to grant permissions to allow the application to run the remote server
   - 7777 is just any port number
5. Now that we have the server running, we can run the remote methods inside the Bank server using a client.
![alt text](https://github.com/agodinez1/ditributedBankingAppRMI/blob/master/results/res1.JPG)

# Test cases (Client side)  
- ```java ATM localhost 7777 inquiry 1```  (not logged in)  
![alt text](https://github.com/agodinez1/ditributedBankingAppRMI/blob/master/results/res2.JPG)
- ```java ATM localhost 7777 login username1 password1```  
![alt text](https://github.com/agodinez1/ditributedBankingAppRMI/blob/master/results/res7.JPG)
- ```java ATM localhost 7777 inquiry 1```  
![alt text](https://github.com/agodinez1/ditributedBankingAppRMI/blob/master/results/res3.JPG)
- ```java ATM localhost 7777 withdraw 1 5```    
- ```java ATM localhost 7777 withdraw 1 10```    
- ```java ATM localhost 7777 withdraw 1 15```  
![alt text](https://github.com/agodinez1/ditributedBankingAppRMI/blob/master/results/res4.JPG)
- ```java ATM localhost 7777 statement 1 01/01/2019 31/12/2019```  
![alt text](https://github.com/agodinez1/ditributedBankingAppRMI/blob/master/results/res5.JPG)

# Test cases (Server side) 
- Result after 5 minutes - not allowed to do operations anymore 
![alt text](https://github.com/agodinez1/ditributedBankingAppRMI/blob/master/results/res6.JPG)



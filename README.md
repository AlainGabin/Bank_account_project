# Bank Prefix Checker 

This program asks the user for the first **3 digits** of a Polish bank account
and tells them which bank it belongs to.

It downloads the official bank prefix list from NBP:
https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt

## How to Run
1. Compile: `javac Main.java`  
2. Run: `java Main`  
3. Enter the 3-digit prefix.

## Output
- Bank abbreviation  
- Bank name  

If no match is found, the program shows a message.

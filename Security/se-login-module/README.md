JAAS-SE example
[Link to inspired manual](http://www.oracle.com/technetwork/articles/java/jaasv2-137221.html)
If link is not available you could look at saved version in /resources/JAAS-SE.html
**Run command:**
Run it in /target folder after build.
```
java -Djava.security.auth.login.config=./classes/example.conf -cp se-login-module-1.0-SNAPSHOT.jar com.security.jaas.Client  
```
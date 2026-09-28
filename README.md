## 🐧 FIPS-Checker

![GitHub top language](https://img.shields.io/github/languages/top/zappee/fips-checker)
![GitHub Issues](https://img.shields.io/github/issues/zappee/fips-checker)
![GitHub Release](https://img.shields.io/github/v/release/zappee/fips-checker)

### 1) Overview

The _FIPS-Checker_ repository is a specialized Java-based web utility designed to verify if Federal Information Processing Standards (FIPS) mode is actively running on an application server.

Federal Information Processing Standards (FIPS 140-2) are US government security standards that specify the security requirements for cryptographic modules.
Organizations handling sensitive or government data must ensure their environments run in compliance with these standards.
Enabling FIPS mode on an application server - such as Oracle WenLogic - involves configuring specific cryptographic providers like RSA JCE and RSA JSSE.
Because these configurations are complex, the fips-checker application functions as a diagnostic tool to easily check, confirm, and audit whether the server's runtime environment is successfully operating in FIPS mode.


### 2. Technical Stack

* Language: Java 8 or above
* Artifact type: WAR
* Deployment targets: Oracle WebLogic Admin and/or Managed Servers, or any compatible Java application servers
* Build tool: Maven


### 3. How to Build and Deploy the Tool
As a standard Maven-based Java web application, the general deployment lifecycle involves compiling the code into a web archive (WAR file) and deploying it to your target application server.

1. Clone the project code to your local machine or build server.


2. Use Maven to package the application. This compiles the Java classes and generates a deployable .war file:

   ```
   mvn clean package
   ```


3. Deploy the generated WAR file to your server environment.


### 4. How to use the tool

Once deployed and running, the utility exposes a web interface to report the server's status.

1. Access the Application: Open your web browser and navigate to the application's deployed context path URL, e.g., `http(s)://<your-server-host>:<port>/fips-checker`.


2. Verify the Status: The tool will execute its internal Java runtime checks against the active security providers.


3. Interpret results: The web page will output a direct confirmation message indicating whether FIPS mode is enabled or disabled.


### 5) Contributing

Contributions, feature requests, and custom dictionary pattern submissions are always welcome!

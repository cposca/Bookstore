# Bookstore

**To set up SSL on the server:**

1. Open your server installation directory for Tomcat
2. Locate the *server.xml* file
3. Inside you will find a commented line that looks like:
```
<!--
    <Connector port="8443" protocol="org.apache.coyote.http11.Http11AprProtocol"
               maxThreads="150" SSLEnabled="true" >
        <UpgradeProtocol className="org.apache.coyote.http2.Http2Protocol" />
        <SSLHostConfig>
            <Certificate certificateKeyFile="conf/localhost-rsa-key.pem"
                         certificateFile="conf/localhost-rsa-cert.pem"
                         certificateChainFile="conf/localhost-rsa-chain.pem"
                         type="RSA" />
        </SSLHostConfig>
    </Connector>
    -->
```
4. Replace this with:
```
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               maxThreads="25" SSLEnabled="true" acceptCount="100" clientAuth="false" disableUploadTimeout="true"
               enableLookups="false" keystoreFile="The directory where you have saved Bookstore/Bookstore/keystore/keystore"
               keystorePass="unicorns" scheme="https" secure="true" sslProtocol="TLS">
    </Connector>
```
5. SSL should not be enable on the server

**Installation:**

1. Clone/Download the project from the master branch or import the provided war file
2. Open the project in eclipse and connect to a MySQL server
3. Populate the database with the provided SQL file 
4. Configure the server settings in dao.MySQLConnector.java
5. Run ctrl.Start.java on the server

**Running Test Cases:**
Because of the absence of a real certificate, we are unable to test our services using clients without turning off SSL. To do this quickly and easily follow these steps:

1. Navigate to the web.xml file in WebContent/WEB-INF
2. Scroll down to line 73 in the XML: ```<transport-guarantee>CONFIDENTIAL</transport-guarantee>```
3. Replace line 73 with this snippet: ```<transport-guarantee>NONE</transport-guarantee>```

The project should now be ready to test with SSL turned off momentarily in order to allow clients to connect.



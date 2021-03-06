# Bookstore
### Installation:

1. Clone/Download the project from the master branch or import the provided war file
2. Open the project in eclipse and connect to a MySQL server
3. Populate the database with the provided SQL file (*bookstoreStrawHatUnicorns.sql*)
4. Configure the server settings in *dao.MySQLConnector.java* (Set your username and password as well as the database name)
5. Project -> Clean the project
6. Run *ctrl.Start.java* on the server
7. Access the webapp at **http://localhost:8080/StrawHatUnicorns/Store**
    * Alternatively use **https://localhost:8443/StrawHatUnicorns/Store** if you have set up SSL

### To set up SSL on the server:

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
5. SSL should now be enable on the server
6. Locate the WebContent/WEB-INF/web.xml file
7. Locate line 73 in the XML file: ```<transport-guarantee>CONFIDENTIAL</transport-guarantee>```
   * The line may also be reperesented by ```<transport-guarantee>NONE</transport-guarantee>```
8. If the line contains the key word *CONFIDENTIAL* then leave it alone. If the line contains *NONE* then replace it with *CONFIDENTIAL*

### Setting up REST/SOAP Clients:

1. Locate ClientProjects/
2. Import ClientProjects/Bookstore_REST.zip and ClientProjects/Bookstore_SOAP.war into Eclipse
3. Run Bookstore_SOAP on the sever
   * Visit *http://localhost:8080/Bookstore_SOAP/sampleBookServiceProxy/TestClient.jsp
   * You may have to update the end point using setEndPoint() in the webapp
4. Run *Bookstore_REST/src/restClientTest/OrderServiceTest.java* using JUnit 5
   * You may have to alter your build path to include JUnit 5
   * You may have to alter the url used in the REST request

### Running Test Cases From the REST/SOAP Clients:

Because of the absence of a real certificate, we are unable to test our services using clients without turning off SSL. To do this quickly and easily follow these steps:

1. Navigate to the WebContent/WEB-INF/web.xml file in the project
2. Scroll down to line 73 in the XML: ```<transport-guarantee>CONFIDENTIAL</transport-guarantee>```
3. Replace line 73 with this snippet: ```<transport-guarantee>NONE</transport-guarantee>```

The project should now be ready to test with SSL turned off momentarily in order to allow clients to connect. Reverse this process when you'd like to reactivate SSL.



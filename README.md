# Android-Extra-SSL-CA
Describes how to add SSL certificates to Android devices. If SSLHandshakeException occurs, modifying the server chain is best, but you can add a certificate to the device.


## Common problems verifying server certificates
[Read Android Developer official documents](https://developer.android.com/training/articles/security-ssl#CommonProblems)

## Note
1. Check the SSL Web server information that the server is using.
   - SSL web server configuration information can be known by entering the domain of the URL to be used at the following address.
   - https://www.ssllabs.com/ssltest/index.html
2. On Android, **KeyStore** is only available in the **BKS, CRT** method.

##  Development Plan
- Create a TrustManager who considers "intermediate CA" as "unknown CA" and trusts this CA.
1. **Download** the certificate that the server is using.
2. Use the certificate in **CRT** format or convert it to **BKS** format.
3. Add BKS files to the **assets** folder inside the Android project.
4. Write Source Code(Create a TrustManager that trusts the CAs in our KeyStore)
      
## Create a BKS file with a CRT file
- This explains the method I used among many methods. There is no correct answer, so you can use a comfortable method.
1. Download **[KeyStore Explore](https://keystore-explorer.org/)**
2. Click **Create a new KeyStore** because we're going to create a new KeyStore
3. Set the **Alias** for the certificate and press OK. (You can insert **multiple** required certificates.)
4. Press the **Save** button on the toolbar to save the list of certificates in BKS format. **Password** can be specified at this time.

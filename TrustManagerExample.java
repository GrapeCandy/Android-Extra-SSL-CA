====================================================================
Copyright [2023] [grapecandy]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
====================================================================

Certificate certificate = null;
char[] pwdArray = "changeit".toCharArray();
KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
InputStream is = Nexacro.getInstance().getContext().getAssets().open("sample.bks");	// "sample.bks" = Your CRT or BKS file name

keyStore.load(is, null);

Enumeration<String> enumeration = keyStore.aliases();
while(enumeration.hasMoreElements()) 
{
	String alias = enumeration.nextElement();
	//System.out.println("alias name: " + alias);	// Check keystore's alias
	certificate = keyStore.getCertificate(alias);
	keyStore.setCertificateEntry("ca", certificate);
}

// Create a TrustManager that trusts the CAs in our KeyStore
String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
tmf.init(keyStore);

// Create an SSLContext that uses our TrustManager
SSLContext context = SSLContext.getInstance("TLS");
context.init(null, tmf.getTrustManagers(), null);

// Tell the URLConnection to use a SocketFactory from our SSLContext
httpsURLConnection.setSSLSocketFactory(context.getSocketFactory());

httpsURLConnection.connect();
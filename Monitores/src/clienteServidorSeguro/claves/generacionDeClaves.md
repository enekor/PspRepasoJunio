## generacion de las claves de cifrado (ssl)
* _keytool -genkey -keyalg RSA -keysize 2048 -validity 360 -alias mykey -keystore myKeyStore.jks_ para generar el archivo jks que contendra todo
  * completamos los datos respondiendo a las preguntas
* _keytool -export -alias mykey -keystore myKeyStore.jks -file myKey.cert_ para exportar el certificado
* _keytool -import -file mykey.cert -alias mykey -keystore myTrustStore.jts_ para meter el certificado en una TrustedStore que usara el cliente
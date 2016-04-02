[�w��]

1. JRE (Java Runtime Environment) 

  http://www.oracle.com/technetwork/java/javase/downloads/index.html

2. Eclipse
  
  �i�H�U�� Standard �� (http://www.eclipse.org/downloads/)
  �A�w�� ADT �X�R�M��
  �о�(https://code.google.com/p/androidbmi/wiki/InstallAndroid)�G
  ���ù��W�誺���C�A ��� �uHelp->Install New Softare�v �ﶵ�A�o�ﶵ�|�a�X�@�ӷs�����C ��ܡuAvailable Software�v���ҡA��ܥk�誺 �uAdd...�v (�s�W����)���s�A�|�u�X�@�ӿ�J�ءC
  �b��J�ؤ���"Location"��줤��J�X�R�M�󪺦W��(Name) �uADT�v ����}(URL) �uhttp://dl-ssl.google.com/android/eclipse/site.xml �v�A���U "OK" ���s���}�CEclipse �|��@�I�ɶ��M��X�A�������C
  �b����������u https://dl-ssl.google.com/android/eclipse/site.xml �v���ءuDeveloper Tools�v�������ﶵ��A���U�k�誺�uInstall�v���s�C
  ���U �uNext�v (�U�@�B)��C�ӵۨB�J�N�w�˧����C�w�˧��|���ܻݭ��s�Ұ� Eclipse�A���U �uYes�v ���s�ҰʡC

  �άO�����U����W�g Android �� Eclipse SDK
  http://developer.android.com/sdk/index.html

  ==========================================================

  eclipse�����D
  Android SDK����Ƨ���bprogram files (x86)����Ƨ��U��eclipse�n���|�䤣��
  �ҥH���Ƨ��h��C�ѤU���a

  ���G

  Please ensure that adb is correctly located�K

  Posted on September 10, 2011 by pustekuchen
  Hello folks,

  some days ago i got a new Smartphone. Its an Samsung Galaxy S2 with Android 2.3.3. So i wanted to program my first own Android Application.

  I followed a German Guide about Android-Programming.

  But when had i finished my HelloWorld App and wanted to Compile it, the first unexcepted error appears.

  The Error was the following:

  [2011-09-10 20:19:36 - HelloWorld] The connection to adb is down, and a severe error has occured.
  [2011-09-10 20:19:36 - HelloWorld] You must restart adb and Eclipse.
  [2011-09-10 20:19:36 - HelloWorld] Please ensure that adb is correctly located at 'C:\Program Files (x86)\Android\android-sdk\platform-tools\adb.exe' and can be executed.

  Soon i found out that the Path of SDK Location(Preferences -> Android -> SDK Location) was the cause. My SDK Path was the following:

  C:\Program Files (x86)\Android\android-sdk

  So what is wrong? The Spaces in the Path are the problems. To get it work, you must change ��Program Files (x86)�� to ��Progra~2��

  The complete right path is ��C:\Progra~2\Android\android-sdk��.

  Now it should work and you can compile your first Android Application :)

  ==========================================================

  �t�~�@�ӥi��|�X�{�����~
  �������ܳo�ӿ��~:
  Android requires compiler compliance level 5.0 or 6.0. Found '1.7' instead.
  Please use Android Tools > Fix Project Properties.

  �N�b�M�ת���Ƨ����k�� ���android tools �̭�����Fix Project Properties
  �I�U�h�N�n�F!
  
  ==========================================================
  
  �פJ�M�׫�A�s�Wjar�G
  (�W��)�M�� > ���e > (����)�귽 > Java�ظm���| > �{���w���� > �s�W�~��JAR

[PC�� �{��]

  1. OpenOffice
  
  �D�n�O�γo�ӽd�Ҩӭק�
  https://wiki.openoffice.org/wiki/API/Tutorials/PDF_export
  
  ���O BootstrapSocketConnector �n���|�����D
  �ҥH�פJ bootstrapconnector.jar �N��ץ����~
  �ڬO�N bootstrapconnector.jar ��b OpenOffice ��Ƨ��A /opt/openoffice.org/ure/share/java ��
  
  �ڦbPDFMaker.java�����F��K���աA�N61�檺oooExeFolder�g���F�A�p�G�Q�ק�i�H�h��62�A63��
  Windows �����|�i��O C:/Program Files/OpenOffice.org 3/program/
  Linux �����|�i��O /opt/openoffice.org3/program
  �n�� OpenOffice �w�˦b����
  
  OpenOffice��Ū��URL�X�k�d��
  http://openofficetechnology.com/documentation/OpenOffice-Configuration/File_URLs

  2. ���W
  
  �ѦҺ����Ghttps://sites.google.com/a/widomski.net/ntporgpl2/client-tsc-rfc3161
  
  RFC3161client_jar.zip �O�d�ҵ{��
  RFC3161client_source.zip�O�d�ҵ{�����{���X
  
  VerTimeStampTest.zip�O�qRFC3161client_source�^���X�Ӧ������Ү��W������
  
  ���աG
      �Q��RFC3161client_jar���ɮײ��ͮ��W
      ���l�ɮשM�[�J���W�᪺�ɮ׵�VerTimeStampTest����
  
  �o�X�� java �ɳ��O�M���W������
  
  HttpTimestamper.java
  QRGenerationException.java
  SenRequest.java
  Timestamper.java
  TimestampToken.java
  TSRequest.java
  TSResponse.java
  
  �����פJ��A���� SenRequest request = new SenRequest(string); �إ� SenRequest
  �A��request.SendingRequest()�A���o���W�����(byte[])
  
  3. ZIP���Y
  
  �Ѧҳo��
  http://wallacenotebook.blogspot.tw/2012/12/javautilzip.html
  
  �ڬOimport java.util.zip.GZIPOutputStream �o��Class
  
  �Ԩ�Ziptool.java�A��test�禡�i�H����
  compress(String)�O���Y�Adecompress(byte[])�O�����Y
  
  4. QRCODE
  
  �Ѧ�
  http://viralpatel.net/blogs/create-qr-codes-java-servlet-qr-code-java/
  
  �ϥ�
  https://github.com/kenglxn/QRGen
    
  �פJ
  
  core.jar
  zxing-j2se-1.7.jar
  
  QRCode.java
  ImageType.java
  QRGenerationException.java
  
  �K��ϥ�QRCode�o��Class
  
  QRCode.from(source).to(ImageType.GIF).withCharset("UTF-8").withSize(25, 25).stream();
  �N��ͦ�QRCode����(ByteArrayOutputStream)

  
[Android �{��]
  
  1. QRCode
  
  android-integration.jar
  android-support-v4.jar
  core.jar
  
  �פJ�H�Wjar�ɡA���ӴNOK�F
  ���檺�ܱN /src/com/example/barcodeencrypt ���� java codes ���X�ӫطs�M�����ӴNOK�F
  
  2.���W
  
  �פJ android-sun-jarsign-support-1.1.jar (ñ�����ɤB������?)	
  
  �H�ΥH�U��� java ��
  
  TimestampToken.java
  VerTimeStamp.java

  ���� VerTimeStamp verify = new VerTimeStamp(time,data); ���oVerTimeStamp
  �N��� verify.VerifyTimeStamp() ���Ү��W
  
  
[��L]

�������W���A��

1. Perl (Ubuntu ���ӴN���F)

2. libcurl �Χ�v������k�S���\�A��ӥΡG
  sudo apt-get install libcurl4-openssl-dev ����k�w��

3. �b�o�̧��F��v��������patch
  http://security.nknu.edu.tw/textbook1ed/update/CH16/
  �٦n�٧䪺�� �{�bwww.opentsa.org�������F

4. �o�̥i�H���openssl����l�X
  http://www.openssl.org/source/

5. �w��Apache�A�X�{�F�n�h���D�A�j�M����o�{�O�o��
  http://www.cambus.net/compiling-apache-1.3.x-on-modern-linux-distributions/
	
6. ��v����20���g���F�A���Ӽgmake OPENSSL=/usr/local/openssl�~��I���Ossl

7. (�n�����O���n�B�J)
  mod_tsa �䤣�� apr_portable.h ? �i�H�ո�
  http://apache.sunsite.ualberta.ca/apr/
  ��apr�U���ӷӳo�Ӧw��
  http://www.techsww.com/tutorials/libraries/apr/installation/installing_apache_portable_runtime_library_on_ubuntu_linux.php
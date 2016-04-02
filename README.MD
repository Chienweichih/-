[安裝]

1. JRE (Java Runtime Environment) 

  http://www.oracle.com/technetwork/java/javase/downloads/index.html

2. Eclipse
  
  可以下載 Standard 版 (http://www.eclipse.org/downloads/)
  再安裝 ADT 擴充套件
  教學(https://code.google.com/p/androidbmi/wiki/InstallAndroid)：
  找到螢幕上方的選單列， 選擇 「Help->Install New Softare」 選項，這選項會帶出一個新視窗。 選擇「Available Software」標籤，選擇右方的 「Add...」 (新增網站)按鈕，會彈出一個輸入框。
  在輸入框中的"Location"欄位中輸入擴充套件的名稱(Name) 「ADT」 跟網址(URL) 「http://dl-ssl.google.com/android/eclipse/site.xml 」，按下 "OK" 按鈕離開。Eclipse 會花一點時間尋找合適的版本。
  在視窗中全選「 https://dl-ssl.google.com/android/eclipse/site.xml 」項目「Developer Tools」中的的選項後，按下右方的「Install」按鈕。
  按下 「Next」 (下一步)鍵。照著步驟就安裝完成。安裝完會提示需重新啟動 Eclipse，按下 「Yes」 重新啟動。

  或是直接下載單獨寫 Android 的 Eclipse SDK
  http://developer.android.com/sdk/index.html

  ==========================================================

  eclipse的問題
  Android SDK的資料夾放在program files (x86)的資料夾下面eclipse好像會找不到
  所以把資料夾搬到C槽下面吧

  原文：

  Please ensure that adb is correctly located…

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

  So what is wrong? The Spaces in the Path are the problems. To get it work, you must change “Program Files (x86)” to “Progra~2〃

  The complete right path is “C:\Progra~2\Android\android-sdk”.

  Now it should work and you can compile your first Android Application :)

  ==========================================================

  另外一個可能會出現的錯誤
  執行後顯示這個錯誤:
  Android requires compiler compliance level 5.0 or 6.0. Found '1.7' instead.
  Please use Android Tools > Fix Project Properties.

  就在專案的資料夾按右鍵 找到android tools 裡面有個Fix Project Properties
  點下去就好了!
  
  ==========================================================
  
  匯入專案後，新增jar：
  (上方)專案 > 內容 > (左邊)資源 > Java建置路徑 > 程式庫標籤 > 新增外部JAR

[PC端 程式]

  1. OpenOffice
  
  主要是用這個範例來修改
  https://wiki.openoffice.org/wiki/API/Tutorials/PDF_export
  
  但是 BootstrapSocketConnector 好像會有問題
  所以匯入 bootstrapconnector.jar 就能修正錯誤
  我是將 bootstrapconnector.jar 放在 OpenOffice 資料夾， /opt/openoffice.org/ure/share/java 裡
  
  我在PDFMaker.java中為了方便測試，將61行的oooExeFolder寫死了，如果想修改可以去掉62，63行
  Windows 的路徑可能是 C:/Program Files/OpenOffice.org 3/program/
  Linux 的路徑可能是 /opt/openoffice.org3/program
  要看 OpenOffice 安裝在哪裡
  
  OpenOffice的讀檔URL合法範例
  http://openofficetechnology.com/documentation/OpenOffice-Configuration/File_URLs

  2. 時戳
  
  參考網站：https://sites.google.com/a/widomski.net/ntporgpl2/client-tsc-rfc3161
  
  RFC3161client_jar.zip 是範例程式
  RFC3161client_source.zip是範例程式的程式碼
  
  VerTimeStampTest.zip是從RFC3161client_source擷取出來有關驗證時戳的部份
  
  測試：
      利用RFC3161client_jar對檔案產生時戳
      把原始檔案和加入時戳後的檔案給VerTimeStampTest驗證
  
  這幾個 java 檔都是和時戳有關的
  
  HttpTimestamper.java
  QRGenerationException.java
  SenRequest.java
  Timestamper.java
  TimestampToken.java
  TSRequest.java
  TSResponse.java
  
  全部匯入後，先用 SenRequest request = new SenRequest(string); 建立 SenRequest
  再用request.SendingRequest()，取得時戳的資料(byte[])
  
  3. ZIP壓縮
  
  參考這個
  http://wallacenotebook.blogspot.tw/2012/12/javautilzip.html
  
  我是import java.util.zip.GZIPOutputStream 這個Class
  
  詳見Ziptool.java，有test函式可以測試
  compress(String)是壓縮，decompress(byte[])是解壓縮
  
  4. QRCODE
  
  參考
  http://viralpatel.net/blogs/create-qr-codes-java-servlet-qr-code-java/
  
  使用
  https://github.com/kenglxn/QRGen
    
  匯入
  
  core.jar
  zxing-j2se-1.7.jar
  
  QRCode.java
  ImageType.java
  QRGenerationException.java
  
  便能使用QRCode這個Class
  
  QRCode.from(source).to(ImageType.GIF).withCharset("UTF-8").withSize(25, 25).stream();
  就能生成QRCode圖檔(ByteArrayOutputStream)

  
[Android 程式]
  
  1. QRCode
  
  android-integration.jar
  android-support-v4.jar
  core.jar
  
  匯入以上jar檔，應該就OK了
  不行的話將 /src/com/example/barcodeencrypt 中的 java codes 拿出來建新專案應該就OK了
  
  2.時戳
  
  匯入 android-sun-jarsign-support-1.1.jar (簽章的補丁之類的?)	
  
  以及以下兩個 java 檔
  
  TimestampToken.java
  VerTimeStamp.java

  先用 VerTimeStamp verify = new VerTimeStamp(time,data); 取得VerTimeStamp
  就能用 verify.VerifyTimeStamp() 驗證時戳
  
  
[其他]

有關時戳伺服器

1. Perl (Ubuntu 本來就有了)

2. libcurl 用投影片的方法沒成功，後來用：
  sudo apt-get install libcurl4-openssl-dev 的方法安裝

3. 在這裡找到了投影片的那些patch
  http://security.nknu.edu.tw/textbook1ed/update/CH16/
  還好還找的到 現在www.opentsa.org都掛掉了

4. 這裡可以找到openssl的原始碼
  http://www.openssl.org/source/

5. 安裝Apache，出現了好多問題，搜尋之後發現是這個
  http://www.cambus.net/compiling-apache-1.3.x-on-modern-linux-distributions/
	
6. 投影片第20頁寫錯了，應該寫make OPENSSL=/usr/local/openssl才對！不是ssl

7. (好像不是必要步驟)
  mod_tsa 找不到 apr_portable.h ? 可以試試
  http://apache.sunsite.ualberta.ca/apr/
  找apr下載來照這個安裝
  http://www.techsww.com/tutorials/libraries/apr/installation/installing_apache_portable_runtime_library_on_ubuntu_linux.php
<?php
	$passed = $_COOKIE['passed'];
	if($passed != 'TRUE' ){
		$url = "adminlogin.html";
		echo "<script type='text/javascript'>";
		echo "window.location.href='$url'";
		echo "</script>"; 
	}
	else{
		$id=$_COOKIE{"id"};
		$password=$_POST["password"];
		$name=$_POST["name"];
		$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
		mysql_query("SET NAMES 'UTF8'");
		if(! mysql_select_db("a5")) die("無法讀取資料庫");
		$data=mysql_query ("UPDATE administer SET admin_pwd='$password',admin_name='$name' WHERE admin_id=$id") or die("ERROR");		
	}
?>
<html>
	<head>
		<title>修改管理員資料成功</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-37458318-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
	</head>
	<body>
		<center>
			<?=$name?>，您已經成功修改資料。
			<p><a href="adminmain.php">返回管理頁</a></p>
		</center>
	</body>
</html>
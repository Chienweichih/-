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
<?php
	$author = $_POST['author'];
	$content = $_POST['content'];
	$mon_id = $_GET['mon_id'];
	date_default_timezone_set('Asia/Taipei');
	$current_time = date('Y-m-d H:i:s');
	
	if (!empty($_SERVER['HTTP_CLIENT_IP']))
		$ip=$_SERVER['HTTP_CLIENT_IP'];
	else if (!empty($_SERVER['HTTP_X_FORWARDED_FOR']))
		$ip=$_SERVER['HTTP_X_FORWARDED_FOR'];
	else
		$ip=$_SERVER['REMOTE_ADDR'];
	
	if(strlen($author) < 1 || strlen($content) < 1) echo "<script type = 'text/javascript'>alert(\"作者或內容不可空白\") </script>";
	
	else{
		$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
		mysql_query("SET NAMES 'UTF8'");
		if(! mysql_select_db("a5")) die("無法讀取資料庫");
		$sql = "INSERT INTO comment (monster_id,message,comment_name,comment_date,ip) VALUE('$mon_id','$content','$author','$current_time','$ip')";
		mysql_query($sql, $con);
	}
	
	echo "<script language='JavaScript'>
	<!--
		if($mon_id==0) window.location.replace('messageborad.php');
		else window.location.replace('mon_d.php?mon_id=$mon_id');
	//-->
	</script>";
	
	exit();

?>
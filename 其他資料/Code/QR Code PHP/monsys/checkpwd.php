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
			$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
			mysql_query("SET NAMES 'UTF8'");
			if(! mysql_select_db("a5")) die("無法讀取資料庫");

	$id = $_POST['id'];
	$pw = $_POST['pw'];

	$sql = "SELECT * FROM administer where admin_id = '$id'";
	$result = mysql_query($sql);
	$row = @mysql_fetch_row($result);

	if($id != null && $pw != null && $row[0] == $id && $row[1] == $pw)
	{
		ob_start();
		setcookie("id",$id);
		setcookie("passed","TRUE");		
		echo  $row[2];
		echo  '歡迎你，登入成功!';
		echo '<meta http-equiv=REFRESH CONTENT=1;url=adminmain.php>';
		ob_end_flush();
	}
	else
	{
		setcookie("passed","FALSE");
		echo '登入失敗，請再確認帳號密碼';
		echo '<meta http-equiv=REFRESH CONTENT=1;url=adminlogin.html>';
	}

?>

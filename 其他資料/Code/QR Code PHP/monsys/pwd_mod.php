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
	$passed = $_COOKIE['passed'];
	if($passed != 'TRUE' ){
		$url = "adminlogin.html";
		echo "<script type='text/javascript'>";
		echo "window.location.href='$url'";
		echo "</script>"; 
	}
	else{
		$id=$_COOKIE{"id"};
		$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
		mysql_query("SET NAMES 'UTF8'");
		if(! mysql_select_db("a5")) die("無法讀取資料庫");
		$data=mysql_query ("SELECT * from administer WHERE admin_id=$id;") or die("ERROR");		
		$row=mysql_fetch_assoc($data) or die("ERROR");
	}
?>
<html>
	<head>
		<title>修改管理員資料</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script src="admin.js" language="JavaScript"></script>
		<style type="text/css">
		<!--
		body{
			background-image: url(suse_zhiwen.jpg);
		}
		-->
		</style>
		<script language = "Javascript">
			function check_data(){
				if(document.myForm.password.value.length == 0){
					alert("密碼修改欄位不可空白");
					return false;
				}
				if(document.myForm.re_password.value.length == 0){
					alert("密碼確認欄位不可空白");
					return false;
				}
				if(document.myForm.name.value.length == 0){
					alert("姓名欄位不可空白");
					return false;
				}
				if(document.myForm.password.value != document.myForm.re_password.value){
					alert("兩邊密碼需相同");
					return false;
				}
				else myForm.submit();
			}
		</script>
	</head>
	<body>
		<form name="myForm" method = "post" action = "admin_update.php">
		<table border="2" align="center" bordercolor="#6666FF">
			<tr bgcolor="#99FF99">
				<th align="right">密碼修改</th>
				<td><input type="password" name="password" size="15" value="<?php echo $row['admin_pwd'];?>">(請使用英文或數字，勿使用特殊字元)</td>
			</tr>
			<tr bgcolor="#99FF99">
				<th align="right">密碼確認</th>
				<td><input type="password" name="re_password" size="15" value="<?php echo $row['admin_pwd'];?>">(需與上面符合)</td>
			<tr bgcolor="#99FF99">
				<th align="right">姓名</th>
				<td><input type="text" name="name" size="8" value="<?php echo $row['admin_name'];?>"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="修改資料" onClick="check_data()">
					<input type="reset" value="重新輸入">
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>
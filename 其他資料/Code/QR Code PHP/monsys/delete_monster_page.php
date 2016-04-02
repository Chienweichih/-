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
?>
<html>
<head>
	<title>刪除怪物</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script src="admin.js" language="JavaScript"></script>
			<style type="text/css">
		<!--
		body{
			background-image: url(suse_zhiwen.jpg);
		}
		-->
		</style>
</head>
<body>


<table  border="1″ width ="500">
	<tbody>
			<td>ID</td>
			<td>Image</td>
			<td>Name</td>
			<td>Level</td>
			<td>Hp</td>
			<td>Atk</td>
			<td>Def</td>
			<td>Exp</td>
			<td>Delete</td>
	</tbody>
	
<?php 
mysql_query("SET NAMES ‘UTF8′");



			$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
			mysql_query("SET NAMES 'UTF8'");
			if(! mysql_select_db("a5")) die("無法讀取資料庫");
$data=mysql_query ("select * from monster_data ");
while($row=mysql_fetch_row($data)){
	echo("

	<tr>
		<td>$row[0]</td>
		<td><img src='$row[1]' onload='javascript:if(this.width>200)this.width=200'></td>
		<td>$row[2]</td>
		<td>$row[3]</td>
		<td>$row[4]</td>
		<td>$row[5]</td>
		<td>$row[6]</td>
		<td>$row[7]</td>	
		<td><input type='button' value='刪除' onClick='javascript:self.location.href=\"delete_monster.php?id=$row[0]\"'></td>
	</tr>");
	}
?>

</table>

<a href="adminmain.php">返回管理頁</a>

</body>
</html>
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
		<title>管理員功能</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
		<style type="text/css">
		<!--
		body{
			background-image: url(suse_zhiwen.jpg);
		}
		-->
		</style>
	</head>
	<body>
		<table border="1" align="center">
		<tr>		
		<td align="center">
			<a href="mon_mod.php">修改怪物</a></td><td align="center">
			<a href="delete_monster_page.php">刪除怪物</a>
		</td></tr><tr>
		<td align="center">
			<a href="comment_del.php">刪除留言</a></td><td align="center">
			<a href="delete_treasure_page.php">刪除寶物</a>
		</td></tr>
		<!--
		<tr>
		<td align="center">
			<a href="drop_mod.php">修改掉寶資訊(未實裝)</a></td><td align="center">
			<a href="drop_del.php">刪除掉寶資訊(未實裝)</a>
		</td></tr>
		--!>
		
		<tr>
		<td align="center">
			<a href="pwd_mod.php">修改管理員資料</a></td><td align="center">
			<a href="logout.php">登出</a>
		</td></tr>
		</table>
	</body>
</html>
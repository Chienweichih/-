<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>新增掉寶資訊</title>
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
	background-image: url(http://pic.pimg.tw/hitlerx/1184064533_n.jpg);
}
-->
</style>
</head>
<body text="#ffffff" bgcolor="#899896">
	<form>
		<table align="center">
			 <tr>
				<td>怪物名稱</td>
				 <td><select name="monster_id">
				 <?
					$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
					mysql_query("SET NAMES 'UTF8'");
					if(! mysql_select_db("a5")) die("無法讀取資料庫");
					$mon_name=mysql_query ("SELECT monster_id,monster_name FROM monster_data;");
					while($row = mysql_fetch_array($mon_name, MYSQL_ASSOC)){
						echo"<option value ='{$row["monster_id"]}'>{$row["monster_name"]}</option>";
					}
				?>
				</select></td>
			 </tr>
			 <tr>
				<td>寶物名稱</td>
				<td><input name="treasure_name" type="text" id="treasure_id" size="15"/></td>
			 </tr>
			 <tr>
			 <td colspan="2">
				<input type="submit" name="goInsert" value="提交"/>
				<input name="GoBack" type="button" onClick="location.href='insert.php'" value="回上一頁" />
			 </td></tr>
		 </table>
	</form>
</body>
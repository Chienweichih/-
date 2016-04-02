<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
<!--
body{
	background-image: url(image/11_dezember_2011_emania_anime_christmas_big.jpg);
}
-->
</style>
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
  <form id="1" name="1" method="POST" action="SearchItem2.php">
<table align="center">
	<tr>
		<td>寶物名稱</td>
				<td><select name="treasure_id">
				 <?
					$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
					mysql_query("SET NAMES 'UTF8'");
					if(! mysql_select_db("a5")) die("無法讀取資料庫");
					$mon_name=mysql_query ("SELECT treasure_id,treasure_name FROM treasure_data;");
					while($row = mysql_fetch_array($mon_name, MYSQL_ASSOC)){
						echo"<option value ='{$row["treasure_id"]}'>{$row["treasure_name"]}</option>";
					}
				?>
				</select></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" name="button" id="button" value="提交" />
			<input name="GoBack" type="button" onClick="javascript:history.back(1)" value="回上一頁" />
		</td>
	</tr>
</table>
</form>

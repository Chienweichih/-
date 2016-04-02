<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<?
	$trea_id = $_POST["treasure_id"];
		$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
		mysql_query("SET NAMES 'UTF8'");
		if(! mysql_select_db("a5")) die("無法讀取資料庫");

$sqlname = "SELECT monster_name,image,level,hp,atk,def,exp FROM treasure_data natural join treasure_drop natural join monster_data where  treasure_id= '$trea_id'";
$result = mysql_query($sqlname);
$i=0;

echo"<table  border='1' width ='500'>
	<tbody>
			<th>模樣</th>
			<th>名稱</th>
			<th>等級</th>
			<th>血量</th>
			<th>攻擊力</th>
			<th>防禦力</th>
			<th>經驗值</th>
	</tbody>";
while ($record = mysql_fetch_array($result)) {
		 
$i++;
         echo("
	<tr>
		<td><img src='$record[1]' onload='javascript:if(this.width>200)this.width=200'></td>
		<td align='center'>$record[0]</td>
		<td align='center'>$record[2]</td>
		<td align='center'>$record[3]</td>
		<td align='center'>$record[4]</td>
		<td align='center'>$record[5]</td>
		<td align='center'>$record[6]</td>	
	</tr>");
}
echo"</table>";
echo"<a href='SearchItem.php'>返回查詢頁</a>";
$back_page = "SearchItem.php";
if(!$i){
	echo "<script> alert('沒有會掉落該物品的怪物'); location.href='$back_page'; </script>";
}
?>
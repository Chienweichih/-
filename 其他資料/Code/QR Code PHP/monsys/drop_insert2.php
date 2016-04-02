<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
$mon_id = $_POST["monster_id"];
$trea_name = $_POST["treasure_name"];


					$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
					mysql_query("SET NAMES 'UTF8'");
					if(! mysql_select_db("a5")) die("無法讀取資料庫");
					$trea_sql=mysql_query ("SELECT treasure_id FROM treasure_data WHERE treasure_name = '$trea_name';");
					$trea_id=mysql_fetch_array($trea_sql, MYSQL_ASSOC);
					if(!$trea_id){
						echo("尚未有 $trea_name 的資料，將新增至資料庫");
						$tsql = mysql_query ("INSERT INTO treasure_data (treasure_name) VALUES ('$trea_name');");
						$trea_sql=mysql_query ("SELECT treasure_id FROM treasure_data WHERE treasure_name = '$trea_name';");
						$trea_id=mysql_fetch_array($trea_sql, MYSQL_ASSOC);
					}
					
$sql = "INSERT INTO treasure_drop (monster_id,treasure_id) VALUES ('$mon_id','{$trea_id['treasure_id']}');";

$back_page = "insert.php";
if(mysql_query($sql))
{ 
	echo "<script> alert('新增成功!'); location.href='$back_page'; </script>";
}
else
{   
	echo "<script> alert('新增失敗或有相同掉落資訊'); location.href='$back_page'; </script>";
}

?>
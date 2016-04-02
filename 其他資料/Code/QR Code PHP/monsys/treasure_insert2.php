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

$trea_name = $_POST["treasure_name"];
					$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
					mysql_query("SET NAMES 'UTF8'");
					if(! mysql_select_db("a5")) die("無法讀取資料庫");
					if(mysql_num_rows(mysql_query ("SELECT treasure_id FROM treasure_data WHERE treasure_name = '$trea_name';"))){
						echo"$trea_sql";
						echo "<script> alert('已有相同名稱寶物'); location.href='treasure_insert.php'; </script>";
					}
					else{
$sql =
"INSERT INTO treasure_data (treasure_name)
VALUES ('$trea_name');
";
$back_page = "insert.php";

if(mysql_query($sql))
{ 
	echo "<script> alert('新增成功!'); location.href='$back_page'; </script>";
}
else
{   
	echo "<script> alert('新增失敗'); location.href='$back_page'; </script>";
}
}
?>
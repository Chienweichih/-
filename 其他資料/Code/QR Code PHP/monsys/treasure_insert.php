<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>新增寶物</title>
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
		<script language = "Javascript">
			function check_data(){
				if(document.myForm.treasure_name.value.length == 0){
					alert("寶物名稱欄位不可空白");
					return false;
				}
				myForm.submit();
			}
		</script>
</head>
<body bgcolor="FFE4E1" background="http://img.miigii.com.tw/Files/Gonglue/20120206/45b253275eb84eb6b5fc9f75cd59c92e.jpg"
	style="background-repeat:no-repeat;background-attachment:fixed;background-position:center center;background-size:65%">
	<form name="myForm" method="POST" action="treasure_insert2">
		<table border=1 align="center">
			<tr>
				<td bgcolor="EEB4B4"align="center">寶物名稱:</td>
					<td bgcolor="FFFACD"><input name="treasure_name" type="text" id="treasure_name" size="10" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" name="insert" value="提交" onClick="check_data()" />
					<input name="GoBack" type="button" onClick="location.href='insert.php'" value="回上一頁" />
				</td>
			</tr>
		</table> 
	</form>
<?
$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
mysql_query("SET NAMES 'UTF8'");
if(! mysql_select_db("a5")) die("無法讀取資料庫");
$data=mysql_query ("select * from treasure_data ");
echo"<table  border='1' width ='200' bgcolor='#D9D9FF'>
	<tbody>
			<th>已知寶物</th>
	</tbody>";
while($row=mysql_fetch_row($data)){
	echo("
	<tr>
		<td>$row[1]</td>	
	</tr>");
	}
	echo"</table>";
?>
</body>
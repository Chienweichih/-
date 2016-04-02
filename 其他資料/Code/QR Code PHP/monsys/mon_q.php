<html>
	<head>
		<title>怪物查詢</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	background-image: url(suse_zhiwen-033.jpg);
}
.alpha{FILTER: Alpha(opacity=60);/*表格透明*/}
-->
</style>
<script src="titledesc.js" type="text/javascript"></script>
	</head>
	<body>
	<fieldset>
		<legend><b>條件篩選</b></legend>
	<form method = "GET" action = "mon_q.php">
		<p>
		名稱：<input name="q_name" type="text" size="20">
		</p><p>經驗值：<input name="q_min_exp" type="text" size="5">～<input name="q_max_exp" type="text" size="5"><br/>
		</p><p>
		<input type="submit" value="搜尋"/></p>
	</form>
	</fieldset>
		<?php
		
			$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
			mysql_query("SET NAMES 'UTF8'");
			if(! mysql_select_db("a5")) die("無法讀取資料庫");
			
			if(isset($_GET['q_name']) && strlen($_GET['q_name']) > 0) $nameSQL = "monster_name LIKE '%{$_GET['q_name']}%' and";
			else $nameSQL = "";
			
			if(isset($_GET['q_min_exp']) && $_GET['q_min_exp'] > 0) $min_exp = $_GET['q_min_exp'];
			else $min_exp = 0;
			
			if(isset($_GET['q_max_exp']) && $_GET['q_max_exp'] > 0) $max_exp = $_GET['q_max_exp'];
			else $max_exp = 9999999999999;
			
			if($_GET['q_max_exp'] < $_GET['q_min_exp']) 
			{
				echo "<script type = 'text/javascript'>alert('經驗輸入順序是不是錯了呢?') </script>";
				echo "<p>輸入資料錯誤無法查詢!!</p>";
			}
			else
			{	
				switch ($_GET['order']){
					case '名稱':
						$sql = "SELECT * FROM monster_data WHERE $nameSQL exp > $min_exp and exp < $max_exp ORDER BY monster_name ASC;";
						break;
					case '等級':
						$sql = "SELECT * FROM monster_data WHERE $nameSQL exp > $min_exp and exp < $max_exp ORDER BY level ASC;";
						break;
					case '血量':
						$sql = "SELECT * FROM monster_data WHERE $nameSQL exp > $min_exp and exp < $max_exp ORDER BY hp ASC;";
						break;
					case '攻擊力':
						$sql = "SELECT * FROM monster_data WHERE $nameSQL exp > $min_exp and exp < $max_exp ORDER BY atk ASC;";
						break;
					case '防禦力':
						$sql = "SELECT * FROM monster_data WHERE $nameSQL exp > $min_exp and exp < $max_exp ORDER BY def ASC;";
						break;
					case '經驗值':
						$sql = "SELECT * FROM monster_data WHERE $nameSQL exp > $min_exp and exp < $max_exp ORDER BY exp ASC;";
						break;
					default :
						$sql = "SELECT * FROM monster_data WHERE $nameSQL exp > $min_exp and exp < $max_exp ORDER BY monster_id ASC;;";						
				}
				$monster_data=mysql_query($sql);
				$min_exp = $_GET['q_min_exp'];
				$max_exp = $_GET['q_max_exp'];
				$nameSQL = $_GET['q_name'];
				echo"<table border='1'  style='background-color:#78ACFF;filter:Alpha(opacity=40)'>
					<tr>
					<form method='get' action='mon_q.php?q_max_exp=$max_exp&q_min_exp=$min_exp&q_name=$nameSQL'>
					<th>點擊右邊由小至大排序</th>
					<th>
						<input type = 'hidden' name = 'q_min_exp' value = '$min_exp' \>
						<input type = 'hidden' name = 'q_max_exp' value = '$max_exp' \>
						<input type = 'hidden' name = 'q_name' value = '$nameSQL' \>
						<input type = 'submit' name = 'order' value = '名稱' \>
					</th>
					<th><input type = 'submit' name = 'order' value = '等級' \></th>
					<th><input type = 'submit' name = 'order' value = '血量' \></th>
					<th><input type = 'submit' name = 'order' value = '攻擊力' \></th>
					<th><input type = 'submit' name = 'order' value = '防禦力' \></th>
					<th><input type = 'submit' name = 'order' value = '經驗值' \></th>
					</tr>";
				
				while($a=mysql_fetch_row($monster_data))
				{
					echo"<tr>
							<td>
								<img title='<img src=a[1]>' src=$a[1] onload='javascript:if(this.width>200)this.width=200' onMouseOver>
							</td>
							<td align='center'>
								<a href='mon_d.php?mon_id=$a[0]'>$a[2]</a>
							</td>
							<td width='100' align='center'>$a[3]</td>
							<td width='20' align='center'>$a[4]</td>
							<td width='20' align='center'>$a[5]</td>
							<td width='20' align='center'>$a[6]</td>
							<td width='20' align='center'>$a[7]</td>
						</tr>";
				}
				echo"</table>";
			}

		?>
	</body>
</html>
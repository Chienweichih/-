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
<?
					$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
					mysql_query("SET NAMES 'UTF8'");
					if(! mysql_select_db("a5")) die("無法讀取資料庫");
					$mon_name=mysql_query ("SELECT monster_id,monster_name FROM monster_data;");
?>
	<form name="myForm" method="GET" action="mon_mod.php">
		<table align="center">
			 <tr>
				<td>怪物名稱</td>
				 <td><select name="mon_id">
				 <?

					while($row = mysql_fetch_array($mon_name, MYSQL_ASSOC)){
						echo"<option value ='{$row["monster_id"]}'>{$row["monster_name"]}</option>";
					}
				?>
				</select></td>
			 </tr>
			 <td colspan="2">
				<input type="submit" value="提交"/>
				<input name="GoBack" type="button" onClick="location.href='adminmain.php'" value="回上一頁" />
			 </td></tr>
		 </table>
	</form>
	<?if($mon_id>0){
		$mon_det=mysql_fetch_row(mysql_query ("SELECT * FROM monster_data WHERE monster_id = {$_GET['mon_id']}"));
		echo"<form name='myForm2' method='POST' action='mon_mod2.php'>
		<table cellpadding='0' cellspacing='0' align='center' border='1'>
							<tr>
								<td colspan='2' rowspan='4' width='200' align='center'>
									<img src=$mon_det[1] onload='javascript:if(this.width>200)this.width=200'><br/>
									<input type='text' name='id' value='$mon_det[0]' size='2'>
								</td>							
								<th>名稱</th>
								<th>等級</th>
								<th>血量</th>								
							</tr>
							<tr>
								<td align='center' width=15%><input type='text' name='name' size='15' value='$mon_det[2]'></td>
								<td align='center' width=15%><input type='text' name='level' size='15' value='$mon_det[3]'></td>
								<td align='center' width=15%><input type='text' name='hp' size='15' value='$mon_det[4]'></td>					
								</td>
							</tr>
							<tr>						
								<th>攻擊力</th>							
								<th>防禦力</th>
								<th>經驗值</th>
							</tr>
							<tr>
								<td align='center'><input type='text' name='atk' size='15' value='$mon_det[5]'></td>
								<td align='center'><input type='text' name='def' size='15' value='$mon_det[6]'></td>
								<td align='center'><input type='text' name='exp' size='15' value='$mon_det[7]'></td>
							</tr>
							<tr>
							<td colspan='5' align='center'>
				<input type='submit' value='提交'/>
				<input name='GoBack' type='button' onClick='location.href='adminmain.php'' value='回上一頁' />
				</td>
							</tr>
					</table>";
	
	
	
	
	}	
	?>
</body>
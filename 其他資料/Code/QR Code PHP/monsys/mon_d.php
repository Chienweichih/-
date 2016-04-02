<html>
		<script language = "Javascript">
			function check_data(){
				if(document.myForm.author.value.length() == 0){
					alert("作者欄位不可空白");
					return false;
				}
				if(document.myForm.content.value.length() == 0){
					alert("內容欄位不可空白");
					return false;
				}
				myForm.submit();
			}
		</script>
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
td{bgcolor:#78ACFF;FILTER: Alpha(opacity=60);/*表格透明*/}
-->
</style>
		<?php
			$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
			mysql_query("SET NAMES 'UTF8'");
			if(! mysql_select_db("a5")) die("無法讀取資料庫");
			
			$mon_det=mysql_fetch_row(mysql_query ("SELECT * FROM monster_data WHERE monster_id = {$_GET['mon_id']}"));

			echo"
	<head>
		<title>$mon_det[2] - 怪物詳細資料</title>
		<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>		
	</head>
	<body>
			";
					echo"<table cellpadding='0' cellspacing='0' align='center' border='1'>
							<tr>
								<td colspan='2' rowspan='4' width='200' align='center'>
									<img src=$mon_det[1] onload='javascript:if(this.width>200)this.width=200'>
								</td>							
								<th>名稱</th>
								<th>等級</th>
								<th>血量</th>								
							</tr>
							<tr>
								<td align='center' width=15%>$mon_det[2]</td>
								<td align='center' width=15%>$mon_det[3]</td>
								<td align='center' width=15%>$mon_det[4]</td>					
								</td>
							</tr>
							<tr>						
								<th>攻擊力</th>							
								<th>防禦力</th>
								<th>經驗值</th>
							</tr>
							<tr>
								<td align='center'>$mon_det[5]</td>
								<td align='center'>$mon_det[6]</td>
								<td align='center'>$mon_det[7]</td>
							</tr>
							<tr>
								<th colspan='2'>掉落寶物</th>
								<td align='center' colspan='3'>";
									$treasure_data=mysql_query ("SELECT treasure_data.treasure_name FROM treasure_data, treasure_drop, monster_data WHERE monster_data.monster_id = treasure_drop.monster_id AND treasure_drop.treasure_id = treasure_data.treasure_id AND monster_data.monster_id = $mon_det[0];");
									while($b=mysql_fetch_row($treasure_data))
											echo "$b[0]<br/>";			
							echo"</tr>
					</table>";
					
					
			
			$bg[0] = "#D9D9FF";
			$bg[1] = "#FFCAEE";
			$bg[2] = "#FFFFCC";
			$bg[3] = "#B9EEB9";
			$bg[4] = "#B9E9FF";
			
			$records_per_page = 5;
			if(isset($_GET['page']))
				$page = $_GET['page'];
			else $page = 1;
			$comt=mysql_query ("SELECT * FROM comment WHERE monster_id = {$_GET['mon_id']} ORDER BY comment_date DESC");
			$total_records = mysql_num_rows($comt);
			$total_pages = ceil($total_records / $records_per_page);
			$started_record = $records_per_page * ($page-1);			
			
			echo"<table width='800' align='center' cellspacing='3'>";
			$j = 0;
			if($total_records == 0){
				echo"<tr bgcolor='$bg[$j]' align=center>
						<td>還沒有人來留過言，快點搶頭香
						</td>
					</tr>";
			}
			else mysql_data_seek($comt,$started_record);
			//顯示記錄;
			
			while($row = mysql_fetch_assoc($comt) and $j < $records_per_page){
				echo"<tr bgcolor='$bg[$j]'>
						<td><b>{$row['comment_name']}</b> 在 {$row['comment_date']} 說道：<hr>
							{$row['message']}
						</td>
					</tr>";
				$j++;
			}
			echo"</table>";
			
			echo"<p align='center'>";			
			if($page > 1)
				echo "<a href='mon_d.php?mon_id={$_GET['mon_id']}&page=".($page-1)."'>上一頁  </a>";
			for($i=1;$i<=$total_pages;$i++){
				if($i == $page)
					echo"  $i  ";
				else
					echo"<a href='mon_d.php?mon_id={$_GET['mon_id']}&page=$i'>  $i  </a> ";
			}
			if($page < $total_pages)
				echo "<a href='mon_d.php?mon_id={$_GET['mon_id']}&page=".($page+1)."'>  下一頁</a>";
			echo"<br/><a href='mon_q.php'>返回查詢頁</a>";
			echo"</p>";
		?>
		<form name="myForm" method="post" action="post.php?mon_id=<?php echo $_GET['mon_id'];?>">
			<table border="0" width="800" align="center" cellspacing="0">
				<tr height="40" bgcolor="#0084CA" align="center" valign="middle">
					<td colspan="2">
					<font color="#FFFFF">請在此輸入新的留言</font></td>
				</tr>
				<tr height="40" bgcolor="#D9F2FF" align="center" valign="middle">
					<td width="15%">大名</td>
					<td width="85%"><input name="author" type="text" size="60"></td>
				</tr>
				<tr height="40" bgcolor="#84D7FF" align="center" valign="middle">
					<td width="15%">內容</td>
					<td width="85%"><textarea name="content" cols="50" rows="10"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" height="40" align="center">
						<input type="submit" value="發表">
						<input type="reset" value="重新輸入">						
					</td>
				</tr>				
			</table>
		</form>
	</body>
</html>
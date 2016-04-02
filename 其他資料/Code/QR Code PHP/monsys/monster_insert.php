<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> 新增怪物資料</title>
		<script type="text/javascript">
			function is_int(value){
				for (i = 0 ; i < value.length ; i++) {
					if ((value.charAt(i) < '0') || (value.charAt(i) > '9')) return false ;
				}
			return true;
			}
		</script>
		<script language = "Javascript">
			function check_data(){
				if(document.myForm.monster_name.value.length == 0||document.myForm.myfile.value.length == 0||document.myForm.level.value.length == 0||document.myForm.hp.value.length == 0||document.myForm.atk.value.length == 0||document.myForm.def.value.length == 0||document.myForm.exp.value.length == 0){
					alert("不可有空白欄位");
					return false;}
				if(document.myForm.monster_name.value > 1000000000||document.myForm.myfile.value > 1000000000||document.myForm.level.value > 1000000000||document.myForm.hp.value > 1000000000||document.myForm.atk.value > 1000000000||document.myForm.def.value > 1000000000||document.myForm.exp.value > 1000000000){
					alert("有欄位數值過大");
					return false;}
				if(!is_int(document.myForm.level.value)||!is_int(document.myForm.hp.value)||!is_int(document.myForm.atk.value)||!is_int(document.myForm.def.value)||!is_int(document.myForm.exp.value)){
					alert("有欄位數值非數字");
					return false;}	
				else myForm.submit();
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
</head>
<body bgcolor="E9EFEF"background="http://ed.arte.gov.tw/uploadfile/result/3930_%E6%9C%80%E6%A3%92%E7%9A%84%E6%80%AA%E7%8D%B8.jpg"style="background-repeat:no-repeat;background-attachment:fixed;background-position:right bottom;background-size:77%">
<form id="1"  name="myForm" method="POST" action="monster_insert2.php" enctype="multipart/form-data">
<table border=0 align="center">
<tr><th bgcolor="FFB5C5"align="center">怪物名稱</th>
	<td bgcolor="FFE4E1"><input name="monster_name" id="monster_name" type="text" size="10"/></td>
</tr>
<tr><th bgcolor="D8BFD8"align="center">怪物圖片</th>
	<td bgcolor="FFE1FF">
	<input type="hidden" name="MAX_FILE_SIZE" value="1048576">
	<input type="file" name="myfile" size="50"><br/>
	<b>(檔案格式：jpg,jpeg,gif,png)<br/>
	(檔案尺寸：最大1MB)<br/>
	(檔案路徑：請勿有中文或特殊符號)</b>
	</td>
</tr>
<tr><th bgcolor="C6E2FF"align="center">怪物等級</th>
	<td bgcolor="BFEFFF"><input name="level" id="level" type="text" size="10"/></td>
</tr>
<tr><th bgcolor="C1FFC1"align="center">怪物血量</th>
	<td bgcolor="F0FFD"><input name="hp" id="hp" type="text" size="10"/></td>
</tr>
<tr><th bgcolor="CDAF95"align="center">怪物攻擊力</th>
	<td bgcolor="EEE9BF"><input name="atk" id="atk" type="text" size="10"/></td>
</tr>
<tr><th bgcolor="CCCCCC"align="center">怪物防禦力</th>
	<td bgcolor="eeeeee"><input name="def" id="def" type="text" size="10"/></td>
</tr>
<tr><th bgcolor="FFFACD"align="center">怪物經驗值</th>
	<td bgcolor="FAFAD2"><input name="exp" id="exp" type="text" size="10"/></td>
</tr>
<tr><td colspan="2"align="center">
<input type="button" name="goInsert" value="提交" onClick="check_data()"/>
<input name="GoBack" type="button" onClick="location.href='right.html'" value="回上一頁" />
</td></tr>
</table>
</form>
</body>
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
<?php
	
$id=	        $_POST["id"];
$monster_name = $_POST["name"];
$level=		$_POST["level"];
$hp=		$_POST["hp"];
$atk=		$_POST["atk"];
$def=		$_POST["def"];
$exp=		$_POST["exp"];

		$con = mysql_connect("localhost","A5","5149") or die("無法與資料庫伺服器建立連線");
		mysql_query("SET NAMES 'UTF8'");
		if(! mysql_select_db("a5")) die("無法讀取資料庫");
		
$sql =
"UPDATE  monster_data SET monster_name='$monster_name',level='$level',hp='$hp',atk='$atk',def='$def',exp='$exp' WHERE monster_id=$id;
";
$back_page = "adminmain.php";

if(mysql_query($sql))
{ 
	echo "<script> alert('修改成功!'); location.href='$back_page'; </script>";
}
else
{   
	echo "<script> alert('修改失敗'); location.href='$back_page'; </script>";
}

?>